package com.example.trawell;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class KullaniciKayit extends AppCompatActivity {

    ImageView ImgUserPhoto;
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    Uri pickedImgUri;

    private  EditText userMail, userPassword, userPassword2, userName;
    private ProgressBar progressBar;
    private Button regBtn;


    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_kayit);

        userName = findViewById(R.id.txtKayitAd);
        userMail = findViewById(R.id.txtKayitMail);
        userPassword = findViewById(R.id.txtKayitSifre);
        userPassword2 = findViewById(R.id.txtKayitSifre2);
        progressBar = findViewById(R.id.regProgressBar);
        regBtn = findViewById(R.id.btnKayitYap);
        progressBar.setVisibility(View.INVISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                regBtn.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                regBtn.setVisibility(View.VISIBLE);

                final String email = userMail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Mail adresinizi giriniz.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Şifrenizi giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    Toast.makeText(getApplicationContext(), "Şifrenizi tekrar giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Şifreniz çok kısa en az 6 karakter olmalıdır.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    CreateUserAccount(email,name,password);

                }
            }
        });


        ImgUserPhoto = findViewById(R.id.ImgUserPhoto) ;

        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                }
                else
                {
                    openGallery();
                }





            }
        });



    }


    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(KullaniciKayit.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(KullaniciKayit.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(KullaniciKayit.this,"Lütfen erişim için izinleri kabul ediniz.",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(KullaniciKayit.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();

    }

    private void CreateUserAccount(String email, final String name, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //Kullanıcı oluşturuldu
                            showMessage("Kullanıcı Oluşturuldu");
                            updateUserInfo(name, pickedImgUri,firebaseAuth.getCurrentUser());


                        }
                        else
                        {
                            showMessage("Kullanıcı oluşturmada hata oldu"+ task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });





    }


    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser){

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("user_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(KullaniciKayit.this, "Kayıt başarısız. Bilgilerinizi kontrol edip tekrar deneyiniz." + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                            updateUI();
                                        } else {
                                            Toast.makeText(KullaniciKayit.this, "Kayıt başarılı.", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(KullaniciKayit.this, MainActivity.class));


                                        }

                                    }
                                });
                    }
                });
            }
        });

    }

    private void updateUI() {

        Intent home_Activity = new Intent(getApplicationContext(), Anasayfa.class);
        startActivity(home_Activity);
        finish();

    }


    //ekrana gösterilecek yazının metodu
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }


    public void LinkHesap(View view) {
        Intent LinkHesap = new Intent(getApplicationContext(), KullaniciGiris.class );
        startActivity(LinkHesap);
    }


    private void openGallery() {


        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            ImgUserPhoto.setImageURI(pickedImgUri);


        }


    }
}
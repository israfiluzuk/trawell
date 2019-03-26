package com.example.trawell;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
                final String email = userMail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();

                if (email.isEmpty() || name.isEmpty()|| password.isEmpty()|| password2.isEmpty()|| !password.equals(password2))

                {
                    //Ekrana yazılacak Uyarı
                   showMessage("Lütfen bilgilerinizi tekrar konrol ediniz.");
                   regBtn.setVisibility(View.VISIBLE);
                   progressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    CreateUserAccount(email,name,password);
                    
                }
            }
        });




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
                            finish();

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
                                        if (task.isSuccessful()){
                                            showMessage("Kayıt Tamamlandı");
                                            updateUI();
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

    public void LinkHesap(View view) {
        Intent LinkHesap = new Intent(getApplicationContext(), KullaniciGiris.class );
        startActivity(LinkHesap);
    }
}

package com.example.trawell.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trawell.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

public class KullaniciKayit extends AppCompatActivity {

    private EditText mNameField, mEmailField, mPasswordField, mPasswordField2;
    private Button mRegisterBtn;


    //Firebase
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //Progress
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_kayit);

        mNameField = (EditText) findViewById(R.id.txtKayitAd);
        mEmailField = (EditText) findViewById(R.id.txtKayitMail);
        mPasswordField = (EditText) findViewById(R.id.txtKayitSifre);
        mPasswordField2 = (EditText) findViewById(R.id.txtKayitSifre2);
        mRegisterBtn = (Button) findViewById(R.id.btnKayitYap);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        //Progress
        mProgress = new ProgressDialog(this);


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = mEmailField.getText().toString();
                final String password = mPasswordField.getText().toString();
                final String password2 = mPasswordField2.getText().toString();
                final String name = mNameField.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "İsminizi giriniz.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Mail adresinizi giriniz.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "Mail adresi geçersiz.", Toast.LENGTH_SHORT).show();
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
                } else {
                    startRegister();

                    //uploadImage();
                }
            }
        });

    }


    private void startRegister() {

        final String name = mNameField.getText().toString().trim();
        final String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();


        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mProgress.setMessage("Kayıt Yapılıyor...");
            mProgress.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mProgress.dismiss();

                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = mDatabase.child(user_id);
                        current_user_db.child("name").setValue(name);
                        current_user_db.child("image").setValue("default");
                        current_user_db.child("phone").setValue("default");
                        current_user_db.child("userFB").setValue("default");
                        current_user_db.child("userTW").setValue("default");
                        FirebaseUser user = mAuth.getCurrentUser();

                        String email = user.getEmail();
                        String uid = user.getUid();

                        HashMap<Object, String> hashMap = new HashMap<>();
                        hashMap.put("email",email);
                        hashMap.put("uid", uid);
                        hashMap.put("name",""); //profil edit kısmında yapılacak.
                        hashMap.put("phone","");//profil edit kısmında yapılacak.
                        hashMap.put("image","");//profil edit kısmında yapılacak.
                        hashMap.put("userFB","");//profil edit kısmında yapılacak.
                        hashMap.put("userTW","");//profil edit kısmında yapılacak.
                        //Firebase Databse
                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        mDatabase.child(uid).setValue(hashMap);


                        Intent i = new Intent(KullaniciKayit.this, Anasayfa.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();

                    }
                    else{
                        mProgress.dismiss();
                        Toast.makeText(KullaniciKayit.this, "Kayıt Başarısız!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mProgress.dismiss();
                    Toast.makeText(KullaniciKayit.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    public void LinkHesap(View view) {
        Intent LinkHesap = new Intent(getApplicationContext(), KullaniciGiris.class);
        startActivity(LinkHesap);
    }


}
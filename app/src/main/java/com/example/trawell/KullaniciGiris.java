package com.example.trawell;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class KullaniciGiris extends AppCompatActivity {

    private Button loginButton, btnReset;
    private FirebaseAuth firebaseAuth;
    private ProgressBar loginProgress;
    private EditText girisMail, girisSifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);


        /*girisMail = findViewById(R.id.login_mail);
        girisSifre = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.btnGirisYap);
        loginProgress = findViewById(R.id.login_progress);
        loginProgress.setVisibility(View.INVISIBLE);


        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(KullaniciGiris.this, MainActivity.class));
            finish();
        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.INVISIBLE);
            }
        });*/

        setContentView(R.layout.activity_giris);
        girisMail = (EditText) findViewById(R.id.login_mail);
        girisSifre = (EditText) findViewById(R.id.login_password);
        loginProgress = (ProgressBar) findViewById(R.id.login_progress);
        loginButton = (Button) findViewById(R.id.btnGirisYap);
        btnReset = (Button) findViewById(R.id.btnSifreUnuttum);

        firebaseAuth = FirebaseAuth.getInstance();
        loginProgress.setVisibility(View.INVISIBLE);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KullaniciGiris.this, SifremiUnuttum.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = girisMail.getText().toString().trim();
                final String password = girisSifre.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Mail adresinizi giriniz.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){

                    Toast.makeText(getApplicationContext(),"Şifrenizi giriniz.", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginProgress.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(KullaniciGiris.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loginProgress.setVisibility(View.GONE);
                                if (!task.isSuccessful()){
                                    if (password.length()<6){
                                        Toast.makeText(getApplicationContext(),"Minimum 6 karakter girmelisiniz.", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(KullaniciGiris.this, getString(R.string.firebase_database_url),Toast.LENGTH_SHORT).show();

                                    }
                                }
                                else{
                                    Intent intent = new Intent(KullaniciGiris.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

    }

}

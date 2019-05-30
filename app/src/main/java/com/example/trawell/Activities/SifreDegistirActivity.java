package com.example.trawell.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trawell.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SifreDegistirActivity extends AppCompatActivity {

    EditText editText;
    Button btnDegistir;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_degistir);
        editText = (EditText) findViewById(R.id.txt_change_pass);
        dialog = new ProgressDialog(this);
        btnDegistir = (Button) findViewById(R.id.btn_sifre_deg);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        editText.setText(user.getEmail());


    }

    public void change(View view){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null)
        {
            dialog.setMessage("Şifre değiştiriliyor, Lütfen bekleyin!");
            dialog.show();
            user.updatePassword(editText.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Şifreniz başarı ile değiştirildi", Toast.LENGTH_SHORT).show();
                                auth.signOut();
                                finish();
                                Intent i = new Intent(SifreDegistirActivity.this,KullaniciGiris.class);
                                startActivity(i);
                            }
                            else
                            {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Şifre değiştirilemedi",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void deactive(View v){

    }
}


/*
user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
@Override
public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()){
        Toast.makeText(getApplicationContext(),"Kullanıcı hesabı aktif değildir.", Toast.LENGTH_SHORT).show();
        }
        }
        });*/

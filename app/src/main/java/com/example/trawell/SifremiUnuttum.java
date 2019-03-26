package com.example.trawell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SifremiUnuttum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifremi_unuttum);
    }

    public void ClickHomefromSifre(View view) {
        Intent SifreIntent = new Intent(getApplicationContext(), KullaniciGiris.class );
        startActivity(SifreIntent);
    }
}

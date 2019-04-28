package com.example.trawell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GirisOnClick(View view) {
        Intent GirisIntent = new Intent(getApplicationContext(), KullaniciGiris.class );
        startActivity(GirisIntent);
    }

    public void KayitGo(View view){
        Intent kayitPage = new Intent(getApplicationContext(),KullaniciKayit.class);
        startActivity(kayitPage);

    }

    public void ClickHakkimizda(View view) {
        Intent KayitIntent = new Intent(getApplicationContext(), Hakkimizda.class );
        startActivity(KayitIntent);
    }

    public void btnSurumGo(View view) {
        Intent SurumGo = new Intent(getApplicationContext(), SurumNotlari.class );
        startActivity(SurumGo);
    }

    public void geciciGiris(View view) {
        Intent giris = new Intent(getApplicationContext(),Anasayfa.class);
        startActivity(giris);

    }
}

package com.example.trawell.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.trawell.R;

public class SurumNotlari extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surum_notlari);
    }

    public void btnSurumBack(View view) {
        Intent Surum = new Intent(getApplicationContext(), MainActivity.class );
        startActivity(Surum);
    }


}

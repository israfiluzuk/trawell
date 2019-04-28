package com.example.trawell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trawell.Haber.HaberDB;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HaberGiris extends AppCompatActivity {

    Button bt;
    TextView bText;
    TextView hText;
    TextView kText;
    TextView tText;
    TextView yText;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private void writeNews(String baslik, String haber, String kaynak, String tarih, String yazar){
        HaberDB haberDB = new HaberDB(baslik, haber, kaynak, tarih, yazar);

        myRef.child("haber").child(baslik).setValue(haberDB);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber_giris);

        bt= (Button) findViewById(R.id.dbButon);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                writeNews("Baslik", "Haber", "Trawell", "25.04.2019", "Dogukan Uraz");


            }
        });

    }


}

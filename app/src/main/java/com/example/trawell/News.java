package com.example.trawell;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trawell.Haber.HaberDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class News extends AppCompatActivity {

    TextView twBaslik;
    Button btnListle;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference oku = FirebaseDatabase.getInstance().getReference().child("news");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        twBaslik = (TextView) findViewById(R.id.twBaslik);
        btnListle = (Button) findViewById(R.id.btnListele);

        btnListle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ValueEventListener dinle = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HaberDB hdb = new HaberDB();
                        hdb = dataSnapshot.getValue(HaberDB.class);
                        twBaslik.setText(hdb.baslik);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                oku.addValueEventListener(dinle);
            }

        });

    }
}

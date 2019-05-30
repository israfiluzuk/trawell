package com.example.trawell.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trawell.Posts.PostAdapter;
import com.example.trawell.Posts.PostDB;
import com.example.trawell.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<PostDB> postlist;
    PostAdapter sAdapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = (RecyclerView) findViewById(R.id.searchRecyclerV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postlist=new ArrayList<PostDB>();

        editText=(EditText) findViewById(R.id.editTextSearch);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()){
                    arama(s.toString());
                }
                else {
                    arama("");
                }
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("heritage");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    PostDB postDB=dataSnapshot1.getValue(PostDB.class);
                    postlist.add(postDB);
                }
                sAdapter=new PostAdapter(Search.this,postlist);
                recyclerView.setAdapter(sAdapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Search.this,"Bir şeyler yanlış gitti...",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void arama(String s) {

        Query query = reference.orderByChild("heritage")
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                if(dS.hasChildren()){
                    postlist.clear();
                    for (DataSnapshot dataSnapshot2: dS.getChildren()){
                        final PostDB postDB = dataSnapshot2.getValue(PostDB.class);
                        postlist.add(postDB);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

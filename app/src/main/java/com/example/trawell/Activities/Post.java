package com.example.trawell.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.trawell.Posts.PostAdapter;
import com.example.trawell.Posts.PostDB;
import com.example.trawell.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Post extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<PostDB> postlist;
    PostAdapter pAdapter;
    DatabaseReference likereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = (RecyclerView) findViewById(R.id.postRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postlist= new ArrayList<PostDB>();

        reference= FirebaseDatabase.getInstance().getReference().child("heritage");
        likereference=FirebaseDatabase.getInstance().getReference().child("likes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    PostDB postDB = dataSnapshot1.getValue(PostDB.class);
                    postlist.add(postDB);
                }

                pAdapter=new PostAdapter(Post.this,postlist);
                recyclerView.setAdapter(pAdapter);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Post.this, "Bir şeyler yanlış gitti...", Toast.LENGTH_SHORT).show();
            }
        });
        likereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Post.this, "Bir şeyler yanlış gitti...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

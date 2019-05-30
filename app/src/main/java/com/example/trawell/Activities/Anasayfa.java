package com.example.trawell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trawell.Fragments.AnasayfaFragment;
import com.example.trawell.Fragments.AyarlarFragment;
import com.example.trawell.Fragments.ProfilFragment;
import com.example.trawell.Fragments.YardimFragment;
import com.example.trawell.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Anasayfa extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null){
                    Intent go = new Intent(Anasayfa.this,KullaniciKayit.class);
                    go.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(go);
                }
            }
        };
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        NavHeadGuncelle();
    }
    @Override
    protected void onStart(){
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    private void NavHeadGuncelle() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View baslikView = navigationView.getHeaderView(0);


        TextView navUserMail = baslikView.findViewById(R.id.nav_user_mail);
        TextView navUserName = baslikView.findViewById(R.id.nav_user_name);
        ImageView navUserPhoto = baslikView.findViewById(R.id.nav_user_photo);

        navUserName.setText(currentUser.getDisplayName());
        navUserMail.setText(currentUser.getEmail());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);

    }

    private static long back_pressed;
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (back_pressed + 1000 > System.currentTimeMillis()) {

            super.onBackPressed();
        }
        else Toast.makeText(getBaseContext(), "Çıkmak için tekrar GERİ tuşuna basınız.", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
        drawer.closeDrawer(GravityCompat.START);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.anasayfa, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_home){

            fragmentClass = AnasayfaFragment.class;

        }
        else if (id == R.id.nav_profile) {


            fragmentClass = ProfilFragment.class;

        }




        else if (id == R.id.nav_yardim) {
            fragmentClass = YardimFragment.class;


        } else if (id == R.id.nav_ayarlar) {

            fragmentClass = AyarlarFragment.class;


        }
        else if (id == R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            Intent GirisActivity = new Intent(this,KullaniciGiris.class);
            startActivity(GirisActivity);
            finish();
        }

        try{
            fragment =(Fragment) fragmentClass.newInstance();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        setTitle(item.getTitle());



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void cameraAc(View view) {

        Intent camera = new Intent(getApplicationContext(),Camera.class);
        startActivity(camera);

    }

    public void postAc(View view){
        Intent post=new Intent(getApplicationContext(),Post.class);
        startActivity(post);
    }

    public void newsAc(View view) {

        Intent news = new Intent(getApplicationContext(),News.class);
        startActivity(news);
    }

    public void searchAc(View view) {
         Intent search = new Intent (getApplicationContext(),Search.class);
         startActivity(search);
    }

    public void  mapAc(View view){
        Intent ynv = new Intent(getApplicationContext(),YakindaNelerVar.class);
        startActivity(ynv);
    }

}

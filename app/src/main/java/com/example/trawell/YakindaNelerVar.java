package com.example.trawell;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class YakindaNelerVar extends FragmentActivity implements OnMapReadyCallback {

    static final LatLng HARPUT = new LatLng(38.7040862,39.2571568);
    static final LatLng HAZARGOLU= new LatLng(38.466311, 39.319778);

    private GoogleMap mMap;

    private FusedLocationProviderClient mFusedLocationClient;
    //private TextView txtLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakinda_neler_var);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*// Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        if (mMap!=null){
            mMap.addMarker(new MarkerOptions().position(HARPUT).title("Harput Kalesi").snippet("Urartular tarafından dikdörtgen bir plan üzerine kurularak yapılmış olan mimari yapı."));
            mMap.addMarker(new MarkerOptions().position(HAZARGOLU).title("Hazar Gölü").snippet("Elâzığ yakınlarında, güneybatı-kuzeydoğu doğrultusunda uzanan tektonik bir göl."));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HARPUT,15));
    }
}

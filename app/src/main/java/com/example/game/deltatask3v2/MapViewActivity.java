package com.example.game.deltatask3v2;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapViewActivity extends AppCompatActivity implements OnMapReadyCallback {

        private GoogleMap mMap;
        private double latitude;
        private double longitude;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mapview_layout);
            Intent intent=getIntent();
            latitude= intent.getDoubleExtra("Latitude",0);
            longitude= intent.getDoubleExtra("Longitude",0);
            Log.i("Tag1",latitude+"");
            Log.i("Tag1",longitude+"");
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.g_map);
            mapFragment.getMapAsync(this);
        }
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            LatLng markerz = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(markerz).title("Crime Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(markerz));
        }
}

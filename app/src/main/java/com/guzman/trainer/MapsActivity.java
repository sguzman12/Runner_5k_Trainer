package com.guzman.trainer;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.guzman.model.GeoLocationObjectModel;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private ArrayList<GeoLocationObjectModel> list;
    private GoogleMap mMap;
    public static final ArrayList<GeoLocationObjectModel> EXTRA_Location = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        list = (ArrayList<GeoLocationObjectModel>) intent.getSerializableExtra("EXTRA_LOCATION");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<LatLng> routeList = new ArrayList<>();
        mMap = googleMap;



        for(GeoLocationObjectModel g: list){
            System.out.println("Lat: " + g.getLatitude() + " Long:" + g.getLongitude());
            routeList.add(new LatLng(g.getLatitude(), g.getLongitude()));
        }

        //Add polylines to the map
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(routeList);
        polylineOptions.color(Color.RED);



        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));
        mMap.addPolyline(polylineOptions);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}

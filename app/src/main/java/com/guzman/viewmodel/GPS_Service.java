package com.guzman.viewmodel;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Binder;
import android.location.LocationListener;
import android.location.Location;
import android.location.LocationManager;
import android.location.Criteria;
import android.content.pm.PackageManager;
import android.util.Log;
import android.webkit.GeolocationPermissions;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import com.guzman.model.GeoLocationObjectModel;

public class GPS_Service extends Service
{
    private GeoLocationObjectModel geo;
    private final IBinder binder = new OdometerBinder();
    private LocationListener listener;
    private LocationManager locManager;
    private static double distanceInMeters;
    private static Location lastLocation = null;
    public static final String PERMISSION_STRING
            = android.Manifest.permission.ACCESS_FINE_LOCATION;

    public class OdometerBinder extends Binder
    {
        GPS_Service getOdometer()
        {
            return GPS_Service.this;
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        listener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                if (lastLocation == null) {
                    lastLocation = location;
                }
                distanceInMeters += location.distanceTo(lastLocation);
                lastLocation = location;

                //Create Geolocation object
                geo = new GeoLocationObjectModel(lastLocation.getLatitude(), lastLocation.getLongitude());

            }

            @Override
            public void onProviderDisabled(String arg0)
            {
            }

            @Override
            public void onProviderEnabled(String arg0)
            {
            }

            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle bundle)
            {
            }
        };

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING)
                == PackageManager.PERMISSION_GRANTED) {
            String provider = locManager.getBestProvider(new Criteria(), true);
            if (provider != null) {
                locManager.requestLocationUpdates(provider, 3000, 5, listener);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return binder;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (locManager != null && listener != null) {
            if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING)
                    == PackageManager.PERMISSION_GRANTED) {
                locManager.removeUpdates(listener);
            }
            locManager = null;
            listener = null;
        }
    }

    public double getDistance()
    {
        return this.distanceInMeters / 1609.344;
    }

    public GeoLocationObjectModel getCoordinates()
    {
        return geo;
    }


}

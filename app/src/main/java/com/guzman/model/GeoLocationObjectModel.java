package com.guzman.model;

import java.io.Serializable;

/**
 * GeoLocation objects created when gathering user GPS location.
 */
public class GeoLocationObjectModel implements Serializable
{
    private double longitude;
    private double latitude;

    public GeoLocationObjectModel(double latitude, double longitude ){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

}

package com.guzman.model;

/**
 * GeoLocation objects created when gathering user GPS location.
 */
public class GeoLocationObjectModel
{
    private float longitude;
    private float latitude;

    public GeoLocationObjectModel(float longitude, float latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

    public float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

}

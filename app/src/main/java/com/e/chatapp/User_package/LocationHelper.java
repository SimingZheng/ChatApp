package com.e.chatapp.User_package;

import com.google.android.gms.maps.model.LatLng;

public class LocationHelper {
    private double Longitude;
    private double Latitude;

    public LocationHelper(){
    }

    public LocationHelper(double longitude, double latitude){
        Longitude = longitude;
        Latitude = latitude;
    }

    public double getLongitude(){
        return Longitude;
    }

    public void setLongitude(double longitude){
        Longitude = longitude;
    }

    public double getLatitude(){
        return Latitude;
    }

    public void setLatitude(double longitude){
        Longitude = Latitude;
    }
}

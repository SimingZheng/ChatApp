package com.e.chatapp.User_package;

import com.google.android.gms.maps.model.LatLng;

public class LocationHelper {
    private double longitude;
    private double latitude;

    public LocationHelper(){
    }

    public LocationHelper(double latitude, double longitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
}

package com.mobiquityinc.weather.domain.entities;

import com.google.android.gms.maps.model.LatLng;

public class FavouriteCity {

    private String cityName;
    private LatLng coord;

    public FavouriteCity(String cityName, LatLng coord) {
        this.cityName = cityName;
        this.coord = coord;
    }

    public FavouriteCity() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public LatLng getCoord() {
        return coord;
    }

    public void setCoord(LatLng coord) {
        this.coord = coord;
    }
}

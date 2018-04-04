package com.mobiquityinc.weather.domain.entities;

public class FavouriteCity {

    private int id;
    private String name;
    private LatLon coord;
    private String country;

    public FavouriteCity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLon getCoord() {
        return coord;
    }

    public void setCoord(LatLon coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}

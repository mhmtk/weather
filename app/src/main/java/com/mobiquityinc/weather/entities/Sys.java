package com.mobiquityinc.weather.entities;

class Sys {

    private int type;
    private int id;
    private double message;
    private String country;
    private int sunrise;
    private int sunse;

    public Sys() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunse() {
        return sunse;
    }

    public void setSunse(int sunse) {
        this.sunse = sunse;
    }
}

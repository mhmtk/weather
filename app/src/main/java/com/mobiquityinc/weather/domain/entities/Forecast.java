package com.mobiquityinc.weather.domain.entities;

import java.util.ArrayList;

public class Forecast {

    private String cod;
    private double message;
    private int cnt;
    private ArrayList<ForecastBlock> list;
    private City city;

    public Forecast() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public ArrayList<ForecastBlock> getList() {
        return list;
    }

    public void setList(ArrayList<ForecastBlock> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

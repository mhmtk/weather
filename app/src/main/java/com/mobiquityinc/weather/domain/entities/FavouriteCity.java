package com.mobiquityinc.weather.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class FavouriteCity implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeParcelable(this.coord, flags);
    }

    protected FavouriteCity(Parcel in) {
        this.cityName = in.readString();
        this.coord = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<FavouriteCity> CREATOR = new Creator<FavouriteCity>() {
        @Override
        public FavouriteCity createFromParcel(Parcel source) {
            return new FavouriteCity(source);
        }

        @Override
        public FavouriteCity[] newArray(int size) {
            return new FavouriteCity[size];
        }
    };
}

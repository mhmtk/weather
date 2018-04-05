package com.mobiquityinc.weather.ui.mapscreen;

import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.domain.FavouriteCityRepository;
import com.mobiquityinc.weather.domain.entities.FavouriteCity;

import java.io.IOException;

class MapScreenPresenter implements MapScreenContract.Presenter {

    private FavouriteCityRepository favouriteCityRepository;
    private Geocoder geocoder;
    private MapScreenContract.View view;

    public MapScreenPresenter(FavouriteCityRepository favouriteCityRepository, Geocoder geocoder, MapScreenContract.View view) {
        this.favouriteCityRepository = favouriteCityRepository;
        this.geocoder = geocoder;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void mapClicked(LatLng latLng) {
        view.replaceMarker(latLng);
        view.showSelectFab();
    }

    @Override
    public void locationSelected(LatLng position) {
        try {
            Address address = geocoder.getFromLocation(position.latitude, position.longitude,
                    1).get(0);
            String locality = address.getLocality();
            if (TextUtils.isEmpty(locality)) {
                view.displayLocationError();
            } else {
                FavouriteCity city = new FavouriteCity(locality, position);
                favouriteCityRepository.addFavourite(city);
                view.launchCityScreen(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
            view.displayLocationError();
        }
    }
}

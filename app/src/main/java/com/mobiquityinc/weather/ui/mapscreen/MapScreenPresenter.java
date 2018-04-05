package com.mobiquityinc.weather.ui.mapscreen;

import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.domain.FavouriteCityRepository;

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
        view.launchCityScreen(position);
    }
}

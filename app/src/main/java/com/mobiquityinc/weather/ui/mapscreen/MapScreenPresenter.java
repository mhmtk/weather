package com.mobiquityinc.weather.ui.mapscreen;

import com.google.android.gms.maps.model.LatLng;

public class MapScreenPresenter implements MapScreenContract.Presenter {

    private MapScreenContract.View view;

    public MapScreenPresenter(MapScreenContract.View view) {
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

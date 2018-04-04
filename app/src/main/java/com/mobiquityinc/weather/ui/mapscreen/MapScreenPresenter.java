package com.mobiquityinc.weather.ui.mapscreen;

/**
 * Created by mkologlu on 04/04/2018.
 */

class MapScreenPresenter implements MapScreenContract.Presenter {

    private MapScreenContract.View view;

    public MapScreenPresenter(MapScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.initiateUI();
    }
}

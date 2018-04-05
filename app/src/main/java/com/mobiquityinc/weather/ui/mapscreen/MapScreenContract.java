package com.mobiquityinc.weather.ui.mapscreen;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.ui.BasePresenter;
import com.mobiquityinc.weather.ui.BaseView;

public class MapScreenContract {

    interface View extends BaseView<Presenter> {

        void replaceMarker(LatLng latLng);

        void launchCityScreen(LatLng latLng);

        void displayLocationError();

        void showSelectFab();
    }

    interface Presenter extends BasePresenter {

        void mapClicked(LatLng latLng);

        void locationSelected(LatLng position);
    }
}

package com.mobiquityinc.weather.ui.main;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.ui.BasePresenter;
import com.mobiquityinc.weather.ui.BaseView;

public class MainContract {

    interface View extends BaseView<Presenter> {

        void displayHomeScreen();

        void launchMap();

        void launchCityScreen(LatLng latLng);
    }

    interface Presenter extends BasePresenter {

        void citySelected(City city);

        void addCityClicked();
    }
}

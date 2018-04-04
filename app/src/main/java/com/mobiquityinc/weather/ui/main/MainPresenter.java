package com.mobiquityinc.weather.ui.main;

import com.mobiquityinc.weather.domain.entities.City;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.displayHomeScreen();
    }

    @Override
    public void citySelected(City city) {

    }

    @Override
    public void addCityClicked() {
        view.launchMap();
    }
}

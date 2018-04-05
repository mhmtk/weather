package com.mobiquityinc.weather.ui.homescreen;

import com.mobiquityinc.weather.domain.FavouriteCityRepository;
import com.mobiquityinc.weather.domain.entities.FavouriteCity;

import java.io.IOException;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private FavouriteCityRepository favouriteCityRepository;
    private HomeScreenContract.View view;

    public HomeScreenPresenter(FavouriteCityRepository favouriteCityRepository, HomeScreenContract.View view) {
        this.favouriteCityRepository = favouriteCityRepository;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.initiateUI();
        populateFavouriteCitiesList();
    }

    private void populateFavouriteCitiesList() {
        try {
            view.setFavouriteCities(favouriteCityRepository.getFavourites());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCityButtonClicked() {
        view.startMapFragment();
    }

    @Override
    public void onCityClicked(FavouriteCity city) {
        view.launchCityScreen(city);
    }
}

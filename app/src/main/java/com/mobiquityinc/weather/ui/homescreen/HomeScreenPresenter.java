package com.mobiquityinc.weather.ui.homescreen;

import com.mobiquityinc.weather.domain.FavouriteCityRepository;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private FavouriteCityRepository mFavouriteCityRepository;
    private HomeScreenContract.View view;

    public HomeScreenPresenter(FavouriteCityRepository favouriteCityRepository, HomeScreenContract.View view) {
        this.mFavouriteCityRepository = favouriteCityRepository;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
    }

}

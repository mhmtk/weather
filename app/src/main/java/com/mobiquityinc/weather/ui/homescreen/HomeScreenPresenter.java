package com.mobiquityinc.weather.ui.homescreen;

import com.mobiquityinc.weather.domain.FavouritesRepository;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private FavouritesRepository favouritesRepository;
    private HomeScreenContract.View view;

    public HomeScreenPresenter(FavouritesRepository favouritesRepository, HomeScreenContract.View view) {
        this.favouritesRepository = favouritesRepository;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
    }

}

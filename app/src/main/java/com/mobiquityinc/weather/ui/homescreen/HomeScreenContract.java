package com.mobiquityinc.weather.ui.homescreen;


import com.mobiquityinc.weather.domain.entities.FavouriteCity;
import com.mobiquityinc.weather.ui.BasePresenter;
import com.mobiquityinc.weather.ui.BaseView;

import java.util.Set;

public interface HomeScreenContract {

    interface View extends BaseView<Presenter> {

        void setFavouriteCities(Set<FavouriteCity> favourites);

        void initiateUI();

        void startMapFragment();

        void launchCityScreen(FavouriteCity city);
    }

    interface Presenter extends BasePresenter {

        void addCityButtonClicked();

        void onCityClicked(FavouriteCity city);
    }
}

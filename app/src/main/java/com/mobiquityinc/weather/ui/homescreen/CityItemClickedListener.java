package com.mobiquityinc.weather.ui.homescreen;

import com.mobiquityinc.weather.domain.entities.FavouriteCity;

interface CityItemClickedListener {

    void onCityClicked(FavouriteCity city);
}

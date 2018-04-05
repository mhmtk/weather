package com.mobiquityinc.weather.ui.homescreen;

import com.mobiquityinc.weather.domain.entities.City;

interface CityItemClickedListener {

    void onCityClicked(City city);

    void onCityDeleteClicked(City city);
}

package com.mobiquityinc.weather.domain;

import com.mobiquityinc.weather.domain.entities.FavouriteCity;

import java.util.Set;

public interface FavouritesRepository {

    Set<FavouriteCity> getFavourites();
}

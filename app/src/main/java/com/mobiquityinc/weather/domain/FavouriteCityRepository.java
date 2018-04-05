package com.mobiquityinc.weather.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquityinc.weather.domain.entities.FavouriteCity;

import java.io.IOException;
import java.util.Set;

public interface FavouriteCityRepository {

    Set<FavouriteCity> getFavourites() throws IOException;

    void addFavourite(FavouriteCity city) throws JsonProcessingException;

    Set<FavouriteCity> removeFavourite(FavouriteCity city) throws IOException;
}

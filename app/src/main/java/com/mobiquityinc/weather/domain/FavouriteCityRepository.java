package com.mobiquityinc.weather.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquityinc.weather.domain.entities.City;

import java.io.IOException;
import java.util.Set;

public interface FavouriteCityRepository {

    Set<City> getFavourites() throws IOException;

    void addFavourite(City city) throws JsonProcessingException;

    Set<City> removeFavourite(City city) throws IOException;
}

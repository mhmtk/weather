package com.mobiquityinc.weather.domain;

import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquityinc.weather.domain.entities.FavouriteCity;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FavouriteCityRepositoryImpl implements FavouriteCityRepository {

    public static String FAVOURITE_CITIES_KEY = "favouriteCities";

    private final SharedPreferences sharedPreferences;
    private final ObjectMapper objectMapper;

    public FavouriteCityRepositoryImpl(final SharedPreferences sharedPreferences, final ObjectMapper objectMapper) {
        this.sharedPreferences = sharedPreferences;
        this.objectMapper = objectMapper;
    }

    @Override
    public void addFavourite(FavouriteCity city) throws JsonProcessingException {
        Set<String> favouriteCitiesStringSet = sharedPreferences.getStringSet(FAVOURITE_CITIES_KEY, new HashSet<String>());
        favouriteCitiesStringSet.add(objectMapper.writeValueAsString(city));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(FAVOURITE_CITIES_KEY, favouriteCitiesStringSet);
        editor.apply();
    }

    @Override
    public Set<FavouriteCity> removeFavourite(FavouriteCity city) throws IOException {
        Set<FavouriteCity> favouriteCitiesSet = new HashSet<>();
        Set<String> favouriteCitiesStringSet = sharedPreferences.getStringSet(FAVOURITE_CITIES_KEY, new HashSet<String>());

        for (String cityString : favouriteCitiesStringSet) {
            favouriteCitiesSet.add(objectMapper.readValue(cityString, FavouriteCity.class));
        }
        favouriteCitiesSet.remove(city);

        favouriteCitiesStringSet = new HashSet<>();
        for(FavouriteCity c : favouriteCitiesSet) {
            favouriteCitiesStringSet.add(objectMapper.writeValueAsString(c));
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(FAVOURITE_CITIES_KEY, favouriteCitiesStringSet);
        editor.apply();

        return favouriteCitiesSet;
    }

    @Override
    public Set<FavouriteCity> getFavourites() throws IOException {
        Set<FavouriteCity> favouriteCitiesSet = new HashSet<>();
        Set<String> favouriteCitiesStringSet = sharedPreferences.getStringSet(FAVOURITE_CITIES_KEY, new HashSet<String>());
        for (String cityString :
                favouriteCitiesStringSet) {
            favouriteCitiesSet.add(objectMapper.readValue(cityString, FavouriteCity.class));
        }
        return favouriteCitiesSet;
    }
}

package com.mobiquityinc.weather.domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mobiquityinc.weather.domain.entities.FavouriteCity;

import java.util.Set;

public class FavouritesRepositoryImpl implements FavouritesRepository {

    private final SharedPreferences sharedPreferences;

    public FavouritesRepositoryImpl(final Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public Set<FavouriteCity> getFavourites() {

        return null;
    }
}

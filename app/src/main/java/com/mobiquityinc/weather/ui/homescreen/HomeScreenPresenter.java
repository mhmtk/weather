package com.mobiquityinc.weather.ui.homescreen;

import com.mobiquityinc.weather.domain.FavouriteCityRepository;
import com.mobiquityinc.weather.domain.entities.City;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private FavouriteCityRepository favouriteCityRepository;
    private HomeScreenContract.View view;

    public HomeScreenPresenter(FavouriteCityRepository favouriteCityRepository, HomeScreenContract.View view) {
        this.favouriteCityRepository = favouriteCityRepository;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.initiateUI();
        populateFavouriteCitiesList();
    }

    private void populateFavouriteCitiesList() {
        try {
            view.setFavouriteCities(getDisplayableCityList(favouriteCityRepository.getFavourites()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCityButtonClicked() {
        view.startMapFragment();
    }

    @Override
    public void onCityClicked(City city) {
        view.launchCityScreen(city);
    }

    @Override
    public void onCityDeleteClicked(City city) {
        try {
            view.setFavouriteCities(getDisplayableCityList(favouriteCityRepository.removeFavourite(city)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<City> getDisplayableCityList(Set<City> cities) {
        ArrayList<City> favourites = new ArrayList<>(cities);
        Collections.sort(favourites, new Comparator<City>() {
            @Override
            public int compare(City city, City city2) {
                return city.getName().compareTo(city2.getName());
            }
        });
        return favourites;
    }

}

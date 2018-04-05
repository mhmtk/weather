package com.mobiquityinc.weather;


import static org.mockito.Mockito.verify;

import com.mobiquityinc.weather.domain.FavouriteCityRepository;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.ui.homescreen.HomeScreenContract;
import com.mobiquityinc.weather.ui.homescreen.HomeScreenPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class HomeScreenPresenterTests {

    @Mock HomeScreenContract.View mockView;
    private HomeScreenPresenter presenter;
    @Mock
    FavouriteCityRepository mockFavouriteCityRepository;
    @Mock
    City city;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new HomeScreenPresenter(mockFavouriteCityRepository, mockView);

    }

    @Test
    public void init_setsPresenter() {
        verify(mockView).setPresenter(presenter);
    }

    @Test
    public void start_initiatesUI() {
        presenter.start();
        verify(mockView).initiateUI();
    }

    @Test
    public void addCityButtonClicked_startsMapFragment() {
        presenter.addCityButtonClicked();
        verify(mockView).startMapFragment();
    }

    @Test
    public void onCityDeleteClicked_removesCity() throws IOException {
        presenter.onCityDeleteClicked(city);
        verify(mockFavouriteCityRepository).removeFavourite(city);
    }

}

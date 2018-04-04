package com.mobiquityinc.weather;


import com.mobiquityinc.weather.domain.FavouriteCityRepository;
import com.mobiquityinc.weather.ui.homescreen.HomeScreenContract;
import com.mobiquityinc.weather.ui.homescreen.HomeScreenPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomeScreenPresenterTests {

    @Mock HomeScreenContract.View mockView;
    private HomeScreenPresenter presenter;
    @Mock
    FavouriteCityRepository mMockFavouriteCityRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPresenter_setsView() {
        presenter = new HomeScreenPresenter(mMockFavouriteCityRepository, mockView);
        verify(mockView).setPresenter(presenter);
    }

    @Test
    public void start_initiatesUI() {
        presenter = new HomeScreenPresenter(mMockFavouriteCityRepository, mockView);
        presenter.start();
        verify(mockView).initiateUI();
    }

    @Test
    public void addCityButtonClicked_startsMapFragment() {
        presenter = new HomeScreenPresenter(mMockFavouriteCityRepository, mockView);
        presenter.addCityButtonClicked();
        verify(mockView).startMapFragment();
    }

}

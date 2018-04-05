package com.mobiquityinc.weather;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.domain.entities.LatLon;
import com.mobiquityinc.weather.ui.main.MainContract;
import com.mobiquityinc.weather.ui.main.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTests {

    @Mock
    MainContract.View mockView;
    @Mock
    City city;
    private MainPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPresenter_setsView() {
        presenter = new MainPresenter(mockView);
        verify(mockView).setPresenter(presenter);
    }

    @Test
    public void start_displaysHomeScreen() {
        presenter = new MainPresenter(mockView);
        presenter.start();
        verify(mockView).displayHomeScreen();
    }

    @Test
    public void citySelected_launchesCityScreen() {
        presenter = new MainPresenter(mockView);
        when(city.getCoord()).thenReturn(new LatLon(1, 1));
        presenter.citySelected(city);
        verify(mockView).launchCityScreen(new LatLng(1,1));
    }

    @Test
    public void helpButtonClicked_launchesHelpScreen() {
        presenter = new MainPresenter(mockView);
        presenter.helpButtonClicked();
        verify(mockView).launchHelpScreen();
    }

    @Test
    public void addCityButtonClicked_launchesMapScreen() {
        presenter = new MainPresenter(mockView);
        presenter.addCityClicked();
        verify(mockView).launchMap();
    }
}

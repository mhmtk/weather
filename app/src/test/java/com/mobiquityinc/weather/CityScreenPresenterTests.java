package com.mobiquityinc.weather;


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.domain.FavouriteCityRepository;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.domain.entities.Forecast;
import com.mobiquityinc.weather.network.ApiManager;
import com.mobiquityinc.weather.ui.cityscreen.CityScreenContract;
import com.mobiquityinc.weather.ui.cityscreen.CityScreenPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CityScreenPresenterTests {

    @Mock CityScreenContract.View mockView;
    private CityScreenPresenter presenter;
    @Mock
    FavouriteCityRepository mockFavouriteCityRepository;
    @Mock
    ApiManager mockApiManager;
    @Mock
    Forecast mockForecast;
    private LatLng dummyLatLng = new LatLng(1, 1);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new CityScreenPresenter(dummyLatLng, mockFavouriteCityRepository, mockApiManager, mockView);
        when(mockForecast.getCity()).thenReturn(Mockito.mock(City.class));
    }

    @Test
    public void createPresenter_setsPresenter() {
        verify(mockView).setPresenter(presenter);
    }

    @Test
    public void start_initializesView() {
        presenter.start();
        verify(mockView).initiateUI();
    }

    @Test
    public void start_startsDownload() {
        presenter.start();
        verify(mockApiManager).downloadForecast(eq(presenter), any(Double[].class));
    }

    @Test
    public void start_startsDownloadOnce() {
        presenter.start();
        presenter.downloadFinished(mockForecast);
        presenter.start();
        verify(mockApiManager, times(1)).downloadForecast(eq(presenter), any(Double[].class));
    }

    @Test
    public void downloadFinished_addsFavourite() throws JsonProcessingException {
        City mockCity = Mockito.mock(City.class);
        when(mockForecast.getCity()).thenReturn(mockCity);
        presenter.downloadFinished(mockForecast);
        verify(mockFavouriteCityRepository).addFavourite(mockCity);
    }

    @Test
    public void downloadFinished_updatesView() throws JsonProcessingException {
        presenter.downloadFinished(mockForecast);
        verify(mockView).displayData(mockForecast);
    }

}

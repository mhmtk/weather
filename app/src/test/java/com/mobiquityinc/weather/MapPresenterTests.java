package com.mobiquityinc.weather;

import static org.mockito.Mockito.verify;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.ui.mapscreen.MapScreenContract;
import com.mobiquityinc.weather.ui.mapscreen.MapScreenPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterTests {

    @Mock
    MapScreenContract.View mockView;
    private MapScreenPresenter presenter;
    LatLng dummyLatLng = new LatLng(1, 1);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPresenter_setsView() {
        presenter = new MapScreenPresenter(mockView);
        verify(mockView).setPresenter(presenter);
    }

    @Test
    public void mapClicked_replacesMarker() {
        presenter = new MapScreenPresenter(mockView);
        presenter.mapClicked(dummyLatLng);
        verify(mockView).replaceMarker(dummyLatLng);
    }

    @Test
    public void mapClicked_showsFab() {
        presenter = new MapScreenPresenter(mockView);
        presenter.mapClicked(dummyLatLng);
        verify(mockView).showSelectFab();
    }

    @Test
    public void locationSelected_launchesCityScreen() {
        presenter = new MapScreenPresenter(mockView);
        presenter.locationSelected(dummyLatLng);
        verify(mockView).launchCityScreen(dummyLatLng);
    }
}

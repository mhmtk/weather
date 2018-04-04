package com.mobiquityinc.weather;


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


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPresenter_setsView() {
        presenter = new HomeScreenPresenter(mockView);
        verify(mockView).setPresenter(presenter);
    }
}

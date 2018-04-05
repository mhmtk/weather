package com.mobiquityinc.weather.ui.cityscreen;

import com.mobiquityinc.weather.domain.entities.Forecast;
import com.mobiquityinc.weather.ui.BasePresenter;
import com.mobiquityinc.weather.ui.BaseView;

public interface CityScreenContract {

    interface View extends BaseView<Presenter> {

        void initiateUI();

        void displayData(Forecast forecast);
    }

    interface Presenter extends BasePresenter {

        void stop();
    }
}

package com.mobiquityinc.weather.ui.cityscreen;

import com.mobiquityinc.weather.ui.BasePresenter;
import com.mobiquityinc.weather.ui.BaseView;

public interface CityScreenContract {

    interface View extends BaseView<Presenter> {

        void initiateUI();

    }

    interface Presenter extends BasePresenter {

    }
}

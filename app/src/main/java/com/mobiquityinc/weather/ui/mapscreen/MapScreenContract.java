package com.mobiquityinc.weather.ui.mapscreen;

import com.mobiquityinc.weather.ui.BasePresenter;
import com.mobiquityinc.weather.ui.BaseView;

public class MapScreenContract {

    interface View extends BaseView<Presenter> {

        void initiateUI();
    }

    interface Presenter extends BasePresenter {

    }
}

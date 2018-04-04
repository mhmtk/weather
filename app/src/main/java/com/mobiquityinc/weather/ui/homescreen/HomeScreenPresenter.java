package com.mobiquityinc.weather.ui.homescreen;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private HomeScreenContract.View view;

    public HomeScreenPresenter(HomeScreenContract.View view) {
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}

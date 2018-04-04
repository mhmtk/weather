package com.mobiquityinc.weather.ui.homescreen;

import android.app.Fragment;

public class HomeScreenFragment extends Fragment implements HomeScreenContract.View {

    private HomeScreenContract.Presenter presenter;

    @Override
    public void setPresenter(HomeScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }
}

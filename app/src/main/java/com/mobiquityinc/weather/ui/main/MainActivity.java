package com.mobiquityinc.weather.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.ui.homescreen.HomeScreenFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        HomeScreenFragment.HomeScreenActionListener {

    @BindView(R.id.fragment_container_frame_layout)
    protected FrameLayout fragmentContainer;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCitySelected(City city) {
        presenter.citySelected(city);
    }

    @Override
    public void onAddClicked() {
        presenter.addCityClicked();
    }

    @Override
    public void displayHomeScreen() {
        HomeScreenFragment homeScreenFragment = HomeScreenFragment.getInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_frame_layout, homeScreenFragment)
                .commit();
    }
}

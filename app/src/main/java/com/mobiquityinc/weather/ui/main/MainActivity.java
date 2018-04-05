package com.mobiquityinc.weather.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.FavouriteCity;
import com.mobiquityinc.weather.ui.cityscreen.CityScreenFragment;
import com.mobiquityinc.weather.ui.homescreen.HomeScreenFragment;
import com.mobiquityinc.weather.ui.mapscreen.MapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        HomeScreenFragment.HomeScreenActionListener,
        MapFragment.MapScreenActionListener,
        CityScreenFragment.CityScreenActionListener {

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
    public void onCitySelected(FavouriteCity city) {
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

    @Override
    public void launchMap() {
        MapFragment mapFragment = MapFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_frame_layout, mapFragment)
                .addToBackStack("map")
                .commit();
    }

    @Override
    public void onLocationSaved(LatLng latLng) {
        CityScreenFragment cityScreenFragment = CityScreenFragment.getInstance(latLng);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_frame_layout, cityScreenFragment)
                .addToBackStack(null)
                .commit();
    }
}

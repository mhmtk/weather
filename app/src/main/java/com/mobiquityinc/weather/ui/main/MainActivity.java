package com.mobiquityinc.weather.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.ui.cityscreen.CityScreenFragment;
import com.mobiquityinc.weather.ui.help.HelpScreenFragment;
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
    private CityScreenFragment cityScreenFragment;
    private MapFragment mapFragment;
    private HomeScreenFragment homeScreenFragment;
    private HelpScreenFragment helpScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                presenter.helpButtonClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        hideBackButton();
        if (homeScreenFragment == null && mapFragment == null && cityScreenFragment == null) {
            homeScreenFragment = HomeScreenFragment.getInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_frame_layout, homeScreenFragment)
                    .commit();
        }
    }

    @Override
    public void launchMap() {
        displayBackButton();
        mapFragment = MapFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_frame_layout, mapFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void launchCityScreen(LatLng latLng) {
        displayBackButton();
        cityScreenFragment = CityScreenFragment.getInstance(latLng);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_frame_layout, cityScreenFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void launchHelpScreen() {
        displayBackButton();
        helpScreenFragment = HelpScreenFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_frame_layout, helpScreenFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onLocationSelected(LatLng latLng) {
        displayBackButton();
        cityScreenFragment = CityScreenFragment.getInstance(latLng);
        getSupportFragmentManager().beginTransaction()
                .remove(mapFragment)
                .commit();
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_frame_layout, cityScreenFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
            hideBackButton();
        }
    }

    public void displayBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void hideBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }
}

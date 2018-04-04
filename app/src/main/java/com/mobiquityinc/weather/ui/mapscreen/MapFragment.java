package com.mobiquityinc.weather.ui.mapscreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.LatLon;

import butterknife.ButterKnife;

public class MapFragment extends android.support.v4.app.Fragment implements MapScreenContract.View,
        OnMapReadyCallback {

    private MapFragment.MapScreenActionListener callback;

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    public interface MapScreenActionListener {
        void onLocationSelected(final LatLon latLon);
    }

    private MapScreenContract.Presenter presenter;

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (MapFragment.MapScreenActionListener) activity;
        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement MapScreenActionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);
        presenter = new MapScreenPresenter(this);
        return view;
    }

    @Override
    public void setPresenter(MapScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }


    @Override
    public void initiateUI() {

    }

}

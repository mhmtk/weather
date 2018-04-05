package com.mobiquityinc.weather.ui.mapscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobiquityinc.weather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapFragment extends android.support.v4.app.Fragment implements MapScreenContract.View,
        OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private Marker marker;

    public interface MapScreenActionListener {
        void onLocationSelected(final LatLng latLng);
    }

    private MapFragment.MapScreenActionListener callback;
    private GoogleMap googleMaps;

    @BindView(R.id.mapView)
    protected MapView mapView;
    @BindView(R.id.fab_select_location)
    protected FloatingActionButton selectLocationFab;

    private MapScreenContract.Presenter presenter;

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (MapFragment.MapScreenActionListener) context;
        } catch (ClassCastException e) {

            throw new ClassCastException(context.toString()
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
        mapView.onCreate(getArguments());
        mapView.getMapAsync(this);
        return view;
    }


    @Override
    public void setPresenter(MapScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void replaceMarker(LatLng latLng) {
        if (marker != null ) {
            marker.remove();
        }
        marker = googleMaps.addMarker(new MarkerOptions().position(latLng));
    }

    @Override
    public void launchCityScreen(LatLng latLng) {
        callback.onLocationSelected(latLng);
    }

    @Override
    public void displayLocationError() {
        Toast.makeText(getActivity(), R.string.error_message_location, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSelectFab() {
        selectLocationFab.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMaps = googleMap;
        googleMap.setOnMapClickListener(this);
        presenter.start();
    }


    @Override
    public void onMapClick(LatLng latLng) {
        presenter.mapClicked(latLng);
    }

    @OnClick(R.id.fab_select_location)
    public void onLocationSelected() {
        presenter.locationSelected(marker.getPosition());
    }
}

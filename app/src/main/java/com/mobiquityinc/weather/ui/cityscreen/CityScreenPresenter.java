package com.mobiquityinc.weather.ui.cityscreen;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.domain.entities.Forecast;
import com.mobiquityinc.weather.network.DownloadForecast;

public class CityScreenPresenter implements CityScreenContract.Presenter, DownloadForecast.Callback {

    private LatLng latLng;
    private final ObjectMapper objectMapper;
    private final CityScreenContract.View view;
    private AsyncTask<Double, Void, Forecast> forecastDownloadTask;

    public CityScreenPresenter(LatLng latLng,
            ObjectMapper objectMapper,
            CityScreenContract.View view) {
        this.latLng = latLng;
        this.objectMapper = objectMapper;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.initiateUI();
        downloadForecast();
    }

    private void downloadForecast() {
        Double parameters[] = { latLng.latitude, latLng.longitude };
        forecastDownloadTask = new DownloadForecast(this, objectMapper).execute(parameters);
    }

    @Override
    public void downloadFinished(Forecast forecast) {

    }
}

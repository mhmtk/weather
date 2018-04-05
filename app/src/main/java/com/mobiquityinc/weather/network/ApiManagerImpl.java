package com.mobiquityinc.weather.network;

import android.os.AsyncTask;

import com.mobiquityinc.weather.domain.entities.Forecast;

public class ApiManagerImpl implements ApiManager {

    private final JsonObjectMapper objectMapper;
    private AsyncTask<Double, Void, Forecast> forecastDownloadTask;

    public ApiManagerImpl(JsonObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void downloadForecast(DownloadForecast.Callback callback, Double[] parameters) {
        forecastDownloadTask = new DownloadForecast(callback, objectMapper).execute(parameters);
    }

    @Override
    public void cancelDownloadForecast() {
        forecastDownloadTask.cancel(true);
    }
}

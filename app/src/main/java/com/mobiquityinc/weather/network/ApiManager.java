package com.mobiquityinc.weather.network;

public interface ApiManager {

    void downloadForecast(DownloadForecast.Callback callback, Double[] parameters);

    void cancelDownloadForecast();
}

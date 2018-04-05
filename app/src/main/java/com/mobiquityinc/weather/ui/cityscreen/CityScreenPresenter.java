package com.mobiquityinc.weather.ui.cityscreen;

import android.text.TextUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.domain.FavouriteCityRepository;
import com.mobiquityinc.weather.domain.entities.Forecast;
import com.mobiquityinc.weather.network.ApiManager;
import com.mobiquityinc.weather.network.DownloadForecast;

public class CityScreenPresenter implements CityScreenContract.Presenter,
        DownloadForecast.Callback {

    private final static String DEFAULT_NO_NAME = "Unknown name";


    private LatLng latLng;
    private FavouriteCityRepository favouriteCityRepository;
    private ApiManager apiManager;
    private final CityScreenContract.View view;
    private boolean downloaded;

    public CityScreenPresenter(LatLng latLng,
            FavouriteCityRepository favouriteCityRepository,
            ApiManager apiManager,
            CityScreenContract.View view) {
        this.latLng = latLng;
        this.favouriteCityRepository = favouriteCityRepository;
        this.apiManager = apiManager;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.initiateUI();
        if (!downloaded) {
            downloadForecast();
        }
    }

    private void downloadForecast() {
        Double parameters[] = {latLng.latitude, latLng.longitude};
        apiManager.downloadForecast(this, parameters);
    }

    @Override
    public void downloadFinished(Forecast forecast) {
        downloaded = true;
        try {
            if (TextUtils.isEmpty(forecast.getCity().getName())) {
                forecast.getCity().setName(DEFAULT_NO_NAME);
            }
            favouriteCityRepository.addFavourite(forecast.getCity());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        view.displayData(forecast);
    }

    @Override
    public void stop() {
        apiManager.cancelDownloadForecast();
    }
}

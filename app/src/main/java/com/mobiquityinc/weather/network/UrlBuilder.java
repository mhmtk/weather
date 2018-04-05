package com.mobiquityinc.weather.network;

import com.mobiquityinc.weather.BuildConfig;

public class UrlBuilder {

    private final static String BASE_URL = "http://api.openweathermap.org/data/2.5";
    private final static String FORECAST_ENDPOINT = "forecast";
    private final static String PARAMETER_BEGINNING = "?";
    private final static String LATITUDE_FIELD = "lat=";
    private final static String LONGITUDE_FEILD = "lon=";
    private final static String API_FIELD = "APPID=";
    private final static String SLASH_SEPARATOR = "/";
    private final static String PARAMETER_SEPARATOR = "&";

    public static String getForecastUrl(final double lat, final double lng) {
        return BASE_URL
                .concat(SLASH_SEPARATOR)
                .concat(FORECAST_ENDPOINT)
                .concat(PARAMETER_BEGINNING)
                .concat(LATITUDE_FIELD)
                .concat(String.valueOf(lat))
                .concat(PARAMETER_SEPARATOR)
                .concat(LONGITUDE_FEILD)
                .concat(String.valueOf(lng))
                .concat(PARAMETER_SEPARATOR)
                .concat(API_FIELD)
                .concat(BuildConfig.API_KEY);
    }

}

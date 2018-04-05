package com.mobiquityinc.weather.network;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquityinc.weather.domain.entities.Forecast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadForecast extends AsyncTask<Double, Void, Forecast> {

    public interface Callback {
        void downloadFinished(Forecast forecast);
    }

    private final Callback callback;
    private final ObjectMapper mapper;

    public DownloadForecast(Callback callback, ObjectMapper objectMapper) {
        this.callback = callback;
        this.mapper = objectMapper;
    }

    @Override
    protected Forecast doInBackground(Double... params) {
        Forecast forecast = null;
        if (!isCancelled() && params != null && params.length > 0) {
            try {
                URL url = new URL(UrlBuilder.getForecastUrl(params[0], params[1]));
                String resultString = fetch(url);
                if (resultString != null) {
                    forecast = mapper.readValue(resultString, Forecast.class);
                } else {
                    throw new IOException("No response received.");
                }
            } catch(Exception e) {
                forecast = new Forecast();
            }
        }
        return forecast;
    }

    @Override
    protected void onPostExecute(Forecast forecast) {
        if (callback != null) {
            callback.downloadFinished(forecast);
        }
    }

    private String fetch(URL url) throws IOException {
        InputStream stream = null;
        HttpURLConnection connection = null;
        String response = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            stream = connection.getInputStream();
            if (stream != null) {
                final JsonNode json = mapper.readTree(stream);
                response = json.toString();
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }
}

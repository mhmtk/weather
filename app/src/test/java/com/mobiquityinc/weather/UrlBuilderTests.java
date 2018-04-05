package com.mobiquityinc.weather;

import static junit.framework.Assert.assertEquals;

import com.mobiquityinc.weather.network.UrlBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UrlBuilderTests {

    @Test
    public void buildsCorrectly() {
        assertEquals("https://api.openweathermap.org/data/2.5/forecast?lat=43.2&lon=33.2&APPID=".concat(BuildConfig.API_KEY),
                UrlBuilder.getForecastUrl(43.2, 33.2));
    }
}

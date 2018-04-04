package com.mobiquityinc.weather;

import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquityinc.weather.domain.FavouriteCityRepositoryImpl;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.domain.entities.LatLon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavouriteCityRepositoryImplTests {

    @Mock
    private SharedPreferences mockSharedPreferences;
    @Mock
    private SharedPreferences.Editor mockEditor;
    @Mock
    private ObjectMapper mockObjectMapper;

    private FavouriteCityRepositoryImpl repository;

    private static String dummyCityString = "{\"id\":7648382,\"name\":\"Weesperzijde\",\"coord\":{\"lat\":52.353,\"lon\":4.9106},\"country\":\"NL\"}";
    private static City dummyCity = new City(7648382, "Weesperzijde", new LatLon(52.353,4.9106), "NL");

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);

        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockObjectMapper.writeValueAsString(any(City.class))).thenReturn(dummyCityString);
        when(mockObjectMapper.readValue(anyString(), eq(City.class))).thenReturn(dummyCity);
    }

    @Test
    public void addFavourite_addInitial() throws JsonProcessingException {
        repository = new FavouriteCityRepositoryImpl(mockSharedPreferences, mockObjectMapper);
        repository.addFavourite(dummyCity);

        verify(mockEditor).putStringSet(FavouriteCityRepositoryImpl.FAVOURITE_CITIES_KEY, getDummyCityStringHashSet());
        verify(mockEditor).apply();
    }

    @Test
    public void getFavourites_filled() throws IOException {
        when(mockSharedPreferences.getStringSet(eq(FavouriteCityRepositoryImpl.FAVOURITE_CITIES_KEY), any(Set.class)))
                .thenReturn(getDummyCityStringHashSet());

        repository = new FavouriteCityRepositoryImpl(mockSharedPreferences, mockObjectMapper);

        Set<City> expected = new HashSet<>();
        expected.add(dummyCity);

        assertEquals(expected, repository.getFavourites());
    }


    @Test
    public void getFavourites_empty() throws IOException {
        repository = new FavouriteCityRepositoryImpl(mockSharedPreferences, mockObjectMapper);

        when(mockSharedPreferences.getStringSet(eq(FavouriteCityRepositoryImpl.FAVOURITE_CITIES_KEY), any(Set.class)))
                .thenReturn(new HashSet());

        Set<City> expected = new HashSet<>();

        assertEquals(expected, repository.getFavourites());
    }

    @Test
    public void removeFavourite() throws IOException {
        repository = new FavouriteCityRepositoryImpl(mockSharedPreferences, mockObjectMapper);
        repository.removeFavourite(dummyCity);

        when(mockSharedPreferences.getStringSet(eq(FavouriteCityRepositoryImpl.FAVOURITE_CITIES_KEY), any(Set.class)))
                .thenReturn(getDummyCityStringHashSet());

        verify(mockEditor).putStringSet(FavouriteCityRepositoryImpl.FAVOURITE_CITIES_KEY, new HashSet<String>());

    }

    private Set<String> getDummyCityStringHashSet() {
        HashSet<String> dummyCityStringHashSet = new HashSet<>();
        dummyCityStringHashSet.add(dummyCityString);
        return dummyCityStringHashSet;
    }
}

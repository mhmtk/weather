package com.mobiquityinc.weather.network;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JsonObjectMapper extends ObjectMapper {

    public JsonObjectMapper() {
        super();
        setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}

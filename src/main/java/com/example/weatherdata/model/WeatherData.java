package com.example.weatherdata.model;

import lombok.Data;

@Data
public class WeatherData {
    private Location location;
    private Forecast forecast;
}

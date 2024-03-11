package com.example.weatherdata.model;


import lombok.Data;
import lombok.ToString;

@Data
public class TodayWeatherData {
    private Location location;
    private Current current;
}

package com.example.weatherdata.controller;

import com.example.weatherdata.model.WeatherData;
import com.example.weatherdata.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/last30days")
    public ResponseEntity<List<WeatherData>> getLast30DaysWeather(@RequestParam String location) {
        List<WeatherData> weatherDataList = weatherService.getLast30DaysWeather(location);
        return ResponseEntity.ok(weatherDataList);
    }

    @GetMapping("/last7days")
    public ResponseEntity<List<WeatherData>> getLast7DaysWeather(@RequestParam String location) {
        List<WeatherData> weatherDataList = weatherService.getLast7DaysWeather(location);
        return ResponseEntity.ok(weatherDataList);
    }
}

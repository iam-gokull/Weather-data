package com.example.weatherdata.controller;

import com.example.weatherdata.model.TodayWeatherData;
import com.example.weatherdata.model.WeatherData;
import com.example.weatherdata.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getWeather(@RequestParam String latitude, @RequestParam String longitude) {
        TodayWeatherData weatherData = weatherService.getCurrentData(latitude, longitude);
        return ResponseEntity.ok(weatherData);
    }
}

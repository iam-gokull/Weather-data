package com.example.weatherdata.service;

import com.example.weatherdata.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public List<WeatherData> getLast30DaysWeather(String location) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        for (int i = 30; i >= 1; i--) {
            LocalDate date = LocalDate.now().minusDays(i);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            WeatherData weatherData = getWeatherData(location, formattedDate);
            weatherDataList.add(weatherData);
        }
        return weatherDataList;
    }

    public List<WeatherData> getLast7DaysWeather(String location) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        for (int i = 7; i >= 1; i--) {
            LocalDate date = LocalDate.now().minusDays(i);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            WeatherData weatherData = getWeatherData(location, formattedDate);
            weatherDataList.add(weatherData);
        }
        return weatherDataList;
    }

    private WeatherData getWeatherData(String location, String date) {
        String apiUrl = "https://api.weatherapi.com/v1/history.json?q=" + location + "&dt=" + date + "&key=" + weatherApiKey;
        ResponseEntity<WeatherData> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, WeatherData.class);
        return responseEntity.getBody();
    }
}


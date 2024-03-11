package com.example.weatherdata.service;

import com.example.weatherdata.model.TodayWeatherData;
import com.example.weatherdata.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@CacheConfig(cacheNames = "weatherCache")
public class WeatherService {

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public List<WeatherData> getLast30DaysWeather(String location) {
        return IntStream.rangeClosed(1, 30)
                .parallel() // Use parallel stream for parallel processing
                .mapToObj(i -> {
                    LocalDate date = LocalDate.now().minusDays(i);
                    String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return getWeatherDataAsync(location, formattedDate).join();
                })
                .collect(Collectors.toList());
    }

    public List<WeatherData> getLast7DaysWeather(String location) {
        return IntStream.rangeClosed(1, 7)
                .parallel() // Use parallel stream for parallel processing
                .mapToObj(i -> {
                    LocalDate date = LocalDate.now().minusDays(i);
                    String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return getWeatherDataAsync(location, formattedDate).join();
                })
                .collect(Collectors.toList());
    }

    @Cacheable
    private WeatherData getWeatherData(String location, String date) {
        String apiUrl = "https://api.weatherapi.com/v1/history.json?q=" + location + "&dt=" + date + "&key=" + weatherApiKey;
        ResponseEntity<WeatherData> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, WeatherData.class);
        return responseEntity.getBody();
    }

    @Async
    public CompletableFuture<WeatherData> getWeatherDataAsync(String location, String date) {
        return CompletableFuture.completedFuture(getWeatherData(location, date));
    }


    public TodayWeatherData getCurrentData(String latitude, String longitude) {
        String apiUrl = "https://api.weatherapi.com/v1/current.json?q=" + latitude + "," + longitude + "&key=" + weatherApiKey;
        ResponseEntity<TodayWeatherData> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, TodayWeatherData.class);
        return responseEntity.getBody();
    }
}


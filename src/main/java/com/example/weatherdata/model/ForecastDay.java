package com.example.weatherdata.model;

import lombok.Data;

@Data
public class ForecastDay {
    private String date;
    private long date_epoch;
    private DayData day;
}

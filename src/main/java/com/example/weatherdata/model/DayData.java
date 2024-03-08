package com.example.weatherdata.model;

import lombok.Data;

@Data
public class DayData {
    private double maxtemp_c;
    private double maxtemp_f;
    private double mintemp_c;
    private double mintemp_f;
    private double avgtemp_c;
    private double avgtemp_f;
    private double maxwind_kph;
    private double totalprecip_mm;
    private double avgvis_km;
    private double avghumidity;
    private Condition condition;
    private int uv;
}

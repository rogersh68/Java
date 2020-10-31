package com.example.prove06;

import java.util.List;

public class ForecastConditions {
    List<ForecastItem> list;

    public ForecastConditions (List<ForecastItem> list) {
        this.list = list;
    }


    public String toString() {
        return "WeatherForecast: \n" + list;
    }
}

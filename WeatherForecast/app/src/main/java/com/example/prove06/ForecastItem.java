package com.example.prove06;

import java.util.List;
import java.util.Map;

public class ForecastItem {
    String dt_txt;
    Map<String, Float> main;

    public ForecastItem(String dt_txt, Map<String, Float> main) {
        this.dt_txt = dt_txt;
        this.main = main;
    }

    public String toString() {
        float temp = main.get("temp");
        return  dt_txt + " " + temp + "\n";
    }
}

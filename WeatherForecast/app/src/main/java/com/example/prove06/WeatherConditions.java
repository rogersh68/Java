package com.example.prove06;

import java.util.Map;

public class WeatherConditions {
    int id;
    String name;
    Map<String, Float> main;

    public WeatherConditions (int id, String name, Map<String, Float> main) {
        this.id = id;
        this.name = name;
        this.main = main;
    }

    public String toString() {
        float temp = main.get("temp");
        return "Current temperature for " + name + ": " + temp;
    }
}

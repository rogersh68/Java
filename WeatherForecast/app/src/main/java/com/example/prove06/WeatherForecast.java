package com.example.prove06;

import android.content.Context;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeatherForecast implements Runnable {
    private MainActivity activity;
    private String city;
    private ArrayAdapter adapter;
    ForecastConditions conditions;

    public WeatherForecast (MainActivity activity, String city, ArrayAdapter adapter) {
        this.activity = activity;
        this.city = city;
        this.adapter = adapter;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void run() {
        Gson gson = new Gson();
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=imperial&apiKey=29532d73c747db9a9cb1be86effaaac6";

        URLConnection connection = null;
        try {
            connection = new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            conditions = gson.fromJson(responseBody, ForecastConditions.class);

            System.out.println(conditions);
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Add forecast to ListView
                for (ForecastItem item : conditions.list) {
                    adapter.add(item);
                }
            }
        });
    }
}

package com.example.prove06;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CurrentTemperature implements Runnable {
    private MainActivity activity;
    private String city;
    private String temp;

    public CurrentTemperature (MainActivity activity, String city) {
        this.activity = activity;
        this.city = city;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void run() {
        Gson gson = new Gson();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=imperial&apiKey=29532d73c747db9a9cb1be86effaaac6";

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
            WeatherConditions conditions = gson.fromJson(responseBody, WeatherConditions.class);
            System.out.println(conditions);
            this.temp = conditions.toString();
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Context context = activity;
                CharSequence text = temp;
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}

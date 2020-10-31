package com.example.prove06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up forecast ListView
        ListView forecastOutput = (ListView) findViewById(R.id.forecastOutput);
        List<String> forecastItems = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, forecastItems);
        forecastOutput.setAdapter(adapter);
    }

    public void getCurrentTemperature (View view) {
        // Get city input
        EditText cityInput = findViewById(R.id.cityInput);
        String city = cityInput.getText().toString();

        // Add output to temp TextView
        /*TextView tempOutput = findViewById(R.id.tempOutput);
        tempOutput.setText("Temp for " + city + ":");*/

        // Run background thread on CurrentTemperature
        Thread tThread = new Thread(new CurrentTemperature(this, city));
        tThread.start();
    }

    public void getWeatherForecast (View view) {
        // Get city input
        EditText cityInput = findViewById(R.id.cityInput);
        String city = cityInput.getText().toString();

        // Run background thread on WeatherForecast
        Thread fThread = new Thread(new WeatherForecast(this, city, adapter));
        fThread.start();

        // add output to forecast ListView
        //adapter.add(city);

    }
}

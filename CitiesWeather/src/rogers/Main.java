package rogers;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();


        Scanner input = new Scanner(System.in);

        System.out.println("Enter a city: ");

        String city = input.nextLine();
        city.replaceAll("\\s","");


        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=imperial&apiKey=29532d73c747db9a9cb1be86effaaac6";

        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();

        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            WeatherConditions conditions = gson.fromJson(responseBody, WeatherConditions.class);
            System.out.println(conditions);
        }


        String forecastUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=imperial&apiKey=29532d73c747db9a9cb1be86effaaac6";
        URLConnection connection2 = new URL(forecastUrl).openConnection();
        InputStream response2 = connection2.getInputStream();

        try (Scanner scanner2 = new Scanner(response2)) {
            String responseBody2 = scanner2.useDelimiter("\\A").next();
            WeatherForecast forecast = gson.fromJson(responseBody2, WeatherForecast.class);
            System.out.println(forecast);
        }


        // STRETCH
        Map<Cities, Float> temp_max = new HashMap<>();
        Map<Cities, Float> speed_max = new HashMap<>();

        for (Cities c : Cities.values()) {

            String fUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + c + "&units=imperial&apiKey=29532d73c747db9a9cb1be86effaaac6";
            URLConnection connection3 = new URL(fUrl).openConnection();
            InputStream response3 = connection3.getInputStream();

            try (Scanner scanner3 = new Scanner(response3)) {
                String responseBody3 = scanner3.useDelimiter("\\A").next();
                WeatherForecast forecast = gson.fromJson(responseBody3, WeatherForecast.class);

                List<Float> tempList = new ArrayList<>();
                List<Float> windList = new ArrayList<>();

                for (WeatherForecastItem e : forecast.list) {
                    tempList.add(e.main.get("temp_max"));
                    windList.add(e.wind.speed);
                }

                // get max values
                float maxTemp = Collections.max(tempList);
                float maxSpeed = Collections.max(windList);

                temp_max.put(c, maxTemp);
                speed_max.put(c, maxSpeed);

            }// end try

        }// end for loop
        // sort
        LinkedHashMap<Cities, Float> sortedTemps = new LinkedHashMap<>();
        temp_max.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sortedTemps.put(x.getKey(), x.getValue()));

        LinkedHashMap<Cities, Float> sortedSpeeds = new LinkedHashMap<>();
        speed_max.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sortedSpeeds.put(x.getKey(), x.getValue()));

        // display
        System.out.println("Max Temps: " + sortedTemps);
        System.out.println("Max Speed: " + sortedSpeeds);
    }// end main
}

package rogers;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WeatherConditions {
    int id;
    String name;
    @SerializedName("main")
    Map<String, Float> measurements;

    public WeatherConditions (int id, String name, Map<String, Float> measurements) {
        this.id = id;
        this.name = name;
        this.measurements = measurements;
    }

    public String toString() {
        return name + "(id: " + id + "): \nWeatherConditions:  \n" + measurements;
    }
}

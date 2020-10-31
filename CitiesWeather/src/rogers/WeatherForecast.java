package rogers;

import java.util.List;
import java.util.Map;

public class WeatherForecast {
    List<WeatherForecastItem> list;

    public WeatherForecast (List<WeatherForecastItem> list) {
        this.list = list;
    }



    public String toString() {
        return "WeatherForecast: \n" + list + "\n";
    }
}

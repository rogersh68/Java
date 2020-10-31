package rogers;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeatherForecastItem {
    String dt_txt;
    Map<String, Float> main;
    WeatherWind wind;
    List<WeatherDescription> weather;


    public WeatherForecastItem(String dt_txt, Map<String, Float> main, WeatherWind wind, List<WeatherDescription> weather) {
        this.dt_txt = dt_txt;
        this.main = main;
        this.wind = wind;
        this.weather = weather;
    }

    public String toString() {
        return  dt_txt + " " + main + " " + wind + " " + weather + "\n";
    }
}

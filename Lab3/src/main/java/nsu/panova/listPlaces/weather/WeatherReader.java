package nsu.panova.listPlaces.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherReader {
    private String base;
    private Main main;
    private Wind wind;

    @Override
    public String toString() {
        String output = "";
        if (main != null) {
            output += String.format("Temp: %s\n", main.getTemp());
        }
        if (main != null) {
            output += String.format("Temp feels like: %s\n", main.getFeels_like());
        }
        if (wind != null) {
            output += String.format("Wind speed: %s\n", wind.getSpeed());
        }

        if (base != null) {
            output += String.format("Base: %s\n", base);
        }

        return output;
    }
}



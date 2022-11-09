package nsu.panova.weather.nearestPlaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sights {
    private Properties properties;

    @Override
    public String toString() {
        String output = "";
        if (properties != null) {
            output += String.format("%s", properties.getName());
        }

        return output;
    }

    public String getXid() {
        return getProperties().getXid();
    }

    public String toDescription() {
        String output = "";
        if (properties != null) {
            String tmp = properties.getKinds();
            String[] places = tmp.split(",");
            for (String i: places) {
                output += String.format("%s\n", i);
            }
        }

        return output;
    }

}

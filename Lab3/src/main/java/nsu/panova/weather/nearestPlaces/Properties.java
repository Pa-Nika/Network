package nsu.panova.weather.nearestPlaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
    private String name;
    private String kinds;
    private String xid;
}



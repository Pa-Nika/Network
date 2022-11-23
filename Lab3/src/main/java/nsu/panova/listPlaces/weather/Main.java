package nsu.panova.listPlaces.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    private String temp;
    private String feels_like;
}

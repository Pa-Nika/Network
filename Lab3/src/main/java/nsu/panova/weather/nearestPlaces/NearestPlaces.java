package nsu.panova.weather.nearestPlaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NearestPlaces {
    private ArrayList<Sights> features = new ArrayList<>();
}

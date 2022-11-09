package nsu.panova.weather.nearestPlaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Parser {

    @SneakyThrows
    public static NearestPlaces nearPlacePars(String strPLace) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(strPLace, NearestPlaces.class);
    }
}

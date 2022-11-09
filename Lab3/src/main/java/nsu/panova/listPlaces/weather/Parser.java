package nsu.panova.listPlaces.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Parser {
    @SneakyThrows
    public static WeatherReader weatherPars(String strPLace) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(strPLace, WeatherReader.class);
    }
}

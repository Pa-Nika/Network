package nsu.panova.weather.description;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Parser {
    @SneakyThrows
    public static Description descriptionPars(String strPLace) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(strPLace, Description.class);
    }
}

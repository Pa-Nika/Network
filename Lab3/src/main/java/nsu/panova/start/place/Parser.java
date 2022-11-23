package nsu.panova.start.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Parser {

    @SneakyThrows
    public static Place placePars(String strPLace) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(strPLace, Place.class);
    }
}

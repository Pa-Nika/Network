package nsu.panova.weather.description;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Description {
    private String wikipedia;
    private Extracts wikipedia_extracts;

    @Override
    public String toString() {
        String output = "";

        if (wikipedia_extracts != null) {
            output += String.format("\n%s\n", wikipedia_extracts.getTitle().toUpperCase());
            output += String.format("%s\n", wikipedia_extracts.getText());
        }

        if (wikipedia != null) {
            output += String.format("\nIf you want to know more: \n%s\n", wikipedia);
        }

        return output;
    }

}

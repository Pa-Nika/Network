module nsu.panova {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires lombok;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens nsu.panova to javafx.fxml;
    exports nsu.panova;

    opens nsu.panova.listPlaces to javafx.fxml;
    exports nsu.panova.listPlaces;

    exports nsu.panova.start;
    opens nsu.panova.start to javafx.fxml;

    exports nsu.panova.weather;
    opens nsu.panova.weather to javafx.fxml;

    exports nsu.panova.start.place;
    opens nsu.panova.start.place to javafx.fxml, com.fasterxml.jackson.databind;

    exports nsu.panova.listPlaces.weather;
    opens nsu.panova.listPlaces.weather to javafx.fxml, com.fasterxml.jackson.databind;

    exports nsu.panova.weather.nearestPlaces;
    opens nsu.panova.weather.nearestPlaces to javafx.fxml, com.fasterxml.jackson.databind;

    exports nsu.panova.weather.description;
    opens nsu.panova.weather.description to javafx.fxml, com.fasterxml.jackson.databind;
}
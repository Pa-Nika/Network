package nsu.panova.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Setter;
import lombok.SneakyThrows;
import nsu.panova.start.ModelStartWindow;
import nsu.panova.start.place.Point;
import nsu.panova.weather.description.Description;
import nsu.panova.weather.nearestPlaces.NearestPlaces;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import static java.net.http.HttpRequest.newBuilder;
import static nsu.panova.weather.description.Parser.descriptionPars;
import static nsu.panova.weather.nearestPlaces.Parser.nearPlacePars;

public class ModelWeatherWindow {
    private Properties properties;
    private String keyOpenTripMap;
    @Setter private Point point;
    @Setter private ControllerWeather controller;
    @Setter private String xid;

    public ModelWeatherWindow() {
        try {
            properties = new Properties();
            InputStream inputStream = ModelStartWindow.class.getResourceAsStream("keys.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void workNearPlace() {
        keyOpenTripMap = properties.getProperty("keyOpenTripMap");
        CompletableFuture<NearestPlaces> nearestPlaces = sendRequestPlace();
        controller.setNearestPlaces(nearestPlaces);
    }

    public void workDescriptionPlace() {
        CompletableFuture<Description> description = sendRequestDescription();
        controller.setDescriptionOfPlace(description);
    }

    @SneakyThrows
    private CompletableFuture<NearestPlaces> sendRequestPlace() {
        String requestURIString = String.format("http://api.opentripmap.com/0.1/ru/places/bbox?lon_min=%s&lat_min=%s&lon_max=%s&lat_max=%s&kinds=churches&format=geojson&apikey=%s",
                Double.parseDouble(point.getLng()) - 0.05,
                Double.parseDouble(point.getLat()) - 0.05,
                Double.parseDouble(point.getLng()) + 0.05,
                Double.parseDouble(point.getLat()) + 0.05,
                keyOpenTripMap
        );

        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(requestURIString))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(str -> {
                    try {
                        return nearPlacePars(str);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    @SneakyThrows
    private CompletableFuture<Description> sendRequestDescription() {
        String requestURIString = String.format("http://api.opentripmap.com/0.1/ru/places/xid/%s?apikey=%s",
                xid,
                keyOpenTripMap
        );

        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(requestURIString))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(str -> {
                    try {
                        return descriptionPars(str);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }
}


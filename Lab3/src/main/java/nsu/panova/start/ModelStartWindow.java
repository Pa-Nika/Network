package nsu.panova.start;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import nsu.panova.Main;
import nsu.panova.listPlaces.LoaderListPlacesWindow;
import nsu.panova.start.place.Parser;
import nsu.panova.start.place.Place;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.net.http.HttpRequest;
import java.util.concurrent.CompletableFuture;

import static java.net.http.HttpRequest.newBuilder;

public class ModelStartWindow {
    private String userPlace;
    private Properties properties;
    private static String keyGraphhopper;
    private CompletableFuture<Place> place;

    public ModelStartWindow() {
        try {
            properties = new Properties();
            InputStream inputStream = ModelStartWindow.class.getResourceAsStream("keys.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlace(String place) {
        userPlace = place;
    }

    public void work() {
        try {
            keyGraphhopper = properties.getProperty("keyGraphhopper");
            place = sendRequest();
            loadNextWindow();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private CompletableFuture<Place> sendRequest() throws URISyntaxException {
        String requestURIString = String.format("https://graphhopper.com/api/1/geocode?q=%s&locale=de&key=%s",
                userPlace,
                keyGraphhopper
        );

        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(requestURIString))
                .build();



        HttpClient httpClient = HttpClient.newHttpClient();

        System.out.println(requestURIString);

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(str -> {
                    try {
                        return Parser.placePars(str);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    private void loadNextWindow() {
        LoaderListPlacesWindow loaderListPlaces = new LoaderListPlacesWindow();
        loaderListPlaces.setUserPlace(userPlace);
        loaderListPlaces.setPlace(place);
        Main.setNewLoader(loaderListPlaces);
    }
}

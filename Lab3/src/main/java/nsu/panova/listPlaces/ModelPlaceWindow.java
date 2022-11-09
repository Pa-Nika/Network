package nsu.panova.listPlaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Setter;
import lombok.SneakyThrows;
import nsu.panova.Main;
import nsu.panova.listPlaces.weather.Parser;
import nsu.panova.listPlaces.weather.WeatherReader;
import nsu.panova.start.ModelStartWindow;
import nsu.panova.start.place.Place;
import nsu.panova.start.place.Point;
import nsu.panova.weather.LoaderWeatherWindow;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpRequest.newBuilder;

public class ModelPlaceWindow {
    @Setter private CompletableFuture<Place> place;
    @Setter private int index;
    private Point point;
    private Properties properties;
    private CompletableFuture<WeatherReader> weather;
    private static String keyOpenWeatherMap;

    public ModelPlaceWindow() {
        try {
            properties = new Properties();
            InputStream inputStream = ModelStartWindow.class.getResourceAsStream("keys.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void work() {
        countPoint();
        keyOpenWeatherMap = properties.getProperty("keyOpenWeatherMap");
        weather = sendRequest();
        loadNextWindow();
    }

    @SneakyThrows
    private CompletableFuture<WeatherReader> sendRequest() {
        String requestURIString = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s",
                point.getLat(),
                point.getLng(),
                keyOpenWeatherMap
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
                        return Parser.weatherPars(str);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    private void countPoint() {
        try {
            point = place.get().getHits().get(index).getPoint();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void loadNextWindow() {
        try {
            LoaderWeatherWindow loaderWeatherWindow = new LoaderWeatherWindow();
            loaderWeatherWindow.setUserPlace(place.get().getHits().get(index).getName());
            loaderWeatherWindow.setWeather(weather);
            loaderWeatherWindow.setPoint(point);
            Main.setNewLoader(loaderWeatherWindow);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}

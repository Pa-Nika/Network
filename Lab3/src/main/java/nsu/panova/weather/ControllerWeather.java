package nsu.panova.weather;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import lombok.Setter;
import nsu.panova.Main;
import nsu.panova.listPlaces.LoaderListPlacesWindow;
import nsu.panova.listPlaces.weather.WeatherReader;
import nsu.panova.start.place.Place;
import nsu.panova.start.place.Point;
import nsu.panova.weather.description.Description;
import nsu.panova.weather.nearestPlaces.NearestPlaces;
import nsu.panova.weather.nearestPlaces.Sights;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ControllerWeather {
    @FXML private Label placeName;
    @FXML private TextArea weatherList;
    @FXML private ListView<String> placesList;
    @FXML private TextArea description;
    private CompletableFuture<WeatherReader> weather;
    private CompletableFuture<NearestPlaces> nearestPlaces;
    private CompletableFuture<Description> descriptionOfPlace;
    @Setter private CompletableFuture<Place> place;
    @Setter private ModelWeatherWindow model;
    @Setter private Point point;
    @Setter private String userPlaceFromStart;
    private int index;
    private Boolean noInfo = false;

    public void setPlaceName(String name) {
        placeName.setText(name);
    }

    public void setWeather(CompletableFuture<WeatherReader> weather_) {
        weather = weather_;
        CompletableFuture.supplyAsync(() -> weather
                .thenAcceptAsync((weather -> weatherList.appendText(weather.toString()))));

        model.setPoint(point);
        model.workNearPlace();
    }

    public void setNearestPlaces(CompletableFuture<NearestPlaces> places) {
        nearestPlaces = places;
        CompletableFuture.supplyAsync(() -> nearestPlaces
                .thenAcceptAsync(nearestPlaces ->  {
                    if (nearestPlaces.getFeatures().isEmpty()) {
                        placesList.getItems().add("No interesting places...");
                        noInfo = true;
                    } else {
                        for(Sights i: nearestPlaces.getFeatures()) {
                            placesList.getItems().add(i.toString());
                        }
                        noInfo = false;
                    }
                }));
    }

    @FXML
    public  void onMouseClick() {
        if (placesList.getSelectionModel().getSelectedIndex() == index || noInfo) {
            return;
        }

        index = placesList.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return;
        }
        description.clear();
        placesList.getSelectionModel().clearSelection(index);

        try {
            model.setXid(nearestPlaces.get().getFeatures().get(index).getXid());
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        }

        model.workDescriptionPlace();
        CompletableFuture.supplyAsync(() -> nearestPlaces
                .thenAcceptAsync((place -> description.appendText(place.getFeatures().get(index).toDescription()))));
    }

    public void setDescriptionOfPlace(CompletableFuture<Description> descriptionCompletableFuture) {
        descriptionOfPlace = descriptionCompletableFuture;
        CompletableFuture.supplyAsync(() -> descriptionOfPlace
                .thenAcceptAsync((description_ -> {
                    if (Objects.equals(description_.toString(), "")) {
                        description.appendText("\nNo more information...");
                    }
                    description.appendText(description_.toString());
                })));
    }

    @FXML
    public void backClick() {
        LoaderListPlacesWindow loaderListPlaces = new LoaderListPlacesWindow();
        loaderListPlaces.setUserPlace(userPlaceFromStart);
        loaderListPlaces.setPlace(place);
        Main.setNewLoader(loaderListPlaces);
    }
}

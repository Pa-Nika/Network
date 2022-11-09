package nsu.panova.weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import nsu.panova.InterfaceLoaders;
import nsu.panova.Main;
import nsu.panova.listPlaces.weather.WeatherReader;
import nsu.panova.start.place.Point;

import java.util.concurrent.CompletableFuture;

public class LoaderWeatherWindow extends Application implements InterfaceLoaders {
    @Setter private String userPlace;
    @Setter private CompletableFuture<WeatherReader> weather;
    private ModelWeatherWindow model = new ModelWeatherWindow();
    @Setter private Point point;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("weather-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);

        ControllerWeather controller = fxmlLoader.getController();
        controller.setModel(model);
        controller.setPoint(point);
        model.setController(controller);
        controller.setWeather(weather);
        controller.setPlaceName(userPlace);


        stage.setTitle("COSMO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}

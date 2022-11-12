package nsu.panova.listPlaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import nsu.panova.InterfaceLoaders;
import nsu.panova.Main;
import nsu.panova.start.place.Place;
import java.util.concurrent.CompletableFuture;

public class LoaderListPlacesWindow extends Application implements InterfaceLoaders {
    private final ModelPlaceWindow model = new ModelPlaceWindow();
    @Setter private String userPlace;
    @Setter private CompletableFuture<Place> place;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("places-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        ControllerPlaces controller = fxmlLoader.getController();
        controller.setUserPlace(userPlace);
        controller.setPlace(place);
        controller.setModelPlaceWindow(model);
        model.setUserPlace(userPlace);

        stage.setTitle("COSMO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}

package nsu.panova.start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsu.panova.InterfaceLoaders;
import nsu.panova.Main;

public class LoaderStartWindow implements InterfaceLoaders {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("start-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("COSMO");
        stage.setScene(scene);
        stage.show();
    }
}

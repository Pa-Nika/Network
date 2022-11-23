package nsu.panova;

import javafx.application.Application;
import javafx.stage.Stage;
import nsu.panova.start.LoaderStartWindow;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage mainStage) throws IOException {
        stage = mainStage;
        setNewLoader(new LoaderStartWindow());
    }

    public static void setNewLoader(InterfaceLoaders interfaceLoaders) {
        try {
            interfaceLoaders.start(stage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

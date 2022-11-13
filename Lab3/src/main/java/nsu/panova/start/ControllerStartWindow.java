package nsu.panova.start;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerStartWindow {
    private final ModelStartWindow startModel = new ModelStartWindow();
    @FXML private TextField place;

    @FXML
    private void onSearchClick() {
        if (place.getLength() != 0) {
            String userPlace = place.getText();

            StringBuilder newPlace = new StringBuilder();
            String[] words = userPlace.split("\\s");
            for(String i: words) {
                newPlace.append(String.format("%s%s", i, "%20"));
            }

            startModel.setPlace(userPlace);
            startModel.setRequestString(newPlace.toString());
            startModel.work();
        }
    }

    @FXML
    private void enterPlace() {
        onSearchClick();
    }

}

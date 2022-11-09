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
            startModel.setPlace(userPlace);
            startModel.work();
        }
    }

    @FXML
    private void enterPlace() {
        onSearchClick();
    }

}

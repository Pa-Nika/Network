package nsu.panova.listPlaces;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lombok.Setter;
import nsu.panova.start.place.Hits;
import nsu.panova.start.place.Place;

import java.util.concurrent.CompletableFuture;

public class ControllerPlaces {
    @FXML private Label placeName;
    @FXML private ListView<String>  listView;
    private CompletableFuture<Place> place;
    @Setter private ModelPlaceWindow modelPlaceWindow;
    private Boolean noInfo = true;
    private int countIndex = 0;

    public void setUserPlace(String place) {
        placeName.setText(place);
    }

    public void setPlace(CompletableFuture<Place> place_) {
        place = place_;
        CompletableFuture.supplyAsync(() -> place
                .thenAcceptAsync(place -> {
                    if (place.getHits().isEmpty()) {
                        listView.getItems().add("No information...");
                        noInfo = true;
                    } else  {
                        for(Hits i: place.getHits()) {
                            listView.getItems().add(i.toString());
                            countIndex++;
                        }
                        noInfo = false;
                    }
                }));

        listView.refresh();
    }

    @FXML
    public void onMouseClick() {
        if (noInfo) {
            return;
        }

        int index = listView.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return;
        }
        listView.getSelectionModel().clearSelection(index);

        modelPlaceWindow.setPlace(place);
        modelPlaceWindow.setIndex(index);
        modelPlaceWindow.work();
    }
}

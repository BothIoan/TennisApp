package layer_presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import layer_presentation.util.Descriptions.Description;

import java.util.ArrayList;

public class ControllerViewSets implements ControllerDescription{

    @FXML
    public Label scoreGames ;


    public void loadValues(Description setDescription) {
        scoreGames.setText(setDescription.getData1());
    }
}

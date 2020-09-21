package layer_presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import layer_presentation.util.Descriptions.Description;

public class ControllerIncrement implements ControllerDescription{

    int CallerId;
    @FXML
    private Button inc;

    @FXML
    private void initialize(){
        inc.setOnAction(event->Match_PresentationToBusines.incrementMatch(CallerId));
    }

    @Override
    public void loadValues(Description description) {
        CallerId = Integer.parseInt(description.getData1());
    }
}

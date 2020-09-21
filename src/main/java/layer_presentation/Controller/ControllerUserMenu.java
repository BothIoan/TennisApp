package layer_presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javassist.runtime.Desc;
import layer_presentation.GUI;
import layer_presentation.util.Descriptions.Description;

import java.io.IOException;

import static layer_presentation.GUI.*;

public class ControllerUserMenu implements ControllerDescription
{
    Description description;
    @FXML
    private Button viewMatches;
    @FXML
    private Button update;

    @FXML
    private void initialize(){
        viewMatches.setOnAction(event->
        {
            try {
                changeScene("ChooseMatch.fxml");
            } catch (IOException ignored) {}
        });
        update.setOnAction(event -> {
            try {
                changeSceneDescription("Increment.fxml", description,new ControllerIncrement());
            } catch (IOException ex) {System.out.println("seFute");}
        });
    }
    @Override
    public void loadValues(Description description) {
    this.description=description;

    }
}

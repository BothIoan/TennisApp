package layer_presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import layer_presentation.util.Bridge.User_PresentationToBusines;

public class ControllerCreatePlayer {
    @FXML
    private   TextField name;
    @FXML
    private TextField mail;
    @FXML
    private TextField password;

    public void sendData(){
        User_PresentationToBusines.createNewUser(name.getText(),mail.getText(),password.getText());
    }
}

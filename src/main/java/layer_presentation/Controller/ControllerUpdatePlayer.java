package layer_presentation.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import layer_business.Bridge.User_BusinesToPresentation;
import layer_presentation.util.Bridge.User_PresentationToBusines;
import layer_presentation.util.ModifiedMenuItems.UserDTO3MenuItem;

import java.util.ArrayList;

public class ControllerUpdatePlayer {

    int choosenId;

    @FXML
    private MenuButton choosePlayer;

    @FXML
    private TextField name;

    @FXML
    private TextField mail;

    @FXML
    private TextField password;

    @FXML
    private void initialize(){
        ArrayList<UserDTO3MenuItem> userDTO3MenuItems = User_BusinesToPresentation.getAllInfo();
        userDTO3MenuItems.forEach(x->{
            x.setOnAction(event -> {
                choosenId = x.getIdUser();
                name.setPromptText(x.getName());
                mail.setPromptText(x.getMail());
                password.setPromptText(x.getPassword());
                choosePlayer.setText(x.getName());
            });
            x.setText(x.getName());
        });
        choosePlayer.getItems().addAll(userDTO3MenuItems);
    }

    @FXML
    void sendData() {
        String nameToSend,mailToSend,passToSend;

    if(name.getText().isBlank())nameToSend= name.getPromptText(); else  nameToSend= name.getText();
    if(mail.getText().isBlank())mailToSend= mail.getPromptText(); else mailToSend = mail.getText();
    if(password.getText().isBlank())passToSend= password.getPromptText(); else  passToSend = password.getText();
    User_PresentationToBusines.updateUser(choosenId,nameToSend,mailToSend,passToSend);
    }

}

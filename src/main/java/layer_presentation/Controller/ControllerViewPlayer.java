package layer_presentation.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import layer_business.Bridge.User_BusinesToPresentation;
import layer_presentation.util.ModifiedMenuItems.UserDTO2MenuItem;

import java.util.ArrayList;

public class ControllerViewPlayer {

    @FXML
    private Label name ;

    @FXML
    private Label mail;

    @FXML
    private Label password;

    @FXML
    private MenuButton choosePlayer;

    @FXML
    private void initialize(){
        ArrayList<UserDTO2MenuItem> userDTO2MenuItems = User_BusinesToPresentation.getAllStrings();
        userDTO2MenuItems.forEach(x-> {
                    x.setOnAction(event -> {
                        choosePlayer.setText(x.getName());
                        name.setText(x.getName());
                        mail.setText(x.getMail());
                        password.setText(x.getPassword());
                    });
                    x.setText(x.getName());
                });
            choosePlayer.getItems().addAll(userDTO2MenuItems);
    }

}

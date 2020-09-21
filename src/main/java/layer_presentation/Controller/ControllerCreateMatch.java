package layer_presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import layer_business.Bridge.User_BusinesToPresentation;
import layer_presentation.util.AlertBox;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import layer_presentation.util.ModifiedMenuItems.UserDTOMenuItem;

import java.util.ArrayList;

public class ControllerCreateMatch {
    int idP1 = 0;
    int idP2 = 0;
    @FXML
    private MenuButton player1;

    @FXML
    private MenuButton player2;

    @FXML
    void initialize(){
        ArrayList<UserDTOMenuItem> items = User_BusinesToPresentation.getIdNames();
        ArrayList<UserDTOMenuItem> items2 = User_BusinesToPresentation.getIdNames();
        items.forEach((x)-> {x.setOnAction(event -> {
            player1.setText(x.getText());
            idP1 = x.getIdUser();
        });x.setText(x.getName());});
        items2.forEach((x)-> {x.setOnAction(event -> {
            player2.setText(x.getName());
            idP2 = x.getIdUser();
        });x.setText(x.getName());});
        player1.getItems().addAll(items);
        player2.getItems().addAll(items2);
    }

    @FXML
    void sendData() {
        AlertBox.display("!",Match_PresentationToBusines.createNewMatch(idP1,idP2));

    }

}

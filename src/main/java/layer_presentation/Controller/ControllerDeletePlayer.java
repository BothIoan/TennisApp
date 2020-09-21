package layer_presentation.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import layer_business.Bridge.User_BusinesToPresentation;
import layer_presentation.util.Bridge.User_PresentationToBusines;
import layer_presentation.util.ModifiedMenuItems.UserDTOMenuItem;

import java.util.ArrayList;

public class ControllerDeletePlayer {
    int chosenID;
    @FXML
    private SplitMenuButton chooseDelete;


    @FXML
    private void initialize(){
        ArrayList<UserDTOMenuItem> userDTOMenuItems = User_BusinesToPresentation.getIdNames();
        userDTOMenuItems.forEach(x->{x.setOnAction(event -> {
            chosenID=x.getIdUser();
            chooseDelete.setText(x.getName());
        });x.setText(x.getName());});
        chooseDelete.getItems().addAll(userDTOMenuItems);
    }

    @FXML
    void delete() {
        User_PresentationToBusines.deleteUser(chosenID);
    }


}

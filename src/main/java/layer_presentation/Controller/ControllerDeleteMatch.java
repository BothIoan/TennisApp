package layer_presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import layer_business.Bridge.Match_BusinesToPresentation;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import layer_presentation.util.ModifiedMenuItems.MatchDTOMenuItem;

import java.util.ArrayList;

public class ControllerDeleteMatch {
    private ArrayList<MatchDTOMenuItem> menuItems;
    private int chosenID;

    @FXML
    private SplitMenuButton chooseDelete;
    @FXML
    void initialize(){
        menuItems = Match_BusinesToPresentation.getTennisMatchNameId();
        menuItems.forEach(x->{x.setOnAction(event -> {
            chosenID = x.getIdMatch();
            chooseDelete.setText(x.getNameMatch());
        });x.setText(x.getNameMatch());});
        chooseDelete.getItems().addAll(menuItems);
    }
    @FXML
    void delete() {
        Match_PresentationToBusines.deleteMatch(chosenID);
    }

}

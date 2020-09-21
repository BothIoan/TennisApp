package layer_presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import layer_business.Bridge.Match_BusinesToPresentation;
import layer_presentation.GUI;
import layer_presentation.util.Descriptions.SetDescription1;
import layer_presentation.util.ModifiedMenuItems.MatchDTO2MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerChooseMatch {
    int chosenID;
    String scoreSetlabel;
    ArrayList<String> sets;
    @FXML
    public SplitMenuButton chooseMatchButton;

    @FXML
    private Label scoreSetsLabel;

    @FXML
    private ButtonBar set5Full;

    @FXML
    private Label set5;

    @FXML
    private ButtonBar set4Full;

    @FXML
    private Label set4;

    @FXML
    private ButtonBar set3Full;

    @FXML
    private Label set3;

    @FXML
    private ButtonBar set2Full;

    @FXML
    private Label set2;

    @FXML
    private ButtonBar set1Full;

    @FXML
    private Label set1;

    @FXML
    private Label MatchName;

    @FXML
    void initialize() {

        ArrayList<MatchDTO2MenuItem> menuItems = Match_BusinesToPresentation.getTennisMatch();
        menuItems.forEach(x -> {
            x.setOnAction(event -> {
                chosenID = x.getIdMatch();
                scoreSetlabel =x.getP1MatchScore()+" - "+x.getP2MatchScore();
                chooseMatchButton.setText(x.getNameMatch());
                MatchName.setText(x.getNameMatch());
                sets = Match_BusinesToPresentation.getSets(chosenID);
            });
            x.setText(x.getNameMatch());
        });
        chooseMatchButton.getItems().addAll(menuItems);
    }

    private boolean assignLabel(int i,Label label){
        if (i < sets.size()){
            label.setText("set"+i+1+": " + sets.get(i));
            return true;
        }
        return false;
    }
    //replace this all
    public void pressButton() {
        scoreSetsLabel.setText("MatchScore: "+scoreSetlabel);
        if(assignLabel(0,set1)){set1Full.setVisible(true);}
        if(assignLabel(1,set2)){set2Full.setVisible(true);}
        if(assignLabel(2,set3)){set3Full.setVisible(true);}
        if(assignLabel(3,set4)){set4Full.setVisible(true);}
        if(assignLabel(4,set5)){set5Full.setVisible(true);}
    }


    public void change(int id)throws IOException{
        String description = Match_BusinesToPresentation.getGames(chosenID,id);
        SetDescription1 setDescription1 = new SetDescription1();
        setDescription1.setData(description);
        ControllerViewSets controllerViewSets =new ControllerViewSets();
        GUI.changeSceneDescription("ViewSets.fxml",setDescription1, controllerViewSets);
    }
    @FXML
    void displaySet1() throws IOException{
        change(0);
    }

    @FXML
    void displaySet2() throws IOException{
        change(1);
    }

    @FXML
    void displaySet3() throws IOException{
        change(2);
    }

    @FXML
    void displaySet4() throws IOException{
        change(3);
    }

    @FXML
    void displaySet5() throws IOException{
        change(4);
    }
}


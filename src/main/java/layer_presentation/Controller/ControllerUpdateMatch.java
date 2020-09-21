package layer_presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import layer_business.Bridge.Match_BusinesToPresentation;
import layer_presentation.GUI;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import layer_presentation.util.Descriptions.SetDescription2;
import layer_presentation.util.ModifiedMenuItems.MatchDTO2MenuItem;
import layer_presentation.util.ModifiedMenuItems.SetDTO;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerUpdateMatch {
    int chosenID;
    String scoreSetlabel;
    ArrayList<String> sets;
    ArrayList<SetDTO> gamesS1;
    ArrayList<SetDTO> gamesS2;
    ArrayList<SetDTO> gamesS3;
    ArrayList<SetDTO> gamesS4;
    ArrayList<SetDTO> gamesS5;
    boolean existsFlags[] = {false,false,false,false,false};
    @FXML
    private Button updateButton;

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
        updateButton.setOnAction(event -> {
        System.out.println(tryUpdate());
        });

    }

    private String tryUpdate(){
        boolean integrityFlag= true;
        if(existsFlags[0]&&gamesS1.isEmpty()){existsFlags[0]=false;gamesS1=null;}
        if(existsFlags[1]&&gamesS2.isEmpty()){existsFlags[1]=false;gamesS2=null;}
        if(existsFlags[2]&&gamesS3.isEmpty()){existsFlags[2]=false;gamesS3=null;}
        if(existsFlags[3]&&gamesS4.isEmpty()){existsFlags[3]=false;gamesS4=null;}
        if(existsFlags[4]&&gamesS5.isEmpty()){existsFlags[4]=false;gamesS5=null;}
        for(int i=0;i<=3;i++){
            if(!existsFlags[i]&&existsFlags[i+1]){integrityFlag=false;break;}
        }
        if (!integrityFlag) return "For a new set to be added all previous sets must exist";
        ArrayList<ArrayList<SetDTO>> updateThis = new ArrayList<>();
        if (existsFlags[0])updateThis.add(gamesS1);
        if (existsFlags[1])updateThis.add(gamesS2);
        if (existsFlags[2])updateThis.add(gamesS3);
        if (existsFlags[3])updateThis.add(gamesS4);
        if (existsFlags[4])updateThis.add(gamesS5);
        return Match_PresentationToBusines.updateMatch(updateThis,chosenID);
    }

    private boolean assignLabel(int i,Label label){
        if (i < sets.size()){
            label.setText("set" +i+1+": "+ sets.get(i));
            return true;
         }
        else label.setText("set"+i+1+": ");
        return false;
    }

    public void pressButton() {
        for(boolean b : existsFlags) b=false;
        scoreSetsLabel.setText(scoreSetsLabel.getText()+scoreSetlabel);
        if(assignLabel(0,set1)){gamesS1 = Match_BusinesToPresentation.getGames2(chosenID,0);existsFlags[0]=true;}
        if(assignLabel(1,set2)){gamesS2 = Match_BusinesToPresentation.getGames2(chosenID,1);existsFlags[1]=true;}
        if(assignLabel(2,set3)){gamesS3 = Match_BusinesToPresentation.getGames2(chosenID,2);existsFlags[2]=true;}
        if(assignLabel(3,set4)){gamesS4 = Match_BusinesToPresentation.getGames2(chosenID,3);existsFlags[3]=true;}
        if(assignLabel(4,set5)){gamesS5 = Match_BusinesToPresentation.getGames2(chosenID,4);existsFlags[4]=true;}
        set1Full.setVisible(true);
        set2Full.setVisible(true);
        set3Full.setVisible(true);
        set4Full.setVisible(true);
        set5Full.setVisible(true);
    }

    public void change( ArrayList<SetDTO> games)throws IOException{

        SetDescription2 gamesDescription= new SetDescription2();
        gamesDescription.setData(games);
        ControllerUpdateSets controllerUpdateSets =new ControllerUpdateSets();
        GUI.changeSceneDescription("UpdateSets.fxml",gamesDescription, controllerUpdateSets);
    }


    @FXML
    void displaySet1() throws IOException{
       if(!existsFlags[0]) {gamesS1 = new ArrayList<>();gamesS1.add(SetDTO.builder().p2Score("0").p1Score("0").build());existsFlags[0]=true;}
        change(gamesS1);
    }

    @FXML
    void displaySet2() throws IOException{
        if(!existsFlags[1]) {gamesS2 = new ArrayList<>();gamesS2.add(SetDTO.builder().p2Score("0").p1Score("0").build());existsFlags[1]=true;}
        change(gamesS2);
    }

    @FXML
    void displaySet3() throws IOException{
        if(!existsFlags[2]) {gamesS3 = new ArrayList<>();gamesS3.add(SetDTO.builder().p2Score("0").p1Score("0").build());existsFlags[2]=true;}
        change(gamesS3);
    }

    @FXML
    void displaySet4() throws IOException{
        if(!existsFlags[3]) {gamesS4 = new ArrayList<>();gamesS4.add(SetDTO.builder().p2Score("0").p1Score("0").build());existsFlags[3]=true;}
        change(gamesS4);
    }

    @FXML
    void displaySet5() throws IOException{
        if(!existsFlags[4]) {gamesS5 = new ArrayList<>();gamesS5.add(SetDTO.builder().p2Score("0").p1Score("0").build());existsFlags[4]=true;}
        change(gamesS5);
    }
}


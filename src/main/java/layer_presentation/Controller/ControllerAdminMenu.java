package layer_presentation.Controller;

import layer_presentation.GUI;

import java.io.IOException;

public class ControllerAdminMenu {

    public void addPlayer()throws IOException {
        GUI.changeScene("CreatePlayer.fxml");
    }
    public void readPlayer()throws IOException{
        GUI.changeScene("ViewPlayer.fxml");
    }
    public void updatePlayer()throws IOException{
        GUI.changeScene("UpdatePlayer.fxml");
    }
    public void deletePlayer()throws IOException{
        GUI.changeScene("DeletePlayer.fxml");
    }
    public void addMatch()throws IOException{
        GUI.changeScene("CreateMatch.fxml");
    }
    public void readMatch()throws IOException{
        GUI.changeScene("ChooseMatch.fxml");
    }
    public void updateMatch()throws IOException{
        GUI.changeScene("UpdateMatch.fxml");
    }
    public void deleteMatch()throws IOException{
        GUI.changeScene("DeleteMatch.fxml");
    }
}

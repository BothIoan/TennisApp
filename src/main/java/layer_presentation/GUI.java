package layer_presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import layer_data_access.repo.BridgeDataToLogic.Match_DataToLogic;
import layer_data_access.repo.BridgeDataToLogic.User_DataToLogic;
import layer_data_access.repo.GenericRepo;
import layer_presentation.Controller.ControllerDescription;
import layer_presentation.util.Descriptions.Description;
import model.User;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;


public class GUI extends Application //implements EventHandler<ActionEvent>
{
	private Stage window;
	
    public static void main( String[] args )
    {
		User_DataToLogic.InitalizeApplicationUsers();
		Match_DataToLogic.initializeApplicationMatch();
    	launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
    	ClassLoader classLoader = GUI.class.getClassLoader();
		Parent root = FXMLLoader.load(classLoader.getResource("login.fxml"));
		window=primaryStage;
		Scene mainScene = new Scene(root);
		mainScene.getStylesheets().add("Style.css");
		window.setScene(mainScene);
		window.setTitle("Assignment1");
		window.show();
	}
		

	public static Scene changeScene(String fxml) throws IOException{
		ClassLoader classLoader = GUI.class.getClassLoader();
		Parent pane = FXMLLoader.load(classLoader.getResource(fxml));
		
		Stage stage = new Stage();
        Scene newScene = new Scene(pane);
        newScene.getStylesheets().add("Style.css");
		stage.setScene(newScene);
		stage.show();
		
		return newScene;
	}

	public static Scene changeSceneDescription (String fxml, Description description, ControllerDescription controllerDescription)throws IOException{
		ClassLoader classLoader = GUI.class.getClassLoader();
		FXMLLoader loader = new FXMLLoader(classLoader.getResource(fxml));
		loader.setController(controllerDescription);

		Parent pane = loader.load();
		controllerDescription.loadValues(description);
		Stage stage = new Stage();
		Scene newScene = new Scene(pane);
		newScene.getStylesheets().add("Style.css");
		stage.setScene(newScene);
		stage.show();

		return newScene;
	}
}

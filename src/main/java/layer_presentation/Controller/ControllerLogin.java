package layer_presentation.Controller;

import dto.AdminDTO;
import dto.UserDTO;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import layer_business.Functions;
import layer_presentation.GUI;
import layer_presentation.util.AlertBox;
import layer_presentation.util.Descriptions.UserDescription;

import java.io.IOException;

public class ControllerLogin {
	
	@FXML
	public Button loginButton;
	
	@FXML
	public TextField mail;
	
	@FXML
	public TextField password;

	@FXML
	private CheckBox checkbox;

	public void login() throws IOException {
		Functions f = new Functions();

		if(mail.getText().equals("") || password.getText().equals(""))
			//tre sa bag pe el chestii
			AlertBox.display("No input", "You forgot to write your mail/password");
		else {
		}

		if(checkbox.isSelected()){
			AdminDTO userDTO = f.loginAdmin(mail.getText(), password.getText());
			if (userDTO==null) return;
			System.out.println(userDTO);
			Scene scene = GUI.changeScene("AdminMenu.fxml");
		}
		else{
				UserDTO userdto = f.loginPlayer(mail.getText(), password.getText());
				if(userdto==null)return;
			System.out.println(userdto);
			Scene scene = GUI.changeSceneDescription("UserMenu.fxml",UserDescription.builder().idUser(Integer.toString(userdto.getId())).build(),new ControllerUserMenu());
		}

    }
}

package devops.view;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import devops.App;
import devops.utils.FXRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

public class LoginWindow {

	@FXML
	private JFXButton login;

	@FXML
	private JFXTextField username;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXButton signUp;

	@FXML
	void handleLogin(ActionEvent event) throws IOException{
        FXRouter.register("main", App.MAIN_RESOURCE);
        FXRouter.setAnimationType("fade", 5);
        FXRouter.show("main");
		
	}

	@FXML
	void handleSignUp(ActionEvent event) {

	}

	
}

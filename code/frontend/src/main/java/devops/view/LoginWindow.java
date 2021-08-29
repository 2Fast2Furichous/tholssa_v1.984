package devops.view;

import java.io.IOException;

import javax.swing.plaf.RootPaneUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
	void handleLogin(ActionEvent event) {
		
	}

	@FXML
	void handleSignUp(ActionEvent event) {

	}

	public void goToMainWindow() throws IOException{
		AnchorPane rootPane = new AnchorPane();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
		rootPane.getChildren().setAll(pane);
	}

}

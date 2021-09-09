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
import devops.model.implementations.Credential;
import devops.model.implementations.ServiceResponse;
import devops.model.implementations.UserAccount;
import devops.network.interfaces.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * The login window controller.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class LoginWindow {

	private static final String CREATE_ACCOUNT_RESOURCE = "/devops/view/CreateAccountWindow.fxml";

	@FXML
	private JFXButton login;

	@FXML
	private JFXTextField username;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXButton signUp;


	public void handleLogin(ActionEvent event) {
		UserService service = App.getUserService();
		String usernameText = this.username.getText();
		String passwordText = this.password.getText();

		try {
			Credential loginCredentials = new Credential(usernameText, passwordText);
			ServiceResponse response = service.login(loginCredentials);
			UserAccount userAccount = (UserAccount) response.getData();
			FXRouter.show("mainUI", userAccount);
		} catch (Exception e) {
			// TODO Show error dialog
		}
    
	}

	@FXML
	public void handleSignUp(ActionEvent event) {
		try {
			FXRouter.register("createAccount", CREATE_ACCOUNT_RESOURCE);
			FXRouter.setAnimationType("fade", 300);
			FXRouter.show("createAccount");
		} catch (Exception e) {
			// swallow catch
		}
	}

	
}

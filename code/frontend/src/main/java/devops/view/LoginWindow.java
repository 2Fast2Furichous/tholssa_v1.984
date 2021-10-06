package devops.view;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import devops.App;
import devops.utils.FXRouter;
import javafx.fxml.FXML;
import devops.model.implementations.Credential;
import devops.model.implementations.ServiceResponse;
import devops.model.implementations.UserAccount;
import devops.network.interfaces.UserService;
import devops.utils.GuiCommands;
import javafx.event.ActionEvent;

/**
 * The login window controller.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
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
	public void initialize(){
		this.setupListeners();
	}

	private void setupListeners(){
		this.login.disableProperty().bind(this.username.textProperty().isEmpty().or(this.password.textProperty().isEmpty()));
	}

	public void handleLogin(ActionEvent event) {
		UserService service = App.getUserService();
		String usernameText = this.username.getText();
		String passwordText = this.password.getText();
		

		try {
			Credential loginCredentials = new Credential(passwordText, usernameText);
			ServiceResponse response = service.login(loginCredentials);
			if (response.getMessage().equals("error")){
				GuiCommands.showErrorDialog((String)response.getData());
			} else {
				UserAccount userAccount = (UserAccount) response.getData();
				FXRouter.show("main", userAccount);
			}
		} catch (Exception e) {
			GuiCommands.showErrorDialog(e.getMessage());
		}
    
	}

	@FXML
	public void handleSignUp(ActionEvent event) {
		try {
			FXRouter.show("createAccount");
		} catch (Exception e) {
			// swallow catch
		}
	}

	
}

package devops.view;

import java.io.IOException;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import devops.App;
import devops.model.implementations.Credential;
import devops.model.implementations.ServiceResponse;
import devops.model.implementations.UserAccount;
import devops.network.interfaces.UserService;
import devops.utils.FXRouter;
import devops.utils.GuiCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

/**
 * Code Behind for the Create Account Window
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class CreateAccountWindow {
    @FXML
    private GridPane createAccountWindowPane;

    @FXML
    private JFXTextField usernameTextField;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXTextField firstNameTextField;

    @FXML
    private JFXTextField lastNameTextField;

    @FXML
    private JFXTextField phoneNumberTextField;

    @FXML
    private DatePicker dateOfBirthDatePicker;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton createAccountButton;

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        try {
            FXRouter.setAnimationType("fade", 300);
            FXRouter.show("login");
        } catch (Exception e) {
            // Swallow catch
        }

    }

    @FXML
    void handleCreateAccount(ActionEvent event) {
        UserService service = App.getUserService();
        String firstName = this.firstNameTextField.textProperty().getValue();
        String lastName = this.lastNameTextField.textProperty().getValue();
        LocalDate dateOfBirth = this.dateOfBirthDatePicker.getValue();
        String phoneNumber = this.phoneNumberTextField.textProperty().getValue();
        String username = this.usernameTextField.textProperty().getValue();
        String password = this.passwordTextField.textProperty().getValue();

        try {
            Credential credential = new Credential(username, password);
            UserAccount account = new UserAccount(firstName, lastName, dateOfBirth, phoneNumber);

            ServiceResponse response =  service.createAccount(account, credential);
            if (response.getMessage().equals("error")){
				GuiCommands.showErrorDialog((String)response.getData());
			} else {
                UserAccount newUser = (UserAccount) response.getData();
                FXRouter.show("mainUI", newUser);
            }

           

        } catch (Exception e) {
            GuiCommands.showErrorDialog(e.getMessage());
        }
    }



}

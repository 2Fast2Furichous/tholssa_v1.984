package devops.view;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import devops.utils.FXRouter;
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
        } catch(Exception e) {
            //Swallow catch
        }

    }

    @FXML
    void handleCreateAccount(ActionEvent event) {
        // TODO Add User Service linking for Create Account. Add in Error Handling for Later when Implementation Occurs.
    }

}

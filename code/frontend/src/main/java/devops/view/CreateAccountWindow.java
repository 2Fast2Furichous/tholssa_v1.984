package devops.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;


/**
 * Code Behind for the Create Account Window
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class CreateAccountWindow {
    //TODO Add in Listeners for Input Validation with Error Dialog.
    @FXML
    private GridPane createAccountWindowPane;

    @FXML
    private JFXTextField usernameTextField;

    @FXML
    private JFXTextField passwordTextField;

    @FXML
    private JFXTextField firstNameTextField;

    @FXML
    private JFXTextField lastNameTextField;

    @FXML
    private JFXTextField phoneNumberTextField;

    @FXML
    private JFXDatePicker dateOfBirthDatePicker;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton createAccountButton;

    @FXML
    void handleCancel(ActionEvent event) {
            //TODO Transfer back to Login Screen and Clear Fields.
    }

    @FXML
    void handleCreateAccount(ActionEvent event) {
        //TODO Add User Service linking for Create Account.
    }

}


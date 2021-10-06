package devops.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

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

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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

    private final String PHONE_NUMBER_FORMAT = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";


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
    public void initialize() {
        this.setupListeners();
    }

    private void setupListeners() {
        this.createAccountButton.disableProperty().bind(this.firstNameTextField.textProperty().isEmpty()
                .or(this.lastNameTextField.textProperty().isEmpty()).or(this.usernameTextField.textProperty().isEmpty())
                .or(this.passwordTextField.textProperty().isEmpty())
                .or(this.textPropertyBindingPattern(this.phoneNumberTextField, Pattern.compile(PHONE_NUMBER_FORMAT))));
    }

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

            ServiceResponse response = service.createAccount(account, credential);
            if (response.getMessage().equals("error")) {
                GuiCommands.showErrorDialog((String) response.getData());
            } else {
                UserAccount newUser = (UserAccount) response.getData();
                FXRouter.show("main", newUser);
            }

        } catch (Exception e) {
            GuiCommands.showErrorDialog(e.getMessage());
        }
    }

    private BooleanBinding textPropertyBindingPattern(JFXTextField textField, Pattern pattern) {
        return Bindings.createBooleanBinding(() -> !pattern.matcher(textField.getText()).matches(),
                textField.textProperty());
    }

}

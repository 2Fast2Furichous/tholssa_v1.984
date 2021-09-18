package devops.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * A series of methods for the GUI that are repeated often.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class GuiCommands {
    
    /**
     * Shows an Error Dialog with a specified content message.
     * 
     * @precondition none
     * @postcondition none
     * @param content the content of the message
     */
    public static void showErrorDialog(String content) {
        Alert error = new Alert(AlertType.ERROR);

        error.setTitle("Error");
        error.setContentText(content);

        error.showAndWait();
    }
}

package devops;

import java.io.IOException;

import devops.utils.FXRouter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Entry point for the program
 *
 * @author Special Topics - DevOps
 * @version Fall 2021
 */
public class App extends Application {

    private static final String WINDOW_TITLE = "THOLSSA v1.984";
    private static final String LOGIN_RESOURCE = "/devops/view/LoginWindow.fxml";
    private static final String MAIN_RESOURCE = "/devops/view/MainUI.fxml";
    /**
     * JavaFX entry point.
     *
     * @precondition none
     * @postcondition none
     *
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXRouter.initialize(this, primaryStage, App.WINDOW_TITLE);
        FXRouter.register("login", App.LOGIN_RESOURCE);
        FXRouter.setAnimationType("fade", 5);
        FXRouter.show("login");
    }

    /**
     * Primary Java entry point.
     *
     * @precondition none
     * @postcondition none
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        App.launch(args);
    }

}

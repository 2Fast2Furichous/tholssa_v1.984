package devops;

import java.io.IOException;

import devops.network.interfaces.UserService;
import devops.network.implementations.JeroUserService;
import devops.utils.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point for the program
 *
 * @author Special Topics - DevOps
 * @version Fall 2021
 */
public class App extends Application {

    private static UserService userService;
    private static final String WINDOW_TITLE = "THOLSSA v1.984";
    private static final String LOGIN_RESOURCE = "/devops/view/LoginWindow.fxml";
    public static final String MAIN_RESOURCE = "/devops/view/MainWindow.fxml";
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
        FXRouter.setAnimationType("fade", 300.0);
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
        App.userService = new JeroUserService();
        App.launch(args);
    }

    /**
     * Returns the applications's user service
     * 
     * @return The applications's user service
     */
    public static UserService getUserService() {
        return App.userService;
    }

}

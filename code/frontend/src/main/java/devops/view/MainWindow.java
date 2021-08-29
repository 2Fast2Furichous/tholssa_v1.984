package devops.view;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class MainWindow {

    private FXMLLoader loader;

    public MainWindow(){
        this.loader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
    }

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton remove;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton cancel;

    @FXML
    void addToTree(ActionEvent event){
        
    }

    @FXML
    void removeFromTree(ActionEvent event){

    }

    @FXML
    void saveTree(ActionEvent event){

    }

    @FXML
    void cancelFromTree(ActionEvent event){

    }

    
}

package devops.view;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class MainWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane tholssaGraph;

    @FXML
    private JFXButton familyButton;

    @FXML
    private JFXButton friendButton;

    @FXML
    private JFXButton buisnessButton;

    @FXML
    private JFXButton spouseButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton removeButton;

    @FXML
    private JFXButton addButton;

     private static final String BUISNESS_NODE = "buisness";
     private static final String FAMILY_NODE = "family";
     private static final String FRIEND_NODE = "friend";
     private static final String SPOUSE_NODE = "spouse";
    
    @FXML
    void addBuisnessNode(ActionEvent event) {
        JFXButton buisnessNode = new JFXButton();
        buisnessNode.setText("buisness");
        buisnessNode.setStyle("-fx-background-color: #16ae58;");
        buisnessNode.setTranslateX(100);
        buisnessNode.setTranslateY(100);
        this.tholssaGraph.getChildren().add(buisnessNode);
        mousePressed(buisnessNode, "buisness");
        mouseDragged(buisnessNode);
    }


    private void mousePressed(JFXButton currentNode, String type) {
        currentNode.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                if(mouseEvent.isSecondaryButtonDown()){
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem removeMenuItem = new MenuItem("Remove");
                    MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
                    MenuItem  addInformationMenuItem = new MenuItem("Add Information");

                    contextMenu.getItems().add(removeMenuItem);
                    contextMenu.getItems().add(addEdgeMenuItem);
                    contextMenu.getItems().add(addInformationMenuItem);
                    contextMenu.show(tholssaGraph, mouseEvent.getSceneX()+520, mouseEvent.getSceneY()+100);

                    removeMenuItem.setOnAction((event) ->{
                        tholssaGraph.getChildren().remove(currentNode);
                        contextMenu.hide();
                    });

                    addEdgeMenuItem.setOnAction((event) ->{
                        if(type == BUISNESS_NODE){
                            addBuisnessNode(event);
                        }
                        if(type == FAMILY_NODE){
                            addFamilyNode(event);
                        }
                        if(type == FRIEND_NODE){
                            addFriendFamily(event);
                        }
                        if(type == SPOUSE_NODE){
                            addSpouseNode(event);
                        }
                        
                    });

                    addInformationMenuItem.setOnAction((event) ->{

                    });
                }
                if(mouseEvent.isPrimaryButtonDown()){
                   
                }
                else{
                    currentNode.setTranslateX(mouseEvent.getSceneX());
                    currentNode.setTranslateY(mouseEvent.getSceneY());
                }
               
            }
        });
    }


    private void mouseDragged(JFXButton currentNode) {
        currentNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                currentNode.setTranslateX(mouseEvent.getScreenX()- 500);
                currentNode.setTranslateY(mouseEvent.getScreenY()- 120);
            }
        });
    }


    @FXML
    void addFamilyNode(ActionEvent event) {
        JFXButton familyNode = new JFXButton();
        familyNode.setText("family");
        familyNode.setStyle("-fx-background-color: #16ae58;");
        familyNode.setTranslateX(100);
        familyNode.setTranslateY(150);
        this.tholssaGraph.getChildren().add(familyNode);

        // familyNode.setOnMousePressed(new EventHandler<MouseEvent>(){
        //     public void handle(MouseEvent mouseEvent){
        //         if(mouseEvent.isSecondaryButtonDown()){
        //             ContextMenu contextMenu = new ContextMenu();
        //             MenuItem removeMenuItem = new MenuItem("Remove");
        //             MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
        //             MenuItem  addInformationMenuItem = new MenuItem("Add Information");

        //             contextMenu.getItems().add(removeMenuItem);
        //             contextMenu.getItems().add(addEdgeMenuItem);
        //             contextMenu.getItems().add(addInformationMenuItem);
        //             contextMenu.show(tholssaGraph, mouseEvent.getSceneX()+350, mouseEvent.getSceneY()+100);
                   

        //             removeMenuItem.setOnAction((event) ->{
        //                 tholssaGraph.getChildren().remove(familyNode);
        //                 contextMenu.hide();
        //             });

        //             addEdgeMenuItem.setOnAction((event)->{
        //                 addFamilyNode(event);
        //             });
        //         }
        //         else{
        //             familyNode.setTranslateX(mouseEvent.getSceneX());
        //             familyNode.setTranslateY(mouseEvent.getSceneY());
        //         }
              
        //     }
        // });
        mousePressed(familyNode, "family");
        mouseDragged(familyNode);
       
    }

    @FXML
    void addFriendFamily(ActionEvent event) {
        JFXButton friendNode = new JFXButton();
        friendNode.setText("friend");
        friendNode.setStyle("-fx-background-color: #16ae58;");
        friendNode.setTranslateX(0);
        friendNode.setTranslateY(0);
        this.tholssaGraph.getChildren().add(friendNode);

        // friendNode.setOnMousePressed(new EventHandler<MouseEvent>(){
        //     public void handle(MouseEvent mouseEvent){
        //         if(mouseEvent.isSecondaryButtonDown()){
        //             ContextMenu contextMenu = new ContextMenu();
        //             MenuItem removeMenuItem = new MenuItem("Remove");
        //             MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
        //             MenuItem  addInformationMenuItem = new MenuItem("Add Information");

        //             contextMenu.getItems().add(removeMenuItem);
        //             contextMenu.getItems().add(addEdgeMenuItem);
        //             contextMenu.getItems().add(addInformationMenuItem);
        //             contextMenu.show(tholssaGraph, mouseEvent.getSceneX()+350, mouseEvent.getSceneY()+100);
                   

        //             removeMenuItem.setOnAction((event) ->{
        //                 tholssaGraph.getChildren().remove(friendNode);
        //                 contextMenu.hide();
        //             });

        //             addEdgeMenuItem.setOnAction((event)->{
        //                 addFriendFamily(event);
        //             });
        //         }
        //         else{
        //             friendNode.setTranslateX(mouseEvent.getSceneX());
        //             friendNode.setTranslateY(mouseEvent.getSceneY());
        //         }
               
        //     }
        // });
            mousePressed(friendNode, "friend");
            mouseDragged(friendNode);
    }

    @FXML
    void removeNode(ActionEvent event){
      
    }

    @FXML
    void saveGraph(ActionEvent event){
        for(Node currNode: this.tholssaGraph.getChildren()){
           JFXButton node = (JFXButton) currNode;
           node.getText();
           node.getTranslateX();
           node.getTranslateY();
        }
    }

    @FXML
    void addSpouseNode(ActionEvent event) {
        JFXButton spouseNode = new JFXButton();
        spouseNode.setText("spouse");
        spouseNode.setStyle("-fx-background-color: #16ae58;");
        spouseNode.setTranslateX(100);
        spouseNode.setTranslateY(180);
        this.tholssaGraph.getChildren().add(spouseNode);

        // spouseNode.setOnMousePressed(new EventHandler<MouseEvent>(){
        //     public void handle(MouseEvent mouseEvent){
        //         if(mouseEvent.isSecondaryButtonDown()){
        //             ContextMenu contextMenu = new ContextMenu();
        //             MenuItem removeMenuItem = new MenuItem("Remove");
        //             MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
        //             MenuItem  addInformationMenuItem = new MenuItem("Add Information");
                    
        //             contextMenu.getItems().add(removeMenuItem);
        //             contextMenu.getItems().add(addEdgeMenuItem);
        //             contextMenu.getItems().add(addInformationMenuItem);
        //             contextMenu.show(tholssaGraph, mouseEvent.getSceneX()+350, mouseEvent.getSceneY()+100);
                   
        //             removeMenuItem.setOnAction((event) ->{
        //                 tholssaGraph.getChildren().remove(spouseNode);
        //                 contextMenu.hide();
        //             });

        //             addEdgeMenuItem.setOnAction((event)->{
        //                 addSpouseNode(event);
        //             });
        //         }
        //         else{
        //             spouseNode.setTranslateX(mouseEvent.getSceneX());
        //             spouseNode.setTranslateY(mouseEvent.getSceneY());
        //         }
               
        //     }
        // });

        mousePressed(spouseNode, "spouse");
        mouseDragged(spouseNode);

    }

    @FXML
    void initialize() {
        assert tholssaGraph != null : "fx:id=\"tholssaGraph\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert familyButton != null : "fx:id=\"familyButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert friendButton != null : "fx:id=\"friendButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert buisnessButton != null : "fx:id=\"buisnessButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert spouseButton != null : "fx:id=\"spouseButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }
}

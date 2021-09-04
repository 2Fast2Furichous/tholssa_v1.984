package devops.view;

import com.jfoenix.controls.JFXButton;

import java.beans.Visibility;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    
    @FXML
    void addBuisnessNode(ActionEvent event) {
        JFXButton buisnessNode = new JFXButton();
        buisnessNode.setText("buisness");
        buisnessNode.setStyle("-fx-background-color: lime;");
        buisnessNode.setTranslateX(100);
        buisnessNode.setTranslateY(100);
        this.tholssaGraph.getChildren().add(buisnessNode);
        buisnessNode.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                if(mouseEvent.isSecondaryButtonDown()){
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem removeMenuItem = new MenuItem("Remove");
                    MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
                    MenuItem  addInformationMenuItem = new MenuItem("Add Information");

                    contextMenu.getItems().add(removeMenuItem);
                    contextMenu.getItems().add(addEdgeMenuItem);
                    contextMenu.getItems().add(addInformationMenuItem);
                    contextMenu.show(tholssaGraph, mouseEvent.getSceneX()+550, mouseEvent.getSceneY()+100);

                    removeMenuItem.setOnAction((event) ->{
                        tholssaGraph.getChildren().remove(buisnessNode);
                        contextMenu.hide();
                    });

                    addEdgeMenuItem.setOnAction((event) ->{
                        Line line = new Line(mouseEvent.getSceneX(),mouseEvent.getSceneY(), mouseEvent.getSceneX()+100, mouseEvent.getSceneY()+100);
                        line.setTranslateX(mouseEvent.getSceneX()+100);
                        line.setTranslateY(mouseEvent.getSceneY()-50);
                        JFXButton buisnessNode = new JFXButton();
                        buisnessNode.setText("buisness");
                        buisnessNode.setStyle("-fx-background-color: lime;");
                        buisnessNode.setTranslateX(mouseEvent.getSceneX()+100);
                        buisnessNode.setTranslateY(mouseEvent.getSceneY()-50);
                        tholssaGraph.getChildren().add(buisnessNode);
                        contextMenu.hide();

                        buisnessNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
                            public void handle(MouseEvent mouseEvent){
                                buisnessNode.setTranslateX(mouseEvent.getScreenX()- 500);
                                buisnessNode.setTranslateY(mouseEvent.getScreenY()- 120);
                            }
                        });
                    
                    });

                    addInformationMenuItem.setOnAction((event) ->{

                    });
                    
                }
                if(mouseEvent.isPrimaryButtonDown()){
                    //buisnessNode.setText("hello");
                    //will create a new view that shows up here when it is just clicked
                    //it will take in the user specified person name, startdate...etc

                }
                else{
                    buisnessNode.setTranslateX(mouseEvent.getSceneX());
                    buisnessNode.setTranslateY(mouseEvent.getSceneY());
                }
               
            }
        });

        buisnessNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                buisnessNode.setTranslateX(mouseEvent.getScreenX()- 500);
                buisnessNode.setTranslateY(mouseEvent.getScreenY()- 120);
            }
        });
    }


    @FXML
    void addFamilyNode(ActionEvent event) {
        JFXButton familyNode = new JFXButton();
        familyNode.setText("family");
        familyNode.setStyle("-fx-background-color: lime;");
        familyNode.setTranslateX(100);
        familyNode.setTranslateY(150);
        this.tholssaGraph.getChildren().add(familyNode);

        familyNode.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                if(mouseEvent.isSecondaryButtonDown()){
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem removeMenuItem = new MenuItem("Remove");
                    MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
                    MenuItem  addInformationMenuItem = new MenuItem("Add Information");

                    contextMenu.getItems().add(removeMenuItem);
                    contextMenu.getItems().add(addEdgeMenuItem);
                    contextMenu.getItems().add(addInformationMenuItem);
                    contextMenu.show(tholssaGraph, mouseEvent.getSceneX()+350, mouseEvent.getSceneY()+100);
                   

                    removeMenuItem.setOnAction((event) ->{
                        tholssaGraph.getChildren().remove(familyNode);
                        contextMenu.hide();
                    });

                    addEdgeMenuItem.setOnAction((event)->{
                        JFXButton familyNode = new JFXButton();
                        familyNode.setText("family");
                        familyNode.setStyle("-fx-background-color: lime;");
                        familyNode.setTranslateX(mouseEvent.getSceneX()+100);
                        familyNode.setTranslateY(mouseEvent.getSceneY()-50);
                        tholssaGraph.getChildren().add(familyNode);
                        contextMenu.hide();

                        familyNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
                            public void handle(MouseEvent mouseEvent){
                                familyNode.setTranslateX(mouseEvent.getScreenX()- 500);
                                familyNode.setTranslateY(mouseEvent.getScreenY()- 120);
                            }
                        });
                    });
                }
                else{
                    familyNode.setTranslateX(mouseEvent.getSceneX());
                    familyNode.setTranslateY(mouseEvent.getSceneY());
                }
              
            }
        });

        familyNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                familyNode.setTranslateX(mouseEvent.getScreenX() -500);
                familyNode.setTranslateY(mouseEvent.getScreenY()- 120);
            }
        });
    }

    @FXML
    void addFriendFamily(ActionEvent event) {
        JFXButton friendNode = new JFXButton();
        friendNode.setText("friend");
        friendNode.setStyle("-fx-background-color: lime;");
        friendNode.setTranslateX(0);
        friendNode.setTranslateY(0);
        this.tholssaGraph.getChildren().add(friendNode);

        friendNode.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                if(mouseEvent.isSecondaryButtonDown()){
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem removeMenuItem = new MenuItem("Remove");
                    MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
                    MenuItem  addInformationMenuItem = new MenuItem("Add Information");

                    contextMenu.getItems().add(removeMenuItem);
                    contextMenu.getItems().add(addEdgeMenuItem);
                    contextMenu.getItems().add(addInformationMenuItem);
                    contextMenu.show(tholssaGraph, mouseEvent.getSceneX()+350, mouseEvent.getSceneY()+100);
                   

                    removeMenuItem.setOnAction((event) ->{
                        tholssaGraph.getChildren().remove(friendNode);
                        contextMenu.hide();
                    });

                    addEdgeMenuItem.setOnAction((event)->{
                        JFXButton friendNode = new JFXButton();
                        friendNode.setText("friend");
                        friendNode.setStyle("-fx-background-color: lime;");
                        friendNode.setTranslateX(mouseEvent.getSceneX()+100);
                        friendNode.setTranslateY(mouseEvent.getSceneY()-50);
                        tholssaGraph.getChildren().add(friendNode);
                        contextMenu.hide();

                        friendNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
                            public void handle(MouseEvent mouseEvent){
                                friendNode.setTranslateX(mouseEvent.getScreenX()- 500);
                                friendNode.setTranslateY(mouseEvent.getScreenY()- 120);
                            }
                        });
                    });
                }
                else{
                    friendNode.setTranslateX(mouseEvent.getSceneX());
                    friendNode.setTranslateY(mouseEvent.getSceneY());
                }
               
            }
        });

        friendNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                friendNode.setTranslateX(mouseEvent.getScreenX()-500);
                friendNode.setTranslateY(mouseEvent.getScreenY()-120);
            }
        });

    }

    @FXML
    void removeNode(ActionEvent event){
        ArrayList<Node> nodesToRemove = new ArrayList<Node>();
        for(Node currNode: this.tholssaGraph.getChildren()){
            //JFXButton node = (JFXButton) currNode;
            // currNode.setOnMouseClicked(new EventHandler<MouseEvent>(){
            //     public void handle(MouseEvent mouseEvent){
            //         if(mouseEvent.getClickCount() == 1 && mouseEvent.isSecondaryButtonDown()){
            //             nodesToRemove.add(currNode);
            //         }
            //     }
            // });
        
        }
        for(Node currNode: nodesToRemove){
            this.tholssaGraph.getChildren().remove(currNode);
            currNode.setTranslateX(-500);
            currNode.setTranslateY(-500);
            currNode.setDisable(true);
        }
        
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
        spouseNode.setStyle("-fx-background-color: lime;");
        spouseNode.setTranslateX(100);
        spouseNode.setTranslateY(180);
        this.tholssaGraph.getChildren().add(spouseNode);

        spouseNode.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                if(mouseEvent.isSecondaryButtonDown()){
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem removeMenuItem = new MenuItem("Remove");
                    MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
                    MenuItem  addInformationMenuItem = new MenuItem("Add Information");
                    contextMenu.getItems().add(removeMenuItem);
                    contextMenu.getItems().add(addEdgeMenuItem);
                    contextMenu.getItems().add(addInformationMenuItem);
                    contextMenu.show(tholssaGraph, mouseEvent.getSceneX()+350, mouseEvent.getSceneY()+100);
                   
                    removeMenuItem.setOnAction((event) ->{
                        tholssaGraph.getChildren().remove(spouseNode);
                        contextMenu.hide();
                    });

                    addEdgeMenuItem.setOnAction((event)->{
                        JFXButton spouseNode = new JFXButton();
                        spouseNode.setText("spouse");
                        spouseNode.setStyle("-fx-background-color: lime;");
                        spouseNode.setTranslateX(mouseEvent.getSceneX()+100);
                        spouseNode.setTranslateY(mouseEvent.getSceneY()-50);
                        tholssaGraph.getChildren().add(spouseNode);
                        contextMenu.hide();

                        spouseNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
                            public void handle(MouseEvent mouseEvent){
                                spouseNode.setTranslateX(mouseEvent.getScreenX()- 500);
                                spouseNode.setTranslateY(mouseEvent.getScreenY()- 120);
                            }
                        });
                    });
                }
                else{
                    spouseNode.setTranslateX(mouseEvent.getSceneX());
                    spouseNode.setTranslateY(mouseEvent.getSceneY());
                }
               
            }
        });

        spouseNode.setOnMouseDragged(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                spouseNode.setTranslateX(mouseEvent.getScreenX()-500);
                spouseNode.setTranslateY(mouseEvent.getScreenY()-120);
            }
        });

    }

    @FXML
    void initialize() {
        assert tholssaGraph != null : "fx:id=\"tholssaGraph\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert familyButton != null : "fx:id=\"familyButton\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert friendButton != null : "fx:id=\"friendButton\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert buisnessButton != null : "fx:id=\"buisnessButton\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert spouseButton != null : "fx:id=\"spouseButton\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'MainUI.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'MainUI.fxml'.";

    }
}

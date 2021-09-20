package devops.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import devops.App;
import devops.model.implementations.NodeFilter;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.ServiceResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 * The main window controller.
 * 
 * @author Javon Onuigbo
 * @version Fall 2021
 */
public class MainWindow {

    @FXML
    private AnchorPane backgroundPane;

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

    private static final String BUSINESS_NODE = "business";
    private static final String FAMILY_NODE = "family";
    private static final String FRIEND_NODE = "friend";
    private static final String SPOUSE_NODE = "spouse";

    private JFXButton startNode;

    @FXML
    void addBuisnessNode(ActionEvent event) {
        requestCreateNode("business", 250, 250);
    }

    private void nodeDrag(JFXButton currentNode, String type) {


        final Delta mousePoint = new Delta();
        final Delta nodePoint = new Delta();

        currentNode.setOnMousePressed(mouseEvent -> {

            mousePoint.x = mouseEvent.getSceneX();
            mousePoint.y = mouseEvent.getSceneY();

            nodePoint.x = currentNode.getTranslateX();
            nodePoint.y = currentNode.getTranslateY();

            if (mouseEvent.isPrimaryButtonDown()) {
                if (MainWindow.this.startNode != null && MainWindow.this.startNode != currentNode) {
                    requestCreateEdge(MainWindow.this.startNode, currentNode);
                    MainWindow.this.startNode = null;
                }
            } else {

            }
            
        });

        currentNode.setOnMouseDragged(mouseEvent -> {
            double offsetX = mouseEvent.getSceneX() - mousePoint.x;
            double offsetY = mouseEvent.getSceneY() - mousePoint.y;
            double newTranslateX = nodePoint.x + offsetX;
            double newTranslateY = nodePoint.y + offsetY;

            currentNode.setTranslateX(newTranslateX);
            currentNode.setTranslateY(newTranslateY);

        });
    }

    private void setupNodeContextMenu(JFXButton currentNode) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeMenuItem = new MenuItem("Remove");
        MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
        MenuItem addInformationMenuItem = new MenuItem("Add Information");

        contextMenu.getItems().add(removeMenuItem);
        contextMenu.getItems().add(addEdgeMenuItem);
        contextMenu.getItems().add(addInformationMenuItem);

        removeMenuItem.setOnAction(event -> {
            contextMenu.hide();
            PersonNode node = (PersonNode) currentNode.getUserData();
            ServiceResponse response = App.getGraphService().removeNode(node.getUniqueID());

            if (!response.getData().equals("error")) {
                tholssaGraph.getChildren().remove(currentNode);
            }
        });

        addEdgeMenuItem.setOnAction(event -> {
            MainWindow.this.startNode = currentNode;
            contextMenu.hide();
        });

        addInformationMenuItem.setOnAction(event -> {
            //Add prompt
        });

        currentNode.setContextMenu(contextMenu);
    }

    class Delta {
        double x, y;
    }

    private void mouseDraggedLine(Line currentLine) {
        final Delta mousePoint = new Delta();
        final Delta endPoint = new Delta();

        currentLine.setOnMousePressed(mouseEvent -> {
            mousePoint.x = mouseEvent.getSceneX();
            mousePoint.y = mouseEvent.getSceneY();

            endPoint.x = currentLine.getEndX();
            endPoint.y = currentLine.getEndY();
        });

        currentLine.setOnMouseDragged(mouseEvent -> {
            double offsetX = mouseEvent.getSceneX() - mousePoint.x;
            double offsetY = mouseEvent.getSceneY() - mousePoint.y;
            double newTranslateX = endPoint.x + offsetX;
            double newTranslateY = endPoint.y + offsetY;

            currentLine.setEndX(mouseEvent.getSceneX());
            currentLine.setEndY(mouseEvent.getSceneY());

        });

    }


    private void setupEdgeContextMenu(Line currentEdge) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeMenuItem = new MenuItem("Remove");
        MenuItem addInformationMenuItem = new MenuItem("Add Information");

        contextMenu.getItems().add(removeMenuItem);
        contextMenu.getItems().add(addInformationMenuItem);

        removeMenuItem.setOnAction(event -> {
            
            contextMenu.hide();
            PersonEdge edge = (PersonEdge) currentEdge.getUserData();
            ServiceResponse response = App.getGraphService().removeEdge(edge.getUniqueID());
            if (!response.getData().equals("error")) {
                tholssaGraph.getChildren().remove(currentEdge);
            }
        });


        addInformationMenuItem.setOnAction(event -> {
            // Add prompt
        });

        currentEdge.setOnMouseClicked(mouseEvent -> {
            //if (MouseButton.SECONDARY.equals(mouseEvent.getButton())) {
                contextMenu.show(App.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
            //}
        });
    }

    @FXML
    void addFamilyNode(ActionEvent event) {
        requestCreateNode("family", 250, 250);
    }

    @FXML
    void addFriendFamily(ActionEvent event) {
        requestCreateNode("friend", 250, 250);
    }

    @FXML
    void removeNode(ActionEvent event) {

    }

    @FXML
    void saveGraph(ActionEvent event) {
        for (Node currNode : this.tholssaGraph.getChildren()) {
            JFXButton node = (JFXButton) currNode;
            node.getText();
            node.getTranslateX();
            node.getTranslateY();
        }
    }

    @FXML
    void addSpouseNode(ActionEvent event) {
        requestCreateNode("spouse", 250, 250);
    }

    @FXML
    void initialize() {
        assert backgroundPane != null
                : "fx:id=\"backgroundPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert tholssaGraph != null
                : "fx:id=\"tholssaGraph\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert familyButton != null
                : "fx:id=\"familyButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert friendButton != null
                : "fx:id=\"friendButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert buisnessButton != null
                : "fx:id=\"buisnessButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert spouseButton != null
                : "fx:id=\"spouseButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cancelButton != null
                : "fx:id=\"cancelButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert removeButton != null
                : "fx:id=\"removeButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'MainWindow.fxml'.";

        try {
            Collection<NodeFilter> filters = new ArrayList<NodeFilter>();
            ServiceResponse response = App.getGraphService().getFilteredNetwork("", filters);
            PersonNetwork network = (PersonNetwork) response.getData();

            HashMap<String, JFXButton> nodeMap = new HashMap<String, JFXButton>();
            HashMap<String, Line> lineMap = new HashMap<String, Line>();

            for (PersonNode node : network.getNodes()) {

                JFXButton nodeButton = createNode("family", 250, 250);

                nodeButton.setUserData(node);
                nodeMap.put(node.getUniqueID(), nodeButton);
            }

            for (PersonEdge edge : network.getEdges()) {

                JFXButton sourceButton = nodeMap.get(edge.getSource());
                JFXButton destinationButton = nodeMap.get(edge.getDestination());

                Line lineEdge = createEdge(sourceButton, destinationButton);
                lineEdge.setUserData(edge);
                lineMap.put(edge.getUniqueID(), lineEdge);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void requestCreateNode(String type, int originX, int originY) {

        
        JFXButton nodeButton = createNode(type, originX, originY);

        ServiceResponse response = App.getGraphService().createNode(null, null, null, null, null, null, null, null,
                null);
        nodeButton.setUserData(response.getData());
    }

    private JFXButton createNode (String type, int originX, int originY) {
         JFXButton nodeButton = new JFXButton();
        nodeButton.setText(type);
        nodeButton.setStyle("-fx-background-color: #16ae58;");
        nodeButton.setTranslateX(originX);
        nodeButton.setTranslateY(originY);
        this.tholssaGraph.getChildren().add(nodeButton);

        nodeDrag(nodeButton, type);
        setupNodeContextMenu(nodeButton);
        return nodeButton;
    }

    private void requestCreateEdge(JFXButton sourceButton, JFXButton destinationButton) {

        Line edge = createEdge(sourceButton, destinationButton);

        PersonNode sourceNode = (PersonNode) sourceButton.getUserData();
        PersonNode destinationNode = (PersonNode) destinationButton.getUserData();

        ServiceResponse response = App.getGraphService().connectNodes(sourceNode.getUniqueID(), destinationNode.getUniqueID(), null, null, null);
        edge.setUserData(response.getData());
    }

    private Line createEdge(JFXButton sourceButton, JFXButton destinationButton) {
        Line line = new Line();
        line.setStrokeWidth(5);

        line.startXProperty().bind(sourceButton.translateXProperty());
        line.startYProperty().bind(sourceButton.translateYProperty());

        line.endXProperty().bind(destinationButton.translateXProperty());
        line.endYProperty().bind(destinationButton.translateYProperty());

        line.setStyle("-fx-background-color: #16ae58;");
        tholssaGraph.getChildren().add(line);

        setupEdgeContextMenu(line);

        return line;
    }

}

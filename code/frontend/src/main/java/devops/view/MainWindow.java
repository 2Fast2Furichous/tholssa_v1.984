package devops.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import devops.App;
import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.ServiceResponse;
import devops.utils.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class MainWindow {

    @FXML
    private JFXButton logoutButton;

    @FXML
    private AnchorPane tholssaGraph;

    @FXML
    private JFXTextField nickname;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField phoneNumber;

    @FXML
    private JFXTextField occupation;

    @FXML
    private JFXTextField description;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private DatePicker dateOfDeath;

    @FXML
    private JFXComboBox<?> relation;

    @FXML
    private DatePicker relationStartDate;

    @FXML
    private DatePicker relationEndDate;

    @FXML
    private JFXTextField locationX;

    @FXML
    private JFXTextField locationY;

    @FXML
    private JFXButton submitNode;

    private JFXButton startNode;

    private PersonNode rootNode;

    private JFXButton selectedNode;

    /**
     * Zero-parameter constructor.
     * 
     * @precondition none
     * @postcondition none
     *
     */
    public MainWindow() {
        this.rootNode = null;
        this.selectedNode = null;
    }

    @FXML
    void handleAddress(ActionEvent event) {

    }

    @FXML
    void handleDateOfBirth(ActionEvent event) {

    }

    @FXML
    void handleDateOfDeath(ActionEvent event) {

    }

    @FXML
    void handleDescription(ActionEvent event) {

    }

    @FXML
    void handleFirstName(ActionEvent event) {

    }

    @FXML
    void handleLastName(ActionEvent event) {

    }

    @FXML
    void handleLocationX(ActionEvent event) {

    }

    @FXML
    void handleLocationY(ActionEvent event) {

    }

    @FXML
    void handleNickname(ActionEvent event) {

    }

    @FXML
    void handleOccupation(ActionEvent event) {

    }

    @FXML
    void handlePhoneNumber(ActionEvent event) {

    }

    @FXML
    void handleRelation(ActionEvent event) {

    }

    @FXML
    void handleRelationEndDate(ActionEvent event) {

    }

    @FXML
    void handleRelationStartDate(ActionEvent event) {

    }

    private void addSubmitNodeInputValidation(){
        this.submitNode.disableProperty().bind(this.nickname.textProperty().isEmpty().or(this.locationX.textProperty().isEmpty().or(this.locationY.textProperty().isEmpty())));
    }

    @FXML
    void submitNode(ActionEvent event) {

        PersonNode currentNode = (PersonNode) this.selectedNode.getUserData();

        String nickname = this.nickname.getText();
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String address = this.address.getText();
        String phoneNumber = this.phoneNumber.getText();
        LocalDate dateOfBirth = this.dateOfBirth.getValue();
        LocalDate dateOfDeath = this.dateOfDeath.getValue();
        String occupation = this.occupation.getText();
        String description = this.description.getText();

        ServiceResponse response = App.getGraphService().updateNode(this.selectedNode.getTranslateX(),
                this.selectedNode.getTranslateY(), currentNode.getUniqueID(), nickname, firstName, lastName, address,
                phoneNumber, dateOfBirth, dateOfDeath, occupation, description);
        PersonNode updatedNode = (PersonNode) response.getData();

        this.selectedNode.setUserData(updatedNode);
        this.selectedNode.textProperty().set(nickname);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        try {
            FXRouter.show("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        this.makeGraph();
        this.addSubmitNodeInputValidation();
    }

    private void makeGraph() {
        try {
            Collection<NodeFilter> filters = new ArrayList<NodeFilter>();
            ServiceResponse response = App.getGraphService().getFilteredNetwork("", filters);
            PersonNetwork network = (PersonNetwork) response.getData();

            HashMap<String, JFXButton> nodeMap = new HashMap<String, JFXButton>();
            HashMap<String, Line> lineMap = new HashMap<String, Line>();

            for (PersonNode node : network.getNodes()) {
                Person currentPerson = node.getValue();
                JFXButton nodeButton = createNode("node", currentPerson.getPositionX(), currentPerson.getPositionY());

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

            ContextMenu contextMenu = new ContextMenu();
            MenuItem addNodeMenuItem = new MenuItem("Add Node");

            contextMenu.getItems().add(addNodeMenuItem);

            final Delta mousePoint = new Delta();

            addNodeMenuItem.setOnAction(event -> {
                contextMenu.hide();
                requestCreateNode("empty node", mousePoint.x, mousePoint.y);
            });

            tholssaGraph.setOnMouseClicked(mouseEvent -> {
                if (MouseButton.SECONDARY.equals(mouseEvent.getButton())) {
                    mousePoint.x = mouseEvent.getSceneX();
                    mousePoint.y = mouseEvent.getSceneY();
                    contextMenu.show(App.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
                } else if (MouseButton.PRIMARY.equals(mouseEvent.getButton())) {
                    deselectNode();
                }
            });

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void selectNode(JFXButton node) {
        this.selectedNode = node;

        PersonNode currentNode = (PersonNode) this.selectedNode.getUserData();
        Person currentPerson = currentNode.getValue();

        this.nickname.setText(currentPerson.getNickname());

        this.firstName.setText(currentPerson.getFirstName());
        this.lastName.setText(currentPerson.getLastName());
        this.address.setText(currentPerson.getAddress());
        this.phoneNumber.setText(currentPerson.getPhoneNumber());
        this.dateOfBirth.setValue(currentPerson.getDateOfBirth());
        this.dateOfDeath.setValue(currentPerson.getDateOfDeath());
        this.occupation.setText(currentPerson.getOccupation());
        this.description.setText(currentPerson.getDescription());
        this.locationX.setText(String.valueOf(currentPerson.getPositionX()));
        this.locationY.setText(String.valueOf(currentPerson.getPositionY()));
        this.selectedNode.textProperty().setValue(currentPerson.getNickname());
    }

    private void deselectNode() {
        this.selectedNode = null;
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

        currentNode.setOnMouseReleased(mouseEvent -> {
            PersonNode node = (PersonNode) currentNode.getUserData();
            Person currentPerson = node.getValue();
            ServiceResponse response = App.getGraphService().updateNode(currentNode.getTranslateX(),
                    currentNode.getTranslateY(), node.getUniqueID(), currentPerson.getNickname(),
                    currentPerson.getFirstName(), currentPerson.getLastName(), currentPerson.getAddress(),
                    currentPerson.getPhoneNumber(), currentPerson.getDateOfBirth(), currentPerson.getDateOfDeath(),
                    currentPerson.getOccupation(), currentPerson.getDescription());
            PersonNode updatedNode = (PersonNode) response.getData();
            currentNode.setUserData(updatedNode);
        });

        currentNode.setOnMouseClicked(MouseEvent-> {
            selectNode(currentNode);
        });
    }

    private void setupNodeContextMenu(JFXButton currentNode) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeMenuItem = new MenuItem("Remove");
        MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
        MenuItem addInformationMenuItem = new MenuItem("Add Information");
        MenuItem setAsRootNodeMenuItem = new MenuItem("Set as Root Node");

        contextMenu.getItems().add(removeMenuItem);
        contextMenu.getItems().add(addEdgeMenuItem);
        contextMenu.getItems().add(addInformationMenuItem);
        contextMenu.getItems().add(setAsRootNodeMenuItem);

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
            // Add prompt
        });

        setAsRootNodeMenuItem.setOnAction(event -> {
            this.rootNode = (PersonNode) currentNode.getUserData();
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
            contextMenu.show(App.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
        });
    }

    private void requestCreateNode(String type, double originX, double originY) {
        JFXButton nodeButton = createNode(type, originX, originY);

        ServiceResponse response = App.getGraphService().createNode(originX, originY, null, null, null, null, null,
                null, null, null, null);
        nodeButton.setUserData(response.getData());
    }

    private JFXButton createNode(String type, double originX, double originY) {
        JFXButton nodeButton = new JFXButton();
        nodeButton.setText(type);
        nodeButton.setStyle(
                "-fx-background-color: #16ae58; -fx-background-radius: 5em; -fx-border-radius: 15; -fx-background-insets: -1.4, 0;");
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

        ServiceResponse response = App.getGraphService().connectNodes(sourceNode.getUniqueID(),
                destinationNode.getUniqueID(), null, null, null);
        edge.setUserData(response.getData());
    }

    private Line createEdge(JFXButton sourceButton, JFXButton destinationButton) {
        Line line = new Line();
        line.setStrokeWidth(5);
        line.setStroke(Color.web("#16ae58"));

        line.startXProperty().bind(sourceButton.translateXProperty());
        line.startYProperty().bind(sourceButton.translateYProperty());

        line.endXProperty().bind(destinationButton.translateXProperty());
        line.endYProperty().bind(destinationButton.translateYProperty());

        tholssaGraph.getChildren().add(line);

        setupEdgeContextMenu(line);

        return line;
    }

}

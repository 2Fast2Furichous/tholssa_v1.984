package devops.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import devops.App;
import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.Relationship;
import devops.model.implementations.ServiceResponse;
import devops.utils.FXRouter;
import devops.view.Elements.DragContext;
import devops.view.Elements.NodeGestures;
import devops.view.Elements.PannableCanvas;
import devops.view.Elements.SceneGestures;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class MainWindow {

    /**
     *
     */
    private static final String HIDDEN_STYLE = "visibility:hidden";

    /**
     *
     */
    private static final String DEFAULT_NODE_STYLE = "-fx-background-color: #16ae58; -fx-background-radius: 5em; -fx-border-radius: 15; -fx-background-insets: -1.4, 0;";

    @FXML
    private JFXButton logoutButton;

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

    @FXML
    private JFXTextField searchTextField;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton showButton;

    @FXML
    private JFXCheckBox familyFilter;

    @FXML
    private JFXButton applyFiltersButton;

    @FXML
    private AnchorPane tholssaGraph;

    @FXML
    private ColumnConstraints infoColumn;

    @FXML
    private ColumnConstraints filterColumn;

    @FXML
    private JFXComboBox<String> searchResultsByName;

    private JFXButton startNode;

    private PersonNode rootNode;

    private JFXButton selectedNode;

    private NodeGestures nodeGestures;

    private PannableCanvas canvas;

    /**
     * Constructor for Main Window creates the main window and sets the root node
     * 
     * @precondition none
     * @postcondition none
     *
     */
    public MainWindow() {
        this.rootNode = null;

    }

    private void addSubmitNodeInputValidation() {
        this.submitNode.disableProperty().bind(this.nickname.textProperty().isEmpty()
                .or(this.locationX.textProperty().isEmpty().or(this.locationY.textProperty().isEmpty())));
    }

    @FXML
    void submitNode(ActionEvent event) {
        if (this.selectedNode == null) {
            return;
        }

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

        this.setupGraph();
        this.populateGraph("", new ArrayList<NodeFilter>());
        this.addSubmitNodeInputValidation();

        this.infoColumn.maxWidthProperty().set(0);
        this.infoColumn.minWidthProperty().set(0);

        this.filterColumn.maxWidthProperty().set(0);
        this.filterColumn.minWidthProperty().set(0);
    }

    private void setupGraph() {
        this.canvas = new PannableCanvas();
        this.canvas.setPrefSize(this.tholssaGraph.getPrefWidth(), this.tholssaGraph.getPrefHeight());
        this.canvas.setTranslateX(this.tholssaGraph.getPrefWidth() / 2);
        this.canvas.setTranslateY(this.tholssaGraph.getPrefHeight() / 2);

        this.tholssaGraph.getChildren().add(this.canvas);

        this.nodeGestures = new NodeGestures(this.canvas);
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addNodeMenuItem = new MenuItem("Add Node");

        contextMenu.getItems().add(addNodeMenuItem);

        DragContext clickPoint = new DragContext();

        addNodeMenuItem.setOnAction(event -> {
            contextMenu.hide();

            Point2D relPoint = canvas.sceneToLocal(clickPoint.mouseAnchorX, clickPoint.mouseAnchorY);
            requestCreateNode(relPoint.getX(), relPoint.getY());
        });

        this.tholssaGraph.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
                    clickPoint.mouseAnchorX = mouseEvent.getSceneX();
                    clickPoint.mouseAnchorY = mouseEvent.getSceneY();

                    contextMenu.show(App.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
                if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 1) {
                    deselectNode();
                }
            }
        });

        SceneGestures sceneGestures = new SceneGestures(canvas);
        this.tholssaGraph.addEventFilter(MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        this.tholssaGraph.addEventFilter(MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        this.tholssaGraph.addEventFilter(ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());

    }

    @FXML
    void handleApplyFilters(ActionEvent event) {
        Collection<NodeFilter> filters = new ArrayList<NodeFilter>();
        if (this.rootNode != null) {
            if (this.familyFilter.isSelected()) {
                filters.add(NodeFilter.Family);
            }
            this.populateGraph(this.rootNode.getUniqueID(), filters);
        } else {
            this.populateGraph("", filters);
        }
    }

    private void populateGraph(String rootNodeGuid, Collection<NodeFilter> filters) {
        this.canvas.getChildren().clear();
        this.canvas.addGrid();

        try {

            ServiceResponse response = App.getGraphService().getFilteredNetwork(rootNodeGuid, filters);
            PersonNetwork network = (PersonNetwork) response.getData();

            HashMap<String, JFXButton> nodeMap = new HashMap<String, JFXButton>();
            HashMap<String, Line> lineMap = new HashMap<String, Line>();

            for (PersonNode node : network.getNodes()) {
                Person currentPerson = node.getValue();
                JFXButton nodeButton = createNode(currentPerson.getPositionX(), currentPerson.getPositionY());
                nodeButton.textProperty().set(currentPerson.getNickname());
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
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String textFromSearchTextField = this.searchTextField.getText();
        var visibleNodes = new ArrayList<Node>();
        var hiddenNodes = new ArrayList<Node>();
        ObservableList<String> list = FXCollections.observableArrayList();
        
        if (textFromSearchTextField == null || textFromSearchTextField.isBlank()) {
            visibleNodes.addAll(this.canvas.getChildren());
        } else {
            for (var tholssaNode : this.canvas.getChildren()) {
                var nodeData = tholssaNode.getUserData();
                if (nodeData instanceof PersonNode) {
                    PersonNode currentNode = (PersonNode) nodeData;
                    Person currentPerson = currentNode.getValue();
                    if (!checkForMatchToSearchValue(textFromSearchTextField, currentPerson)) {
                        hiddenNodes.add(tholssaNode);
                    } else {
                        list.add(currentPerson.getFirstName() +" "+ currentPerson.getLastName() +" "+ currentPerson.getNickname());
                        this.searchResultsByName.setItems(list);
                        visibleNodes.add(tholssaNode);
                    }
                } else if (nodeData instanceof PersonEdge) {
                    hiddenNodes.add(tholssaNode);
                }
            }
        }

        for (var tholssaNode : visibleNodes) {
            tholssaNode.setStyle(DEFAULT_NODE_STYLE);
        }
        for (var tholssaNode : hiddenNodes) {
            tholssaNode.setStyle(HIDDEN_STYLE);
        }
    }

    private boolean checkForMatchToSearchValue(String textFromSearchTextField, Person currentPerson) {

        return (currentPerson.getNickname() != null ? currentPerson.getNickname().contains(textFromSearchTextField)
                : false)
                || (currentPerson.getFirstName() != null
                        ? currentPerson.getFirstName().contains(textFromSearchTextField)
                        : false)
                || (currentPerson.getLastName() != null ? currentPerson.getLastName().contains(textFromSearchTextField)
                        : false)
                || (currentPerson.getAddress() != null ? currentPerson.getAddress().contains(textFromSearchTextField)
                        : false)
                || (currentPerson.getDescription() != null
                        ? currentPerson.getDescription().contains(textFromSearchTextField)
                        : false)
                || (currentPerson.getOccupation() != null
                        ? currentPerson.getOccupation().contains(textFromSearchTextField)
                        : false)
                || (currentPerson.getPhoneNumber() != null
                        ? currentPerson.getPhoneNumber().contains(textFromSearchTextField)
                        : false);
    }

    @FXML
    void handleShow(ActionEvent event) {
        for (var tholssaNode : this.canvas.getChildren()) {
            tholssaNode.setStyle(DEFAULT_NODE_STYLE);
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

        Timeline timelineDown = new Timeline();

        KeyValue kvDwn1 = new KeyValue(infoColumn.maxWidthProperty(), infoColumn.prefWidthProperty().doubleValue());
        KeyValue kvDwn2 = new KeyValue(infoColumn.minWidthProperty(), infoColumn.prefWidthProperty().doubleValue());

        final KeyFrame kfDwn = new KeyFrame(Duration.millis(200), kvDwn1, kvDwn2);

        timelineDown.getKeyFrames().add(kfDwn);

        timelineDown.play();

    }

    private void deselectNode() {
        this.selectedNode = null;

        Timeline timelineDown = new Timeline();

        KeyValue kvDwn1 = new KeyValue(infoColumn.maxWidthProperty(), 0);
        KeyValue kvDwn2 = new KeyValue(infoColumn.minWidthProperty(), 0);

        final KeyFrame kfDwn = new KeyFrame(Duration.millis(200), kvDwn1, kvDwn2);

        timelineDown.getKeyFrames().add(kfDwn);

        timelineDown.play();
    }

    private void setupDrag(JFXButton currentNode) {

        currentNode.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.isPrimaryButtonDown()) {
                    if (MainWindow.this.startNode != null && MainWindow.this.startNode != currentNode) {
                        requestCreateEdge(MainWindow.this.startNode, currentNode);
                        MainWindow.this.startNode = null;
                    }
                    selectNode(currentNode);
                }
            }
        });

        currentNode.addEventFilter(MouseEvent.MOUSE_PRESSED, this.nodeGestures.getOnMousePressedEventHandler());
        currentNode.addEventFilter(MouseEvent.MOUSE_DRAGGED, this.nodeGestures.getOnMouseDraggedEventHandler());

        currentNode.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PersonNode node = (PersonNode) currentNode.getUserData();
                Person currentPerson = node.getValue();
                ServiceResponse response = App.getGraphService().updateNode(currentNode.getTranslateX(),
                        currentNode.getTranslateY(), node.getUniqueID(), currentPerson.getNickname(),
                        currentPerson.getFirstName(), currentPerson.getLastName(), currentPerson.getAddress(),
                        currentPerson.getPhoneNumber(), currentPerson.getDateOfBirth(), currentPerson.getDateOfDeath(),
                        currentPerson.getOccupation(), currentPerson.getDescription());
                PersonNode updatedNode = (PersonNode) response.getData();
                currentNode.setUserData(updatedNode);
            }
        });
    }

    private void setupNodeContextMenu(JFXButton currentNode) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeMenuItem = new MenuItem("Remove");
        MenuItem addEdgeMenuItem = new MenuItem("Add Edge");
        MenuItem setAsRootNodeMenuItem = new MenuItem("Set as Root Node");

        contextMenu.getItems().add(removeMenuItem);
        contextMenu.getItems().add(addEdgeMenuItem);
        contextMenu.getItems().add(setAsRootNodeMenuItem);

        removeMenuItem.setOnAction(event -> {
            contextMenu.hide();
            PersonNode node = (PersonNode) currentNode.getUserData();
            ServiceResponse response = App.getGraphService().removeNode(node.getUniqueID());

            if (!response.getData().equals("error")) {
                this.canvas.getChildren().remove(currentNode);
            }
        });

        addEdgeMenuItem.setOnAction(event -> {
            MainWindow.this.startNode = currentNode;
            contextMenu.hide();
        });

        setAsRootNodeMenuItem.setOnAction(event -> {
            this.setRootNode((PersonNode) currentNode.getUserData());
        });

        currentNode.setContextMenu(contextMenu);
    }

    private void setRootNode(PersonNode node) {
        this.rootNode = node;

        if (node == null) {
            Timeline timelineDown = new Timeline();
            KeyValue transitionMax = new KeyValue(filterColumn.maxWidthProperty(), 0);
            KeyValue transitionMin = new KeyValue(filterColumn.minWidthProperty(), 0);

            KeyFrame kfDwn = new KeyFrame(Duration.millis(200), transitionMax, transitionMin);
            timelineDown.getKeyFrames().add(kfDwn);
            timelineDown.play();
        } else {
            Timeline timelineDown = new Timeline();
            KeyValue transitionMax = new KeyValue(filterColumn.maxWidthProperty(),
                    filterColumn.prefWidthProperty().doubleValue());
            KeyValue transitionMin = new KeyValue(filterColumn.minWidthProperty(),
                    filterColumn.prefWidthProperty().doubleValue());

            KeyFrame kfDwn = new KeyFrame(Duration.millis(200), transitionMax, transitionMin);
            timelineDown.getKeyFrames().add(kfDwn);
            timelineDown.play();
        }

    }

    private void requestCreateNode(double originX, double originY) {
        JFXButton nodeButton = createNode(originX, originY);

        ServiceResponse response = App.getGraphService().createNode(originX, originY, "unknown", null, null, null, null,
                null, null, null, null);
        nodeButton.setUserData(response.getData());
        nodeButton.textProperty().set("unknown");
    }

    private JFXButton createNode(double originX, double originY) {
        JFXButton nodeButton = new JFXButton();
        nodeButton.setStyle(DEFAULT_NODE_STYLE);
        nodeButton.setTranslateX(originX);
        nodeButton.setTranslateY(originY);
        nodeButton.setMinWidth(100);

        this.canvas.getChildren().add(nodeButton);
        nodeButton.toFront();

        setupDrag(nodeButton);
        setupNodeContextMenu(nodeButton);

        return nodeButton;
    }

    private void requestCreateEdge(JFXButton sourceButton, JFXButton destinationButton) {

        Line edge = createEdge(sourceButton, destinationButton);

        PersonNode sourceNode = (PersonNode) sourceButton.getUserData();
        PersonNode destinationNode = (PersonNode) destinationButton.getUserData();

        ServiceResponse response = App.getGraphService().connectNodes(sourceNode.getUniqueID(),
                destinationNode.getUniqueID(), Relationship.Parent, null, null);
        edge.setUserData(response.getData());
    }

    private Line createEdge(JFXButton sourceButton, JFXButton destinationButton) {
        Line line = new Line();
        line.setStrokeWidth(5);
        line.setStroke(Color.web("#16ae58"));

        line.startXProperty().bind(sourceButton.translateXProperty().add(sourceButton.widthProperty().divide(2)));
        line.startYProperty().bind(sourceButton.translateYProperty().add(sourceButton.heightProperty().divide(2)));

        line.endXProperty()
                .bind(destinationButton.translateXProperty().add(destinationButton.widthProperty().divide(2)));
        line.endYProperty()
                .bind(destinationButton.translateYProperty().add(destinationButton.heightProperty().divide(2)));

        this.canvas.getChildren().add(line);
        line.toBack();
        return line;
    }

}

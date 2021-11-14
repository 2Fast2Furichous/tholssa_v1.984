package devops.view;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import devops.App;
import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.Relationship;
import devops.model.implementations.Review;
import devops.model.implementations.ServiceResponse;
import devops.utils.FXRouter;
import devops.view.Elements.DragContext;
import devops.view.Elements.NodeGestures;
import devops.view.Elements.PannableCanvas;
import devops.view.Elements.SceneGestures;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class MainWindow {

    private static final String HIDDEN_STYLE = "visibility:hidden";

    private static final String DEFAULT_NODE_STYLE = "-fx-background-color: #16ae58; -fx-background-radius: 5em; -fx-border-radius: 15; -fx-background-insets: -1.4, 0;";

    private static final String SELECTED_NODE_STYLE = "-fx-background-color: #6897bb; -fx-background-radius: 5em; -fx-border-radius: 15; -fx-background-insets: -1.4, 0;";

    private static final String ROOT_NODE_STYLE = "-fx-background-color: #ea3645; -fx-background-radius: 5em; -fx-border-radius: 15; -fx-background-insets: -1.4, 0;";

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
    private JFXComboBox<Relationship> relation;

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

    @FXML
    private TitledPane relationshipPane;

    @FXML
    private JFXListView<Review> reviewsListView;

    @FXML
    private JFXButton addReviewButton;

    @FXML
    private JFXTextField reviewNameTextBox;

    @FXML
    private JFXTextField reviewContentTextBox;

    @FXML
    private JFXComboBox<Integer> reviewScoreComboBox;

    @FXML
    private JFXTextField zoomLevelTextField;

    private JFXButton rootNode;

    private JFXButton selectedNodeButton;

    private NodeGestures nodeGestures;

    private PannableCanvas canvas;

    private HashMap<String, Group> lineMap;

    private HashMap<String, JFXButton> nodeMap;

    /**
     * Constructor for Main Window creates the main window and sets the root node
     * 
     * @precondition none
     * @postcondition none
     *
     */
    public MainWindow() {
        this.rootNode = null;
        this.nodeMap = new HashMap<String, JFXButton>();
        this.lineMap = new HashMap<String, Group>();
    }

    private void addSubmitNodeInputValidation() {
        this.submitNode.disableProperty().bind(this.nickname.textProperty().isEmpty()
                .or(this.locationX.textProperty().isEmpty().or(this.locationY.textProperty().isEmpty())));
    }

    @FXML
    void handleAddReview(ActionEvent event) {
        String name = this.reviewNameTextBox.getText();
        String content = this.reviewContentTextBox.getText();
        int score = this.reviewScoreComboBox.getValue();

        var review = new Review(name, content, score);
        this.reviewsListView.getItems().add(0, review);

        this.reviewNameTextBox.setText(null);
        this.reviewContentTextBox.setText(null);
        this.reviewScoreComboBox.setValue(null);
    }

    @FXML
    void submitNode(ActionEvent event) {
        if (this.selectedNodeButton == null) {
            return;
        }

        PersonNode currentNode = (PersonNode) this.selectedNodeButton.getUserData();
        String nodeUniqueID = currentNode.getUniqueID();
        String nickname = this.nickname.getText();
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String address = this.address.getText();
        String phoneNumber = this.phoneNumber.getText();
        LocalDate dateOfBirth = this.dateOfBirth.getValue();
        LocalDate dateOfDeath = this.dateOfDeath.getValue();
        String occupation = this.occupation.getText();
        String description = this.description.getText();
        var reviews = this.reviewsListView.getItems();

        App.getGraphService().updateNode(this.selectedNodeButton.getTranslateX(),
                this.selectedNodeButton.getTranslateY(), nodeUniqueID, nickname, firstName, lastName, address,
                phoneNumber, dateOfBirth, dateOfDeath, occupation, description, reviews);

        Relationship relation = this.relation.getValue();
        LocalDate relationStartDate = this.relationStartDate.getValue();
        LocalDate relationEndDate = this.relationEndDate.getValue();

        var edge = this.findEdge(nodeUniqueID);
        if (relation != null) {
            if (edge.isPresent()) {
                App.getGraphService().updateEdge(
                        edge.get(), relation, relationStartDate, relationEndDate);

            } else {
                this.requestCreateEdge(this.rootNode, this.selectedNodeButton, relation, relationStartDate, relationEndDate);
            }
        }
        else if (edge.isPresent()) {
            App.getGraphService().removeEdge(edge.get());
        }

        this.applyFilters();
    }

    private Optional<String> findEdge(String currentUniqueID) {
        if (this.rootNode == null || currentUniqueID == null) {
            return Optional.empty();
        }
        
        PersonNode rootNode = (PersonNode) this.rootNode.getUserData();
        
        return rootNode.getEdges().stream().filter((edgeUniqueID) -> {
            var line = this.lineMap.get(edgeUniqueID);
            var currentEdge = (PersonEdge) line.getUserData();
            return currentEdge.getDestination().equals(currentUniqueID);
        }).findFirst();
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
    void selectedSearchResultPerson(ActionEvent event) {
        if (this.searchResultsByName.getValue() == null) {
            return;
        }

        for (var tholssaNode : this.canvas.getChildren()) {
            var nodeData = tholssaNode.getUserData();
            if (nodeData instanceof PersonNode) {
                PersonNode currentNode = (PersonNode) nodeData;
                Person currentPerson = currentNode.getValue();
                if(currentPerson.getFullNameWithNickname().equals(this.searchResultsByName.getValue())){
                    this.canvas.setTranslateX(this.tholssaGraph.getPrefWidth() / 2);
                    this.canvas.setTranslateY(this.tholssaGraph.getPrefHeight() / 2);

                    this.canvas.setTranslateX(currentPerson.getPositionX());
                    this.canvas.setTranslateY(currentPerson.getPositionY());

                    this.canvas.setPivot((currentPerson.getPositionX()/3)-75, (currentPerson.getPositionY()/3)+100);
                    this.canvas.setScale(1);
                    this.canvas.setScale(canvas.getScale()/1.2);
                    this.updateZoomLevel();
                    break;
                }
            }
        }
        
        this.deselectSearchSelection();
    }

    private void deselectSearchSelection() {
        this.searchResultsByName.getItems().add(0, null);
        this.searchResultsByName.setValue(null);
        this.searchResultsByName.getItems().remove(0);
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
        
        this.relation.getItems().add(null);
        this.relation.getItems().addAll(Relationship.values());

        for (var score = Review.MINIMUM_SCORE; score <= Review.MAXIMUM_SCORE; score++) {
            this.reviewScoreComboBox.getItems().add(score);
        }
        
        this.reviewsListView.setCellFactory(param -> new ListCell<Review>(){
            @Override
            protected void updateItem(Review item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    this.setGraphic(null);
                    this.setText(null); 
                }else{
                    this.setMinWidth(param.getWidth());
                    this.setMaxWidth(param.getWidth());
                    this.setPrefWidth(param.getWidth());

                    this.setWrapText(true);
                    var formattedReview = MainWindow.this.formatReview(item);
                    this.setText(formattedReview);
                }
            }
        });

        this.zoomLevelTextField.setStyle("-fx-text-fill: green; -fx-font-size: 10px;");
        this.updateZoomLevel();
    }

    private String formatReview(Review review) {
        StringBuilder builder = new StringBuilder();
        builder.append("Reviewer: ").append(review.getName()).append(System.lineSeparator());
        builder.append("Content: ").append(review.getContent()).append(System.lineSeparator());
        builder.append("Score: ").append(review.getScore()).append(System.lineSeparator());
        builder.append("Entry Date: ").append(review.getEntryDate().toLocalDate());
        return builder.toString();
    }

    private void updateZoomLevel() {
        var format = new DecimalFormat("0.00");
        this.zoomLevelTextField.setText(format.format(this.canvas.getScale()));
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
                    MainWindow.this.deselectNode();
                }
                if (mouseEvent.isSecondaryButtonDown() && mouseEvent.getClickCount() == 2) {
                    MainWindow.this.deselectRootNode();
                }
            }
        });

        SceneGestures sceneGestures = new SceneGestures(canvas);
        this.tholssaGraph.addEventFilter(MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        this.tholssaGraph.addEventFilter(MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        this.tholssaGraph.addEventFilter(ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());
        this.tholssaGraph.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                MainWindow.this.updateZoomLevel();
            }
        });  
    }

    @FXML
    void handleApplyFilters(ActionEvent event) {
       this.applyFilters();
    }

    private void applyFilters() {
                Collection<NodeFilter> filters = new ArrayList<NodeFilter>();
        if (this.rootNode != null) {
            if (this.familyFilter.isSelected()) {
                filters.add(NodeFilter.Family);
            }
            this.populateGraph(((PersonNode) this.rootNode.getUserData()).getUniqueID(), filters);
        } else {
            this.populateGraph("", filters);
        }
    }

    private void populateGraph(String rootNodeGuid, Collection<NodeFilter> filters) {
        this.canvas.getChildren().clear();
        //this.canvas.addGrid();

        this.nodeMap.clear();
        this.lineMap.clear();
        try {

            ServiceResponse response = App.getGraphService().getFilteredNetwork(rootNodeGuid, filters);
            PersonNetwork network = (PersonNetwork) response.getData();

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

                Group lineEdge = createEdge(sourceButton, destinationButton);
                lineEdge.setUserData(edge);
                lineMap.put(edge.getUniqueID(), lineEdge);
            }

            if (rootNodeGuid != null && !rootNodeGuid.isBlank()) {
                var newRoot = nodeMap.get(rootNodeGuid);
                this.rootNode = newRoot;
                this.updateNodeStyle(this.rootNode);
            }

            if (this.selectedNodeButton != null) {
                var newSelected = nodeMap.get(((PersonNode) this.selectedNodeButton.getUserData()).getUniqueID());
                this.selectedNodeButton = newSelected;
                this.updateNodeStyle(this.selectedNodeButton);

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
                        list.add(currentPerson.getFullNameWithNickname());
                        visibleNodes.add(tholssaNode);
                    }
                } else if (nodeData instanceof PersonEdge) {
                    hiddenNodes.add(tholssaNode);
                }
            }
        }
        this.searchResultsByName.setItems(list);

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

    private void updateNodeStyle(JFXButton node) {
        if (node == null) {
            return;
        }
        if (this.rootNode == node) {
            node.setStyle(ROOT_NODE_STYLE);
        } else if (this.selectedNodeButton == node) {
            node.setStyle(SELECTED_NODE_STYLE);
        } else {
            node.setStyle(DEFAULT_NODE_STYLE);
        }
    }

    private void selectNode(JFXButton node) {
        var previousNodeButton = this.selectedNodeButton;
        this.selectedNodeButton = node;

        this.updateNodeStyle(previousNodeButton);
        this.updateNodeStyle(node);

        var currentNode = (PersonNode) this.selectedNodeButton.getUserData();
        var currentPerson = currentNode.getValue();

        this.nickname.setText(currentPerson.getNickname());

        this.firstName.setText(currentPerson.getFirstName());
        this.lastName.setText(currentPerson.getLastName());
        this.address.setText(currentPerson.getAddress());
        this.phoneNumber.setText(currentPerson.getPhoneNumber());
        this.dateOfBirth.setValue(currentPerson.getDateOfBirth());
        this.dateOfDeath.setValue(currentPerson.getDateOfDeath());
        this.occupation.setText(currentPerson.getOccupation());
        this.description.setText(currentPerson.getDescription());

        var reviews = FXCollections.observableArrayList(currentPerson.getReviews());
        this.reviewsListView.getItems().setAll(reviews);

        this.relationshipPane.setDisable(this.selectedNodeButton == this.rootNode || this.rootNode == null);
        var edge = this.findEdge(currentNode.getUniqueID());

        if (edge.isPresent()) {
            var edgeUniqueID = edge.get();
            var line = this.lineMap.get(edgeUniqueID);
            var currentEdge = (PersonEdge) line.getUserData();
            this.relation.setValue(currentEdge.getRelation());
            this.relationStartDate.setValue(currentEdge.getDateOfConnection());
            this.relationEndDate.setValue(currentEdge.getDateOfConnectionEnd());
        } else {
            this.relation.setValue(null);
            this.relationStartDate.setValue(null);
            this.relationEndDate.setValue(null);
        }

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
        JFXButton previousNode = this.selectedNodeButton;
        this.selectedNodeButton = null;

        this.updateNodeStyle(previousNode);

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
                        currentPerson.getOccupation(), currentPerson.getDescription(), currentPerson.getReviews());
                PersonNode updatedNode = (PersonNode) response.getData();
                currentNode.setUserData(updatedNode);
            }
        });
    }

    private void setupNodeContextMenu(JFXButton currentNode) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeMenuItem = new MenuItem("Remove");
        MenuItem setAsRootNodeMenuItem = new MenuItem("Set as Root Node");

        contextMenu.getItems().add(removeMenuItem);
        contextMenu.getItems().add(setAsRootNodeMenuItem);

        removeMenuItem.setOnAction(event -> {
            contextMenu.hide();
            PersonNode node = (PersonNode) currentNode.getUserData();
            App.getGraphService().removeNode(node.getUniqueID());

            this.canvas.getChildren().remove(currentNode);
            this.applyFilters();
        });


        setAsRootNodeMenuItem.setOnAction(event -> {
            this.selectRootNode(currentNode);
        });

        currentNode.setContextMenu(contextMenu);
    }

    private void selectRootNode(JFXButton node) {
        JFXButton previousNode = this.rootNode;
        this.rootNode = node;

        this.updateNodeStyle(previousNode);
        this.updateNodeStyle(node);
        
        Timeline timelineDown = new Timeline();
        KeyValue transitionMax = new KeyValue(filterColumn.maxWidthProperty(),
                filterColumn.prefWidthProperty().doubleValue());
        KeyValue transitionMin = new KeyValue(filterColumn.minWidthProperty(),
                filterColumn.prefWidthProperty().doubleValue());

        KeyFrame kfDwn = new KeyFrame(Duration.millis(200), transitionMax, transitionMin);
        timelineDown.getKeyFrames().add(kfDwn);
        timelineDown.play();
        
        this.applyFilters();
    }

    private void deselectRootNode() {
        JFXButton previousNode = this.rootNode;
        this.rootNode = null;

        this.updateNodeStyle(previousNode);
        
        Timeline timelineDown = new Timeline();
        KeyValue transitionMax = new KeyValue(filterColumn.maxWidthProperty(), 0);
        KeyValue transitionMin = new KeyValue(filterColumn.minWidthProperty(), 0);

        KeyFrame kfDwn = new KeyFrame(Duration.millis(200), transitionMax, transitionMin);
        timelineDown.getKeyFrames().add(kfDwn);
        timelineDown.play();
        
        this.applyFilters();
    }

    private void requestCreateNode(double originX, double originY) {
        JFXButton nodeButton = createNode(originX, originY);

        ServiceResponse response = App.getGraphService().createNode(originX, originY, "unknown", null, null, null, null,
                null, null, null, null);
        PersonNode node = (PersonNode) response.getData();
        nodeButton.setUserData(node);
        nodeButton.textProperty().set("unknown");

        nodeMap.put(node.getUniqueID(), nodeButton);
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

    private void requestCreateEdge(JFXButton sourceButton, JFXButton destinationButton, Relationship relation, LocalDate relationStartDate, LocalDate relationEndDate) {

        Group edgeLine = createEdge(sourceButton, destinationButton);

        PersonNode sourceNode = (PersonNode) sourceButton.getUserData();
        PersonNode destinationNode = (PersonNode) destinationButton.getUserData();

        ServiceResponse response = App.getGraphService().connectNodes(sourceNode.getUniqueID(),
                destinationNode.getUniqueID(), relation, relationStartDate, relationEndDate);
        PersonEdge edge = (PersonEdge) response.getData();
        edgeLine.setUserData(response.getData());

        lineMap.put(edge.getUniqueID(), edgeLine);
    }

    private Group createEdge(JFXButton sourceButton, JFXButton destinationButton) {
        Line line = new Line();
        line.setStrokeWidth(5);
        line.setStroke(Color.web("#16ae58"));

        line.startXProperty().bind(sourceButton.translateXProperty().add(sourceButton.widthProperty().divide(2)));
        line.startYProperty().bind(sourceButton.translateYProperty().add(sourceButton.heightProperty().divide(2)));

        line.endXProperty()
                .bind(destinationButton.translateXProperty().add(destinationButton.widthProperty().divide(2)));
        line.endYProperty()
                .bind(destinationButton.translateYProperty().add(destinationButton.heightProperty().divide(2)));

        Circle circle = new Circle();
        circle.setRadius(10);
        circle.setStroke(Color.web("#16ae58"));

        var deltaX = line.endXProperty().subtract(line.startXProperty());
        var deltaY = line.endYProperty().subtract(line.startYProperty());

        var xSqrd = deltaX.multiply(deltaX);
        var ySqrd = deltaY.multiply(deltaY);

        var sqrdSum = xSqrd.add(ySqrd);

        DoubleBinding dist = new DoubleBinding() {
        
            {
                super.bind(sqrdSum);
            }
        
            @Override
            protected double computeValue() {
                return Math.sqrt(sqrdSum.get());
            }
        };

        var unitX = deltaX.divide(dist);
        var unitY = deltaY.divide(dist);

        circle.centerXProperty().bind(line.endXProperty().add(unitX.multiply(-60)));
        circle.centerYProperty().bind(line.endYProperty().add(unitY.multiply(-60)));
        
        Group arrow = new Group(line, circle);

        this.canvas.getChildren().addAll(arrow);
        arrow.toBack();
        return arrow;
    }

}

package com.company;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubGenApp extends Application {
    Stage window;
    Scene scene, scene1, scene2, scene3;
    String job, jobAdd1, jobAdd2, architectName, architectAdd1, architectAdd2, architectPhone, genConName, genConAdd1, genConAdd2, genConPhone;

    static ObservableList<String> subSheets = FXCollections.observableArrayList();

    private static final ObservableList<Image> subSheetsImages = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Submittal Generator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label projName = new Label("Project Name:");
        grid.add(projName, 0, 1);

        final TextField pnField = new TextField();
        grid.add(pnField, 1, 1);

        Label projAdd1 = new Label("Project Address Part One:");
        grid.add(projAdd1, 0, 2);

        final TextField pAdd1 = new TextField();
        grid.add(pAdd1, 1, 2);

        Label projAdd2 = new Label("Project Address Part Two:");
        grid.add(projAdd2, 0, 3);

        final TextField pAdd2 = new TextField();
        grid.add(pAdd2, 1, 3);

        Label archName = new Label("Architect Name:");
        grid.add(archName, 0, 4);

        final TextField archNameField = new TextField();
        grid.add(archNameField, 1, 4);

        Label archAdd1 = new Label("Architect Address Part One:");
        grid.add(archAdd1, 0, 5);

        final TextField aAdd1 = new TextField();
        grid.add(aAdd1, 1, 5);

        Label archAdd2 = new Label("Architect Address Part Two:");
        grid.add(archAdd2, 0, 6);

        final TextField aAdd2 = new TextField();
        grid.add(aAdd2, 1, 6);

        Label archPhone = new Label("Architect Phone Number:");
        grid.add(archPhone, 0, 7);

        final TextField archPhoneField = new TextField();
        grid.add(archPhoneField, 1, 7);

        Label gcName = new Label("General Contractor Name:");
        grid.add(gcName, 0, 8);

        final TextField gcNameField = new TextField();
        grid.add(gcNameField, 1, 8);

        Label gcAdd1 = new Label("General Contractor Address Part One:");
        grid.add(gcAdd1, 0, 9);

        final TextField gAdd1 = new TextField();
        grid.add(gAdd1, 1, 9);

        Label gcAdd2 = new Label("General Contractor Address Part Two:");
        grid.add(gcAdd2, 0, 10);

        final TextField gAdd2 = new TextField();
        grid.add(gAdd2, 1, 10);

        Label gcPhone = new Label("General Contractor Phone Number:");
        grid.add(gcPhone, 0, 11);

        final TextField gcPhoneField = new TextField();
        grid.add(gcPhoneField, 1, 11);

        Label archBox = new Label("Architect List:");
        final ChoiceBox architect = new ChoiceBox(FXCollections.observableArrayList(
                "Peacock Architects", "ASD/SKY")
        );
        grid.add(architect, 5, 4);
        grid.add(archBox, 5, 3);

        architect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                switch (newValue.intValue()) {

                    case 0:
                        archNameField.setText("Peacock Partnership");
                        aAdd1.setText("5525 Interstate North Parkway");
                        aAdd2.setText("Atlanta, GA 30328");
                        archPhoneField.setText("404-214-5200");
                        break;

                    case 1:
                        archNameField.setText("ASD|SKY");
                        aAdd1.setText("55 Ivan Allen Jr Boulevard NW Suite 100");
                        aAdd2.setText("Atlanta, GA 30308");
                        archPhoneField.setText("404-688-2255");
                        break;

                    case 2:
                        archNameField.setText("");
                        aAdd1.setText("");
                        aAdd2.setText("");
                        archPhoneField.setText("");
                        break;

                    case 3:
                        archNameField.setText("");
                        aAdd1.setText("");
                        aAdd2.setText("");
                        archPhoneField.setText("");
                        break;
                }
            }
        });

        Label gcBox = new Label("General Contractor List:");
        final ChoiceBox genContractor = new ChoiceBox(FXCollections.observableArrayList(
                "Balfour Beatty", "Batson-Cook", "Brasfield & Gorrie", "DPR Construction", "Van Winkle")
        );
        grid.add(genContractor, 5, 8);
        grid.add(gcBox, 5, 7);

        genContractor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                switch (newValue.intValue()) {

                    case 0:
                        gcNameField.setText("Balfour Beatty");
                        gAdd1.setText("600 Galleria Parkway, Suite 1500");
                        gAdd2.setText("Atlanta, GA 30339");
                        gcPhoneField.setText("678-921-6800");
                        break;

                    case 1:
                        gcNameField.setText("Batson-Cook");
                        gAdd1.setText("200 Galleria Parkway, Suite 1300");
                        gAdd2.setText("Atlanta, GA 30339");
                        gcPhoneField.setText("770-955-1951");
                        break;

                    case 2:
                        gcNameField.setText("Brasfield & Gorrie");
                        gAdd1.setText("1990 Vaughn Road");
                        gAdd2.setText("Kennesaw, GA 30144");
                        gcPhoneField.setText("678-581-6400");
                        break;

                    case 3:
                        gcNameField.setText("DPR Construction");
                        gAdd1.setText("3301 Windy Ridge Parkway SE Suite 500");
                        gAdd2.setText("Atlanta, GA 30339");
                        gcPhoneField.setText("404-264-0404");
                        break;

                    case 4:
                        gcNameField.setText("Van Winkle Construction");
                        gAdd1.setText("1731 Commerce Drive NW Suite 110");
                        gAdd2.setText("Atlanta, GA 30318");
                        gcPhoneField.setText("404-351-9500");
                        break;
                }
            }
        });

        Button btn = new Button("Next");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 12);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 13);

        btn.setOnAction(e -> {
            job = pnField.getText();
            jobAdd1 = pAdd1.getText();
            jobAdd2 = pAdd2.getText();
            architectName = archNameField.getText();
            architectAdd1 = aAdd1.getText();
            architectAdd2 = aAdd2.getText();
            architectPhone = archPhoneField.getText();
            genConName = gcNameField.getText();
            genConAdd1 = gAdd1.getText();
            genConAdd2 = gAdd2.getText();
            genConPhone = gcPhoneField.getText();
            createScene1();
        });
/*
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Button pressed");
                String job = pnField.getText();
                String jobAdd1 = pAdd1.getText();
                String jobAdd2 = pAdd2.getText();
                String architectName = archNameField.getText();
                String architectAdd1 = aAdd1.getText();
                String architectAdd2 = aAdd2.getText();
                String architectPhone = archPhoneField.getText();
                String genConName = gcNameField.getText();
                String genConAdd1 = gAdd1.getText();
                String genConAdd2 = gAdd2.getText();
                String genConPhone = gcPhoneField.getText();
            }
        });
*/
        scene = new Scene(grid, 900, 700);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void createScene1() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        Label title = new Label();
        title.setText("Categories");

        Label mainCatLabel = new Label("Add a Main Category");
        final TextField mainCatField = new TextField();
        Button addMain = new Button();
        addMain.setText("Add Main Category");

        Label subCatLabel = new Label("Add a Sub Category");
        final TextField subCatField = new TextField();
        Button addSub = new Button();
        addSub.setText("Add Sub Category");

        Button button = new Button();
        button.setText("Next");
        // switch to scene 2
        button.setOnAction(e -> {
            /*job = pnField.getText();
            jobAdd1 = pAdd1.getText();
            jobAdd2 = pAdd2.getText();
            architectName = archNameField.getText();
            architectAdd1 = aAdd1.getText();
            architectAdd2 = aAdd2.getText();
            architectPhone = archPhoneField.getText();
            genConName = gcNameField.getText();
            genConAdd1 = gAdd1.getText();
            genConAdd2 = gAdd2.getText();
            genConPhone = gcPhoneField.getText();
            createScene2();*/
        });
        HBox nextBtn = new HBox(10);
        nextBtn.setAlignment(Pos.BOTTOM_RIGHT);
        nextBtn.getChildren().add(button);
        grid.add(nextBtn, 1, 12);


        grid.add(mainCatLabel, 0, 1);
        grid.add(subCatLabel, 1, 1);
        grid.add(mainCatField, 0, 2);
        grid.add(subCatField, 1, 2);


        ListView<String> fileListView = new ListView<>(subSheets);

        HBox mainBtn = new HBox(10);
        mainBtn.getChildren().add(addMain);
        grid.add(mainBtn, 0, 3);

        addMain.setOnAction(e -> {
            Text t;
            if (!mainCatField.getText().isEmpty()) {
                t = new Text(mainCatField.getText());
                subSheetsImages.add(t.snapshot(null, null));
                subSheets.add(mainCatField.getText());
                System.out.println(subSheets);
                System.out.println(subSheetsImages);
            }
        });

        HBox subBtn = new HBox(10);
        subBtn.getChildren().add(addSub);
        grid.add(subBtn, 1, 3);

        addSub.setOnAction(e -> {
            Text t;
            if (!subCatField.getText().isEmpty()) {
                t = new Text("  " + subCatField.getText());
                subSheetsImages.add(t.snapshot(null, null));
                subSheets.add("  " + subCatField.getText());
                System.out.println(subSheets);
                System.out.println(subSheetsImages);
            }
        });


        // Add the Controls
        grid.add(fileListView, 0, 5, 2, 1);
        scene1 = new Scene(grid, 600, 600);

        Button delete = new Button();
        delete.setText("Delete");
        delete.setOnAction(e -> {
            if (!fileListView.getSelectionModel().getSelectedItems().isEmpty()) {
                String item = fileListView.getSelectionModel().getSelectedItem();
                int index = fileListView.getSelectionModel().getSelectedIndex();
                fileListView.getItems().remove(index);
                subSheetsImages.remove(index);
                //subSheets.remove(subSheets.indexOf(item));

            }
        });
        HBox deleteBtn = new HBox(10);
        deleteBtn.setAlignment(Pos.CENTER_RIGHT);
        deleteBtn.getChildren().add(delete);
        grid.add(deleteBtn, 2, 5);

        fileListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2 && !fileListView.getSelectionModel().getSelectedItems().isEmpty()) {
                    String item = fileListView.getSelectionModel().getSelectedItem();
                    int index = fileListView.getSelectionModel().getSelectedIndex();
                    fileListView.getItems().remove(index);
                    subSheetsImages.remove(index);
                    //subSheets.remove(subSheets.indexOf(item));
                }
            }
        });

        fileListView.setCellFactory(param -> new FileCell());

        scene1.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        scene1.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file : db.getFiles()) {
                        filePath = "    " + (file.getAbsolutePath()).substring((("    " + file.getAbsolutePath()).lastIndexOf('\\')) + 1);
                        Text t = new Text(filePath);
                        subSheetsImages.add(t.snapshot(null, null));
                        subSheets.add(filePath);
                        System.out.println(subSheets);
                        System.out.println(subSheetsImages);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        Text scenetitle = new Text("Add all main and sub categories as you would like them to appear on the final document. Drag word " +
                "documents containing a submittal and drop it in this window but outside of the list. Any item can be double clicked to " +
                "delete or dragged to reorder within the list.");
        scenetitle.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 12));
        //scenetitle.setAlignment(Pos.BOTTOM_CENTER);
        grid.add(scenetitle, 0, 6, 2, 1);

        window.setScene(scene1);
    }

    public void createScene2() {
        Label title = new Label();
        title.setText(jobAdd1);

        // button showing pending request
        Button button = new Button();
        button.setText("NextAgain");
        // switch to scene 2
        button.setOnAction(e -> {

        });

        // Layout 1 and adding elements to the layout
        VBox layout2 = new VBox(200);
        layout2.getChildren().addAll(title, button);
        layout2.setAlignment(Pos.CENTER);

        // setting the layout size for the first scene
        scene2 = new Scene(layout2, 900, 650);
        window.setScene(scene2);
    }

    public static void main(String[] args) {
        launch(args);
        for (int i = 0; i < subSheets.size(); i++) {
            System.out.println(subSheets.get(i));
            System.out.println(subSheetsImages.get(i));
        }

    }

    private class FileCell extends ListCell<String> {
        private final ImageView imageView = new ImageView();

        public FileCell() {
            ListCell thisCell = this;

            setAlignment(Pos.CENTER_LEFT);

            setOnDragDetected(event -> {
                if (getItem() == null) {
                    return;
                }

                ObservableList<String> items = getListView().getItems();

                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(getItem());
                dragboard.setDragView(
                        subSheetsImages.get(
                                items.indexOf(
                                        getItem()
                                )
                        )
                );
                dragboard.setContent(content);

                event.consume();
            });

            setOnDragOver(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            });

            setOnDragEntered(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(0.3);
                }
            });

            setOnDragExited(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(1);
                }
            });

            setOnDragDropped(event -> {
                if (getItem() == null) {
                    return;
                }

                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {
                    ObservableList<String> items = getListView().getItems();
                    int draggedIdx = items.indexOf(db.getString());
                    int thisIdx = items.indexOf(getItem());

                    Image temp = subSheetsImages.get(draggedIdx);
                    subSheetsImages.set(draggedIdx, subSheetsImages.get(thisIdx));
                    subSheetsImages.set(thisIdx, temp);

                    items.set(draggedIdx, getItem());
                    items.set(thisIdx, db.getString());

                    List<String> itemscopy = new ArrayList<>(getListView().getItems());
                    getListView().getItems().setAll(itemscopy);

                    success = true;
                }
                event.setDropCompleted(success);

                event.consume();
            });

            setOnDragDone(DragEvent::consume);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty); //item is new item for the cell

            if (empty || item == null) {
                setGraphic(null);
            } else {
                imageView.setImage(
                        subSheetsImages.get(getListView().getItems().indexOf(item)));
                setGraphic(imageView);
            }
        }

    }
}








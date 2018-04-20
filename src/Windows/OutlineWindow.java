package Windows;

import com.company.FileCell;
import com.company.SubGenApp;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import java.io.*;

import static Windows.ProjectInfoWindow.createGrid;
import static com.company.AutoSave.listAddSave;
import static com.company.AutoSave.listDeleteSave;
import static com.company.SubGenApp.*;
import static com.company.SubmittalProcessing.createSubmittal;

public class OutlineWindow {

    public static GridPane outlineGrid() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");


        RowConstraints listRow = new RowConstraints();
        listRow.setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().add(listRow);


        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 50, 25));


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

        ListView<String> fileListView = new ListView<>(subSheets);

        grid.add(mainCatLabel, 2, 1);
        grid.add(subCatLabel, 2, 4);
        grid.add(mainCatField, 1, 1);
        grid.add(subCatField, 1, 4);

        HBox mainBtn = new HBox(10);
        mainBtn.getChildren().add(addMain);
        grid.add(mainBtn, 1, 2);

        addMain.setOnAction(e -> {
            Text t;
            if (!mainCatField.getText().isEmpty() && !subSheets.contains(mainCatField.getText())) {
                t = new Text(mainCatField.getText());
                subSheetsImages.add(t.snapshot(null, null));
                subSheets.add(mainCatField.getText());
                System.out.println(subSheets);
                System.out.println(subSheetsImages);

                listAddSave(t);
            }
        });

        HBox subBtn = new HBox(10);
        subBtn.getChildren().add(addSub);
        grid.add(subBtn, 1, 5);

        addSub.setOnAction(e -> {
            int track = 0;
            String spaces = "";
            Text t;
            if (!subCatField.getText().isEmpty()) {
                if(!subSheets.contains(subCatField.getText())) {
                    t = new Text("    " + subCatField.getText());
                } else {
                    track++;
                    for(int y = 0; y < track; y++) {
                        spaces = spaces + " ";
                    }
                    t = new Text("    " + subCatField.getText() + spaces);
                }
                subSheetsImages.add(t.snapshot(null, null));
                subSheets.add("  " + subCatField.getText());
                System.out.println(subSheets);
                System.out.println(subSheetsImages);

                listAddSave(t);
            }
        });


        // Add the Controls
        grid.add(fileListView, 0, 0, 1, 11);
        SubGenApp.scene1 = new Scene(grid, 700, 750);

        Button delete = new Button();
        delete.setText("Delete");
        delete.setOnAction(e -> {
            if (!fileListView.getSelectionModel().getSelectedItems().isEmpty()) {
                String item = fileListView.getSelectionModel().getSelectedItem();
                int index = fileListView.getSelectionModel().getSelectedIndex();
                fileListView.getItems().remove(index);
                subSheetsImages.remove(index);

                listDeleteSave(index);
            }
        });
        HBox deleteBtn = new HBox(10);
        deleteBtn.setAlignment(Pos.BOTTOM_LEFT);
        deleteBtn.getChildren().add(delete);
        grid.add(deleteBtn, 1, 7);

        fileListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2 && !fileListView.getSelectionModel().getSelectedItems().isEmpty()) {
                    String item = fileListView.getSelectionModel().getSelectedItem();
                    int index = fileListView.getSelectionModel().getSelectedIndex();
                    fileListView.getItems().remove(index);
                    subSheetsImages.remove(index);

                    listDeleteSave(index);
                }
            }
        });

        fileListView.setCellFactory(param -> new FileCell());

        SubGenApp.scene1.setOnDragOver(new EventHandler<DragEvent>() {
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
        SubGenApp.scene1.setOnDragDropped(new EventHandler<DragEvent>() {
            int track = 0;
            String spaces = "";

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file : db.getFiles()) {

                        if(!subSheets.contains("    " + file.getAbsolutePath())) {
                            filePath = "    " + file.getAbsolutePath();
                        } else {
                            track++;
                            for(int y = 0; y < track; y++) {
                                spaces = spaces + " ";
                            }
                            filePath = "    " + file.getAbsolutePath() + spaces;
                        }

                        Text t = new Text("        " + filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.lastIndexOf('.')));
                        subSheetsImages.add(t.snapshot(null, null));
                        subSheets.add(filePath);
                        System.out.println(subSheets);
                        System.out.println(subSheetsImages);

                        listAddSave(t);

                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });


        Button button = new Button();
        button.setText("Create Submittal");
        HBox nextBtn = new HBox(10);
        nextBtn.setAlignment(Pos.BOTTOM_RIGHT);
        nextBtn.getChildren().add(button);
        grid.add(nextBtn, 1, 9);

        // switch to scene 2
        button.setOnAction(e -> {
            createSubmittal();
        });

        return grid;
    }
}

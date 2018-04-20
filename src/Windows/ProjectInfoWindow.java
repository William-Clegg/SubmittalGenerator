package Windows;

import com.company.AutoSave;
import com.company.SubGenApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import static com.company.AutoSave.loadLastList;
import static com.company.AutoSave.loadProjectInfo;
import static com.company.AutoSave.saveProjectInfo;
import static com.company.SubGenApp.*;

public class ProjectInfoWindow {

    public static String job, jobAdd1, jobAdd2, architectName, architectAdd1, architectAdd2, architectPhone, genConName, genConAdd1, genConAdd2, genConPhone;
    public static TextField pnField, pAdd1, pAdd2, archNameField, aAdd1, aAdd2, archPhoneField, gcNameField, gAdd1, gAdd2, gcPhoneField;

    public static GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

            Text scenetitle = new Text("Project Information");
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



        /*-------------------------------------------------------
         *  Creating the ChoiceBox for Architects and its
         *  info.
         */

        Label archBox = new Label("Architect List:");
        final ChoiceBox<String> architect = new ChoiceBox<>(FXCollections.observableArrayList(
                "ASD|SKY", "Freespace Architects", "Lindsay Pope Brayfield Clifford & Assoc.", "Peacock Architects")
        );
        grid.add(architect, 5, 4);
        grid.add(archBox, 5, 3);

        architect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                switch (newValue.intValue()) {

                    case 0:
                        archNameField.setText("ASD|SKY");
                        aAdd1.setText("55 Ivan Allen Jr Boulevard NW Suite 100");
                        aAdd2.setText("Atlanta, GA 30308");
                        archPhoneField.setText("404-688-2255");
                        break;

                    case 1:
                        archNameField.setText("Freespace Architecture");
                        aAdd1.setText("887 West Marietta Street NW, Suite T-103");
                        aAdd2.setText("Atlanta, Georgia 30318");
                        archPhoneField.setText("404-591-5670");
                        break;

                    case 2:
                        archNameField.setText("Lindsay Pope Brayfield Clifford & Associates");
                        aAdd1.setText("344 West Pike Street");
                        aAdd2.setText("Lawrenceville, GA 30046");
                        archPhoneField.setText("770-963-8989");
                        break;

                    case 3:
                        archNameField.setText("Peacock Partnership");
                        aAdd1.setText("5525 Interstate North Parkway");
                        aAdd2.setText("Atlanta, GA 30328");
                        archPhoneField.setText("404-214-5200");
                        break;
                }
            }
        });



        /*-------------------------------------------------------
         *  Creating the ChoiceBox for General Contractors and its
         *  info.
         */

        Label gcBox = new Label("General Contractor List:");
        final ChoiceBox<String> genContractor = new ChoiceBox<>(FXCollections.observableArrayList(
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

        /*-------------------------------------------------------
         *  Add Button to load project info that was entered on
         *  the previous session.
         */

        Button loadLastInfo = new Button("Load Last Info");
        HBox loadLastInfoHB = new HBox(10);
        loadLastInfoHB.setAlignment(Pos.BOTTOM_LEFT);
        loadLastInfoHB.getChildren().add(loadLastInfo);
        grid.add(loadLastInfoHB, 0, 12);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 13);

        loadLastInfo.setOnAction(e -> {

            String[] savedInfo = loadProjectInfo();;
            pnField.setText(savedInfo[0]);
            pAdd1.setText(savedInfo[1]);
            pAdd2.setText(savedInfo[2]);
            archNameField.setText(savedInfo[3]);
            aAdd1.setText(savedInfo[4]);
            aAdd2.setText(savedInfo[5]);
            archPhoneField.setText(savedInfo[6]);
            gcNameField.setText(savedInfo[7]);
            gAdd1.setText(savedInfo[8]);
            gAdd2.setText(savedInfo[9]);
            gcPhoneField.setText(savedInfo[10]);

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

        });

        /*---------------------------------------------------------------------------
         *  Add Button to load submittal outline upon progressing to the next window.
         *  The outline loaded is the final state of the outline upon exit of the
         *  previous session, whether submittal was generated or not.
         */

        Button loadLastList = new Button("Load Last List");
        HBox loadLastListHB = new HBox(10);
        loadLastListHB.setAlignment(Pos.BOTTOM_LEFT);
        loadLastListHB.getChildren().add(loadLastList);
        grid.add(loadLastListHB, 0, 13);

        loadLastList.setOnAction(e -> {
            loadLastList();
        });

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

            String[] savedInfo = new String[11];
            savedInfo[0] = pnField.getText();
            savedInfo[1] = pAdd1.getText();
            savedInfo[2] = pAdd2.getText();
            savedInfo[3] = archNameField.getText();
            savedInfo[4] = aAdd1.getText();
            savedInfo[5] = aAdd2.getText();
            savedInfo[6] = archPhoneField.getText();
            savedInfo[7] = gcNameField.getText();
            savedInfo[8] = gAdd1.getText();
            savedInfo[9] = gAdd2.getText();
            savedInfo[10] = gcPhoneField.getText();

            saveProjectInfo(savedInfo);

            SubGenApp.createScene1();
        });

        return grid;

    }

}

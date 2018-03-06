package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.String.valueOf;

public class SubGenApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Submittal Generator");

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

        TextField pnField = new TextField();
        grid.add(pnField, 1, 1);

        Label projAdd1 = new Label("Project Address Part One:");
        grid.add(projAdd1, 0, 2);

        TextField pAdd1 = new TextField();
        grid.add(pAdd1, 1, 2);

        Label projAdd2 = new Label("Project Address Part Two:");
        grid.add(projAdd2, 0, 3);

        TextField pAdd2 = new TextField();
        grid.add(pAdd2, 1, 3);

        Label archName = new Label("Architect Name:");
        grid.add(archName, 0, 4);

        TextField archNameField = new TextField();
        grid.add(archNameField, 1, 4);

        Label archAdd1 = new Label("Architect Address Part One:");
        grid.add(archAdd1, 0, 5);

        TextField aAdd1 = new TextField();
        grid.add(aAdd1, 1, 5);

        Label archAdd2 = new Label("Architect Address Part Two:");
        grid.add(archAdd2, 0, 6);

        TextField aAdd2 = new TextField();
        grid.add(aAdd2, 1, 6);

        Label archPhone = new Label("Architect Phone Number:");
        grid.add(archPhone, 0, 7);

        TextField archPhoneField = new TextField();
        grid.add(archPhoneField, 1, 7);

        Label gcName = new Label("General Contractor Name:");
        grid.add(gcName, 0, 8);

        TextField gcNameField = new TextField();
        grid.add(gcNameField, 1, 8);

        Label gcAdd1 = new Label("General Contractor Address Part One:");
        grid.add(gcAdd1, 0, 9);

        TextField gAdd1 = new TextField();
        grid.add(gAdd1, 1, 9);

        Label gcAdd2 = new Label("General Contractor Address Part Two:");
        grid.add(gcAdd2, 0, 10);

        TextField gAdd2 = new TextField();
        grid.add(gAdd2, 1, 10);

        Label gcPhone = new Label("General Contractor Phone Number:");
        grid.add(gcPhone, 0, 11);

        TextField gcPhoneField = new TextField();
        grid.add(gcPhoneField, 1, 11);

        Button btn = new Button("Create Submittal");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 12);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 13);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Button pressed");
                String job = valueOf(pnField.getText());
                String jobAdd1 = valueOf(pAdd1.getText());
                String jobAdd2 = valueOf(pAdd2.getText());
                String architectName = valueOf(archNameField.getText());
                String architectAdd1 = valueOf(aAdd1.getText());
                String architectAdd2 = valueOf(aAdd2.getText());
                String architectPhone = valueOf(archPhoneField.getText());
                String genConName = valueOf(gcNameField.getText());
                String genConAdd1 = valueOf(gAdd1.getText());
                String genConAdd2 = valueOf(gAdd2.getText());
                String genConPhone =valueOf(gcPhoneField.getText());
            }
        });

        Scene scene = new Scene(grid, 600, 700);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


}

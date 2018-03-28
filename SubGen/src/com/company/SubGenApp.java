package com.company;

import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class SubGenApp extends Application {
    private Stage window;
    private Scene scene, scene1;
    private String job, jobAdd1, jobAdd2, architectName, architectAdd1, architectAdd2, architectPhone, genConName, genConAdd1, genConAdd2, genConPhone;


    private static ObservableList<String> subSheets = FXCollections.observableArrayList();

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
        final ChoiceBox<String> architect = new ChoiceBox<>(FXCollections.observableArrayList(
                "Peacock Architects", "ASD|SKY", "Freespace Architects")
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
                        archNameField.setText("Freespace Architecture");
                        aAdd1.setText("887 West Marietta Street NW, Suite T-103");
                        aAdd2.setText("Atlanta, Georgia 30318");
                        archPhoneField.setText("404-591-5670");
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

        scene = new Scene(grid, 900, 700);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void createScene1() {

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
            if (!mainCatField.getText().isEmpty() && !subSheets.contains(mainCatField.getText())) {
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
            int track = 0;
            String spaces = "";
            Text t;
            if (!subCatField.getText().isEmpty()  && !subSheets.contains("  " + subCatField.getText())) {
                t = new Text("  " + subCatField.getText());
                subSheetsImages.add(t.snapshot(null, null));
                subSheets.add("  " + subCatField.getText());
                System.out.println(subSheets);
                System.out.println(subSheetsImages);
            } else if (!subCatField.getText().isEmpty()  && subSheets.contains("  " + subCatField.getText())) {
                track++;
                for(int i = 0; i < track; i++) {
                    spaces = spaces + " ";
                }
                t = new Text("  " + subCatField.getText() + spaces);
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
            int track = 0;
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
                            Text t = new Text(filePath);
                            subSheetsImages.add(t.snapshot(null, null));
                            subSheets.add(filePath);
                            System.out.println(subSheets);
                            System.out.println(subSheetsImages);
                        } else {
                            track++;
                            filePath = "    " + file.getAbsolutePath();
                            for(int i = 0; i < track; i++) {
                                filePath = filePath + " ";
                            }
                            Text t = new Text(filePath);
                            subSheetsImages.add(t.snapshot(null, null));
                            subSheets.add(filePath);
                            System.out.println(subSheets);
                            System.out.println(subSheetsImages);
                        }
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });


        Button button = new Button();
        button.setText("Create Submittal");

        // switch to scene 2
        button.setOnAction(e -> {

            XWPFDocument doc = new XWPFDocument();

            CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
            CTPageMar pageMar = sectPr.addNewPgMar();
            pageMar.setLeft(BigInteger.valueOf(0L));
            pageMar.setTop(BigInteger.valueOf(0L));
            pageMar.setRight(BigInteger.valueOf(0L));
            pageMar.setBottom(BigInteger.valueOf(0L));

            XWPFParagraph p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun r = p.createRun();
            String imgFile = "C:\\Users\\Rudy\\Documents\\logoBig.jpg";
            int format;
            format = XWPFDocument.PICTURE_TYPE_JPEG;
            r.addBreak();
            try {
                r.getCTR().insertNewBr(1);
                r.getCTR().insertNewBr(1);
                r.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(480), Units.toEMU(222));
                p.setIndentationLeft(620);
            } catch (Exception a) {
                System.err.println(a);
            }

            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            p1.setVerticalAlignment(TextAlignment.CENTER);

            XWPFRun r0 = p1.createRun();
            r0.getCTR().insertNewBr(1);
            r0.setFontSize(36);
            r0.setBold(true);
            r0.setText("Plumbing Submittal");
            r0.setBold(true);
            r0.setFontFamily("Calibri (Body)");

            XWPFRun r1 = p1.createRun();
            r1.getCTR().insertNewBr(1);
            r1.getCTR().insertNewBr(1);
            r1.setFontSize(46);
            r1.setBold(true);
            r1.setUnderline(UnderlinePatterns.SINGLE);
            r1.setText(job);
            r1.setBold(true);
            r1.setFontFamily("Calibri (Body)");


            XWPFParagraph p2 = doc.createParagraph();
            p2.setAlignment(ParagraphAlignment.CENTER);
            p2.setVerticalAlignment(TextAlignment.BOTTOM);

            XWPFRun r2 = p2.createRun();
            r2.getCTR().insertNewBr(1);
            r2.getCTR().insertNewBr(1);
            r2.setFontSize(20);
            r2.setBold(true);
            r2.setText("Prepared by Stasco Mechanical");
            r2.setBold(true);
            r2.setFontFamily("Calibri (Body)");
            r2.addBreak(BreakType.PAGE);


            XWPFParagraph p3 = doc.createParagraph();
            p3.setAlignment(ParagraphAlignment.CENTER);
            p3.setVerticalAlignment(TextAlignment.TOP);

            XWPFRun r3 = p3.createRun();
            r3.setFontSize(22);
            r3.getCTR().insertNewBr(1);
            r3.getCTR().insertNewBr(1);
            r3.setUnderline(UnderlinePatterns.SINGLE);
            r3.setBold(true);
            r3.setText("Plumbing Submittal");
            r3.setBold(true);
            r3.setFontFamily("Calibri (Body)");


            XWPFParagraph p4 = doc.createParagraph();
            p4.setAlignment(ParagraphAlignment.LEFT);
            p4.setIndentationLeft(4500);
            p4.setVerticalAlignment(TextAlignment.TOP);

            XWPFRun r4 = p4.createRun();
            r4.setFontSize(16);
            r4.setFontFamily("Calibri (Body)");
            r4.getCTR().insertNewBr(1);
            r4.getCTR().insertNewBr(1);
            r4.getCTR().insertNewBr(1);
            r4.getCTR().insertNewBr(1);
            r4.setBold(true);
            r4.setText("Project");
            r4.setBold(true);

            XWPFRun proName = p4.createRun();
            proName.setFontSize(14);
            proName.setFontFamily("Calibri (Body)");
            proName.getCTR().insertNewBr(1);
            proName.setText(job);

            XWPFRun proAdd1 = p4.createRun();
            proAdd1.setFontSize(12);
            proAdd1.setFontFamily("Calibri (Body)");
            proAdd1.getCTR().insertNewBr(1);
            proAdd1.setText(jobAdd1);

            XWPFRun proAdd2 = p4.createRun();
            proAdd2.setFontSize(12);
            proAdd2.setFontFamily("Calibri (Body)");
            proAdd2.getCTR().insertNewBr(1);
            proAdd2.setText(jobAdd2);
            p4.setSpacingAfter(1);


            XWPFParagraph p5 = doc.createParagraph();
            p5.setAlignment(ParagraphAlignment.LEFT);
            p5.setIndentationLeft(4500);
            p5.setVerticalAlignment(TextAlignment.TOP);

            XWPFRun r6 = p5.createRun();
            r6.getCTR().insertNewBr(1);
            r6.getCTR().insertNewBr(1);
            r6.setFontSize(16);
            r6.setFontFamily("Calibri (Body)");
            r6.setBold(true);
            r6.setText("Architect");
            r6.setBold(true);

            XWPFRun archTitle = p5.createRun();
            archTitle.getCTR().insertNewBr(1);
            archTitle.setFontSize(14);
            archTitle.setFontFamily("Calibri (Body)");
            archTitle.setText(architectName);

            XWPFRun archAddress1 = p5.createRun();
            archAddress1.setFontSize(12);
            archAddress1.setFontFamily("Calibri (Body)");
            archAddress1.getCTR().insertNewBr(1);
            archAddress1.setText(architectAdd1);

            XWPFRun archAddress2 = p5.createRun();
            archAddress2.setFontSize(12);
            archAddress2.setFontFamily("Calibri (Body)");
            archAddress2.getCTR().insertNewBr(1);
            archAddress2.setText(architectAdd2);

            XWPFRun archPhoneNum = p5.createRun();
            archPhoneNum.setFontSize(12);
            archPhoneNum.setFontFamily("Calibri (Body)");
            archPhoneNum.getCTR().insertNewBr(1);
            archPhoneNum.setText(architectPhone);
            p5.setSpacingAfter(1);


            XWPFParagraph p6 = doc.createParagraph();
            p6.setAlignment(ParagraphAlignment.LEFT);
            p6.setIndentationLeft(4500);
            p6.setVerticalAlignment(TextAlignment.TOP);

            XWPFRun r8 = p6.createRun();
            r8.getCTR().insertNewBr(1);
            r8.getCTR().insertNewBr(1);
            r8.setFontSize(16);
            r8.setFontFamily("Calibri (Body)");
            r8.setBold(true);
            r8.setText("General Contractor");
            r8.setBold(true);

            XWPFRun gcTitle = p6.createRun();
            gcTitle.getCTR().insertNewBr(1);
            gcTitle.setFontSize(14);
            gcTitle.setFontFamily("Calibri (Body)");
            gcTitle.setText(genConName);

            XWPFRun gcAddress1 = p6.createRun();
            gcAddress1.setFontSize(12);
            gcAddress1.setFontFamily("Calibri (Body)");
            gcAddress1.getCTR().insertNewBr(1);
            gcAddress1.setText(genConAdd1);

            XWPFRun gcAddress2 = p6.createRun();
            gcAddress2.setFontSize(12);
            gcAddress2.setFontFamily("Calibri (Body)");
            gcAddress2.getCTR().insertNewBr(1);
            gcAddress2.setText(genConAdd2);

            XWPFRun gcPhoneNum = p6.createRun();
            gcPhoneNum.setFontSize(12);
            gcPhoneNum.setFontFamily("Calibri (Body)");
            gcPhoneNum.getCTR().insertNewBr(1);
            gcPhoneNum.setText(genConPhone);
            p6.setSpacingAfter(1);


            XWPFParagraph p7 = doc.createParagraph();
            p7.setAlignment(ParagraphAlignment.LEFT);
            p7.setIndentationLeft(4500);
            p7.setVerticalAlignment(TextAlignment.TOP);

            XWPFRun r10 = p7.createRun();
            r10.getCTR().insertNewBr(1);
            r10.getCTR().insertNewBr(1);
            r10.setFontSize(16);
            r10.setFontFamily("Calibri (Body)");
            r10.setBold(true);
            r10.setText("SubContractor");
            r10.setBold(true);

            XWPFRun stascoName = p7.createRun();
            stascoName.getCTR().insertNewBr(1);
            stascoName.setFontSize(14);
            stascoName.setFontFamily("Calibri (Body)");
            stascoName.setText("Stasco Mechanical Contractors");

            XWPFRun stascoAdd1 = p7.createRun();
            stascoAdd1.setFontSize(12);
            stascoAdd1.setFontFamily("Calibri (Body)");
            stascoAdd1.getCTR().insertNewBr(1);
            stascoAdd1.setText("1391 Cobb Parkway North");

            XWPFRun stascoAdd2 = p7.createRun();
            stascoAdd2.setFontSize(12);
            stascoAdd2.setFontFamily("Calibri (Body)");
            stascoAdd2.getCTR().insertNewBr(1);
            stascoAdd2.setText("Marietta, Georgia 30062");

            XWPFRun stascoPhone = p7.createRun();
            stascoPhone.setFontSize(12);
            stascoPhone.setFontFamily("Calibri (Body)");
            stascoPhone.getCTR().insertNewBr(1);
            stascoPhone.setText("770-422-7118");
            stascoPhone.addBreak(BreakType.PAGE);



            XWPFParagraph p8 = doc.createParagraph();
            p8.setAlignment(ParagraphAlignment.CENTER);
            p8.setVerticalAlignment(TextAlignment.TOP);

            XWPFRun r11 = p8.createRun();
            r11.setFontSize(22);
            r11.setFontFamily("Calibri (Body)");

            r11.getCTR().insertNewBr(1);
            r11.getCTR().insertNewBr(1);

            r11.setUnderline(UnderlinePatterns.SINGLE);
            r11.setBold(true);
            r11.setText("INDEX");
            r11.setBold(true);

            XWPFParagraph p9 = doc.createParagraph();
            p9.setAlignment(ParagraphAlignment.LEFT);
            p9.setVerticalAlignment(TextAlignment.TOP);
            p9.setIndentationLeft(1080);

            String curNumString;
            int curNum = 0;
            int curSubNum = 1;
            boolean newCat = false;

            for(int i = 0; i < subSheets.size(); i++) {
                XWPFRun r12 = p9.createRun();
                r12.setFontSize(14);
                r12.setFontFamily("Calibri (Body)");

                if (!subSheets.get(i).substring(0, 2).equals("  ")) {
                    r12.getCTR().insertNewBr(1);
                    r12.getCTR().insertNewBr(1);

                    newCat = true;
                    curSubNum = 1;
                    curNum += 1;

                    curNumString = curNum + ") ";
                    r12.setBold(true);
                    r12.setText(curNumString + subSheets.get(i));
                    r12.setBold(true);

                }
                if (subSheets.get(i).substring(0, 2).equals("  ") && (!subSheets.get(i).substring(0, 4).equals("    ") || subSheets.get(i).length() < 4)) {

                    if(newCat){
                        r12.setFontSize(12);
                        r12.setFontFamily("Calibri (Body)");

                        if(curSubNum == 1) {
                            r12.getCTR().insertNewBr(1);
                        }
                        r12.getCTR().insertNewBr(1);

                        curNumString = "     " + String.valueOf((char) (curSubNum + 64)) + ". ";
                        r12.setText(curNumString + subSheets.get(i));
                        curSubNum += 1;
                    }

                }


            }

            String file;
            int slashIndex;
            int dotIndex;
            CharBuffer slash = CharBuffer.allocate(1);
            slash.append('\\');
            curNum = 1;
            curSubNum = 1;
            newCat = false;
            List<Integer> list = new ArrayList<>();
            int numDocs = 0;
            String subHeader = "";
            int subNumber = 0;
            int lastMain = 0;

            for (int i = 0; i < subSheets.size(); i++) {

                if (!subSheets.get(i).substring(0, 2).equals("  ") || i == subSheets.size()-1) {

                    if(i == subSheets.size() - 1) {

                        XWPFParagraph p11 = doc.createParagraph();
                        p11.setSpacingAfter(0);
                        p11.setSpacingBefore(0);

                        XWPFRun r15 = p11.createRun();
                        r15.setFontSize(12);
                        r15.setFontFamily("Calibri (Body)");

                        if (newCat) {
                            r15.getCTR().insertNewBr(1);
                        }

                        slashIndex = subSheets.get(i).lastIndexOf('\\') + 1;
                        dotIndex = subSheets.get(i).lastIndexOf('.');
                        if (subSheets.get(i).contains(slash)) {
                            file = subSheets.get(i).substring(slashIndex, dotIndex);
                        } else {
                            file = subSheets.get(i);
                        }
                        r15.setText("                                   " + file);

                        newCat = false;
                    }

                    if(i != 0) {

                        for (int j = lastMain; j <= i; j++) {

                            if(subSheets.get(j).substring(0, 2).equals("  ") && !subSheets.get(j).substring(0, 4).equals("    ")) {
                                subNumber++;
                                subHeader = subSheets.get(j);
                            }

                            if(subSheets.get(j).substring(0, 4).equals("    ")) {

                                try {
                                    XWPFDocument subDoc = new XWPFDocument(new FileInputStream(subSheets.get(j).substring(4)));
                                    List<XWPFPictureData> pic = subDoc.getAllPictures();


                                    for(int k = 0; k < pic.size(); k++) {
                                        XWPFParagraph p11 = doc.createParagraph();
                                        p11.setAlignment(ParagraphAlignment.RIGHT);

                                        XWPFRun picRun = p11.createRun();

                                        slashIndex = subSheets.get(j).lastIndexOf('\\') + 1;
                                        dotIndex = subSheets.get(j).lastIndexOf('.');
                                        file = subSheets.get(j).substring(slashIndex, dotIndex);

                                        CTShd cTShd = picRun.getCTR().addNewRPr().addNewShd();
                                        cTShd.setVal(STShd.CLEAR);
                                        cTShd.setColor("auto");
                                        cTShd.setFill("FFFF9e");

                                        picRun.addBreak(BreakType.PAGE);
                                        picRun.setFontSize(14);
                                        picRun.setText(curNum-1 + "-" + String.valueOf((char) (subNumber + 64)) + ") " + subHeader);

                                        BufferedImage oldJpg;
                                        XWPFPictureData pict = pic.get(k);
                                        byte[] data = pict.getData();
                                        oldJpg = ImageIO.read(new ByteArrayInputStream(data));
                                        int w = oldJpg.getWidth();
                                        int h = oldJpg.getHeight();
                                        BufferedImage newJpg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                                        int[] rgb = oldJpg.getRGB(0,0,w,h,null,0,w);
                                        newJpg.setRGB(0,0,w,h,rgb,0,w);;
                                        File out = new File("C:\\Users\\Rudy\\IdeaProjects\\SubGen\\tempImages\\imgFile" + j + k + ".jpg");
                                        ImageIO.write(newJpg, "jpg", out);
                                        picRun.addPicture(new FileInputStream("C:\\Users\\Rudy\\IdeaProjects\\SubGen\\tempImages\\imgFile" + j + k + ".jpg"), XWPFDocument.PICTURE_TYPE_JPEG, subSheets.get(j), Units.toEMU(610), Units.toEMU(770));
                                    }

                                } catch (Exception notFile) {
                                    System.err.println(notFile);
                                }
                            }

                        }
                        subNumber = 0;
                        list.clear();
                    }

                    if(i != subSheets.size() - 1) {
                        XWPFParagraph p11 = doc.createParagraph();
                        p11.setAlignment(ParagraphAlignment.LEFT);
                        p11.setVerticalAlignment(TextAlignment.TOP);
                        p11.setSpacingAfter(0);
                        p11.setSpacingBefore(0);

                        XWPFRun r13 = p11.createRun();
                        r13.setFontSize(20);
                        r13.setFontFamily("Calibri (Body)");

                        r13.addBreak(BreakType.PAGE);
                        r13.getCTR().insertNewBr(1);
                        r13.getCTR().insertNewBr(1);
                        r13.getCTR().insertNewBr(1);
                        r13.getCTR().insertNewBr(1);
                        r13.getCTR().insertNewBr(1);
                        r13.getCTR().insertNewBr(1);
                        r13.getCTR().insertNewBr(1);
                        r13.getCTR().insertNewBr(1);
                        r13.getCTR().insertNewBr(1);

                        curNumString = "                " + curNum + ")      ";
                        r13.setBold(true);
                        r13.setText(curNumString + subSheets.get(i));
                        r13.setBold(true);
                        curSubNum = 1;
                        curNum += 1;
                    }
                    lastMain = i;

                } else if (subSheets.get(i).substring(0, 2).equals("  ") && !subSheets.get(i).substring(0, 4).equals("    ")) {

                    list.add(numDocs);
                    numDocs = 0;
                    newCat = true;

                    XWPFParagraph p11 = doc.createParagraph();
                    p11.setSpacingAfter(0);
                    p11.setSpacingBefore(0);

                    XWPFRun r14 = p11.createRun();
                    r14.setFontSize(16);
                    r14.setFontFamily("Calibri (Body)");

                    r14.getCTR().insertNewBr(1);

                    curNumString = "                    " + String.valueOf((char) (curSubNum + 64)) + ". ";
                    r14.setText(curNumString + subSheets.get(i));
                    curSubNum += 1;

                } else {
                    numDocs++;
                    XWPFParagraph p11 = doc.createParagraph();
                    p11.setSpacingAfter(0);
                    p11.setSpacingBefore(0);

                    XWPFRun r15 = p11.createRun();
                    r15.setFontSize(12);
                    r15.setFontFamily("Calibri (Body)");

                    if (newCat) {
                        r15.getCTR().insertNewBr(1);
                    }

                    slashIndex = subSheets.get(i).lastIndexOf('\\') + 1;
                    dotIndex = subSheets.get(i).lastIndexOf('.');
                    if (subSheets.get(i).contains(slash)) {
                        file = subSheets.get(i).substring(slashIndex, dotIndex);
                    } else {
                        file = subSheets.get(i);
                    }
                    r15.setText("                                   " + file);
                    newCat = false;
                }

            }

            try (FileOutputStream out = new FileOutputStream("simple.docx")) {
                doc.write(out);
            } catch(Exception f) {
                System.err.println();
            }

            File tempFolder = new File("C:\\Users\\Rudy\\IdeaProjects\\SubGen\\tempImages");
            try {
                FileUtils.cleanDirectory(tempFolder);
            } catch(IOException noFolder) {
                System.err.println(noFolder);
            }

            window.close();
        });
        HBox nextBtn = new HBox(10);
        nextBtn.setAlignment(Pos.BOTTOM_RIGHT);
        nextBtn.getChildren().add(button);
        grid.add(nextBtn, 1, 12);

        window.setScene(scene1);


    }

    public static void main(String[] args) {
        launch(args);
    }

    private class FileCell extends ListCell<String> {
        private final ImageView imageView = new ImageView();

        public FileCell() {
            ListCell<String> thisCell = this;

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








package com.company;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import static com.company.SubGenApp.byteList;
import static com.company.SubGenApp.subSheets;
import static com.company.SubGenApp.subSheetsImages;


public class FileCell extends ListCell<String> {
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
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (getItem() == null) {
                //ObservableList<String> items = getListView().getItems();
                int draggedIdx = subSheets.indexOf(db.getString());
                int thisIdx = subSheets.size()-1;
                Image temp = subSheetsImages.get(draggedIdx);
                String temp1 = subSheets.get(draggedIdx);
                System.out.println("SUBSHEETS BEFORE DRAG " + subSheets.toString());
                for(int i = draggedIdx + 1; i < subSheets.size(); i++) {
                    subSheetsImages.set((draggedIdx + (i - (draggedIdx + 1))), subSheetsImages.get(i));
                    //subSheets.set((draggedIdx + (i - (draggedIdx + 1))), subSheets.get(i));

                    subSheets.set(draggedIdx + (i - (draggedIdx + 1)), subSheets.get(i));

                }
                subSheetsImages.set(thisIdx, temp);
                subSheets.set(thisIdx, db.getString());
                List<String> itemscopy = new ArrayList<>(getListView().getItems());
                getListView().getItems().setAll(itemscopy);
                //subSheets.set(thisIdx, temp1);
                success = true;

                System.out.println("SUBSHEETS AFTER DRAG " + subSheets.toString());
                try {
                    FileOutputStream fosDropped = new FileOutputStream("Objectsavefile1.ser");
                    ObjectOutputStream oosDropped = new ObjectOutputStream(fosDropped);
                    oosDropped.writeObject(new ArrayList<String>(subSheets));
                    oosDropped.close();


                    byte[] tempArr = byteList.get(draggedIdx);
                    byteList.set(draggedIdx, byteList.get(thisIdx));
                    byteList.set(thisIdx, tempArr);
                    FileOutputStream fosMain2 = new FileOutputStream("Objectsavefile2.ser");
                    ObjectOutputStream oosMain2 = new ObjectOutputStream(fosMain2);
                    oosMain2.writeObject(byteList);
                    oosMain2.close();


                } catch (FileNotFoundException mainSave) {
                    mainSave.printStackTrace();
                } catch (IOException mainSave) {
                    mainSave.printStackTrace();
                }


                event.setDropCompleted(success);

                event.consume();
            }

            if (db.hasString()) {
                ObservableList<String> items = getListView().getItems();
                int draggedIdx = items.indexOf(db.getString());
                int thisIdx = items.indexOf(getItem());

                String itemTemp = items.get(thisIdx);
                String itemTemp2 = items.get(draggedIdx);

                byte[] tempArr = byteList.get(thisIdx);
                byte[] tempArr2 = byteList.get(draggedIdx);

                Image temp = subSheetsImages.get(thisIdx);
                Image temp2 = subSheetsImages.get(draggedIdx);
                //subSheetsImages.set(draggedIdx, subSheetsImages.get(thisIdx));

                if(draggedIdx > thisIdx) { //if the item is dragged up
                    //subSheetsImages.set(thisIdx, subSheetsImages.get(draggedIdx));
                    //byteList.set(thisIdx, byteList.get(draggedIdx));
                    //items.set(thisIdx, items.get(draggedIdx));
                    for(int i = 0; i <= draggedIdx - thisIdx; i += 1) {
                        /*temp2 = subSheetsImages.get(i);
                        subSheetsImages.set(i, temp);
                        temp = subSheetsImages.get(i+1);
                        subSheetsImages.set(i+1, temp2);

                        tempArr2 = byteList.get(i);
                        byteList.set(i, tempArr);
                        tempArr = byteList.get(i+1);
                        byteList.set(i+1, tempArr2);

                        itemTemp2 = items.get(i);
                        items.set(i, itemTemp);
                        itemTemp = items.get(i+1);
                        items.set(i+1, itemTemp2);*/

                        if(draggedIdx - (i) == thisIdx) {
                            subSheetsImages.set(thisIdx, temp2);
                        } else {
                            subSheetsImages.set(draggedIdx - i, subSheetsImages.get(draggedIdx - (i + 1)));
                        }

                        if(draggedIdx - (i) == thisIdx) {
                            items.set(thisIdx, itemTemp2);
                        } else {
                            items.set(draggedIdx - i, items.get(draggedIdx - (i + 1)));
                        }

                        if(draggedIdx - (i) == thisIdx) {
                            byteList.set(thisIdx, tempArr2);
                        } else {
                            byteList.set(draggedIdx - i, byteList.get(draggedIdx - (i + 1)));
                        }
                    }
                } else { //if item is dragged down
                    //subSheetsImages.set(thisIdx - 1, subSheetsImages.get(draggedIdx));
                    //items.set(thisIdx - 1, items.get(draggedIdx));
                    for(int i = 0; i < thisIdx - draggedIdx; i += 1) {
                        if(i + draggedIdx == thisIdx - 1) {
                            subSheetsImages.set(thisIdx - 1, temp2);
                        } else {
                            subSheetsImages.set(draggedIdx + i, subSheetsImages.get(draggedIdx + i + 1));
                        }

                        if(i + draggedIdx == thisIdx - 1) {
                            items.set(thisIdx - 1, itemTemp2);
                        } else {
                            items.set(draggedIdx + i, items.get(draggedIdx + i + 1));
                        }

                        if(i + draggedIdx == thisIdx - 1) {
                            byteList.set(thisIdx - 1, tempArr2);
                        } else {
                            byteList.set(draggedIdx + i, byteList.get(draggedIdx + i + 1));
                        }
                    }
                }

                List<String> itemscopy = new ArrayList<>(getListView().getItems());
                getListView().getItems().setAll(itemscopy);

                success = true;

                try {
                    FileOutputStream fosDropped = new FileOutputStream("Objectsavefile1.ser");
                    ObjectOutputStream oosDropped = new ObjectOutputStream(fosDropped);
                    oosDropped.writeObject(new ArrayList<String>(subSheets));
                    oosDropped.close();

                    FileOutputStream fosMain2 = new FileOutputStream("Objectsavefile2.ser");
                    ObjectOutputStream oosMain2 = new ObjectOutputStream(fosMain2);
                    oosMain2.writeObject(byteList);
                    oosMain2.close();


                } catch (FileNotFoundException mainSave) {
                    mainSave.printStackTrace();
                } catch (IOException mainSave) {
                    mainSave.printStackTrace();
                }
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
            imageView.setImage(subSheetsImages.get(getListView().getItems().indexOf(item)));
            setGraphic(imageView);
        }
    }

}

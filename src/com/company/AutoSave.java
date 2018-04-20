package com.company;

import Windows.ProjectInfoWindow;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.SubGenApp.*;

public class AutoSave extends ProjectInfoWindow{


    /*---------------------------------------------------------------------------
     *  Method used when Load Last Info is clicked. Loads serialized string array
     *  from the project folder.
     */

    public static String[] loadProjectInfo() {

        String[] savedInfo = new String[11];

        try {
            FileInputStream fis = new FileInputStream("Objectsavefile.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            savedInfo = (String[]) ois.readObject();
            ois.close();

        } catch (IOException savedInfoLoadE) {
            savedInfoLoadE.printStackTrace();
        } catch (ClassNotFoundException noClass) {
            noClass.printStackTrace();
        }

        return savedInfo;
    }




    /*---------------------------------------------------------------------------
     *  Method used when Load Last List is clicked. Loads serialized array lists
     *  in project folder and sets the actual outline String content into subSheets.
     *  Converts snapshots of those strings from byte arrays to readable images
     *  for the list view, which are placed into subSheetsImages.
     */

    public static void loadLastList() {

        try {
            FileInputStream fis = new FileInputStream("Objectsavefile1.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<String> loadSubList = (List<String>) ois.readObject();
            subSheets = FXCollections.observableList(loadSubList);
            System.out.println("The list of Strings " + subSheets.toString());
            ois.close();


            FileInputStream fis2 = new FileInputStream("Objectsavefile2.ser");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            byteListRead = (List<byte[]>) ois2.readObject();
            for (int z = 0; z < byteListRead.size(); z++) {
                InputStream in = new ByteArrayInputStream(byteListRead.get(z));
                BufferedImage buffIm = ImageIO.read(in);
                midList.add(SwingFXUtils.toFXImage(buffIm, null));
                if (z == 0) {
                    subSheetsImages.clear();
                }
                subSheetsImages = FXCollections.observableList(midList);
                File outFile = new File("C:\\Users\\Rudy\\IdeaProjects\\SubGen\\tempImages\\imgTest" + z + ".png");
                ImageIO.write(buffIm, "png", outFile);
                in.close();
            }
            for (int i = 0; i < subSheetsImages.size(); i++) {
                BufferedImage serImage = SwingFXUtils.fromFXImage(subSheetsImages.get(i), null);
                ByteArrayOutputStream s = new ByteArrayOutputStream();
                ImageIO.write(serImage, "png", s);
                byte[] res = s.toByteArray();
                byteList.add(res);
                s.close();
            }


        } catch (IOException savedInfoLoadE) {
            savedInfoLoadE.printStackTrace();
        } catch (ClassNotFoundException noClass) {
            noClass.printStackTrace();
        }
    }




    /*---------------------------------------------------------------------------
     *  Method used when Create Submittal is clicked. Saves serialized string array
     *  in project folder of whatever text was in the fields at the time.
     */

    public static void saveProjectInfo(String[] savedInfo) {


        try {
            FileOutputStream fos = new FileOutputStream("Objectsavefile.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(savedInfo);
            oos.close();


        } catch (FileNotFoundException savedInfoE) {
            savedInfoE.printStackTrace();
        } catch (IOException savedInfoE) {
            savedInfoE.printStackTrace();
        }
    }



    /*---------------------------------------------------------------------------
     *  Method used when any entry is added to the outline. Serializes and saves
     *  updates lists.
     */

    public static void listAddSave(Text t) {

        try {
            FileOutputStream fosMain = new FileOutputStream("Objectsavefile1.ser");
            ObjectOutputStream oosMain = new ObjectOutputStream(fosMain);
            oosMain.writeObject(new ArrayList<String>(subSheets));
            oosMain.close();

            BufferedImage serImage = SwingFXUtils.fromFXImage(t.snapshot(null, null), null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(serImage, "png", s);
            byte[] res = s.toByteArray();
            byteList.add(res);
            s.close();
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




    /*---------------------------------------------------------------------------
     *  Method used when any entry is removed from the outline. Serializes and saves
     *  updates lists.
     */

    public static void listDeleteSave(int index) {

        try {
            FileOutputStream fosDeleteButton = new FileOutputStream("Objectsavefile1.ser");
            ObjectOutputStream oosDeleteButton = new ObjectOutputStream(fosDeleteButton);
            oosDeleteButton.writeObject(new ArrayList<String>(subSheets));
            oosDeleteButton.close();

            byteList.remove(index);
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



    /*---------------------------------------------------------------------------
     *  Method used to save the outline state when any entry is dragged to an
     *  empty line underneath the bottom entry of the outline.
     */

    public static void bottomDragSave(int draggedIdx, int thisIdx) {

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
    }




    /*---------------------------------------------------------------------------
     *  Method used to save the outline state when an entry is dragged to a new
     *  location on the outline.
     */

    public static void listSwapSave() {

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
}

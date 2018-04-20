package OCR;

import net.sourceforge.javaocr.ocrPlugins.mseOCR.OCRScanner;
import net.sourceforge.javaocr.scanner.PixelImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.company.SubGenApp.southernList;
import static com.company.SubGenApp.southernListIndex;
import static com.company.SubGenApp.subSheets;

public class OcrProcessing {

    public static void readSouthernDoc(int i) {
        try {

            boolean debug = true;
            String southernSourceDir = subSheets.get(i + 1).substring(4);
            File sourceFile = new File(southernSourceDir);
            PDDocument document = PDDocument.load(new File(southernSourceDir));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            @SuppressWarnings("unchecked")
            String fileName = sourceFile.getName().replace(".pdf", "");
            int pageNumber = 0;

            OCRScanner ocrScanner = OcrTrain.trainFont(true);

            for (PDPage page : document.getPages()) {
                System.out.println("going through pdf pages");

                if (pageNumber > 1) {
                    BufferedImage image = pdfRenderer.renderImageWithDPI(pageNumber, 190, ImageType.RGB);
                    String imageFilename = "C:\\Users\\Rudy\\IdeaProjects\\SubGen\\ocrTempImages\\imgFile" + i + pageNumber + ".png";

                    ImageIOUtil.writeImage(image, imageFilename, 190);

                    try {
                        image = ImageIO.read(new File(imageFilename));
                    } catch (IOException ocrE) {
                        //ocrE.printStackTrace();
                    }

                    PixelImage pixelImage = new PixelImage(image);

                    pixelImage.toGrayScale(true);

                    pixelImage.filter();

                    String text = ocrScanner.scan(image, 0, 0, 0, 0, null);
                    if (!text.contains("\n") && text.length() > 2) {
                        System.out.println(imageFilename + ":");
                        System.out.println("[" + text + "]");
                        southernListIndex.add(pageNumber);
                        southernList.add(text);

                        System.out.println(southernListIndex);

                    }
                }

                pageNumber++;
            }
            document.close();


        } catch (Exception ocr) {
            ocr.printStackTrace();
        }
    }

}

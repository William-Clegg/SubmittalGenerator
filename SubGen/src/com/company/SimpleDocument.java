package com.company;

import java.io.FileOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;

public class SimpleDocument {

    public static void main(String[] args) throws Exception {

        //String filePath = "C:\\Users\\Rudy\\Desktop\\SubmittalTemplate.docx";
        try {

            XWPFDocument doc = new XWPFDocument();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Please enter job name, then address part one, and address part two:");
            String job = scanner.nextLine();
            String jobAdd1 = scanner.nextLine();
            String jobAdd2 = scanner.nextLine();

            System.out.println("Please enter Architect name, then address part one, address part two, and phone number:");
            String archName = scanner.nextLine();
            String archAdd1 = scanner.nextLine();
            String archAdd2 = scanner.nextLine();
            String archPhone = scanner.nextLine();

            System.out.println("Please enter General Contractor name, then address part one, address part two, and phone number:");
            String gcName = scanner.nextLine();
            String gcAdd1 = scanner.nextLine();
            String gcAdd2 = scanner.nextLine();
            String gcPhone = scanner.nextLine();


                System.out.println("Please enter Category, followed by Sub-Category, filepaths of each submittal in that category, " +
                        "and repeat for all needed Sub-Categories and submittals. Use stop to finish the Category.");

                TreeNode<String> root = new TreeNode<String>("root");
                TreeNode<String> curNode = root;
                String input = "";

                input = scanner.nextLine();

                while(!input.equals("stop")) {


                    curNode = root.addChild(input);
                    input = scanner.nextLine();

                    while(!input.equals("stop")) {

                        curNode = curNode.addChild(input);
                        input = scanner.nextLine();

                        while(!input.equals("stop"))  {

                            curNode.addChild(input);
                            input = scanner.nextLine();

                        }

                        curNode = curNode.parent;
                        input = scanner.nextLine();
                    }

                    curNode = curNode.parent;
                    input = scanner.nextLine();
                }




                for (TreeNode<String> node : root) {
                    String indent = SampleIterating.createIndent(node.getLevel());
                    System.out.println(indent + node.data);
                }

            XWPFParagraph p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun r = p.createRun();
            String imgFile = "C:\\Users\\William\\Downloads\\logo.png";
            int format;
            format = XWPFDocument.PICTURE_TYPE_PNG;
            r.addBreak();
            r.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(512), Units.toEMU(261));

            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            p1.setVerticalAlignment(TextAlignment.CENTER);

            XWPFRun r1 = p1.createRun();
            r1.getCTR().insertNewBr(1);
            r1.setFontSize(48);
            r1.setBold(true);
            r1.setUnderline(UnderlinePatterns.SINGLE);
            r1.setText(job);
            r1.setBold(true);
            r1.setFontFamily("Arial");

            XWPFParagraph p2 = doc.createParagraph();
            p2.setAlignment(ParagraphAlignment.CENTER);
            p2.setVerticalAlignment(TextAlignment.BOTTOM);
            XWPFRun r2 = p2.createRun();
            r2.getCTR().insertNewBr(1);
            r2.getCTR().insertNewBr(1);
            r2.setFontSize(22);
            r2.setBold(true);
            r2.setText("by Stasco Mechanical");
            r2.setBold(true);
            r2.setFontFamily("Arial");

            XWPFParagraph p3 = doc.createParagraph();
            p3.setAlignment(ParagraphAlignment.CENTER);
            p3.setVerticalAlignment(TextAlignment.TOP);
            XWPFRun r3 = p3.createRun();
            r3.setFontSize(22);
            r3.setBold(true);
            r3.setText("Plumbing Submittal");
            r3.setBold(true);
            r3.setFontFamily("Arial");

            XWPFParagraph p4 = doc.createParagraph();
            p4.setAlignment(ParagraphAlignment.LEFT);
            p4.setIndentationLeft(2700);
            p4.setVerticalAlignment(TextAlignment.TOP);
            XWPFRun r4 = p4.createRun();
            r4.setFontSize(16);
            r4.setFontFamily("Arial");
            r4.getCTR().insertNewBr(1);
            r4.getCTR().insertNewBr(1);
            r4.setBold(true);
            r4.setText("Project Name");
            r4.setBold(true);
            XWPFRun proName = p4.createRun();
            proName.setFontSize(16);
            proName.setFontFamily("Arial");
            proName.getCTR().insertNewBr(1);
            proName.setText(job);
            XWPFRun proAdd1 = p4.createRun();
            proAdd1.setFontSize(16);
            proAdd1.setFontFamily("Arial");
            proAdd1.getCTR().insertNewBr(1);
            proAdd1.setText(jobAdd1);
            XWPFRun proAdd2 = p4.createRun();
            proAdd2.setFontSize(16);
            proAdd2.setFontFamily("Arial");
            proAdd2.getCTR().insertNewBr(1);
            proAdd2.setText(jobAdd2);
            p4.setSpacingAfter(1);

            XWPFParagraph p5 = doc.createParagraph();
            p5.setAlignment(ParagraphAlignment.LEFT);
            p5.setIndentationLeft(2700);
            p5.setVerticalAlignment(TextAlignment.TOP);
            XWPFRun r6 = p5.createRun();
            r6.getCTR().insertNewBr(1);
            r6.getCTR().insertNewBr(1);
            r6.setFontSize(16);
            r6.setFontFamily("Arial");
            r6.setBold(true);
            r6.setText("Architect");
            r6.setBold(true);
            XWPFRun archTitle = p5.createRun();
            archTitle.getCTR().insertNewBr(1);
            archTitle.setFontSize(16);
            archTitle.setFontFamily("Arial");
            archTitle.setText(archName);
            XWPFRun archAddress1 = p5.createRun();
            archAddress1.setFontSize(16);
            archAddress1.setFontFamily("Arial");
            archAddress1.getCTR().insertNewBr(1);
            archAddress1.setText(archAdd1);
            XWPFRun archAddress2 = p5.createRun();
            archAddress2.setFontSize(16);
            archAddress2.setFontFamily("Arial");
            archAddress2.getCTR().insertNewBr(1);
            archAddress2.setText(archAdd2);
            XWPFRun archPhoneNum = p5.createRun();
            archPhoneNum.setFontSize(16);
            archPhoneNum.setFontFamily("Arial");
            archPhoneNum.getCTR().insertNewBr(1);
            archPhoneNum.setText("770-422-7118");
            p5.setSpacingAfter(1);

            XWPFParagraph p6 = doc.createParagraph();
            p6.setAlignment(ParagraphAlignment.LEFT);
            p6.setIndentationLeft(2700);
            p6.setVerticalAlignment(TextAlignment.TOP);
            XWPFRun r8 = p6.createRun();
            r8.getCTR().insertNewBr(1);
            r8.getCTR().insertNewBr(1);
            r8.setFontSize(16);
            r8.setFontFamily("Arial");
            r8.setBold(true);
            r8.setText("General Contractor");
            r8.setBold(true);
            XWPFRun gcTitle = p6.createRun();
            gcTitle.getCTR().insertNewBr(1);
            gcTitle.setFontSize(16);
            gcTitle.setFontFamily("Arial");
            gcTitle.setText(gcName);
            XWPFRun gcAddress1 = p6.createRun();
            gcAddress1.setFontSize(16);
            gcAddress1.setFontFamily("Arial");
            gcAddress1.getCTR().insertNewBr(1);
            gcAddress1.setText(archAdd1);
            XWPFRun gcAddress2 = p6.createRun();
            gcAddress2.setFontSize(16);
            gcAddress2.setFontFamily("Arial");
            gcAddress2.getCTR().insertNewBr(1);
            gcAddress2.setText(archAdd2);
            XWPFRun gcPhoneNum = p6.createRun();
            gcPhoneNum.setFontSize(16);
            gcPhoneNum.setFontFamily("Arial");
            gcPhoneNum.getCTR().insertNewBr(1);
            gcPhoneNum.setText("770-422-7118");
            p6.setSpacingAfter(1);

            XWPFParagraph p7 = doc.createParagraph();
            p7.setAlignment(ParagraphAlignment.LEFT);
            p7.setIndentationLeft(2700);
            p7.setVerticalAlignment(TextAlignment.TOP);
            XWPFRun r10 = p7.createRun();
            r10.getCTR().insertNewBr(1);
            r10.getCTR().insertNewBr(1);
            r10.setFontSize(16);
            r10.setFontFamily("Arial");
            r10.setBold(true);
            r10.setText("SubContractor");
            r10.setBold(true);
            XWPFRun stascoName = p7.createRun();
            stascoName.getCTR().insertNewBr(1);
            stascoName.setFontSize(16);
            stascoName.setFontFamily("Arial");
            stascoName.setText("Stasco Mechanical Contractors");
            XWPFRun stascoAdd1 = p7.createRun();
            stascoAdd1.setFontSize(16);
            stascoAdd1.setFontFamily("Arial");
            stascoAdd1.getCTR().insertNewBr(1);
            stascoAdd1.setText("1391 Cobb Parkway North");
            XWPFRun stascoAdd2 = p7.createRun();
            stascoAdd2.setFontSize(16);
            stascoAdd2.setFontFamily("Arial");
            stascoAdd2.getCTR().insertNewBr(1);
            stascoAdd2.setText("Marietta, Georgia 30062");
            XWPFRun stascoPhone = p7.createRun();
            stascoPhone.setFontSize(16);
            stascoPhone.setFontFamily("Arial");
            stascoPhone.getCTR().insertNewBr(1);
            stascoPhone.setText("770-422-7118");
            r2.addBreak(BreakType.PAGE);

            try (FileOutputStream out = new FileOutputStream("simple1.docx")) {
                doc.write(out);
            }

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        /*try (XWPFDocument doc = new XWPFDocument()) {

            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            p1.setBorderBottom(Borders.DOUBLE);
            p1.setBorderTop(Borders.DOUBLE);

            p1.setBorderRight(Borders.DOUBLE);
            p1.setBorderLeft(Borders.DOUBLE);
            p1.setBorderBetween(Borders.SINGLE);

            p1.setVerticalAlignment(TextAlignment.TOP);

            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setText("The quick brown fox");
            r1.setBold(true);
            r1.setFontFamily("Courier");
            r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
            r1.setTextPosition(100);

            XWPFParagraph p2 = doc.createParagraph();
            p2.setAlignment(ParagraphAlignment.RIGHT);

            //BORDERS
            p2.setBorderBottom(Borders.DOUBLE);
            p2.setBorderTop(Borders.DOUBLE);
            p2.setBorderRight(Borders.DOUBLE);
            p2.setBorderLeft(Borders.DOUBLE);
            p2.setBorderBetween(Borders.SINGLE);

            XWPFRun r2 = p2.createRun();
            r2.setText("jumped over the lazy dog");
            r2.setStrikeThrough(true);
            r2.setFontSize(20);

            XWPFRun r3 = p2.createRun();
            r3.setText("and went away");
            r3.setStrikeThrough(true);
            r3.setFontSize(20);
            r3.setSubscript(VerticalAlign.SUPERSCRIPT);


            XWPFParagraph p3 = doc.createParagraph();
            p3.setWordWrapped(true);
            p3.setPageBreak(true);

            //p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
            p3.setAlignment(ParagraphAlignment.BOTH);
            p3.setSpacingBetween(25, LineSpacingRule.EXACT);

            p3.setIndentationFirstLine(600);


            XWPFRun r4 = p3.createRun();
            r4.setTextPosition(20);
            r4.setText("To be, or not to be: that is the question: "
                    + "Whether 'tis nobler in the mind to suffer "
                    + "The slings and arrows of outrageous fortune, "
                    + "Or to take arms against a sea of troubles, "
                    + "And by opposing end them? To die: to sleep; ");
            r4.addBreak(BreakType.PAGE);
            r4.setText("No more; and by a sleep to say we end "
                    + "The heart-ache and the thousand natural shocks "
                    + "That flesh is heir to, 'tis a consummation "
                    + "Devoutly to be wish'd. To die, to sleep; "
                    + "To sleep: perchance to dream: ay, there's the rub; "
                    + ".......");
            r4.setItalic(true);
//This would imply that this break shall be treated as a simple line break, and break the line after that word:

            XWPFRun r5 = p3.createRun();
            r5.setTextPosition(-10);
            r5.setText("For in that sleep of death what dreams may come");
            r5.addCarriageReturn();
            r5.setText("When we have shuffled off this mortal coil,"
                    + "Must give us pause: there's the respect"
                    + "That makes calamity of so long life;");
            r5.addBreak();
            r5.setText("For who would bear the whips and scorns of time,"
                    + "The oppressor's wrong, the proud man's contumely,");

            r5.addBreak(BreakClear.ALL);
            r5.setText("The pangs of despised love, the law's delay,"
                    + "The insolence of office and the spurns" + ".......");

            try (FileOutputStream out = new FileOutputStream("simple.docx")) {
                doc.write(out);
            }
        }*/
    }
}

package com.wonderskool.bo.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF
{
  private static Rectangle rect = null;

  private static HashMap<String, String> resultMap = new HashMap<String, String>();
  private static HashMap<String, String> profMap = new HashMap<String, String>();

  public static void processFiles(String instName, String enrollNo, JLabel errMsgLbl) throws Exception {
    getProfession(enrollNo, errMsgLbl);
    createProfessionPdf(instName, enrollNo, errMsgLbl);
    addStaticPart2Pdf(instName, enrollNo, errMsgLbl);
  }

  private static void createProfessionPdf(String instName, String enrollNo, JLabel errMsgLbl) throws Exception {
    String filename = Utility.systVariables.getProperty("DESTINATION_FILE") + instName;
    File file = new File(filename);
    file.mkdirs();
    filename = filename + "\\" + enrollNo + "Temp.pdf";
    Document document = new Document();
    RandomAccessFile raf = new RandomAccessFile(filename, "rw");
    raf.close();
    FileOutputStream out = new FileOutputStream(filename);
    PdfWriter writer = PdfWriter.getInstance(document, out);
    document.open();
    rect = Utility.getRectangle(document);
    PdfContentByte cb = writer.getDirectContent();
    for (String key : TraitVariablesLst.dataMap.keySet()) {
      createGraphRecord(cb, enrollNo, document, key, key, TraitVariablesLst.dataMap.get(key), errMsgLbl);
    }
    document.close();
    out.close();
  }

  private static void addStaticPart2Pdf(String instName, String enrollNo, JLabel errMsgLbl) throws Exception {
    Document document = new Document();
    String filename = Utility.systVariables.getProperty("DESTINATION_FILE") + instName + "\\" + enrollNo + ".pdf";
    String sourceFilename = Utility.systVariables.getProperty("DESTINATION_FILE") + instName + "\\" + enrollNo + "Temp.pdf";
    RandomAccessFile raf = new RandomAccessFile(filename, "rw");
    raf.close();
    FileOutputStream out = new FileOutputStream(filename);
    PdfWriter writer = PdfWriter.getInstance(document, out);
    document.open();
    PdfContentByte cb = writer.getDirectContent();
    cb.setColorStroke(BaseColor.BLACK);
    cb.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
    cb.stroke();
    PdfReader[] reader = { new PdfReader(Utility.systVariables.getProperty("STATIC_FILE")), new PdfReader(sourceFilename), new PdfReader(Utility.systVariables.getProperty("STATIC_FILE")) };
    for (int i = 0; i < 3; i++) {
      PdfReader readerTemp = reader[i];
      int start = i == 2 ? 9 : 1;
      int end = i == 1 ? 4 : i == 2 ? 12 : 3;
      for (int j = start; j <= end; j++) {
        cb.setColorStroke(BaseColor.BLACK);
        cb.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
        cb.stroke();
        cb.addTemplate(writer.getImportedPage(readerTemp, j), 0.0F, 0.0F);
        if ((i == 0) && (j == 1)) {
          document.add(new Chunk(enrollNo, new Font(BaseFont.createFont("Times-BoldItalic", "Cp1252", false), 16.0F, 0)));
        }
        document.newPage();
      }
      readerTemp.close();
    }
    int counter = 0;
    document.add(new Paragraph("\r\n"));
    Chunk professionChunk = new Chunk("Career Recommendations");
    professionChunk.setFont(new Font(BaseFont.createFont("Times-BoldItalic", "Cp1252", false), 18.0F, 4));
    document.add(professionChunk);
    PdfPTable table = new PdfPTable(2);
    PdfPCell cell1 = new PdfPCell();
    Paragraph p1 = new Paragraph("S no.");
    p1.setFont(new Font(BaseFont.createFont("Times-BoldItalic", "Cp1252", false), 12.0F, 3));
    cell1.addElement(p1);
    table.addCell(cell1);
    PdfPCell cell2 = new PdfPCell();
    Paragraph p2 = new Paragraph("Profession");
    p2.setFont(new Font(BaseFont.createFont("Times-BoldItalic", "Cp1252", false), 12.0F, 3));
    cell2.addElement(p2);
    table.addCell(cell2);

    table.setHeaderRows(1);
    for (String profession : ((String)profMap.get("FINAL")).split(",")) {
      table.addCell(String.valueOf(++counter) + ".");
      table.addCell(profession);
    }

    document.add(new Paragraph("\r\n"));
    document.add(table);
    cb.setColorStroke(BaseColor.BLACK);
    cb.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
    cb.stroke();
    document.close();
    out.close();
    new File(sourceFilename).delete();
  }

  private static String getStrm4Profession(String profession, JLabel errMsgLbl) throws Exception {
    if (!Utility.getjdbcconnection(errMsgLbl))
      throw new RuntimeException(errMsgLbl.getText());
    ResultSet dataSet = Utility.conn.createStatement().executeQuery("SELECT stream FROM profession_stream_tbl WHERE LOWER(profession) = '?'".replace("?", profession.toLowerCase()));
    Utility.finishjdbcconnection();
    String stream = "";
    if (dataSet.next())
      stream = dataSet.getString(1);
    return stream;
  }

  public static String getCodeDesc(String codeType, String code, JLabel errMsgLbl) throws Exception {
    if (!Utility.getjdbcconnection(errMsgLbl))
      throw new RuntimeException(errMsgLbl.getText());
    PreparedStatement prep = Utility.conn.prepareStatement("SELECT description FROM codes_tbl WHERE code_type = ? AND parent_code = ? AND code = ?");
    prep.setString(1, codeType);
    prep.setString(2, "");
    prep.setString(3, code);
    ResultSet dataSet = prep.executeQuery();
    Utility.finishjdbcconnection();
    if (dataSet.next())
      return dataSet.getString(1);
    return "";
  }

  private static void getProfession(String enrollNo, JLabel errMsgLbl) throws Exception {
    if (!Utility.getjdbcconnection(errMsgLbl))
      throw new RuntimeException(errMsgLbl.getText());
    ResultSet dataSet = Utility.conn.createStatement().executeQuery(QueryCollection.STUDENT_PROFESSION.GET_EXISTING_DATA.replace("?", enrollNo.toLowerCase()));
    Utility.finishjdbcconnection();
    while (dataSet.next()) {
      resultMap.put(dataSet.getString(3), dataSet.getString(4));
      profMap.put(dataSet.getString(3), dataSet.getString(5));
    }
  }

  private static void createGraphRecord(PdfContentByte cb, String enrollNo, Document document, String title, String xAxisName, List<String> dataLst, JLabel errMsgLbl) throws Exception {
    cb.setColorStroke(BaseColor.BLACK);
    cb.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
    cb.stroke();
    Chunk miChunk = new Chunk(title);
    miChunk.setFont(new Font(BaseFont.createFont("Times-BoldItalic", "Cp1252", false), 18.0F, 4));
    document.add(miChunk);
    document.add(new Paragraph("\r\n"));
    document.add(GenerateCharts.getCharts(enrollNo, null, dataLst, title, xAxisName));
    document.add(new Paragraph("\r\n"));
    int counter = 0;
    if (title.equalsIgnoreCase(Const.Code_Type.QUALITY)) {
      for (String criteria : (resultMap.get(title)).split(",")) {
        Chunk chunk = new Chunk(++counter + ". " + criteria);
        chunk.setFont(new Font(BaseFont.createFont("Times-BoldItalic", "Cp1252", false), 14.0F, 0));
        document.add(chunk);
        document.add(new Paragraph("\r\n"));
        document.addTitle(enrollNo);
      }
    }
    else {
      for (String criteria : (resultMap.get(title)).split(",")) {
        String desc = getCodeDesc(title, criteria, errMsgLbl);
        Chunk chunk = new Chunk(criteria);
        chunk.setFont(new Font(BaseFont.createFont("Times-BoldItalic", "Cp1252", false), 14.0F, 4));
        document.add(chunk);
        document.add(new Paragraph("\r\n"));
        Chunk chunk1 = new Chunk(desc);
        chunk1.setFont(new Font(BaseFont.createFont("Times-Roman", "Cp1252", false), 10.0F, 0));
        document.add(chunk1);
        document.add(new Paragraph("\r\n"));
        document.addTitle(enrollNo);
      }
    }
    document.newPage();
  }
}
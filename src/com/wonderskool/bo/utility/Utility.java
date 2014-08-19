package com.wonderskool.bo.utility;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.wonderskool.bo.common.MenuFrame;

public class Utility
{
  public static Connection conn;
  public static Properties systVariables = new Properties();

  public static String userName = "";
  public static String instName = "";

  public static String getPath(String[] args) {
    if ((args == null) || (args.length == 0))
      return "";
    StringBuffer path = new StringBuffer();
    for (int i = 0; i < args.length; i++) {
      if (i == 0)
        path = path.append(args[i]);
      else
        path = path.append(File.separator + args[i]);
    }
    return path.toString();
  }

  public static void callCancel(JFrame sourceFrame) {
    finishjdbcconnection();
    startFrame(new MenuFrame());
    sourceFrame.dispose();
  }

  public static double parseDouble(String text) {
    if (isNullEmpty(text)) {
      return 0.0D;
    }
    return Double.parseDouble(text);
  }

  public static int parseInteger(String text) {
    if (isNullEmpty(text)) {
      return 0;
    }
    return Integer.parseInt(text);
  }

  public static boolean getjdbcconnection(JLabel errMsgLbl)
  {
    try {
      Class.forName(systVariables.getProperty("DRIVER_CLASS"));
      String url = systVariables.getProperty("CONN_URL");
      conn = DriverManager.getConnection(url, systVariables.getProperty("DB_USER_NAME"), systVariables.getProperty("DB_PASSWORD"));
      return true;
    } catch (Exception f) {
      f.printStackTrace();
      errMsgLbl.setText("Error:" + f.getMessage());
    }return false;
  }

  public static void finishjdbcconnection()
  {
    try {
      if ((conn != null) && (!conn.isClosed()))
        conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static long datetolong(Date date) {
    return date.getTime();
  }

  public static long stringDate2Long(String date) {
    if (isNullEmpty(date)) {
      return 0L;
    }
    Date datelong = null;
    try
    {
      DateFormat formatter;
      if (date.indexOf(47) > -1)
        formatter = new SimpleDateFormat("dd/MM/yyyy");
      else
        formatter = new SimpleDateFormat("dd-MM-yyyy");
      datelong = formatter.parse(date);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return datelong.getTime();
  }

  public static void startFrame(JFrame targetFrame) {
    targetFrame.setLocationRelativeTo(null);
    targetFrame.setResizable(false);
    targetFrame.setVisible(true);
  }

  public static String long2StringDate(Long date) {
    if ((date == null) || (date.longValue() == 0L))
      return "";
    Date dt = new Date(date.longValue());
    Calendar cal = new GregorianCalendar();
    cal.setTime(dt);
    int month = cal.get(2) + 1;
    return cal.get(5) + "-" + month + "-" + cal.get(1);
  }

  public static boolean isNullEmpty(String data)
  {
    return (data == null) || (data.equalsIgnoreCase(""));
  }

  public static void initCodeCombo(JComboBox comboBox, String codeType, JLabel errMsgLbl, boolean allIncluded) {
    comboBox.removeAllItems();
    comboBox.addItem(allIncluded ? "ALL" : "");
    if (!getjdbcconnection(errMsgLbl))
      return;
    try {
      PreparedStatement getDataStmt = conn.prepareStatement("SELECT code FROM codes_tbl WHERE code_type = ? AND parent_code = ? AND is_deleted = 0 ORDER BY LOWER(code)");
      getDataStmt.setString(1, codeType);
      getDataStmt.setString(2, "");
      ResultSet dataSet = getDataStmt.executeQuery();
      while (dataSet.next()) {
        comboBox.addItem(dataSet.getString(1));
      }
      finishjdbcconnection();
    }
    catch (Exception e) {
      errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  public static void initCodeCombo(JComboBox comboBox, String codeType, String prntCode, JLabel errMsgLbl, boolean allIncluded) {
    if (!getjdbcconnection(errMsgLbl))
      return;
    comboBox.removeAllItems();
    comboBox.addItem(allIncluded ? "ALL" : "");
    try {
      PreparedStatement getDataStmt = conn.prepareStatement("SELECT code FROM codes_tbl WHERE code_type = ? AND parent_code = ? AND is_deleted = 0 ORDER BY LOWER(code)");
      getDataStmt.setString(1, codeType);
      getDataStmt.setString(2, prntCode);
      ResultSet dataSet = getDataStmt.executeQuery();
      while (dataSet.next()) {
        comboBox.addItem(dataSet.getString(1));
      }
      finishjdbcconnection();
    }
    catch (Exception e) {
      errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  public static String getInstName() {
    return instName;
  }

  public static String getStr(Collection<String> criteriaStage, int topSxnCutOff) {
    StringBuffer str = new StringBuffer();
    for (String data : criteriaStage) {
      topSxnCutOff--;
      if (data.indexOf(":") > -1)
        str.append(data.split(":")[1] + ",");
      else
        str.append(data + ",");
      if (topSxnCutOff == 0)
        break;
    }
    return str.toString();
  }

  public static boolean isNumeric(String text) {
    for (int i = 0; i < text.length(); i++) {
      if (!Character.isDigit(text.charAt(i)))
        return false;
    }
    return true;
  }

  public static Rectangle getRectangle(Document document) {
    Rectangle pageRect = new Rectangle(document.getPageSize());
    pageRect.setLeft(pageRect.getLeft() + 20.0F);
    pageRect.setRight(pageRect.getRight() - 20.0F);
    pageRect.setTop(pageRect.getTop() - 20.0F);
    pageRect.setBottom(pageRect.getBottom() + 20.0F);
    return pageRect;
  }

  public static void initInstCombo(JComboBox comboBox, JLabel errMsgLbl) {
    if (!getjdbcconnection(errMsgLbl))
      return;
    comboBox.removeAllItems();
    comboBox.addItem("<NONE>");
    try {
      PreparedStatement getDataStmt = conn.prepareStatement("SELECT DISTINCT(inst_name) FROM user_tbl ORDER BY inst_name");
      ResultSet dataSet = getDataStmt.executeQuery();
      while (dataSet.next()) {
        comboBox.addItem(dataSet.getString(1));
      }
      finishjdbcconnection();
    }
    catch (Exception e) {
      errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  public static void initSystVariables() {
    String filePath = System.getenv("SYST_VAR");
    if (isNullEmpty(filePath))
      throw new RuntimeException("System Variable not set!!!");
    try {
      FileInputStream fis = new FileInputStream(filePath);
      systVariables.clear();
      systVariables.load(fis);
      fis.close();
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
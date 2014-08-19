package com.wonderskool.main.bo;

import java.io.File;
import java.sql.PreparedStatement;

import jxl.Sheet;
import jxl.Workbook;

import com.wonderskool.bo.utility.Utility;

public class ImportStudents
{
  public static void main(String[] args)
  {
    try
    {
      Utility.initSystVariables();
      File inputWorkbook = new File("E:\\Assignments\\Saurabh\\DAV\\shps_students_excel.xls");
      Utility.getjdbcconnection(null);

      Workbook w = Workbook.getWorkbook(inputWorkbook);
      Sheet sheet = w.getSheet(0);
      String enrollNo = "";
      PreparedStatement saveDataStmt = Utility.conn.prepareStatement("INSERT INTO student_tbl VALUES (?, ?, ?, ?, ?, ?)");
      for (int j = 2; j < sheet.getRows(); j++) {
        enrollNo = sheet.getCell(0, j).getContents();
        saveDataStmt.setString(1, "SHPS");
        saveDataStmt.setString(2, enrollNo);
        saveDataStmt.setString(3, enrollNo);
        saveDataStmt.setLong(4, 999999999999999L);
        saveDataStmt.setString(5, "");
        saveDataStmt.setString(6, "");
        saveDataStmt.executeUpdate();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
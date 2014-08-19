package com.wonderskool.bo.excel;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

import com.wonderskool.bo.utility.Utility;

public class CriteriaDataExcelReader
{
  public void read(String inputFile, HashMap<String, Integer> miMap, HashMap<String, Integer> cmplxPersMap, HashMap<String, Integer> abilityMap, HashMap<String, Integer> qualityMap, int strmColNum)
    throws Exception
  {
    PreparedStatement criteriaInsertData = Utility.conn.prepareStatement("INSERT INTO profession_criteria VALUES (?, ?)");
    PreparedStatement streamInsertData = Utility.conn.prepareStatement("INSERT INTO profession_stream_tbl VALUES (?, ?)");
    File inputWorkbook = new File(inputFile);

    Workbook w = Workbook.getWorkbook(inputWorkbook);
    Sheet sheet = w.getSheet(0);
    String profession = "";
    String stream = "";
    for (int j = 2; j < sheet.getRows(); j++) {
      profession = sheet.getCell(0, j).getContents();
      stream = sheet.getCell(strmColNum, j).getContents();
      for (String criteria : miMap.keySet()) {
        if (!Utility.isNullEmpty(sheet.getCell(((Integer)miMap.get(criteria)).intValue(), j).getContents())) {
          criteriaInsertData.setString(1, profession);
          criteriaInsertData.setString(2, criteria);
          criteriaInsertData.executeUpdate();
        }
      }

      for (String criteria : cmplxPersMap.keySet()) {
        if (!Utility.isNullEmpty(sheet.getCell(((Integer)cmplxPersMap.get(criteria)).intValue(), j).getContents())) {
          criteriaInsertData.setString(1, profession);
          criteriaInsertData.setString(2, criteria);
          criteriaInsertData.executeUpdate();
        }
      }

      for (String criteria : abilityMap.keySet()) {
        if (!Utility.isNullEmpty(sheet.getCell(((Integer)abilityMap.get(criteria)).intValue(), j).getContents())) {
          criteriaInsertData.setString(1, profession);
          criteriaInsertData.setString(2, criteria);
          criteriaInsertData.executeUpdate();
        }
      }

      for (String criteria : qualityMap.keySet()) {
        if (!Utility.isNullEmpty(sheet.getCell(((Integer)qualityMap.get(criteria)).intValue(), j).getContents())) {
          criteriaInsertData.setString(1, profession);
          criteriaInsertData.setString(2, criteria);
          criteriaInsertData.executeUpdate();
        }
      }

      streamInsertData.setString(1, profession);
      streamInsertData.setString(2, stream);
      streamInsertData.executeUpdate();
    }
  }
}
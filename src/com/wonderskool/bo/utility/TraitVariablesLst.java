package com.wonderskool.bo.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TraitVariablesLst
{
  public static LinkedHashMap<String, List<String>> dataMap = new LinkedHashMap<String, List<String>>();

  public static void initTraitMap() {
    if (!Utility.getjdbcconnection(null))
      return;
    try
    {
      PreparedStatement prep = Utility.conn.prepareStatement("SELECT code FROM codes_tbl WHERE code_type = ? AND parent_code = ? AND is_deleted = 0 ORDER BY LOWER(code)");
      for (String str : Const.Code_Type.getProfTraitLst()) {
        dataMap.put(str, new ArrayList<String>());
      }
      ResultSet dataSet = null;
      for (String key : dataMap.keySet()) {
        prep.setString(1, key);
        prep.setString(2, "");
        dataSet = prep.executeQuery();
        while (dataSet.next()) {
          (dataMap.get(key)).add(dataSet.getString(1));
        }
      }
      Utility.finishjdbcconnection();

      initModel();
    } catch (Exception e) {
    	e.printStackTrace();
      throw new RuntimeException("Unable to load system data!!!");
    }
  }

  public static void initModel() {
    TableModels.MITableModel.data = new Object[(dataMap.get("Multiple Intelligences")).size()][2];
    for (int i = 0; i < (dataMap.get("Multiple Intelligences")).size(); i++) {
      TableModels.MITableModel.data[i][0] = (dataMap.get("Multiple Intelligences")).get(i);
      TableModels.MITableModel.data[i][1] = null;
    }

    TableModels.CmplxPersTableModel.data = new Object[(dataMap.get("Complex Personality Graph")).size()][2];
    for (int i = 0; i < (dataMap.get("Complex Personality Graph")).size(); i++) {
      TableModels.CmplxPersTableModel.data[i][0] = (dataMap.get("Complex Personality Graph")).get(i);
      TableModels.CmplxPersTableModel.data[i][1] = null;
    }

    TableModels.AbilityTableModel.data = new Object[(dataMap.get("Ability Graph")).size()][2];
    for (int i = 0; i < (dataMap.get("Ability Graph")).size(); i++) {
      TableModels.AbilityTableModel.data[i][0] = (dataMap.get("Ability Graph")).get(i);
      TableModels.AbilityTableModel.data[i][1] = null;
    }

    TableModels.QualityTableModel.data = new Object[(dataMap.get("Quality Graph")).size()][2];
    for (int i = 0; i < (dataMap.get("Quality Graph")).size(); i++) {
      TableModels.QualityTableModel.data[i][0] = (dataMap.get("Quality Graph")).get(i);
      TableModels.QualityTableModel.data[i][1] = null;
    }

    TableModels.MotivatorTableModel.data = new Object[dataMap.get("Motivators").size()][2];
    for (int i = 0; i < (dataMap.get("Motivators")).size(); i++) {
      TableModels.MotivatorTableModel.data[i][0] = (dataMap.get("Motivators")).get(i);
      TableModels.MotivatorTableModel.data[i][1] = null;
    }
  }
}
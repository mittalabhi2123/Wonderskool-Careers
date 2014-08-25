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
    TableModels.MITableModel.data = new Object[(dataMap.get(Const.Code_Type.MI)).size()][2];
    for (int i = 0; i < (dataMap.get(Const.Code_Type.MI)).size(); i++) {
      TableModels.MITableModel.data[i][0] = (dataMap.get(Const.Code_Type.MI)).get(i);
      TableModels.MITableModel.data[i][1] = null;
    }

    TableModels.CmplxPersTableModel.data = new Object[(dataMap.get(Const.Code_Type.CMPLX_PERS)).size()][2];
    for (int i = 0; i < (dataMap.get(Const.Code_Type.CMPLX_PERS)).size(); i++) {
      TableModels.CmplxPersTableModel.data[i][0] = (dataMap.get(Const.Code_Type.CMPLX_PERS)).get(i);
      TableModels.CmplxPersTableModel.data[i][1] = null;
    }

    TableModels.AbilityTableModel.data = new Object[(dataMap.get(Const.Code_Type.ABILITY)).size()][2];
    for (int i = 0; i < (dataMap.get(Const.Code_Type.ABILITY)).size(); i++) {
      TableModels.AbilityTableModel.data[i][0] = (dataMap.get(Const.Code_Type.ABILITY)).get(i);
      TableModels.AbilityTableModel.data[i][1] = null;
    }

    TableModels.QualityTableModel.data = new Object[(dataMap.get(Const.Code_Type.QUALITY)).size()][2];
    for (int i = 0; i < (dataMap.get(Const.Code_Type.QUALITY)).size(); i++) {
      TableModels.QualityTableModel.data[i][0] = (dataMap.get(Const.Code_Type.QUALITY)).get(i);
      TableModels.QualityTableModel.data[i][1] = null;
    }

    TableModels.MotivatorTableModel.data = new Object[dataMap.get(Const.Code_Type.MOTIVATORS).size()][2];
    for (int i = 0; i < (dataMap.get(Const.Code_Type.MOTIVATORS)).size(); i++) {
      TableModels.MotivatorTableModel.data[i][0] = (dataMap.get(Const.Code_Type.MOTIVATORS)).get(i);
      TableModels.MotivatorTableModel.data[i][1] = null;
    }

    TableModels.WorkingStyleTableModel.data = new Object[dataMap.get(Const.Code_Type.WORKING_STYLE).size()][2];
    for (int i = 0; i < (dataMap.get(Const.Code_Type.WORKING_STYLE)).size(); i++) {
      TableModels.WorkingStyleTableModel.data[i][0] = (dataMap.get(Const.Code_Type.WORKING_STYLE)).get(i);
      TableModels.WorkingStyleTableModel.data[i][1] = null;
    }
  }
}
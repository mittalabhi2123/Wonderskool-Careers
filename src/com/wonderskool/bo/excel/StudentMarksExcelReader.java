package com.wonderskool.bo.excel;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JLabel;

import jxl.Sheet;
import jxl.Workbook;

import com.wonderskool.bo.utility.Const;
import com.wonderskool.bo.utility.QueryCollection;
import com.wonderskool.bo.utility.Utility;

public class StudentMarksExcelReader {
	public String read(String inputFile, HashMap<String, Integer> miMap, HashMap<String, Integer> cmplxPersMap,
			HashMap<String, Integer> abilityMap, HashMap<String, Integer> qualityMap,
			HashMap<String, Integer> motivatorsMap, HashMap<String, Integer> workingStyleMap, JLabel errMsgLbl)
			throws Exception {
		StringBuffer result = new StringBuffer();
		String instName = Utility.getInstName();
		if (!Utility.getjdbcconnection(errMsgLbl))
			return "Error in getting connection";
		PreparedStatement insertData = Utility.conn
				.prepareStatement("INSERT INTO student_result VALUES (?, ?, ?, ?, ?)");
		ResultSet stuDataSet = null;
		File inputWorkbook = new File(inputFile);

		Workbook w = Workbook.getWorkbook(inputWorkbook);
		Sheet sheet = w.getSheet(0);
		String enrollNo = "";
		HashMap<String, String> profTraitData = new HashMap<String, String>();
		populateMap(profTraitData);
		for (int j = 2; j < sheet.getRows(); j++) {
			enrollNo = sheet.getCell(0, j).getContents();
			stuDataSet = Utility.conn.createStatement().executeQuery(
					QueryCollection.STUDENT_PROFILE.GET_EXISTING_DATA.replace("?", enrollNo.toLowerCase()));
			if (!stuDataSet.next()) {
				result.append("Enroll No: " + enrollNo + " does not exists!!!\r\n");
			} else {
				for (String criteria : miMap.keySet()) {
					insertData.setString(1, instName);
					insertData.setString(2, enrollNo);
					insertData.setString(3, (String) profTraitData.get(criteria));
					insertData.setString(4, criteria);
					insertData.setDouble(5, Utility.parseDouble(sheet.getCell(
							((Integer) miMap.get(criteria)).intValue(), j).getContents()));
					insertData.executeUpdate();
				}

				for (String criteria : cmplxPersMap.keySet()) {
					insertData.setString(1, instName);
					insertData.setString(2, enrollNo);
					insertData.setString(3, (String) profTraitData.get(criteria));
					insertData.setString(4, criteria);
					insertData.setDouble(5, Utility.parseDouble(sheet.getCell(
							((Integer) cmplxPersMap.get(criteria)).intValue(), j).getContents()));
					insertData.executeUpdate();
				}

				for (String criteria : abilityMap.keySet()) {
					insertData.setString(1, instName);
					insertData.setString(2, enrollNo);
					insertData.setString(3, (String) profTraitData.get(criteria));
					insertData.setString(4, criteria);
					insertData.setDouble(5, Utility.parseDouble(sheet.getCell(
							((Integer) abilityMap.get(criteria)).intValue(), j).getContents()));
					insertData.executeUpdate();
				}

				for (String criteria : qualityMap.keySet()) {
					insertData.setString(1, instName);
					insertData.setString(2, enrollNo);
					insertData.setString(3, (String) profTraitData.get(criteria));
					insertData.setString(4, criteria);
					insertData.setDouble(5, Utility.parseDouble(sheet.getCell(
							((Integer) qualityMap.get(criteria)).intValue(), j).getContents()));
					insertData.executeUpdate();
				}

				for (String criteria : motivatorsMap.keySet()) {
					insertData.setString(1, instName);
					insertData.setString(2, enrollNo);
					insertData.setString(3, (String) profTraitData.get(criteria));
					insertData.setString(4, criteria);
					insertData.setDouble(5, Utility.parseDouble(sheet.getCell(
							((Integer) motivatorsMap.get(criteria)).intValue(), j).getContents()));
					insertData.executeUpdate();
				}

				for (String criteria : workingStyleMap.keySet()) {
					insertData.setString(1, instName);
					insertData.setString(2, enrollNo);
					insertData.setString(3, (String) profTraitData.get(criteria));
					insertData.setString(4, criteria);
					insertData.setDouble(5, Utility.parseDouble(sheet.getCell(
							((Integer) workingStyleMap.get(criteria)).intValue(), j).getContents()));
					insertData.executeUpdate();
				}
			}
		}
		return result.toString();
	}

	public static void populateMap(HashMap<String, String> profTraitData) throws Exception {
		PreparedStatement prepStmt = Utility.conn
				.prepareStatement("SELECT code FROM codes_tbl WHERE code_type = ? AND parent_code = ? AND is_deleted = 0 ORDER BY LOWER(code)");
		for (String key : Const.Code_Type.getProfTraitLst()) {
			prepStmt.setString(1, key);
			prepStmt.setString(2, "");
			ResultSet dataSet = prepStmt.executeQuery();
			while (dataSet.next())
				profTraitData.put(dataSet.getString(1), key);
		}
	}
}
package com.wonderskool.main.bo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.wonderskool.bo.utility.Const;
import com.wonderskool.bo.utility.CreatePDF;
import com.wonderskool.bo.utility.QueryCollection;
import com.wonderskool.bo.utility.TraitVariablesLst;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class ProcessSingleResult extends JFrame {
	private JButton cancelBtn;
	private JLabel enrollLbl;
	private JTextField enrollTxt;
	private JLabel errMsgLbl;
	private JEditorPane jEditorPane1;
	private JScrollPane jScrollPane2;
	private JButton resultBtn;
	private JButton saveBtn;
	private JButton showBtn;

	public ProcessSingleResult() {
		initComponents();
	}

	private void initComponents() {
		this.errMsgLbl = new JLabel();
		this.saveBtn = new JButton();
		this.cancelBtn = new JButton();
		this.enrollLbl = new JLabel();
		this.enrollTxt = new JTextField();
		this.resultBtn = new JButton();
		this.showBtn = new JButton();
		this.jScrollPane2 = new JScrollPane();
		this.jEditorPane1 = new JEditorPane();

		setDefaultCloseOperation(3);
		setTitle("Process Single Student");
		setIconImages(null);

		this.errMsgLbl.setForeground(new Color(255, 0, 0));
		this.errMsgLbl.setHorizontalAlignment(2);

		this.saveBtn.setMnemonic('P');
		this.saveBtn.setText("Process");
		this.saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ProcessSingleResult.this.saveBtnActionPerformed(evt);
			}
		});
		this.cancelBtn.setMnemonic('C');
		this.cancelBtn.setText("Cancel");
		this.cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ProcessSingleResult.this.cancelBtnActionPerformed(evt);
			}
		});
		this.enrollLbl.setText("Enroll No:");

		this.resultBtn.setMnemonic('G');
		this.resultBtn.setText("Generate Report");
		this.resultBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ProcessSingleResult.this.resultBtnActionPerformed(evt);
			}
		});
		this.showBtn.setMnemonic('S');
		this.showBtn.setText("Show Result Step Wise");
		this.showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ProcessSingleResult.this.showBtnActionPerformed(evt);
			}
		});
		this.jEditorPane1.setContentType("text/html");
		this.jScrollPane2.setViewportView(this.jEditorPane1);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(this.jScrollPane2, -2, 430, -2)
										.addGroup(
												layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(this.errMsgLbl, -1, 547, 32767)
														.addGroup(
																layout.createSequentialGroup()
																		.addComponent(this.enrollLbl)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(this.enrollTxt, -2, 111, -2))
														.addGroup(
																layout.createSequentialGroup()
																		.addComponent(this.saveBtn)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(this.resultBtn)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(this.showBtn, -2, 167, -2)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(this.cancelBtn))))
						.addContainerGap(-1, 32767)));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(this.errMsgLbl)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.enrollLbl)
										.addComponent(this.enrollTxt, -2, -1, -2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.jScrollPane2, -1, 217, 32767)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.saveBtn)
										.addComponent(this.cancelBtn).addComponent(this.resultBtn)
										.addComponent(this.showBtn)).addContainerGap()));

		pack();
	}

	private void saveBtnActionPerformed(ActionEvent evt) {
		if (!DBValidateUtility.validUserAccess("single_result_write", this.errMsgLbl))
			return;
		this.jEditorPane1.setText("");
		this.errMsgLbl.setText("");
		HashMap<String, Double> marksMap = new HashMap<String, Double>();
		if (Utility.isNullEmpty(this.enrollTxt.getText())) {
			this.errMsgLbl.setText("Error:Student enroll no. is mandatory!!!");
			return;
		}
		String enrollNo = this.enrollTxt.getText();
		String instName = Utility.getInstName();
		if (Utility.isNullEmpty(instName)) {
			return;
		}
		if (!Utility.getjdbcconnection(this.errMsgLbl))
			return;
		LinkedHashMap<String, Integer> cutOffMap = new LinkedHashMap<String, Integer>();
		HashMap<String, List<String>> prof2CriteriaMap = new HashMap<String, List<String>>();
		HashMap<String, List<String>> criteria2ProfMap = new HashMap<String, List<String>>();
		try {
			if (!loadCutOffData(cutOffMap)) {
				this.errMsgLbl.setText("Error:Criteria cut-off data not entered yet!!!");
				return;
			}
			if (!loadProfessionData(prof2CriteriaMap, criteria2ProfMap)) {
				this.errMsgLbl.setText("Error:Profession data not entered yet!!!");
				return;
			}
			setCursor(Cursor.getPredefinedCursor(3));
			ResultSet dataSet = Utility.conn.createStatement().executeQuery(
					QueryCollection.STUDENT_RESULT.GET_EXISTING_DATA.replace("?", enrollNo.toLowerCase()));
			while (dataSet.next()) {
				marksMap.put(dataSet.getString(4), Double.valueOf(dataSet.getDouble(5)));
			}
			if (marksMap.isEmpty()) {
				this.errMsgLbl.setText("Error:Marks not entered for this Student. No Result Generated!!!");
				return;
			}
			processMarksData(instName, enrollNo, marksMap, cutOffMap, criteria2ProfMap, prof2CriteriaMap);
			Utility.finishjdbcconnection();
			this.errMsgLbl.setText("Info:Processing Complete!!!");
			setCursor(Cursor.getPredefinedCursor(0));
		} catch (Exception e) {
			setCursor(Cursor.getPredefinedCursor(0));
			e.printStackTrace();
			this.errMsgLbl.setText(new StringBuilder().append("Error:").append(e.getMessage()).toString());
			return;
		}
	}

	private void cancelBtnActionPerformed(ActionEvent evt) {
		Utility.callCancel(this);
	}

	private void resultBtnActionPerformed(ActionEvent evt) {
		if (!DBValidateUtility.validUserAccess("single_result_write", this.errMsgLbl))
			return;
		this.jEditorPane1.setText("");
		this.errMsgLbl.setText("");
		if (Utility.isNullEmpty(this.enrollTxt.getText())) {
			this.errMsgLbl.setText("Error:Student enroll no. is mandatory!!!");
			return;
		}
		String enrollNo = this.enrollTxt.getText();
		String instName = Utility.getInstName();
		if (Utility.isNullEmpty(instName))
			return;
		try {
			setCursor(Cursor.getPredefinedCursor(3));
			CreatePDF.processFiles(instName, enrollNo, this.errMsgLbl);
			this.errMsgLbl.setText("Info:Processing Complete!!!");
			setCursor(Cursor.getPredefinedCursor(0));
		} catch (Exception e) {
			e.printStackTrace();
			setCursor(Cursor.getPredefinedCursor(0));
			this.errMsgLbl.setText(new StringBuilder().append("Error:").append(e.getMessage()).toString());
		}
	}

	private void showBtnActionPerformed(ActionEvent evt) {
		if (!DBValidateUtility.validUserAccess("single_result_write", this.errMsgLbl))
			return;
		this.errMsgLbl.setText("");
		if (Utility.isNullEmpty(this.enrollTxt.getText())) {
			this.errMsgLbl.setText("Error:Student enroll no. is mandatory!!!");
			return;
		}
		String enrollNo = this.enrollTxt.getText();
		try {
			if (!Utility.getjdbcconnection(this.errMsgLbl)) {
				throw new RuntimeException(this.errMsgLbl.getText());
			}
			ResultSet dataSet = Utility.conn.createStatement().executeQuery(
					QueryCollection.STUDENT_PROFESSION.GET_EXISTING_DATA.replace("?", enrollNo.toLowerCase()));
			Utility.finishjdbcconnection();
			HashMap<String, String> resultMap = new HashMap<String, String>();
			HashMap<String, String> profMap = new HashMap<String, String>();
			boolean dataExists = false;
			while (dataSet.next()) {
				dataExists = true;
				resultMap.put(dataSet.getString(3), dataSet.getString(4));
				profMap.put(dataSet.getString(3), dataSet.getString(5));
			}
			if (!dataExists) {
				this.errMsgLbl.setText("Error:No data Exists!!!");
				return;
			}
			StringBuilder str = new StringBuilder();
			for (String key : TraitVariablesLst.dataMap.keySet()) {
				str.append(new StringBuilder().append("<html> <u><i><b><font size=8>").append(key)
						.append("</font></b></i></u><br>").toString());
				str.append("<u><i><b><font size=6>Criteria</font></b></i></u><br>");
				for (String data : ((String) resultMap.get(key)).split(","))
					str.append(new StringBuilder().append(data).append("<br>").toString());
				str.append("<u><i><b><font size=6>Profession</font></b></i></u><br>");
				for (String data : ((String) profMap.get(key)).split(","))
					str.append(new StringBuilder().append(data).append("<br>").toString());
			}
			str.append("<u><i><b><font size=8>Final Professions</font></b></i></u><br>");
			for (String data : ((String) profMap.get("FINAL")).split(","))
				str.append(new StringBuilder().append(data).append("<br>").toString());
			str.append("</html>");
			System.out.println(str.toString());
			this.jEditorPane1.setText("");
			this.jEditorPane1.setText(str.toString());
		} catch (Exception e) {
			this.errMsgLbl.setText(new StringBuilder().append("Error:").append(e.getMessage()).toString());
			e.printStackTrace();
		}
	}

	public static boolean loadCutOffData(LinkedHashMap<String, Integer> cutOffMap) throws Exception {
		ResultSet dataSet = Utility.conn.createStatement().executeQuery("SELECT * FROM system_variables");
		boolean dataExists = false;
		String valName = "";
		int value = 0;
		while (dataSet.next()) {
			dataExists = true;
			valName = dataSet.getString(1);
			value = dataSet.getInt(2);
			if (value != -9999) {
				cutOffMap.put(valName, Integer.valueOf(dataSet.getInt(2)));
			}
		}
		return dataExists;
	}

	public static boolean loadProfessionData(HashMap<String, List<String>> prof2CriteriaMap,
			HashMap<String, List<String>> criteria2ProfMap) throws Exception {
		ResultSet dataSet = Utility.conn.createStatement().executeQuery(
				"SELECT profession,criteria FROM profession_criteria");
		boolean dataExists = false;
		String profName = "";
		String criteria = "";
		while (dataSet.next()) {
			dataExists = true;
			profName = dataSet.getString(1);
			criteria = dataSet.getString(2);
			if (!prof2CriteriaMap.containsKey(profName)) {
				prof2CriteriaMap.put(profName, new ArrayList<String>());
			}
			(prof2CriteriaMap.get(profName)).add(criteria);

			if (!criteria2ProfMap.containsKey(criteria)) {
				criteria2ProfMap.put(criteria, new ArrayList<String>());
			}
			(criteria2ProfMap.get(criteria)).add(profName);
		}
		return dataExists;
	}

	public static void addData2DB(String instName, String enrollNum, String profTrait, String traitVar,
			String profession) throws Exception {
		Utility.conn.createStatement().executeUpdate(
				QueryCollection.STUDENT_PROFESSION.DELETE_EXISTING_DATA.replace("?", enrollNum.toLowerCase()).replace(
						"~", profTrait.toLowerCase()));
		PreparedStatement insertData = Utility.conn
				.prepareStatement("INSERT INTO student_profession VALUES (?, ?, ?, ?, ?)");
		insertData.setString(1, instName);
		insertData.setString(2, enrollNum);
		insertData.setString(3, profTrait);
		insertData.setString(4, traitVar);
		insertData.setString(5, profession);
		insertData.executeUpdate();
	}

	public static void processMarksData(String instName, String enrollNo, HashMap<String, Double> marksMap,
			LinkedHashMap<String, Integer> cutOffMap, HashMap<String, List<String>> criteria2ProfMap,
			HashMap<String, List<String>> prof2CriteriaMap) throws Exception {
		List<String> allProfLst = new ArrayList<String>();
		for (String key : TraitVariablesLst.dataMap.keySet()) {
			Set<String> criteriaStr = new TreeSet<String>(Collections.reverseOrder());
			Set<String> profStr = new TreeSet<String>();
			for (String criteria : TraitVariablesLst.dataMap.get(key)) {
				double marks = ((Double) marksMap.get(criteria)).doubleValue();
				if ((key.equalsIgnoreCase(Const.Code_Type.QUALITY) || marks >= cutOffMap.get(criteria))
						&& (!key.equalsIgnoreCase(Const.Code_Type.QUALITY) || marks != 0.0)) {
					criteriaStr.add(marks < 10.0D ? new StringBuilder().append("0").append(marks).append(":")
							.append(criteria).toString() : new StringBuilder().append(marks).append(":")
							.append(criteria).toString());
				}
			}
			for (String criteriaTemp : criteriaStr) {
				if (criteria2ProfMap.containsKey(criteriaTemp.split(":")[1])) {
					profStr.addAll(criteria2ProfMap.get(criteriaTemp.split(":")[1]));
				}
			}
			allProfLst.addAll(profStr);
			addData2DB(instName, enrollNo, key,
					Utility.getStr(criteriaStr, ((Integer) cutOffMap.get("TOP_SXN")).intValue()),
					Utility.getStr(profStr, 999));
		}
		HashMap<String, Integer> finalMap = new HashMap<String, Integer>();
		SortedMap<Integer, List<String>> finalMapSorted = new TreeMap<Integer, List<String>>(Collections.reverseOrder());
		for (String profession : allProfLst) {
			if (!finalMap.containsKey(profession))
				finalMap.put(profession, 1);
			else {
				finalMap.put(profession, finalMap.get(profession) + 1);
			}
		}
		for (String profession : finalMap.keySet()) {
			if (!finalMapSorted.containsKey(finalMap.get(profession)))
				finalMapSorted.put(finalMap.get(profession), new ArrayList<String>());
			(finalMapSorted.get(finalMap.get(profession))).add(profession);
		}
		int count = 5;
		if (finalMap.size() < 5) {
			count = finalMap.size();
		}
		allProfLst.clear();
		for (Integer data : finalMapSorted.keySet()) {
			Collections.shuffle(finalMapSorted.get(data));
			for (String prof : finalMapSorted.get(data)) {
				allProfLst.add(prof);
				if (allProfLst.size() >= count)
					break;
			}
		}
		addData2DB(instName, enrollNo, "FINAL", "FINAL", Utility.getStr(allProfLst, 9999));
	}
}
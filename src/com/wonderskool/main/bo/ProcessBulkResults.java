package com.wonderskool.main.bo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import com.wonderskool.bo.utility.CreatePDF;
import com.wonderskool.bo.utility.QueryCollection;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class ProcessBulkResults extends JFrame
{
  LinkedHashMap<String, String> stuDataLst = new LinkedHashMap();
  int runningCounter = 0;
  String instName = "";
  String enrollNo = "";

  JFrame jframe = null;
  private JLabel batchLbl;
  private JButton cancelBtn;
  private JComboBox classCombo;
  private JLabel errMsgLbl;
  private JLabel jLabel1;
  private JList jList1;
  private JScrollPane jScrollPane1;
  private JProgressBar prgressBar;
  private JLabel progressBarLbl;
  private JButton resultBtn;
  private JButton saveBtn;
  private JComboBox sectionCombo;

  public ProcessBulkResults()
  {
    initComponents();
    this.jList1.setVisible(false);
    this.jframe = this;
    this.prgressBar.setFont(new Font("Monospaced", 0, 10));
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.batchLbl = new JLabel();
    this.classCombo = new JComboBox();
    this.sectionCombo = new JComboBox();
    this.jLabel1 = new JLabel();
    this.saveBtn = new JButton();
    this.cancelBtn = new JButton();
    this.prgressBar = new JProgressBar();
    this.progressBarLbl = new JLabel();
    this.resultBtn = new JButton();
    this.jScrollPane1 = new JScrollPane();
    this.jList1 = new JList();

    setDefaultCloseOperation(3);
    setTitle("Process Bulk Results");
    setIconImages(null);
    setPreferredSize(new Dimension(500, 200));

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.batchLbl.setHorizontalAlignment(4);
    this.batchLbl.setText("Class:");

    this.classCombo.setModel(new DefaultComboBoxModel(new String[] { "All", "PRE-NUR", "NUR", "KG", "L-KG", "U-KG", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII" }));

    this.sectionCombo.setModel(new DefaultComboBoxModel(new String[] { "All", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));

    this.jLabel1.setHorizontalAlignment(11);
    this.jLabel1.setText("Section:");

    this.saveBtn.setMnemonic('P');
    this.saveBtn.setText("Process");
    this.saveBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ProcessBulkResults.this.saveBtnActionPerformed(evt);
      }
    });
    this.cancelBtn.setMnemonic('C');
    this.cancelBtn.setText("Cancel");
    this.cancelBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ProcessBulkResults.this.cancelBtnActionPerformed(evt);
      }
    });
    this.resultBtn.setMnemonic('G');
    this.resultBtn.setText("Generate Report");
    this.resultBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ProcessBulkResults.this.resultBtnActionPerformed(evt);
      }
    });
    this.jList1.setEnabled(false);
    this.jScrollPane1.setViewportView(this.jList1);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.errMsgLbl, -1, -1, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(this.jLabel1, -2, 82, -2)).addComponent(this.batchLbl, -2, 82, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.sectionCombo, -2, 47, -2).addComponent(this.classCombo, -2, 81, -2))).addGroup(layout.createSequentialGroup().addComponent(this.saveBtn).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.resultBtn).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cancelBtn)))).addComponent(this.prgressBar, -2, 266, -2)).addGap(18, 18, 18).addComponent(this.jScrollPane1, -2, 158, -2).addGap(0, 27, 32767))).addContainerGap(13, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.progressBarLbl, -2, 313, -2).addContainerGap(-1, 32767)))));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.classCombo, -2, -1, -2).addComponent(this.batchLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.sectionCombo, -2, -1, -2).addComponent(this.jLabel1)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.saveBtn).addComponent(this.cancelBtn).addComponent(this.resultBtn)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.prgressBar, -2, 25, -2)).addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.progressBarLbl).addContainerGap(-1, 32767)));

    pack();
  }

  private void cancelBtnActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void saveBtnActionPerformed(ActionEvent evt) {
    if (!DBValidateUtility.validUserAccess("bulk_result_write", this.errMsgLbl))
      return;
    this.jList1.setVisible(false);
    this.runningCounter = 0;
    this.enrollNo = "";
    this.stuDataLst.clear();
    this.errMsgLbl.setText("");
    HashMap marksMap = new HashMap();
    if ((this.classCombo.getSelectedIndex() == 0) && (this.sectionCombo.getSelectedIndex() > 0)) {
      this.errMsgLbl.setText("Error:Section cannot be selected if class = All!!!");
      return;
    }
    this.instName = Utility.getInstName();
    if (Utility.isNullEmpty(this.instName)) {
      return;
    }
    LinkedHashMap cutOffMap = new LinkedHashMap();
    HashMap prof2CriteriaMap = new HashMap();
    HashMap criteria2ProfMap = new HashMap();
    try {
      getStudentLst(this.errMsgLbl);
      if (this.stuDataLst.size() == 0) {
        this.errMsgLbl.setText("Error:No student found for given criteria!!!");
        return;
      }
      if (!Utility.getjdbcconnection(this.errMsgLbl)) {
        return;
      }
      if (!ProcessSingleResult.loadCutOffData(cutOffMap)) {
        this.errMsgLbl.setText("Error:Criteria cut-off data not entered yet!!!");
        return;
      }
      if (!ProcessSingleResult.loadProfessionData(prof2CriteriaMap, criteria2ProfMap)) {
        this.errMsgLbl.setText("Error:Profession data not entered yet!!!");
        return;
      }
      this.prgressBar.setMaximum(this.stuDataLst.size());
      this.prgressBar.setStringPainted(true);
      setCursor(Cursor.getPredefinedCursor(3));
      Vector noResultEnroll = new Vector();
      for (String enrollNoTemp : this.stuDataLst.keySet()) {
        marksMap.clear();
        this.enrollNo = enrollNoTemp;
        new Thread() {
          public void run() {
            ProcessBulkResults.this.prgressBar.setValue(++ProcessBulkResults.this.runningCounter);
            ProcessBulkResults.this.prgressBar.setString("Processing:" + (ProcessBulkResults.this.stuDataLst.size() - ProcessBulkResults.this.runningCounter) + "/" + ProcessBulkResults.this.stuDataLst.size() + "-" + (String)ProcessBulkResults.this.stuDataLst.get(ProcessBulkResults.this.enrollNo) + "(" + ProcessBulkResults.this.enrollNo + ")");
            ProcessBulkResults.this.prgressBar.paint(ProcessBulkResults.this.prgressBar.getGraphics());
          }
        }
        .start();

        ResultSet dataSet = Utility.conn.createStatement().executeQuery(QueryCollection.STUDENT_RESULT.GET_EXISTING_DATA.replace("?", this.enrollNo.toLowerCase()));
        while (dataSet.next()) {
          marksMap.put(dataSet.getString(4), Double.valueOf(dataSet.getDouble(5)));
        }
        if (marksMap.isEmpty()) {
          noResultEnroll.add(enrollNoTemp);
        }
        else
          ProcessSingleResult.processMarksData(this.instName, this.enrollNo, marksMap, cutOffMap, criteria2ProfMap, prof2CriteriaMap);
      }
      if (!Utility.isNullEmpty(noResultEnroll.toString())) {
        this.jList1.setVisible(true);
        this.jList1.setListData(noResultEnroll.toString().split(","));
        this.errMsgLbl.setText("Warn:Processing complete. Marks not entered for following students!!!");
      }
      else {
        this.errMsgLbl.setText("Info:Processing complete!!!");
      }Utility.finishjdbcconnection();
      setCursor(Cursor.getPredefinedCursor(0));
    } catch (Exception e) {
      setCursor(Cursor.getPredefinedCursor(0));
      e.printStackTrace();
      this.errMsgLbl.setText("Error:" + e.getMessage());
      return;
    }
  }

  private void resultBtnActionPerformed(ActionEvent evt) {
    if (!DBValidateUtility.validUserAccess("bulk_result_write", this.errMsgLbl))
      return;
    this.jList1.setVisible(false);
    this.runningCounter = 0;
    this.enrollNo = "";
    this.stuDataLst.clear();
    this.errMsgLbl.setText("");
    if ((this.classCombo.getSelectedIndex() == 0) && (this.sectionCombo.getSelectedIndex() > 0)) {
      this.errMsgLbl.setText("Error:Section cannot be selected if class = All!!!");
      return;
    }
    this.instName = Utility.getInstName();
    if (Utility.isNullEmpty(this.instName))
      return;
    try
    {
      getStudentLst(this.errMsgLbl);
      if (this.stuDataLst.size() == 0) {
        this.errMsgLbl.setText("Error:No student found for given criteria!!!");
        return;
      }
      this.prgressBar.setMaximum(this.stuDataLst.size());
      this.prgressBar.setStringPainted(true);
      setCursor(Cursor.getPredefinedCursor(3));
      for (String enrollNoTemp : this.stuDataLst.keySet()) {
        this.enrollNo = enrollNoTemp;
        new Thread() {
          public void run() {
            ProcessBulkResults.this.prgressBar.setValue(++ProcessBulkResults.this.runningCounter);
            ProcessBulkResults.this.prgressBar.setString("Processing:" + (ProcessBulkResults.this.stuDataLst.size() - ProcessBulkResults.this.runningCounter) + "/" + ProcessBulkResults.this.stuDataLst.size() + "-" + (String)ProcessBulkResults.this.stuDataLst.get(ProcessBulkResults.this.enrollNo) + "(" + ProcessBulkResults.this.enrollNo + ")");
            ProcessBulkResults.this.prgressBar.paint(ProcessBulkResults.this.prgressBar.getGraphics());
          }
        }
        .start();

        CreatePDF.processFiles(this.instName, this.enrollNo, this.errMsgLbl);
      }
      this.errMsgLbl.setText("Info:Processing Complete!!!");
      setCursor(Cursor.getPredefinedCursor(0));
    } catch (Exception e) {
      e.printStackTrace();
      setCursor(Cursor.getPredefinedCursor(0));
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ProcessBulkResults().setVisible(true);
      }
    });
  }

  private void getStudentLst(JLabel errMsgLbl)
    throws Exception
  {
    if (!Utility.getjdbcconnection(errMsgLbl)) {
      return;
    }
    PreparedStatement getDataStmt = null;
    if ((this.classCombo.getSelectedIndex() == 0) && (this.sectionCombo.getSelectedIndex() == 0)) {
      getDataStmt = Utility.conn.prepareStatement(QueryCollection.STUDENT_PROFILE.GET_EXISTING_DATA_ALL);
    } else if ((this.classCombo.getSelectedIndex() > 0) && (this.sectionCombo.getSelectedIndex() == 0)) {
      getDataStmt = Utility.conn.prepareStatement(QueryCollection.STUDENT_PROFILE.GET_EXISTING_DATA_CLS);
      getDataStmt.setString(1, this.classCombo.getSelectedItem().toString());
    } else {
      getDataStmt = Utility.conn.prepareStatement(QueryCollection.STUDENT_PROFILE.GET_EXISTING_DATA_CLS_SXN);
      getDataStmt.setString(1, this.classCombo.getSelectedItem().toString());
      getDataStmt.setString(2, this.sectionCombo.getSelectedItem().toString());
    }
    ResultSet stuDataSet = getDataStmt.executeQuery();
    while (stuDataSet.next()) {
      this.stuDataLst.put(stuDataSet.getString(2), stuDataSet.getString(3));
    }
    Utility.finishjdbcconnection();
  }
}
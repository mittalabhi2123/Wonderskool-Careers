package com.wonderskool.bo.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.wonderskool.bo.utility.Const;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class UpdateQAData extends JFrame
{
  private static List<Integer> questionIdLst = new ArrayList();
  private static int selectedQuestionId = 0;
  private JButton cancelBtn;
  private JButton closeBtn;
  private JButton deleteBtn;
  private JLabel errMsgLbl;
  private JLabel jLabel1;
  private JPanel jPanel1;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane2;
  private JTextField marks1Txt;
  private JTextField marks2Txt;
  private JTextField marks3Txt;
  private JTextField marks4Txt;
  private JTextField marks5Txt;
  private JTextField marks6Txt;
  private JButton newBtn;
  private JTextField option1Txt;
  private JTextField option2Txt;
  private JTextField option3Txt;
  private JTextField option4Txt;
  private JTextField option5Txt;
  private JTextField option6Txt;
  private JLabel optionLbl;
  private JLabel questionLbl;
  private JTable questionTbl;
  private JTextArea questionTxt;
  private JButton retrieveBtn;
  private JButton saveBtn;
  private JComboBox traitCombo;
  private JLabel traitLbl;
  private JComboBox variableCombo;
  private JLabel variableLbl;

  public UpdateQAData()
  {
    initComponents();
    this.variableCombo.addItem("ALL");
    this.traitCombo.addItem("ALL");
    for (String str : Const.Code_Type.getProfTraitLst())
      this.traitCombo.addItem(str);
    this.jPanel1.setVisible(false);
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.variableCombo = new JComboBox();
    this.traitCombo = new JComboBox();
    this.traitLbl = new JLabel();
    this.variableLbl = new JLabel();
    this.jScrollPane1 = new JScrollPane();
    this.questionTbl = new JTable();
    this.jPanel1 = new JPanel();
    this.questionLbl = new JLabel();
    this.jScrollPane2 = new JScrollPane();
    this.questionTxt = new JTextArea();
    this.optionLbl = new JLabel();
    this.option1Txt = new JTextField();
    this.option2Txt = new JTextField();
    this.option3Txt = new JTextField();
    this.option4Txt = new JTextField();
    this.saveBtn = new JButton();
    this.deleteBtn = new JButton();
    this.cancelBtn = new JButton();
    this.option5Txt = new JTextField();
    this.option6Txt = new JTextField();
    this.jLabel1 = new JLabel();
    this.marks1Txt = new JTextField();
    this.marks5Txt = new JTextField();
    this.marks4Txt = new JTextField();
    this.marks3Txt = new JTextField();
    this.marks2Txt = new JTextField();
    this.marks6Txt = new JTextField();
    this.retrieveBtn = new JButton();
    this.newBtn = new JButton();
    this.closeBtn = new JButton();

    setDefaultCloseOperation(3);
    setTitle("Maintain Q/A Inventory");
    setIconImages(null);
    setResizable(false);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.variableCombo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateQAData.this.variableComboActionPerformed(evt);
      }
    });
    this.traitCombo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateQAData.this.traitComboActionPerformed(evt);
      }
    });
    this.traitLbl.setHorizontalAlignment(4);
    this.traitLbl.setText("Trait:");

    this.variableLbl.setHorizontalAlignment(4);
    this.variableLbl.setText("Variable:");

    this.questionTbl.setModel(new DefaultTableModel(new Object[][] { { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null } }, new String[] { "Questions" })
    {
      Class[] types = { String.class };

      boolean[] canEdit = { false };

      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return this.canEdit[columnIndex];
      }
    });
    this.questionTbl.setRowHeight(20);
    this.questionTbl.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        UpdateQAData.this.questionTblClicked(evt);
      }
    });
    this.jScrollPane1.setViewportView(this.questionTbl);

    this.jPanel1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

    this.questionLbl.setFont(new Font("Tahoma", 3, 14));
    this.questionLbl.setHorizontalAlignment(2);
    this.questionLbl.setText("<html><u>Question</u></html>");

    this.questionTxt.setColumns(20);
    this.questionTxt.setRows(5);
    this.questionTxt.setAutoscrolls(false);
    this.jScrollPane2.setViewportView(this.questionTxt);
    this.questionTxt.getAccessibleContext().setAccessibleParent(this.jScrollPane1);

    this.optionLbl.setFont(new Font("Tahoma", 3, 14));
    this.optionLbl.setHorizontalAlignment(0);
    this.optionLbl.setText("<html><u>Options</u></html>");

    this.saveBtn.setMnemonic('S');
    this.saveBtn.setText("Save");
    this.saveBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateQAData.this.saveBtnActionPerformed(evt);
      }
    });
    this.deleteBtn.setMnemonic('D');
    this.deleteBtn.setText("Delete");
    this.deleteBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateQAData.this.deleteBtnActionPerformed(evt);
      }
    });
    this.cancelBtn.setMnemonic('L');
    this.cancelBtn.setText("Cancel");
    this.cancelBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateQAData.this.cancelBtnActionPerformed(evt);
      }
    });
    this.jLabel1.setFont(new Font("Tahoma", 3, 14));
    this.jLabel1.setText("<html><u>Marks</u></html>");

    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.questionLbl, -2, -1, -2).addComponent(this.jScrollPane2, -2, 290, -2).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.option6Txt, GroupLayout.Alignment.LEADING, -1, 210, 32767).addComponent(this.option5Txt, GroupLayout.Alignment.LEADING).addComponent(this.option4Txt, GroupLayout.Alignment.LEADING).addComponent(this.option3Txt, GroupLayout.Alignment.LEADING).addComponent(this.option2Txt, GroupLayout.Alignment.LEADING).addComponent(this.option1Txt, GroupLayout.Alignment.LEADING)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.marks1Txt).addComponent(this.marks6Txt).addComponent(this.marks5Txt).addComponent(this.marks4Txt).addComponent(this.marks3Txt).addComponent(this.marks2Txt)))).addContainerGap(24, 32767)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.optionLbl, -2, 72, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jLabel1, -2, -1, -2).addGap(42, 42, 42)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.saveBtn, -2, 62, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.deleteBtn).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cancelBtn).addGap(0, 0, 32767)))));

    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.questionLbl, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.optionLbl, -2, -1, -2).addComponent(this.jLabel1, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.option1Txt, -2, -1, -2).addComponent(this.marks1Txt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.option2Txt, -2, -1, -2).addComponent(this.marks2Txt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.option3Txt, -2, -1, -2).addComponent(this.marks3Txt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.option4Txt, -2, -1, -2).addComponent(this.marks4Txt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.option5Txt, -2, -1, -2).addComponent(this.marks5Txt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.option6Txt, -2, -1, -2).addComponent(this.marks6Txt, -2, -1, -2)).addGap(19, 19, 19).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.saveBtn).addComponent(this.deleteBtn).addComponent(this.cancelBtn)).addGap(40, 40, 40)));

    this.retrieveBtn.setMnemonic('R');
    this.retrieveBtn.setText("Retrieve");
    this.retrieveBtn.setPreferredSize(new Dimension(73, 20));
    this.retrieveBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateQAData.this.retrieveBtnActionPerformed(evt);
      }
    });
    this.newBtn.setMnemonic('N');
    this.newBtn.setText("New");
    this.newBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateQAData.this.newBtnActionPerformed(evt);
      }
    });
    this.closeBtn.setMnemonic('C');
    this.closeBtn.setText("Close");
    this.closeBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateQAData.this.closeBtnActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.errMsgLbl, -2, 547, -2).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -2, -1, -2).addGroup(layout.createSequentialGroup().addComponent(this.newBtn).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.closeBtn)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.variableLbl, -2, 99, -2).addComponent(this.traitLbl, -2, 99, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.traitCombo, -2, 143, -2).addGroup(layout.createSequentialGroup().addComponent(this.variableCombo, -2, 143, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.retrieveBtn, -2, 97, -2))))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, -1, -2))).addContainerGap(-1, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.traitCombo, -2, -1, -2).addComponent(this.traitLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.variableCombo, -2, -1, -2).addComponent(this.variableLbl).addComponent(this.retrieveBtn, -2, -1, -2)).addGap(18, 18, 18).addComponent(this.jScrollPane1, -2, 327, -2)).addComponent(this.jPanel1, -2, 368, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.newBtn).addComponent(this.closeBtn)).addContainerGap(45, 32767)));

    pack();
  }

  private void variableComboActionPerformed(ActionEvent evt) {
    clearTableData();
  }

  private void traitComboActionPerformed(ActionEvent evt) {
    clearTableData();
    if (this.traitCombo.getSelectedIndex() > 0) {
      Utility.initCodeCombo(this.variableCombo, this.traitCombo.getSelectedItem().toString(), this.errMsgLbl, true);
    } else {
      this.variableCombo.removeAllItems();
      this.variableCombo.addItem("ALL");
    }
  }

  private void closeBtnActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void cancelBtnActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    clearPanelData("", "", "", "", "", "", "", 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    this.jPanel1.setVisible(false);
    selectedQuestionId = 0;
  }

  private void questionTblClicked(MouseEvent evt) {
    this.errMsgLbl.setText("");
    int row = this.questionTbl.getSelectedRow();
    if ((this.questionTbl.getValueAt(row, 0) == null) || (Utility.isNullEmpty(this.questionTbl.getValueAt(row, 0).toString())))
      return;
    selectedQuestionId = ((Integer)questionIdLst.get(row)).intValue();
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    this.jPanel1.setVisible(true);
    try {
      PreparedStatement getDataStmt = Utility.conn.prepareStatement("SELECT * FROM qa_data WHERE question_id = ?");
      getDataStmt.setInt(1, selectedQuestionId);
      ResultSet dataSet = getDataStmt.executeQuery();
      while (dataSet.next()) {
        clearPanelData(dataSet.getString(3), dataSet.getString(4), dataSet.getString(5), dataSet.getString(6), dataSet.getString(7), dataSet.getString(8), dataSet.getString(9), dataSet.getDouble(10), dataSet.getDouble(11), dataSet.getDouble(12), dataSet.getDouble(13), dataSet.getDouble(14), dataSet.getDouble(15));
      }
      Utility.finishjdbcconnection();
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void retrieveBtnActionPerformed(ActionEvent evt) {
    clearTableData();
    if (this.variableCombo.getSelectedIndex() <= 0) {
      this.errMsgLbl.setText("Error:Variable is mandatory!!!");
      return;
    }
    if (this.traitCombo.getSelectedIndex() <= 0) {
      this.errMsgLbl.setText("Error:Trait is mandatory!!!");
      return;
    }
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      PreparedStatement getDataStmt = Utility.conn.prepareStatement("SELECT question,question_id FROM qa_data WHERE trait = ? AND variable = ? ORDER BY LOWER(question)");
      getDataStmt.setString(1, this.traitCombo.getSelectedItem().toString());
      getDataStmt.setString(2, this.variableCombo.getSelectedItem().toString());
      ResultSet dataSet = getDataStmt.executeQuery();
      questionIdLst.clear();
      int i = 0;
      while (dataSet.next()) {
        this.questionTbl.setValueAt(dataSet.getString(1), i++, 0);
        questionIdLst.add(Integer.valueOf(dataSet.getInt(2)));
      }
      Utility.finishjdbcconnection();
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void newBtnActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    this.jPanel1.setVisible(true);
    selectedQuestionId = 0;
    clearPanelData("", "", "", "", "", "", "", 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
  }

  private void deleteBtnActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");

    if (selectedQuestionId <= 0) {
      this.errMsgLbl.setText("Error:No data found to be deleted!!!");
      return;
    }
    if (!DBValidateUtility.validUserAccess("update_qa_data_write", this.errMsgLbl))
      return;
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      PreparedStatement getDataStmt = Utility.conn.prepareStatement("DELETE FROM qa_data WHERE question_id = ?");
      getDataStmt.setInt(1, selectedQuestionId);
      getDataStmt.executeUpdate();
      Utility.finishjdbcconnection();
      this.errMsgLbl.setText("Info:Data deleted successfully!!!");
      clearPanelData("", "", "", "", "", "", "", 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      this.jPanel1.setVisible(false);
      retrieveBtnActionPerformed(null);
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void saveBtnActionPerformed(ActionEvent evt) {
    if (!DBValidateUtility.validUserAccess("update_qa_data_write", this.errMsgLbl))
      return;
    this.errMsgLbl.setText("");
    if (Utility.isNullEmpty(this.questionTxt.getText())) {
      this.errMsgLbl.setText("Error:Question is mandatory!!!");
      return;
    }
    if (Utility.isNullEmpty(this.option1Txt.getText())) {
      this.errMsgLbl.setText("Error:At least 1 option is mandatory, and that shud be 1st option!!!");
      return;
    }
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      if (selectedQuestionId > 0) {
        PreparedStatement getDataStmt = Utility.conn.prepareStatement("DELETE FROM qa_data WHERE question_id = ?");
        getDataStmt.setInt(1, selectedQuestionId);
        getDataStmt.executeUpdate();
      }
      else {
        ResultSet dataSet = Utility.conn.createStatement().executeQuery("SELECT MAX(question_id) FROM qa_data");
        if (dataSet.next())
          selectedQuestionId = dataSet.getInt(1) + 1;
      }
      PreparedStatement insertDataStmt = Utility.conn.prepareStatement("INSERT INTO qa_data VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      insertDataStmt.setString(1, this.traitCombo.getSelectedItem().toString());
      insertDataStmt.setString(2, this.variableCombo.getSelectedItem().toString());
      insertDataStmt.setString(3, this.questionTxt.getText());
      insertDataStmt.setString(4, this.option1Txt.getText());
      insertDataStmt.setString(5, this.option2Txt.getText());
      insertDataStmt.setString(6, this.option3Txt.getText());
      insertDataStmt.setString(7, this.option4Txt.getText());
      insertDataStmt.setString(8, this.option5Txt.getText());
      insertDataStmt.setString(9, this.option6Txt.getText());
      insertDataStmt.setDouble(10, Utility.parseDouble(this.marks1Txt.getText()));
      insertDataStmt.setDouble(11, Utility.parseDouble(this.marks2Txt.getText()));
      insertDataStmt.setDouble(12, Utility.parseDouble(this.marks3Txt.getText()));
      insertDataStmt.setDouble(13, Utility.parseDouble(this.marks4Txt.getText()));
      insertDataStmt.setDouble(14, Utility.parseDouble(this.marks5Txt.getText()));
      insertDataStmt.setDouble(15, Utility.parseDouble(this.marks6Txt.getText()));
      insertDataStmt.setInt(16, selectedQuestionId);
      insertDataStmt.executeUpdate();
      Utility.finishjdbcconnection();
      this.errMsgLbl.setText("Info:Data saved successfully!!!");
      clearPanelData("", "", "", "", "", "", "", 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      retrieveBtnActionPerformed(null);
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  public static void main(String[] args)
  {
    new UpdateQAData().setVisible(true);
  }

  private void clearPanelData(String qn, String op1, String op2, String op3, String op4, String op5, String op6, double mrk1, double mrk2, double mrk3, double mrk4, double mrk5, double mrk6)
  {
    this.questionTxt.setText(qn);
    this.option1Txt.setText(op1);
    this.option2Txt.setText(op2);
    this.option3Txt.setText(op3);
    this.option4Txt.setText(op4);
    this.option5Txt.setText(op5);
    this.option6Txt.setText(op6);

    this.marks1Txt.setText(String.valueOf(mrk1));
    this.marks2Txt.setText(String.valueOf(mrk2));
    this.marks3Txt.setText(String.valueOf(mrk3));
    this.marks4Txt.setText(String.valueOf(mrk4));
    this.marks5Txt.setText(String.valueOf(mrk5));
    this.marks6Txt.setText(String.valueOf(mrk6));
  }

  private void clearTableData() {
    this.errMsgLbl.setText("");
    questionIdLst.clear();
    for (int i = 0; i < 15; i++)
      this.questionTbl.setValueAt("", i, 0);
  }
}
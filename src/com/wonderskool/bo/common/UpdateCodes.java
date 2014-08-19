package com.wonderskool.bo.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import com.wonderskool.bo.utility.Const;
import com.wonderskool.bo.utility.TraitVariablesLst;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class UpdateCodes extends JFrame
{
  private JButton cancelBtn;
  private JTable codeTbl;
  private JComboBox codeTypeCombo;
  private JLabel codeTypeLbl;
  private JLabel errMsgLbl;
  private JScrollPane jScrollPane1;
  private JButton saveBtn;

  public UpdateCodes()
  {
    initComponents();
    this.codeTypeCombo.addItem("<SELECT>");
    for (String str : Const.Code_Type.getLst())
      this.codeTypeCombo.addItem(str);
    clearTableData();
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.codeTypeCombo = new JComboBox();
    this.codeTypeLbl = new JLabel();
    this.jScrollPane1 = new JScrollPane();
    this.codeTbl = new JTable();
    this.cancelBtn = new JButton();
    this.saveBtn = new JButton();

    setDefaultCloseOperation(3);
    setTitle("Maintain Codes");
    setIconImages(null);
    setResizable(false);
    setUndecorated(true);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.codeTypeCombo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateCodes.this.codeTypeComboActionPerformed(evt);
      }
    });
    this.codeTypeLbl.setHorizontalAlignment(4);
    this.codeTypeLbl.setText("Code Type:");

    this.codeTbl.setBorder(BorderFactory.createBevelBorder(0));
    this.codeTbl.setFont(new Font("Tahoma", 0, 14));
    this.codeTbl.setModel(new DefaultTableModel(new Object[][] { { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, "<html><i><u><b>Delete</b></u></i></html>" }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null } }, new String[] { "Code", "Click to delete" })
    {
      Class[] types = { String.class, String.class };

      boolean[] canEdit = { true, false };

      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return this.canEdit[columnIndex];
      }
    });
    this.codeTbl.setOpaque(false);
    this.codeTbl.setRowHeight(20);
    this.codeTbl.setRowMargin(2);
    this.codeTbl.setUpdateSelectionOnSort(false);
    this.codeTbl.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        UpdateCodes.this.codeTblMouseClicked(evt);
      }
    });
    this.jScrollPane1.setViewportView(this.codeTbl);
    this.codeTbl.setFillsViewportHeight(true);

    this.cancelBtn.setMnemonic('C');
    this.cancelBtn.setText("Cancel");
    this.cancelBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateCodes.this.cancelBtncancelActionPerformed(evt);
      }
    });
    this.saveBtn.setMnemonic('S');
    this.saveBtn.setText("Save");
    this.saveBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UpdateCodes.this.saveBtnActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(103, 103, 103).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.saveBtn, -2, 121, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cancelBtn, -2, 121, -2)).addComponent(this.jScrollPane1, -2, 387, -2))).addGroup(layout.createSequentialGroup().addGap(71, 71, 71).addComponent(this.codeTypeLbl, -2, 99, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.codeTypeCombo, -2, 143, -2))).addContainerGap(77, 32767)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl, -2, 547, -2).addContainerGap(-1, 32767))));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(42, 42, 42).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.codeTypeLbl).addComponent(this.codeTypeCombo, -2, -1, -2)).addGap(18, 18, 18).addComponent(this.jScrollPane1, -2, 327, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.saveBtn).addComponent(this.cancelBtn)).addContainerGap(37, 32767)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl).addContainerGap(474, 32767))));

    pack();
  }

  private void codeTypeComboActionPerformed(ActionEvent evt) {
    if (this.codeTypeCombo.getSelectedIndex() <= 0) {
      clearTableData();
    }
    else
      populateTableData("");
  }

  private void populateTableData(String prntCode)
  {
    clearTableData();
    try {
      if (!Utility.getjdbcconnection(this.errMsgLbl))
        return;
      PreparedStatement getDataStmt = Utility.conn.prepareStatement("SELECT code, is_deleted FROM codes_tbl WHERE code_type = ? AND parent_code = ? ORDER BY LOWER(code)");
      getDataStmt.setString(1, this.codeTypeCombo.getSelectedItem().toString());
      getDataStmt.setString(2, prntCode);
      ResultSet dataSet = getDataStmt.executeQuery();
      int row = 0;
      while (dataSet.next()) {
        this.codeTbl.setValueAt(dataSet.getString(1), row, 0);
        if (dataSet.getInt(2) > 0)
          this.codeTbl.setValueAt("<html><i><u><b>Undo Delete</b></u></i></html>", row, 1);
        else
          this.codeTbl.setValueAt("<html><i><u><b>Delete</b></u></i></html>", row, 1);
        row++;
      }
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void codeTblMouseClicked(MouseEvent evt) {
    this.errMsgLbl.setText("");
    int row = this.codeTbl.getSelectedRow();
    int col = this.codeTbl.getSelectedColumn();
    if (col != 1) {
      return;
    }
    if (!DBValidateUtility.validUserAccess("code_data_write", this.errMsgLbl))
      return;
    if (this.codeTbl.getValueAt(row, 1).toString().indexOf("Undo") < 0) {
      deleteSingleData(row, 1);
      this.codeTbl.setValueAt("<html><i><u><b>Undo Delete</b></u></i></html>", row, 1);
    }
    else {
      deleteSingleData(row, 0);
      this.codeTbl.setValueAt("<html><i><u><b>Delete</b></u></i></html>", row, 1);
    }
    TraitVariablesLst.initTraitMap();
    this.errMsgLbl.setText("Info: Data deleted successfully!!!");
  }

  private void cancelBtncancelActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void saveBtnActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    if (!DBValidateUtility.validUserAccess("code_data_write", this.errMsgLbl))
      return;
    try {
      if (!Utility.getjdbcconnection(this.errMsgLbl)) {
        return;
      }
      PreparedStatement deleteDataStmt = Utility.conn.prepareStatement("DELETE FROM codes_tbl WHERE code_type = ? AND parent_code = ?");
      deleteDataStmt.setString(1, this.codeTypeCombo.getSelectedItem().toString());
      deleteDataStmt.setString(2, "");
      deleteDataStmt.executeUpdate();

      PreparedStatement insertDataStmt = Utility.conn.prepareStatement("INSERT INTO codes_tbl(code_type, parent_code, code, description, is_deleted) VALUES (?, ?, ?, ?, ?)");
      for (int i = 0; i < this.codeTbl.getRowCount(); i++)
        if ((this.codeTbl.getValueAt(i, 0) != null) && (!this.codeTbl.getValueAt(i, 0).toString().equalsIgnoreCase("")))
        {
          insertDataStmt.setString(1, this.codeTypeCombo.getSelectedItem().toString());
          insertDataStmt.setString(2, "");
          insertDataStmt.setString(3, this.codeTbl.getValueAt(i, 0).toString());
          insertDataStmt.setString(4, "");
          insertDataStmt.setInt(5, this.codeTbl.getValueAt(i, 1).toString().indexOf("Undo") < 0 ? 0 : 1);
          insertDataStmt.executeUpdate();
        }
      Utility.finishjdbcconnection();
      TraitVariablesLst.initTraitMap();
      this.errMsgLbl.setText("Info:Data saved successfully!!!");
    } catch (Exception e) {
      this.errMsgLbl.setText(e.getMessage());
      e.printStackTrace();
    }
  }

  private void clearTableData()
  {
    for (int i = 0; i < this.codeTbl.getRowCount(); i++) {
      this.codeTbl.setValueAt("", i, 0);
      this.codeTbl.setValueAt("<html><i><u><b>Delete</b></u></i></html>", i, 1);
    }
  }

  private void deleteSingleData(int row, int deleteFlag) {
    if ((this.codeTbl.getValueAt(row, 0) == null) || (this.codeTbl.getValueAt(row, 0).toString().equalsIgnoreCase("")))
      return;
    try {
      if (!Utility.getjdbcconnection(this.errMsgLbl)) {
        return;
      }
      PreparedStatement deleteDataStmt = Utility.conn.prepareStatement("UPDATE codes_tbl SET is_deleted = ? WHERE code_type = ? AND parent_code = ? AND code = ?");
      deleteDataStmt.setInt(1, deleteFlag);
      deleteDataStmt.setString(2, this.codeTypeCombo.getSelectedItem().toString());
      deleteDataStmt.setString(3, "");
      deleteDataStmt.setString(4, this.codeTbl.getValueAt(row, 0).toString());
      deleteDataStmt.executeUpdate();
      Utility.finishjdbcconnection();
    } catch (Exception e) {
      this.errMsgLbl.setText(e.getMessage());
      return;
    }
  }
}
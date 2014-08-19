package com.wonderskool.bo.common;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.wonderskool.bo.utility.TableModels;
import com.wonderskool.bo.utility.TraitVariablesLst;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class DefineSystemVariables extends JFrame
{
  private JTable abilityTbl;
  private JTable cmplxPrsTbl;
  private JButton cnclBtn;
  private JLabel errMsgLbl;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane2;
  private JScrollPane jScrollPane3;
  private JScrollPane jScrollPane4;
  private JTable miTbl;
  private JTable qualityTbl;
  private JButton saveBtn;
  private JTextField topSxnTxt;

  public DefineSystemVariables()
  {
    com.wonderskool.bo.utility.TableModels.secondColName = "Cut Off Marks";
    TraitVariablesLst.initModel();
    initComponents();
    populateData();
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.jLabel1 = new JLabel();
    this.topSxnTxt = new JTextField();
    this.jLabel2 = new JLabel();
    this.jScrollPane1 = new JScrollPane();
    this.cmplxPrsTbl = new JTable();
    this.jScrollPane2 = new JScrollPane();
    this.miTbl = new JTable();
    this.jScrollPane3 = new JScrollPane();
    this.abilityTbl = new JTable();
    this.jScrollPane4 = new JScrollPane();
    this.qualityTbl = new JTable();
    this.saveBtn = new JButton();
    this.cnclBtn = new JButton();

    setDefaultCloseOperation(3);
    setTitle("Define System Variables");
    setIconImages(null);
    setResizable(false);
    setUndecorated(true);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.jLabel1.setHorizontalAlignment(4);
    this.jLabel1.setText("Top");

    this.jLabel2.setText("Sections to be considered");

    this.cmplxPrsTbl.setModel(new TableModels.CmplxPersTableModel());
    this.jScrollPane1.setViewportView(this.cmplxPrsTbl);

    this.miTbl.setModel(new TableModels.MITableModel());
    this.jScrollPane2.setViewportView(this.miTbl);

    this.abilityTbl.setModel(new TableModels.AbilityTableModel());
    this.jScrollPane3.setViewportView(this.abilityTbl);

    this.qualityTbl.setModel(new TableModels.QualityTableModel());
    this.jScrollPane4.setViewportView(this.qualityTbl);

    this.saveBtn.setMnemonic('S');
    this.saveBtn.setText("Save");
    this.saveBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        DefineSystemVariables.this.saveBtnActionPerformed(evt);
      }
    });
    this.cnclBtn.setMnemonic('C');
    this.cnclBtn.setText("Cancel");
    this.cnclBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        DefineSystemVariables.this.cnclBtnActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.errMsgLbl, -2, 547, -2).addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane3, -2, 207, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane4, -2, 207, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane2, -2, 207, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jScrollPane1, -2, 207, -2)).addGroup(layout.createSequentialGroup().addGap(34, 34, 34).addComponent(this.jLabel1, -2, 33, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.topSxnTxt, -2, 47, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2, -2, 176, -2)))).addGroup(layout.createSequentialGroup().addGap(8, 8, 8).addComponent(this.saveBtn).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cnclBtn))).addContainerGap(-1, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.topSxnTxt, -2, -1, -2).addComponent(this.jLabel2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane2, -2, 172, -2).addComponent(this.jScrollPane1, -2, 172, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane3, -2, 140, -2).addComponent(this.jScrollPane4, -2, 91, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cnclBtn).addComponent(this.saveBtn)).addGap(60, 60, 60)));

    pack();
  }

  private void cnclBtnActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void saveBtnActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    if (!DBValidateUtility.validUserAccess("define_system_var_write", this.errMsgLbl))
      return;
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      Utility.conn.createStatement().executeUpdate("DELETE FROM system_variables");
      PreparedStatement insertData = Utility.conn.prepareStatement("INSERT INTO system_variables VALUES (?,?)");
      insertData.setString(1, "TOP_SXN");
      insertData.setInt(2, Utility.parseInteger(this.topSxnTxt.getText()));
      insertData.executeUpdate();
      for (int i = 0; i < this.miTbl.getRowCount(); i++) {
        insertData.setString(1, this.miTbl.getValueAt(i, 0).toString());
        insertData.setInt(2, Utility.parseInteger(this.miTbl.getValueAt(i, 1).toString()));
        insertData.executeUpdate();
      }

      for (int i = 0; i < this.cmplxPrsTbl.getRowCount(); i++) {
        insertData.setString(1, this.cmplxPrsTbl.getValueAt(i, 0).toString());
        insertData.setInt(2, Utility.parseInteger(this.cmplxPrsTbl.getValueAt(i, 1).toString()));
        insertData.executeUpdate();
      }

      for (int i = 0; i < this.abilityTbl.getRowCount(); i++) {
        insertData.setString(1, this.abilityTbl.getValueAt(i, 0).toString());
        insertData.setInt(2, Utility.parseInteger(this.abilityTbl.getValueAt(i, 1).toString()));
        insertData.executeUpdate();
      }

      for (int i = 0; i < this.qualityTbl.getRowCount(); i++) {
        insertData.setString(1, this.qualityTbl.getValueAt(i, 0).toString());
        insertData.setInt(2, Utility.parseInteger(this.qualityTbl.getValueAt(i, 1).toString()));
        insertData.executeUpdate();
      }
      Utility.finishjdbcconnection();
      this.errMsgLbl.setText("Info: Data Added successfully!!!");
    }
    catch (Exception e) {
      e.printStackTrace();
      this.errMsgLbl.setText("Error:" + e.getMessage());
      return;
    }
  }

  private void populateData()
  {
    HashMap dataMap = new HashMap();
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      ResultSet dataSet = Utility.conn.createStatement().executeQuery("SELECT * FROM system_variables");
      while (dataSet.next()) {
        dataMap.put(dataSet.getString(1), Integer.valueOf(dataSet.getInt(2)));
      }
      this.topSxnTxt.setText(String.valueOf(dataMap.get("TOP_SXN")));
      for (int i = 0; i < this.miTbl.getRowCount(); i++) {
        this.miTbl.setValueAt(dataMap.get(this.miTbl.getValueAt(i, 0)), i, 1);
      }
      for (int i = 0; i < this.cmplxPrsTbl.getRowCount(); i++) {
        this.cmplxPrsTbl.setValueAt(dataMap.get(this.cmplxPrsTbl.getValueAt(i, 0)), i, 1);
      }
      for (int i = 0; i < this.abilityTbl.getRowCount(); i++) {
        this.abilityTbl.setValueAt(dataMap.get(this.abilityTbl.getValueAt(i, 0)), i, 1);
      }
      for (int i = 0; i < this.qualityTbl.getRowCount(); i++)
        this.qualityTbl.setValueAt(dataMap.get(this.qualityTbl.getValueAt(i, 0)), i, 1);
      Utility.finishjdbcconnection();
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
      return;
    }
  }
}
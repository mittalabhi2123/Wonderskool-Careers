package com.wonderskool.bo.common;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import com.wonderskool.bo.utility.Const;
import com.wonderskool.bo.utility.CreatePDF;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class AddCodeDesc extends JFrame
{
  private JLabel errMsgLbl;
  private JButton jButton1;
  private JButton jButton2;
  private JComboBox jComboBox1;
  private JComboBox jComboBox2;
  private JEditorPane jEditorPane1;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JScrollPane jScrollPane1;

  public AddCodeDesc()
  {
    initComponents();
    for (String str : Const.Code_Type.getProfTraitLst())
      this.jComboBox1.addItem(str);
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jLabel3 = new JLabel();
    this.jScrollPane1 = new JScrollPane();
    this.jEditorPane1 = new JEditorPane();
    this.jButton1 = new JButton();
    this.jComboBox1 = new JComboBox();
    this.jComboBox2 = new JComboBox();
    this.jButton2 = new JButton();

    setDefaultCloseOperation(3);
    setIconImages(null);
    setResizable(false);
    setUndecorated(true);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.jLabel1.setHorizontalAlignment(11);
    this.jLabel1.setText("Profession Trait:");

    this.jLabel2.setHorizontalAlignment(11);
    this.jLabel2.setText("Trait Variable:");

    this.jLabel3.setHorizontalAlignment(11);
    this.jLabel3.setText("Description:");

    this.jScrollPane1.setViewportView(this.jEditorPane1);

    this.jButton1.setMnemonic('S');
    this.jButton1.setText("Save");
    this.jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        AddCodeDesc.this.jButton1ActionPerformed(evt);
      }
    });
    this.jComboBox1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        AddCodeDesc.this.jComboBox1ActionPerformed(evt);
      }
    });
    this.jComboBox2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        AddCodeDesc.this.jComboBox2ActionPerformed(evt);
      }
    });
    this.jButton2.setMnemonic('C');
    this.jButton2.setText("Cancel");
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        AddCodeDesc.this.jButton2ActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.errMsgLbl, -2, 547, -2).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jLabel3, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel2, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING, -1, 106, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -2, 304, -2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jComboBox2, GroupLayout.Alignment.LEADING, 0, -1, 32767).addComponent(this.jComboBox1, GroupLayout.Alignment.LEADING, 0, 106, 32767)))).addGroup(layout.createSequentialGroup().addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2))).addContainerGap(-1, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jComboBox1, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jComboBox2, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.jScrollPane1, -2, 155, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton2).addComponent(this.jButton1)).addContainerGap(42, 32767)));

    pack();
  }

  private void jButton1ActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    if (!DBValidateUtility.validUserAccess("code_data_write", this.errMsgLbl))
      return;
    try {
      if (!Utility.getjdbcconnection(this.errMsgLbl))
        return;
      PreparedStatement prep = Utility.conn.prepareStatement("UPDATE codes_tbl SET description = ? WHERE code_type = ? AND parent_code = ? AND code = ?");
      prep.setString(1, this.jEditorPane1.getText());
      prep.setString(2, this.jComboBox1.getSelectedItem().toString());
      prep.setString(3, "");
      prep.setString(4, this.jComboBox2.getSelectedItem().toString());
      prep.executeUpdate();
      Utility.finishjdbcconnection();
      this.errMsgLbl.setText("Info:Record updated successfully!!!");
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void jComboBox1ActionPerformed(ActionEvent evt) {
    Utility.initCodeCombo(this.jComboBox2, this.jComboBox1.getSelectedItem().toString(), this.errMsgLbl, false);
    this.jComboBox2.removeItemAt(0);
    this.jEditorPane1.setText("");
  }

  private void jButton2ActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void jComboBox2ActionPerformed(ActionEvent evt) {
    try {
      String desc = null;
      if (this.jComboBox2.getSelectedIndex() > -1) {
        desc = CreatePDF.getCodeDesc(this.jComboBox1.getSelectedItem().toString(), this.jComboBox2.getSelectedItem().toString(), this.errMsgLbl);
        this.jEditorPane1.setText(desc);
      }
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new AddCodeDesc().setVisible(true);
      }
    });
  }
}
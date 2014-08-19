package com.wonderskool.bo.common;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class EnterCourseDetails extends JFrame
{
  private static boolean dataExists = false;
  private JButton closeBtn;
  private JComboBox courseCombo;
  private JLabel courseLbl;
  private JLabel errMsgLbl;
  private JComboBox levelCombo;
  private JLabel levelLbl;
  private JButton saveBtn;
  private JComboBox typeCombo;
  private JLabel typeLbl;

  public EnterCourseDetails()
  {
    initComponents();
    Utility.initCodeCombo(this.courseCombo, "Course", this.errMsgLbl, false);
    Utility.initCodeCombo(this.levelCombo, "Course Level", this.errMsgLbl, false);
    Utility.initCodeCombo(this.typeCombo, "Course Type", this.errMsgLbl, false);
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.courseCombo = new JComboBox();
    this.typeCombo = new JComboBox();
    this.levelCombo = new JComboBox();
    this.typeLbl = new JLabel();
    this.levelLbl = new JLabel();
    this.closeBtn = new JButton();
    this.saveBtn = new JButton();
    this.courseLbl = new JLabel();

    setDefaultCloseOperation(3);
    setTitle("Enter Course Details");
    setIconImages(null);
    setResizable(false);
    setUndecorated(true);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.courseCombo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        EnterCourseDetails.this.courseComboActionPerformed(evt);
      }
    });
    this.typeLbl.setHorizontalAlignment(4);
    this.typeLbl.setText("Type:");

    this.levelLbl.setHorizontalAlignment(4);
    this.levelLbl.setText("Level:");

    this.closeBtn.setMnemonic('C');
    this.closeBtn.setText("Close");
    this.closeBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        EnterCourseDetails.this.closeBtnActionPerformed(evt);
      }
    });
    this.saveBtn.setMnemonic('S');
    this.saveBtn.setText("Save");
    this.saveBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        EnterCourseDetails.this.saveBtnActionPerformed(evt);
      }
    });
    this.courseLbl.setHorizontalAlignment(4);
    this.courseLbl.setText("Course:");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.errMsgLbl, -2, 547, -2).addGroup(layout.createSequentialGroup().addComponent(this.courseLbl, -2, 99, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.courseCombo, -2, 143, -2)).addGroup(layout.createSequentialGroup().addComponent(this.typeLbl, -2, 99, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.typeCombo, -2, 143, -2)).addGroup(layout.createSequentialGroup().addComponent(this.levelLbl, -2, 99, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.levelCombo, -2, 143, -2)).addGroup(layout.createSequentialGroup().addComponent(this.saveBtn).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.closeBtn))).addContainerGap(-1, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.courseCombo, -2, -1, -2).addComponent(this.courseLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.typeLbl).addComponent(this.typeCombo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.levelLbl).addComponent(this.levelCombo, -2, -1, -2)).addGap(48, 48, 48).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.saveBtn).addComponent(this.closeBtn)).addContainerGap(25, 32767)));

    pack();
  }

  private void courseComboActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    if (this.courseCombo.getSelectedIndex() <= 0)
      return;
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      ResultSet dataSet = Utility.conn.createStatement().executeQuery("SELECT * FROM course_details WHERE course = ?".replaceAll("?", this.courseCombo.getSelectedItem().toString()));
      if (dataSet.next()) {
        dataExists = true;
        this.typeCombo.setSelectedItem(dataSet.getString(2));
        this.levelCombo.setSelectedItem(dataSet.getString(3));
      }
      else {
        dataExists = false;
      }
    } catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void closeBtnActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void saveBtnActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    if (this.courseCombo.getSelectedIndex() <= 0) {
      this.errMsgLbl.setText("Error:Course is mandatory!!!");
      return;
    }
    if (this.typeCombo.getSelectedIndex() <= 0) {
      this.errMsgLbl.setText("Error:Course type is mandatory!!!");
      return;
    }
    if (this.levelCombo.getSelectedIndex() <= 0) {
      this.errMsgLbl.setText("Error:Course level is mandatory!!!");
      return;
    }
    try {
      if (!DBValidateUtility.validUserAccess("enter_course_dtl_write", this.errMsgLbl))
        return;
      if (dataExists) {
        PreparedStatement updateStmt = Utility.conn.prepareStatement("UPDATE course_details SET type ?, level = ? WHERE course = ?");
        updateStmt.setString(1, this.typeCombo.getSelectedItem().toString());
        updateStmt.setString(2, this.levelCombo.getSelectedItem().toString());
        updateStmt.setString(3, this.courseCombo.getSelectedItem().toString());
        updateStmt.executeUpdate();
      }
      else {
        PreparedStatement insertStmt = Utility.conn.prepareStatement("INSERT INTO course_details(course, type, level) VALUES (?, ?, ?)");
        insertStmt.setString(1, this.courseCombo.getSelectedItem().toString());
        insertStmt.setString(2, this.typeCombo.getSelectedItem().toString());
        insertStmt.setString(3, this.levelCombo.getSelectedItem().toString());
        insertStmt.executeUpdate();
      }
      this.errMsgLbl.setText("INFO:Data saved successfully!!!");
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }
}
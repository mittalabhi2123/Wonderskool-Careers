package com.wonderskool.main.bo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.wonderskool.bo.utility.DatePicker;
import com.wonderskool.bo.utility.QueryCollection;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class StudentProfile extends JFrame
{
  private static boolean dataExists = false;
  private JLabel batchLbl;
  private JButton cancelBtn;
  private JComboBox classCombo;
  private JLabel dobLbl;
  private JTextField dobTxt;
  private JLabel enrollLbl;
  private JTextField enrollTxt;
  private JLabel errMsgLbl;
  private JLabel nameLbl;
  private JTextField nameTxt;
  private JButton saveBtn;
  private JComboBox sectionCombo;

  public StudentProfile()
  {
    initComponents();
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.enrollLbl = new JLabel();
    this.enrollTxt = new JTextField();
    this.nameLbl = new JLabel();
    this.nameTxt = new JTextField();
    this.batchLbl = new JLabel();
    this.classCombo = new JComboBox();
    this.sectionCombo = new JComboBox();
    this.saveBtn = new JButton();
    this.cancelBtn = new JButton();
    this.dobLbl = new JLabel();
    this.dobTxt = new JTextField();

    setDefaultCloseOperation(3);
    setTitle("Student Profile");
    setIconImages(null);
    setResizable(false);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));

    this.enrollLbl.setHorizontalAlignment(4);
    this.enrollLbl.setText("Enrollment No.:");

    this.enrollTxt.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent evt) {
        StudentProfile.this.enrollTxtFocusLost(evt);
      }
    });
    this.nameLbl.setHorizontalAlignment(4);
    this.nameLbl.setText("Name:");

    this.batchLbl.setHorizontalAlignment(4);
    this.batchLbl.setText("Class:");

    this.classCombo.setModel(new DefaultComboBoxModel(new String[] { "PRE-NUR", "NUR", "KG", "L-KG", "U-KG", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII" }));

    this.sectionCombo.setModel(new DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));

    this.saveBtn.setMnemonic('S');
    this.saveBtn.setText("Save");
    this.saveBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        StudentProfile.this.saveBtnActionPerformed(evt);
      }
    });
    this.cancelBtn.setMnemonic('C');
    this.cancelBtn.setText("Cancel");
    this.cancelBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        StudentProfile.this.cancelBtnActionPerformed(evt);
      }
    });
    this.dobLbl.setHorizontalAlignment(4);
    this.dobLbl.setText("Date of Birth:");

    this.dobTxt.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        StudentProfile.this.dobClicked(evt);
      }
    });
    this.dobTxt.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        StudentProfile.this.dobKeyTyped(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.errMsgLbl, -2, 543, -2).addGroup(layout.createSequentialGroup().addComponent(this.nameLbl, -2, 92, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.nameTxt, -2, 218, -2)).addGroup(layout.createSequentialGroup().addComponent(this.enrollLbl, -2, 92, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.enrollTxt, -2, 218, -2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.dobLbl, -2, 92, -2).addComponent(this.batchLbl, -2, 92, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.dobTxt, -2, 218, -2).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.classCombo, 0, -1, 32767).addGap(18, 18, 18).addComponent(this.sectionCombo, -2, 47, -2)))).addGroup(layout.createSequentialGroup().addComponent(this.saveBtn, -2, 97, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cancelBtn, -2, 97, -2))).addContainerGap(-1, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(7, 7, 7).addComponent(this.errMsgLbl).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.nameLbl).addComponent(this.nameTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.enrollLbl).addComponent(this.enrollTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.dobLbl).addComponent(this.dobTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.classCombo, -2, -1, -2).addComponent(this.batchLbl).addComponent(this.sectionCombo, -2, -1, -2)).addGap(44, 44, 44).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.saveBtn).addComponent(this.cancelBtn)).addContainerGap(49, 32767)));

    pack();
  }

  private void cancelBtnActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void saveBtnActionPerformed(ActionEvent evt) {
    if (!validatePass()) {
      return;
    }
    if (!DBValidateUtility.validUserAccess("stu_profile_write", this.errMsgLbl))
      return;
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      String instName = Utility.getInstName();

      if (dataExists) {
        Statement delDataStmt = Utility.conn.createStatement();
        delDataStmt.executeUpdate(QueryCollection.STUDENT_PROFILE.GET_EXISTING_DATA.replace("?", this.enrollTxt.getText().toLowerCase()));
      }
      PreparedStatement saveDataStmt = Utility.conn.prepareStatement("INSERT INTO student_tbl VALUES (?, ?, ?, ?, ?, ?)");
      saveDataStmt.setString(1, instName);
      saveDataStmt.setString(2, this.enrollTxt.getText());
      saveDataStmt.setString(3, this.nameTxt.getText());
      saveDataStmt.setLong(4, Utility.stringDate2Long(this.dobTxt.getText()));
      saveDataStmt.setString(5, this.classCombo.getSelectedItem().toString());
      saveDataStmt.setString(6, this.sectionCombo.getSelectedItem().toString());
      resetFields();
      saveDataStmt.execute();
      Utility.finishjdbcconnection();
    }
    catch (Exception e) {
      this.errMsgLbl.setText(e.getMessage());
      return;
    }
    this.errMsgLbl.setText("Info: Record Saved successfully!!!");
  }

  private void enrollTxtFocusLost(FocusEvent evt) {
    this.errMsgLbl.setText("");
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    if (Utility.isNullEmpty(this.enrollTxt.getText()))
      return;
    try {
      Statement getDataStmt = Utility.conn.createStatement();
      ResultSet dataSet = getDataStmt.executeQuery(QueryCollection.STUDENT_PROFILE.GET_EXISTING_DATA.replace("?", this.enrollTxt.getText().toLowerCase()));
      if (dataSet.next()) {
        dataExists = true;
        this.nameTxt.setText(dataSet.getString(3));
        this.classCombo.setSelectedItem(dataSet.getString(5));
        this.sectionCombo.setSelectedItem(dataSet.getString(6));
        this.dobTxt.setText(Utility.long2StringDate(Long.valueOf(dataSet.getLong(4))));
      }
      else {
        dataExists = false;

        this.classCombo.setSelectedItem("");
        this.sectionCombo.setSelectedItem("");
        this.dobTxt.setText("");
      }
      Utility.finishjdbcconnection();
    }
    catch (Exception e) {
      this.errMsgLbl.setText(e.getMessage());
    }
  }

  private void dobClicked(MouseEvent evt) {
    this.errMsgLbl.setText("");
    this.dobTxt.setText(new DatePicker().setPickedDate());
  }

  private void dobKeyTyped(KeyEvent evt) {
    if (evt.getKeyChar() == '\n')
      dobClicked(null);
  }

  private boolean validatePass()
  {
    boolean flag = true;
    Border border = new LineBorder(Color.RED);
    if (Utility.isNullEmpty(this.enrollTxt.getText())) {
      this.enrollTxt.setBorder(border);
      this.enrollTxt.setToolTipText("Enrollment No is mandatory");
      flag = false;
    }
    if (Utility.isNullEmpty(this.nameTxt.getText())) {
      this.nameTxt.setBorder(border);
      this.nameTxt.setToolTipText("Name is mandatory");
      flag = false;
    }
    if (Utility.isNullEmpty(this.sectionCombo.getSelectedItem().toString())) {
      this.sectionCombo.setBorder(border);
      this.sectionCombo.setToolTipText("Section is mandatory");
      flag = false;
    }
    if (Utility.isNullEmpty(this.classCombo.getSelectedItem().toString())) {
      this.classCombo.setBorder(border);
      this.classCombo.setToolTipText("Class is mandatory");
      flag = false;
    }

    return flag;
  }

  private void resetFields() {
    Border border = new LineBorder(Color.BLACK);
    this.enrollTxt.setBorder(border);
    this.enrollTxt.setToolTipText("");
    this.nameTxt.setBorder(border);
    this.nameTxt.setToolTipText("");
    this.sectionCombo.setBorder(border);
    this.sectionCombo.setToolTipText("");
    this.classCombo.setBorder(border);
    this.classCombo.setToolTipText("");
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new StudentProfile().setVisible(true);
      }
    });
  }
}
package com.wonderskool.bo.common;

import java.awt.Color;
import java.awt.Font;
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

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import com.wonderskool.bo.utility.DatePicker;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class CreateUser extends JFrame
{
  private static boolean dataExists = false;
  private JTable accessTbl;
  private JCheckBox adminCb;
  private JButton cancelBtn;
  private JLabel cnfrmPwdLbl;
  private JPasswordField cnfrmPwdTxt;
  private JLabel contactNoLbl;
  private JTextField contactNoTxt;
  private JButton deleteBtn;
  private JLabel emailLbl;
  private JTextField emailTxt;
  private JLabel errMsgLbl;
  private JTextField instTxt;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JScrollPane jScrollPane1;
  private JLabel pwdLbl;
  private JPasswordField pwdTxt;
  private JButton saveBtn;
  private JLabel userIdLbl;
  private JTextField userIdTxt;
  private JLabel validLbl;
  private JTextField validTxt;

  public CreateUser()
  {
    initComponents();
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.userIdLbl = new JLabel();
    this.userIdTxt = new JTextField();
    this.pwdLbl = new JLabel();
    this.cnfrmPwdLbl = new JLabel();
    this.contactNoLbl = new JLabel();
    this.contactNoTxt = new JTextField();
    this.emailLbl = new JLabel();
    this.emailTxt = new JTextField();
    this.validLbl = new JLabel();
    this.validTxt = new JTextField();
    this.adminCb = new JCheckBox();
    this.jLabel1 = new JLabel();
    this.jScrollPane1 = new JScrollPane();
    this.accessTbl = new JTable();
    this.saveBtn = new JButton();
    this.deleteBtn = new JButton();
    this.cancelBtn = new JButton();
    this.cnfrmPwdTxt = new JPasswordField();
    this.pwdTxt = new JPasswordField();
    this.jLabel2 = new JLabel();
    this.instTxt = new JTextField();

    setDefaultCloseOperation(3);
    setTitle("Maintain User");
    setIconImages(null);
    setResizable(false);
    setUndecorated(true);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.userIdLbl.setHorizontalAlignment(4);
    this.userIdLbl.setText("User Id:");

    this.userIdTxt.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent evt) {
        CreateUser.this.userIdTxtFocusLost(evt);
      }
    });
    this.pwdLbl.setHorizontalAlignment(4);
    this.pwdLbl.setText("Password:");

    this.cnfrmPwdLbl.setHorizontalAlignment(4);
    this.cnfrmPwdLbl.setText("Confirm Password:");

    this.contactNoLbl.setHorizontalAlignment(4);
    this.contactNoLbl.setText("Contact No:");

    this.emailLbl.setHorizontalAlignment(4);
    this.emailLbl.setText("Email:");

    this.validLbl.setHorizontalAlignment(4);
    this.validLbl.setText("Valid Till:");

    this.validTxt.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        CreateUser.this.validTillClicked(evt);
      }
    });
    this.validTxt.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        CreateUser.this.validTillTyped(evt);
      }
    });
    this.adminCb.setText("Administrator");
    this.adminCb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        CreateUser.this.adminCbActionPerformed(evt);
      }
    });
    this.jLabel1.setFont(new Font("Tahoma", 1, 14));
    this.jLabel1.setText("User Access Permissions");

    this.accessTbl.setBorder(BorderFactory.createBevelBorder(0));
    this.accessTbl.setModel(new DefaultTableModel(new Object[][] { { "Define QA Data", null, null }, { "Add Student Profile", null, null }, { "Define System Variables", null, null }, { "Enter Course Details", null, null }, { "Maintain Codes", null, null }, { "Upload Profession Data", null, null }, { "Upload Student Result", null, null }, { "Process Bulk Results", null, null }, { "Process Single Result", null, null }, { "Maintain User Account", null, null } }, new String[] { "Function", "Read", "Write" })
    {
      Class[] types = { String.class, Boolean.class, Boolean.class };

      boolean[] canEdit = { false, true, true };

      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return this.canEdit[columnIndex];
      }
    });
    this.accessTbl.setOpaque(false);
    this.accessTbl.setRowHeight(20);
    this.accessTbl.setRowMargin(2);
    this.accessTbl.setUpdateSelectionOnSort(false);
    this.jScrollPane1.setViewportView(this.accessTbl);
    this.accessTbl.setFillsViewportHeight(true);

    this.saveBtn.setMnemonic('S');
    this.saveBtn.setText("Save");
    this.saveBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        CreateUser.this.saveBtnActionPerformed(evt);
      }
    });
    this.deleteBtn.setMnemonic('D');
    this.deleteBtn.setText("Delete");
    this.deleteBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        CreateUser.this.deleteBtnActionPerformed(evt);
      }
    });
    this.cancelBtn.setMnemonic('C');
    this.cancelBtn.setText("Cancel");
    this.cancelBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        CreateUser.this.cancelBtnActionPerformed(evt);
      }
    });
    this.jLabel2.setHorizontalAlignment(11);
    this.jLabel2.setText("Institute:");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl, -2, 547, -2)).addGroup(layout.createSequentialGroup().addGap(51, 51, 51).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jScrollPane1, -2, 416, -2).addGroup(layout.createSequentialGroup().addComponent(this.saveBtn, -2, 74, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.deleteBtn, -2, 74, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cancelBtn, -2, 74, -2))))).addGap(0, 0, 32767)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(53, 53, 53).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.userIdLbl, -2, 92, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.userIdTxt, -2, 218, -2)).addGroup(layout.createSequentialGroup().addComponent(this.pwdLbl, -2, 92, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pwdTxt, -2, 218, -2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.contactNoLbl, -2, 92, -2).addComponent(this.validLbl).addComponent(this.jLabel2, -2, 62, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.validTxt, -1, 218, 32767).addComponent(this.contactNoTxt, -1, 218, 32767).addComponent(this.adminCb, -2, 166, -2).addComponent(this.instTxt))))).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.emailLbl, -2, 64, -2).addComponent(this.cnfrmPwdLbl, -2, 135, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.emailTxt).addComponent(this.cnfrmPwdTxt, -2, 218, -2)))).addContainerGap(-1, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.errMsgLbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.userIdLbl).addComponent(this.userIdTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.pwdLbl).addComponent(this.pwdTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cnfrmPwdLbl).addComponent(this.cnfrmPwdTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.emailLbl).addComponent(this.emailTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.contactNoLbl).addComponent(this.contactNoTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.validTxt, -2, -1, -2).addComponent(this.validLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.instTxt, -2, -1, -2).addComponent(this.jLabel2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.adminCb).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jScrollPane1, -2, 227, -2).addGap(25, 25, 25).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.saveBtn).addComponent(this.deleteBtn).addComponent(this.cancelBtn)).addContainerGap()));

    pack();
  }

  private void validTillClicked(MouseEvent evt) {
    this.errMsgLbl.setText("");
    this.validTxt.setText(new DatePicker().setPickedDate());
  }

  private void adminCbActionPerformed(ActionEvent evt) {
    this.accessTbl.setEnabled(!this.adminCb.isSelected());
    clearTblData(this.adminCb.isSelected());
  }

  private void deleteBtnActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    if (!DBValidateUtility.validUserAccess("user_write", this.errMsgLbl))
      return;
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      PreparedStatement chekDupUserId = Utility.conn.prepareStatement("DELETE FROM user_tbl WHERE LOWER(user_id) = ?");
      chekDupUserId.setString(1, this.userIdTxt.getText().toLowerCase());
      chekDupUserId.executeUpdate();
      Utility.finishjdbcconnection();
      if (evt != null)
        this.errMsgLbl.setText("Info:Data Deleted successfully!!!");
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void saveBtnActionPerformed(ActionEvent evt) {
    this.errMsgLbl.setText("");
    if (!DBValidateUtility.validUserAccess("user_write", this.errMsgLbl))
      return;
    if (!validateData())
      return;
    if (dataExists)
      deleteBtnActionPerformed(null);
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      PreparedStatement insertUserDataStmt = Utility.conn.prepareStatement("INSERT INTO user_tbl VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      insertUserDataStmt.setString(1, this.userIdTxt.getText());
      insertUserDataStmt.setString(2, new String(this.pwdTxt.getPassword()));
      insertUserDataStmt.setString(3, this.emailTxt.getText());
      insertUserDataStmt.setString(4, this.contactNoTxt.getText());
      insertUserDataStmt.setInt(5, this.adminCb.isSelected() ? 1 : 0);
      insertUserDataStmt.setLong(6, !Utility.isNullEmpty(this.validTxt.getText()) ? Utility.stringDate2Long(this.validTxt.getText()) : 0L);
      int counter = 7;
      for (int i = 0; i < 10; i++) {
        insertUserDataStmt.setInt(counter++, ((Boolean)this.accessTbl.getValueAt(i, 1)).booleanValue() ? 1 : 0);
        insertUserDataStmt.setInt(counter++, ((Boolean)this.accessTbl.getValueAt(i, 2)).booleanValue() ? 1 : 0);
      }
      insertUserDataStmt.setString(27, this.instTxt.getText().toUpperCase());
      insertUserDataStmt.executeUpdate();
      Utility.finishjdbcconnection();
      clearTblData(false);
      this.accessTbl.setEnabled(true);
      this.errMsgLbl.setText("Info:Data saved successfully!!!");
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void cancelBtnActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void userIdTxtFocusLost(FocusEvent evt) {
    this.errMsgLbl.setText("");
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    if (Utility.isNullEmpty(this.userIdTxt.getText())) {
      clearTblData(false);
      dataExists = false;
      this.accessTbl.setEnabled(true);
    }
    try {
      PreparedStatement chekDupUserId = Utility.conn.prepareStatement("SELECT * FROM user_tbl WHERE LOWER(user_id) = ?");
      chekDupUserId.setString(1, this.userIdTxt.getText().toLowerCase());
      ResultSet dataSet = chekDupUserId.executeQuery();
      Utility.finishjdbcconnection();
      if (dataSet.next()) {
        dataExists = true;
        this.pwdTxt.setText(dataSet.getString(2));
        this.cnfrmPwdTxt.setText(dataSet.getString(2));
        this.emailTxt.setText(dataSet.getString(3));
        this.contactNoTxt.setText(dataSet.getString(4));
        this.adminCb.setSelected(dataSet.getInt(5) == 1);
        Long validDate = Long.valueOf(dataSet.getLong(6));
        this.validTxt.setText(validDate.longValue() > 0L ? Utility.long2StringDate(validDate) : "");
        if (this.adminCb.isSelected()) {
          clearTblData(true);
          this.accessTbl.setEnabled(false);
        }
        else {
          int counter = 7;
          this.accessTbl.setEnabled(true);
          for (int i = 0; i < 10; i++) {
            this.accessTbl.setValueAt(Boolean.valueOf(dataSet.getInt(counter++) == 1), i, 1);
            this.accessTbl.setValueAt(Boolean.valueOf(dataSet.getInt(counter++) == 1), i, 2);
          }
        }
        this.instTxt.setText(dataSet.getString(27));
      }
      else {
        dataExists = false;
        clearTblData(false);
        this.pwdTxt.setText("");
        this.cnfrmPwdTxt.setText("");
        this.emailTxt.setText("");
        this.contactNoTxt.setText("");
        this.adminCb.setSelected(false);
        this.validTxt.setText("");
        this.instTxt.setText("");
        this.accessTbl.setEnabled(true);
      }
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  private void validTillTyped(KeyEvent evt) {
    if (evt.getKeyChar() == '\n')
      validTillClicked(null);
  }

  private void clearTblData(boolean value)
  {
    for (int i = 0; i < 10; i++) {
      this.accessTbl.setValueAt(Boolean.valueOf(value), i, 1);
      this.accessTbl.setValueAt(Boolean.valueOf(value), i, 2);
    }
  }

  private boolean validateData() {
    if (Utility.isNullEmpty(this.userIdTxt.getText())) {
      this.errMsgLbl.setText("Error:User Id is mandatory!!!");
      return false;
    }
    String pwd = new String(this.pwdTxt.getPassword());
    if (Utility.isNullEmpty(pwd)) {
      this.errMsgLbl.setText("Error:Password is mandatory!!!");
      return false;
    }
    if (Utility.isNullEmpty(new String(this.cnfrmPwdTxt.getPassword()))) {
      this.errMsgLbl.setText("Error:Please confirm password!!!");
      return false;
    }
    if ((pwd.length() < 8) || (pwd.length() > 12)) {
      this.errMsgLbl.setText("Error:Password length should be between 8 and 12!!!");
      return false;
    }
    if (!pwd.equals(new String(this.cnfrmPwdTxt.getPassword()))) {
      this.errMsgLbl.setText("Error:Both passwords doesn't match!!!");
      return false;
    }
    if ((!Utility.isNullEmpty(this.validTxt.getText())) && (Utility.stringDate2Long(this.validTxt.getText()) < System.currentTimeMillis())) {
      this.errMsgLbl.setText("Error:Valid up to date cannot be past date!!!");
      return false;
    }
    if (Utility.isNullEmpty(this.instTxt.getText())) {
      this.errMsgLbl.setText("Error:Institute Name is mandatory!!!");
      return false;
    }
    for (int i = 0; i < 9; i++) {
      if ((((Boolean)this.accessTbl.getValueAt(i, 2)).booleanValue()) && (!((Boolean)this.accessTbl.getValueAt(i, 1)).booleanValue())) {
        this.errMsgLbl.setText("Error:Write access cannot be given without read access for " + this.accessTbl.getValueAt(i, 0) + "!!!");
        return false;
      }
    }
    return true;
  }
}
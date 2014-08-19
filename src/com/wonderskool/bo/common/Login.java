package com.wonderskool.bo.common;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.wonderskool.bo.utility.TraitVariablesLst;
import com.wonderskool.bo.utility.Utility;

public class Login extends JFrame
{
  private JLabel errMsgLbl;
  private JComboBox instCombo;
  private JLabel instLbl;
  private JButton jButton1;
  private JButton jButton2;
  private JLabel pwdLbl;
  private JPasswordField pwdTxt;
  private JLabel userIdLbl;
  private JTextField userIdTxt;

  public Login()
  {
    Utility.initSystVariables();
    initComponents();
    Utility.initInstCombo(this.instCombo, this.errMsgLbl);
  }

  private void initComponents()
  {
    this.userIdTxt = new JTextField();
    this.pwdLbl = new JLabel();
    this.pwdTxt = new JPasswordField();
    this.userIdLbl = new JLabel();
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();
    this.errMsgLbl = new JLabel();
    this.instLbl = new JLabel();
    this.instCombo = new JComboBox();

    setDefaultCloseOperation(3);
    setTitle("Login");
    setIconImages(null);

    this.pwdLbl.setText("Password:");

    this.userIdLbl.setHorizontalAlignment(4);
    this.userIdLbl.setText("User Name:");

    this.jButton1.setMnemonic('L');
    this.jButton1.setText("Login");
    this.jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Login.this.jButton1ActionPerformed(evt);
      }
    });
    this.jButton1.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        Login.this.loginKeyTyped(evt);
      }
    });
    this.jButton2.setMnemonic('C');
    this.jButton2.setText("Cancel");
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Login.this.jButton2ActionPerformed(evt);
      }
    });
    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.instLbl.setHorizontalAlignment(11);
    this.instLbl.setText("Institute:");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(116, 116, 116).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jButton1, -2, 75, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.userIdLbl).addComponent(this.pwdLbl, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.instLbl, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.pwdTxt, -1, 97, 32767).addComponent(this.userIdTxt).addComponent(this.instCombo, 0, -1, 32767)))).addContainerGap(294, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl, -1, -1, 32767).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.errMsgLbl).addGap(77, 77, 77).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.userIdTxt, -2, -1, -2).addComponent(this.userIdLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.pwdLbl).addComponent(this.pwdTxt, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.instLbl).addComponent(this.instCombo, -2, -1, -2)).addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(108, 32767)));

    pack();
  }

  private void jButton2ActionPerformed(ActionEvent evt) {
    dispose();
  }

  private void jButton1ActionPerformed(ActionEvent evt) {
    loginAction();
  }

  private void loginKeyTyped(KeyEvent evt) {
    if (evt.getKeyChar() == '\n')
      loginAction();
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(new AluminiumLookAndFeel());
    } catch (Exception ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
    Login loginForm = new Login();
    loginForm.setLocationRelativeTo(null);

    loginForm.setVisible(true);
  }

  private void loginAction()
  {
    this.errMsgLbl.setText("");
    if (Utility.isNullEmpty(this.userIdTxt.getText())) {
      this.errMsgLbl.setText("Error:User Name not entered!!!");
      return;
    }
    if (Utility.isNullEmpty(new String(this.pwdTxt.getPassword()))) {
      this.errMsgLbl.setText("Error:Password not entered!!!");
      return;
    }
    if (this.instCombo.getSelectedIndex() <= 0) {
      this.errMsgLbl.setText("Error:Institute not selected!!!");
      return;
    }
    if (!Utility.getjdbcconnection(this.errMsgLbl))
      return;
    try {
      PreparedStatement loginStmt = Utility.conn.prepareStatement("SELECT * FROM user_tbl WHERE LOWER(user_id) = ?");
      loginStmt.setString(1, this.userIdTxt.getText().toLowerCase());
      ResultSet dataSet = loginStmt.executeQuery();
      String instName = "";
      if (dataSet.next()) {
        String password = dataSet.getString("password");
        Long validDate = Long.valueOf(dataSet.getLong("valid_till"));
        instName = dataSet.getString("inst_name");

        if (!new String(this.pwdTxt.getPassword()).equals(password)) {
          this.errMsgLbl.setText("Error:Invalid password!!!");
          return;
        }
        if (validDate.longValue() < System.currentTimeMillis()) {
          this.errMsgLbl.setText("Error:User Account Expired. Please contact admin!!!");
          return;
        }
      }
      else {
        this.errMsgLbl.setText("Error:Invalid User name supplied!!!");
        return;
      }
      Utility.userName = this.userIdTxt.getText();
      Utility.instName = instName;
      TraitVariablesLst.initTraitMap();
      dispose();
      Utility.startFrame(new MenuFrame());
    }
    catch (Exception e) {
      e.printStackTrace();
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }
}
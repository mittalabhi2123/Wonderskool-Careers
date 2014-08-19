package com.wonderskool.bo.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import com.wonderskool.bo.utility.Utility;
import com.wonderskool.bo.utility.upload.StudentMarksUpload;
import com.wonderskool.bo.utility.upload.UploadProfessionCriteria;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;
import com.wonderskool.main.bo.ProcessBulkResults;
import com.wonderskool.main.bo.ProcessSingleResult;
import com.wonderskool.main.bo.StudentProfile;

public class MenuFrame extends JFrame
{
  private JLabel errMsgLbl;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JScrollPane jScrollPane1;
  private JTable jTable1;
  private JLabel welcomeLbl;

  public MenuFrame()
  {
    initComponents();
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.jScrollPane1 = new JScrollPane();
    this.jTable1 = new JTable();
    this.welcomeLbl = new JLabel();
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();

    setDefaultCloseOperation(3);
    setTitle("Menu");
    setIconImages(null);
    setResizable(false);
    setUndecorated(true);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.jScrollPane1.setFont(new Font("Tahoma", 0, 36));

    this.jTable1.setFont(new Font("Tahoma", 3, 18));
    this.jTable1.setModel(new DefaultTableModel(new Object[][] { { "Maintain Codes" }, { "Define QA Data" }, { "Define System Variables" }, { "Enter Course Details" }, { "Upload Profession Data" }, { "Upload Student Result" }, { "Add Student Profile" }, { "Process Bulk Results" }, { "Process Single Result" }, { "Maintain User Account" }, { "Update Code Description" } }, new String[] { "" })
    {
      Class[] types = { String.class };

      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
    });
    this.jTable1.setFillsViewportHeight(true);
    this.jTable1.setIntercellSpacing(new Dimension(3, 3));
    this.jTable1.setPreferredSize(new Dimension(120, 432));
    this.jTable1.setRowHeight(48);
    this.jTable1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        MenuFrame.this.menuItemClicked(evt);
      }
    });
    this.jScrollPane1.setViewportView(this.jTable1);

    this.welcomeLbl.setFont(new Font("Tahoma", 3, 14));
    this.welcomeLbl.setText("Welcome " + Utility.userName + "!!!");

    this.jLabel1.setFont(new Font("Tahoma", 3, 11));
    this.jLabel1.setForeground(new Color(102, 0, 102));
    this.jLabel1.setText("<html><u>Logout</u></html>");
    this.jLabel1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        MenuFrame.this.logoutClicked(evt);
      }
    });
    this.jLabel2.setFont(new Font("Tahoma", 3, 11));
    this.jLabel2.setForeground(new Color(102, 0, 102));
    this.jLabel2.setHorizontalAlignment(0);
    this.jLabel2.setText("<html><u>Change Theme</u></html>");
    this.jLabel2.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        MenuFrame.this.changeThemeClicked(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.errMsgLbl, -1, -1, 32767).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent(this.welcomeLbl).addGap(163, 163, 163).addComponent(this.jLabel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel2, -1, 106, 32767)).addComponent(this.jScrollPane1, -2, 0, 32767)))).addContainerGap(36, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.welcomeLbl).addComponent(this.jLabel2, -2, -1, -2)).addComponent(this.jLabel1, GroupLayout.Alignment.TRAILING, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 537, 32767).addContainerGap()));

    pack();
  }

  private void menuItemClicked(MouseEvent evt) {
    JFrame targetFrame = null;
    String readAccessName = "";
    switch (this.jTable1.getSelectedRow()) {
    case 0:
      targetFrame = new UpdateCodes();
      readAccessName = "code_data_read";
      break;
    case 1:
      targetFrame = new UpdateQAData();
      readAccessName = "update_qa_data_read";
      break;
    case 2:
      targetFrame = new DefineSystemVariables();
      readAccessName = "define_system_var_read";
      break;
    case 3:
      targetFrame = new EnterCourseDetails();
      readAccessName = "enter_course_dtl_read";
      break;
    case 4:
      targetFrame = new UploadProfessionCriteria();
      readAccessName = "upload_profession_read";
      break;
    case 5:
      targetFrame = new StudentMarksUpload();
      readAccessName = "upload_marks_read";
      break;
    case 6:
      targetFrame = new StudentProfile();
      readAccessName = "stu_profile_read";
      break;
    case 7:
      targetFrame = new ProcessBulkResults();
      readAccessName = "bulk_result_read";
      break;
    case 8:
      targetFrame = new ProcessSingleResult();
      readAccessName = "single_result_read";
      break;
    case 9:
      targetFrame = new CreateUser();
      readAccessName = "user_read";
      break;
    case 10:
      targetFrame = new AddCodeDesc();
      readAccessName = "code_data_read";
    }

    if (!DBValidateUtility.validUserAccess(readAccessName, this.errMsgLbl))
      return;
    Utility.startFrame(targetFrame);
    dispose();
  }

  private void logoutClicked(MouseEvent evt) {
    Utility.userName = "";
    com.wonderskool.bo.utility.TraitVariablesLst.dataMap = new LinkedHashMap();
    dispose();
    Login loginForm = new Login();
    loginForm.setLocationRelativeTo(null);
    loginForm.setVisible(true);
  }

  private void changeThemeClicked(MouseEvent evt) {
    Utility.startFrame(new ChangeTheme());
    dispose();
  }
}
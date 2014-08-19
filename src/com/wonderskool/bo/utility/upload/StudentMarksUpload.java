package com.wonderskool.bo.utility.upload;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.wonderskool.bo.excel.ExcelWriterTest;
import com.wonderskool.bo.excel.StudentMarksExcelReader;
import com.wonderskool.bo.utility.QueryCollection;
import com.wonderskool.bo.utility.TableModels;
import com.wonderskool.bo.utility.TraitVariablesLst;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class StudentMarksUpload extends JFrame
{
  private File selectedFile = null;
  private JTable abilityTbl;
  private JCheckBox appendCb;
  private JTable cmplxPrsTbl;
  private JLabel errMsgLbl;
  private JButton jButton1;
  private JButton jButton2;
  private JButton jButton3;
  private JLabel jLabel1;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane2;
  private JScrollPane jScrollPane3;
  private JScrollPane jScrollPane4;
  private JScrollPane jScrollPane5;
  private JTextArea jTextArea1;
  private JTextField jTextField1;
  private JTable miTbl;
  private JTable qualityTbl;

  public StudentMarksUpload()
  {
    com.wonderskool.bo.utility.TableModels.secondColName = "Column No.";
    TraitVariablesLst.initModel();
    initComponents();
    this.jTextArea1.setVisible(false);
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.jTextField1 = new JTextField();
    this.jLabel1 = new JLabel();
    this.jScrollPane2 = new JScrollPane();
    this.miTbl = new JTable();
    this.jScrollPane3 = new JScrollPane();
    this.cmplxPrsTbl = new JTable();
    this.jScrollPane5 = new JScrollPane();
    this.qualityTbl = new JTable();
    this.jScrollPane4 = new JScrollPane();
    this.abilityTbl = new JTable();
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();
    this.appendCb = new JCheckBox();
    this.jScrollPane1 = new JScrollPane();
    this.jTextArea1 = new JTextArea();
    this.jButton3 = new JButton();

    setDefaultCloseOperation(3);
    setTitle("Upload Student Marks");
    setIconImages(null);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.jTextField1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        StudentMarksUpload.this.jTextField1uplaodTxtClicked(evt);
      }
    });
    this.jLabel1.setHorizontalAlignment(4);
    this.jLabel1.setText("Uplaod File:");

    this.miTbl.setModel(new TableModels.MITableModel());
    this.miTbl.setRowHeight(20);
    this.jScrollPane2.setViewportView(this.miTbl);

    this.cmplxPrsTbl.setModel(new TableModels.CmplxPersTableModel());
    this.cmplxPrsTbl.setRowHeight(20);
    this.jScrollPane3.setViewportView(this.cmplxPrsTbl);

    this.qualityTbl.setModel(new TableModels.QualityTableModel());
    this.qualityTbl.setRowHeight(20);
    this.jScrollPane5.setViewportView(this.qualityTbl);

    this.abilityTbl.setModel(new TableModels.AbilityTableModel());
    this.abilityTbl.setRowHeight(20);
    this.jScrollPane4.setViewportView(this.abilityTbl);

    this.jButton1.setMnemonic('P');
    this.jButton1.setText("Process");
    this.jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        StudentMarksUpload.this.jButton1ActionPerformed(evt);
      }
    });
    this.jButton2.setMnemonic('C');
    this.jButton2.setText("Cancel");
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        StudentMarksUpload.this.jButton2ActionPerformed(evt);
      }
    });
    this.appendCb.setText("Overwrite previous data?");

    this.jTextArea1.setEditable(false);
    this.jTextArea1.setColumns(20);
    this.jTextArea1.setRows(5);
    this.jTextArea1.setEnabled(false);
    this.jScrollPane1.setViewportView(this.jTextArea1);

    this.jButton3.setText("Download Template");
    this.jButton3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        StudentMarksUpload.this.jButton3ActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.errMsgLbl, -2, 547, -2).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1, -2, 68, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField1, -2, 157, -2)).addComponent(this.jScrollPane2, -2, 207, -2).addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane4, -2, 207, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane3, -2, 207, -2).addComponent(this.jScrollPane5, -2, 207, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, -1, -2)).addComponent(this.appendCb, -2, 194, -2))).addGroup(layout.createSequentialGroup().addComponent(this.jButton1, -2, 82, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2, -2, 82, -2))).addGap(14, 14, 14)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.errMsgLbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jTextField1, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jScrollPane2, -1, 207, 32767).addComponent(this.jScrollPane3, -2, 0, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane4, -2, 167, -2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addGap(213, 213, 213).addComponent(this.jScrollPane5, -2, 107, -2)).addComponent(this.jScrollPane1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.appendCb))).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2).addComponent(this.jButton3)).addContainerGap(25, 32767)));

    pack();
  }

  private void jTextField1uplaodTxtClicked(MouseEvent evt) {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("Excel", new String[] { "xls" }));
    int returnVal = chooser.showOpenDialog(this);
    if (returnVal != 0) {
      return;
    }
    this.selectedFile = chooser.getSelectedFile();
    if ((this.selectedFile == null) || (!this.selectedFile.exists())) {
      this.errMsgLbl.setText("Error:Invalid/No file selected!!!");
      return;
    }
    this.jTextField1.setText(this.selectedFile.getAbsolutePath());
  }

  private void jButton1ActionPerformed(ActionEvent evt) {
    if (this.selectedFile == null) {
      this.errMsgLbl.setText("Error:No file selected!!!");
      return;
    }

    if (!DBValidateUtility.validUserAccess("upload_marks_write", this.errMsgLbl))
      return;
    HashMap miMap = new HashMap();
    HashMap cmplxPersMap = new HashMap();
    HashMap abilityMap = new HashMap();
    HashMap qualityMap = new HashMap();

    for (int i = 0; i < this.miTbl.getRowCount(); i++) {
      miMap.put(this.miTbl.getValueAt(i, 0).toString(), Integer.valueOf(Utility.parseInteger(this.miTbl.getValueAt(i, 1).toString())));
    }

    for (int i = 0; i < this.cmplxPrsTbl.getRowCount(); i++) {
      cmplxPersMap.put(this.cmplxPrsTbl.getValueAt(i, 0).toString(), Integer.valueOf(Utility.parseInteger(this.cmplxPrsTbl.getValueAt(i, 1).toString())));
    }

    for (int i = 0; i < this.abilityTbl.getRowCount(); i++) {
      abilityMap.put(this.abilityTbl.getValueAt(i, 0).toString(), Integer.valueOf(Utility.parseInteger(this.abilityTbl.getValueAt(i, 1).toString())));
    }

    for (int i = 0; i < this.qualityTbl.getRowCount(); i++)
      qualityMap.put(this.qualityTbl.getValueAt(i, 0).toString(), Integer.valueOf(Utility.parseInteger(this.qualityTbl.getValueAt(i, 1).toString())));
    try
    {
      if (!Utility.getjdbcconnection(this.errMsgLbl))
        return;
      if (!this.appendCb.isSelected()) {
        Utility.conn.createStatement().executeUpdate(QueryCollection.STUDENT_RESULT.DELETE_EXISTING_DATA);
      }
      String result = new StudentMarksExcelReader().read(this.selectedFile.getAbsolutePath(), miMap, cmplxPersMap, abilityMap, qualityMap, this.errMsgLbl);
      if (!Utility.isNullEmpty(result)) {
        this.jTextArea1.setVisible(true);
        this.jTextArea1.setText(result);
        this.jTextArea1.setEditable(false);
        this.errMsgLbl.setText("Info:Some data cannot be saved due to errors!!!");
      }
      else {
        this.jTextArea1.setVisible(false);
        this.errMsgLbl.setText("Info:Data saved successfully!!!");
      }
      Utility.finishjdbcconnection();
    } catch (Exception ex) {
      ex.printStackTrace();
      this.errMsgLbl.setText(ex.getMessage());
      return;
    }
  }

  private void jButton2ActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void jButton3ActionPerformed(ActionEvent evt) {
    if (this.selectedFile == null) {
      this.errMsgLbl.setText("Error:No file selected!!!");
      return;
    }
    File actualFile = new File(this.selectedFile.getParent(), "stuResultTemplate.xls");
    try {
      ExcelWriterTest.write(actualFile, "Enroll No");
      this.errMsgLbl.setText("Info:Generated File-" + actualFile.getAbsolutePath());
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  public static void main(String[] args)
  {
    try
    {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
    }
    catch (ClassNotFoundException ex) {
      Logger.getLogger(StudentMarksUpload.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(StudentMarksUpload.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(StudentMarksUpload.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(StudentMarksUpload.class.getName()).log(Level.SEVERE, null, ex);
    }

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new StudentMarksUpload().setVisible(true);
      }
    });
  }
}
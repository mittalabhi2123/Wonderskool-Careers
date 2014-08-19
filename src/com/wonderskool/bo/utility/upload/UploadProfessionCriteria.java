package com.wonderskool.bo.utility.upload;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdesktop.layout.GroupLayout;

import com.wonderskool.bo.excel.CriteriaDataExcelReader;
import com.wonderskool.bo.excel.ExcelWriterTest;
import com.wonderskool.bo.utility.TableModels;
import com.wonderskool.bo.utility.TraitVariablesLst;
import com.wonderskool.bo.utility.Utility;
import com.wonderskool.inlead.bo.validate.DBValidateUtility;

public class UploadProfessionCriteria extends JFrame
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
  private JScrollPane jScrollPane2;
  private JScrollPane jScrollPane3;
  private JScrollPane jScrollPane4;
  private JScrollPane jScrollPane5;
  private JTextField jTextField1;
  private JTable miTbl;
  private JTable qualityTbl;
  private JLabel strmLbl;
  private JTextField strmTxt;

  public UploadProfessionCriteria()
  {
    com.wonderskool.bo.utility.TableModels.secondColName = "Column No.";
    TraitVariablesLst.initModel();
    initComponents();
  }

  private void initComponents()
  {
    this.errMsgLbl = new JLabel();
    this.jLabel1 = new JLabel();
    this.jTextField1 = new JTextField();
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();
    this.jScrollPane2 = new JScrollPane();
    this.miTbl = new JTable();
    this.jScrollPane3 = new JScrollPane();
    this.cmplxPrsTbl = new JTable();
    this.jScrollPane4 = new JScrollPane();
    this.abilityTbl = new JTable();
    this.jScrollPane5 = new JScrollPane();
    this.qualityTbl = new JTable();
    this.appendCb = new JCheckBox();
    this.strmLbl = new JLabel();
    this.strmTxt = new JTextField();
    this.jButton3 = new JButton();

    setDefaultCloseOperation(3);
    setTitle("Upload Profession Criteria");
    setIconImages(null);

    this.errMsgLbl.setForeground(new Color(255, 0, 0));
    this.errMsgLbl.setHorizontalAlignment(2);

    this.jLabel1.setHorizontalAlignment(4);
    this.jLabel1.setText("Uplaod File:");

    this.jTextField1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        UploadProfessionCriteria.this.uplaodTxtClicked(evt);
      }
    });
    this.jButton1.setText("Process");
    this.jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UploadProfessionCriteria.this.jButton1ActionPerformed(evt);
      }
    });
    this.jButton2.setText("Cancel");
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UploadProfessionCriteria.this.jButton2ActionPerformed(evt);
      }
    });
    this.miTbl.setModel(new TableModels.MITableModel());
    this.miTbl.setRowHeight(20);
    this.jScrollPane2.setViewportView(this.miTbl);

    this.cmplxPrsTbl.setModel(new TableModels.CmplxPersTableModel());
    this.cmplxPrsTbl.setRowHeight(20);
    this.jScrollPane3.setViewportView(this.cmplxPrsTbl);

    this.abilityTbl.setModel(new TableModels.AbilityTableModel());
    this.abilityTbl.setRowHeight(20);
    this.jScrollPane4.setViewportView(this.abilityTbl);

    this.qualityTbl.setModel(new TableModels.QualityTableModel());
    this.qualityTbl.setRowHeight(20);
    this.jScrollPane5.setViewportView(this.qualityTbl);

    this.appendCb.setText("Overwrite previous data?");

    this.strmLbl.setText("Stream:");

    this.jButton3.setText("Download Template");
    this.jButton3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        UploadProfessionCriteria.this.jButton3ActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(10, 10, 10).add(layout.createParallelGroup(1).add(this.errMsgLbl, -2, 547, -2).add(layout.createSequentialGroup().add(this.jLabel1, -2, 68, -2).add(4, 4, 4).add(this.jTextField1, -2, 157, -2)).add(layout.createSequentialGroup().add(this.jScrollPane2, -2, 207, -2).add(6, 6, 6).add(this.jScrollPane3, -2, 207, -2)).add(layout.createSequentialGroup().add(this.jScrollPane4, -2, 207, -2).add(6, 6, 6).add(layout.createParallelGroup(1).add(this.jScrollPane5, -2, 207, -2).add(this.appendCb).add(layout.createSequentialGroup().add(this.strmLbl).add(4, 4, 4).add(this.strmTxt, -2, 47, -2)))).add(layout.createSequentialGroup().add(this.jButton1, -2, 82, -2).addPreferredGap(0).add(this.jButton3).addPreferredGap(0).add(this.jButton2, -2, 82, -2)))));

    layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(11, 11, 11).add(this.errMsgLbl).add(18, 18, 18).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(3, 3, 3).add(this.jLabel1)).add(this.jTextField1, -2, -1, -2)).add(6, 6, 6).add(layout.createParallelGroup(1).add(this.jScrollPane2, -2, 207, -2).add(this.jScrollPane3, -2, 207, -2)).add(6, 6, 6).add(layout.createParallelGroup(1).add(this.jScrollPane4, -2, 167, -2).add(layout.createSequentialGroup().add(this.jScrollPane5, -2, 107, -2).add(11, 11, 11).add(this.appendCb).add(6, 6, 6).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(3, 3, 3).add(this.strmLbl)).add(this.strmTxt, -2, -1, -2)))).add(18, 18, 18).add(layout.createParallelGroup(3).add(this.jButton1).add(this.jButton3).add(this.jButton2))));

    pack();
  }

  private void jButton2ActionPerformed(ActionEvent evt) {
    Utility.callCancel(this);
  }

  private void jButton1ActionPerformed(ActionEvent evt) {
    if (this.selectedFile == null) {
      this.errMsgLbl.setText("Error:No file selected!!!");
      return;
    }
    if ((Utility.isNullEmpty(this.strmTxt.getText())) || (!Utility.isNumeric(this.strmTxt.getText()))) {
      this.errMsgLbl.setText("Error:Invalid Stream col specified!!!");
      return;
    }
    if (!DBValidateUtility.validUserAccess("upload_profession_write", this.errMsgLbl))
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

    for (int i = 0; i < this.qualityTbl.getRowCount(); i++) {
      qualityMap.put(this.qualityTbl.getValueAt(i, 0).toString(), Integer.valueOf(Utility.parseInteger(this.qualityTbl.getValueAt(i, 1).toString())));
    }
    try
    {
      if (!Utility.getjdbcconnection(this.errMsgLbl))
        return;
      if (!this.appendCb.isSelected()) {
        Utility.conn.createStatement().executeUpdate("DELETE FROM profession_criteria");
        Utility.conn.createStatement().executeUpdate("DELETE FROM profession_stream_tbl");
      }
      new CriteriaDataExcelReader().read(this.selectedFile.getAbsolutePath(), miMap, cmplxPersMap, abilityMap, qualityMap, Integer.parseInt(this.strmTxt.getText()));
      Utility.finishjdbcconnection();
      this.errMsgLbl.setText("Info:Data ported successfully!!!");
    } catch (Exception ex) {
      ex.printStackTrace();
      this.errMsgLbl.setText(ex.getMessage());
      return;
    }
  }

  private void uplaodTxtClicked(MouseEvent evt) {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("Excel", new String[] { "xls", "xlsx" }));
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

  private void jButton3ActionPerformed(ActionEvent evt) {
    if (this.selectedFile == null) {
      this.errMsgLbl.setText("Error:No file selected!!!");
      return;
    }
    File actualFile = new File(this.selectedFile.getParent(), "professionTemplate.xls");
    try {
      ExcelWriterTest.write(actualFile, "Profession");
      this.errMsgLbl.setText("Info:Generated File-" + actualFile.getAbsolutePath());
    }
    catch (Exception e) {
      this.errMsgLbl.setText("Error:" + e.getMessage());
    }
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new UploadProfessionCriteria().setVisible(true);
      }
    });
  }
}
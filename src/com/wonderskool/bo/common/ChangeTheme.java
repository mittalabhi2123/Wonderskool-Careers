package com.wonderskool.bo.common;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import com.wonderskool.bo.utility.Utility;

public class ChangeTheme extends JFrame
{
  private SortedMap<String, LookAndFeel> themeMap = new TreeMap();
  private ButtonGroup buttonGroup1;
  private JButton jButton1;
  private JButton jButton2;
  private JComboBox jComboBox1;
  private JLabel jLabel1;
  private JLabel jLabel2;

  public ChangeTheme()
  {
    initComponents();
    this.themeMap.put("Acryl", new AcrylLookAndFeel());
    this.themeMap.put("Aero", new AeroLookAndFeel());
    this.themeMap.put("Aluminium", new AluminiumLookAndFeel());
    this.themeMap.put("Bernstein", new BernsteinLookAndFeel());
    this.themeMap.put("Graphite", new GraphiteLookAndFeel());
    this.themeMap.put("HiFi", new HiFiLookAndFeel());
    this.themeMap.put("McWin", new McWinLookAndFeel());
    this.themeMap.put("Mint", new MintLookAndFeel());
    this.themeMap.put("Noire", new NoireLookAndFeel());
    this.themeMap.put("Smart", new SmartLookAndFeel());
    this.themeMap.put("Texture", new TextureLookAndFeel());
    for (String str : this.themeMap.keySet())
      this.jComboBox1.addItem(str);
  }

  private void initComponents()
  {
    this.buttonGroup1 = new ButtonGroup();
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jComboBox1 = new JComboBox();
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();

    setDefaultCloseOperation(3);
    setTitle("Change Theme");
    setIconImages(null);

    this.jLabel1.setFont(new Font("Tahoma", 3, 18));
    this.jLabel1.setText("Select Theme");

    this.jLabel2.setHorizontalAlignment(11);
    this.jLabel2.setText("Theme:");

    this.jButton1.setMnemonic('A');
    this.jButton1.setText("Apply");
    this.jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ChangeTheme.this.jButton1ActionPerformed(evt);
      }
    });
    this.jButton2.setMnemonic('C');
    this.jButton2.setText("Close");
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ChangeTheme.this.jButton2ActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, -2, 243, -2).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2, -2, 71, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jComboBox1, -2, 177, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2))).addContainerGap(42, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -2, 31, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jComboBox1, -2, -1, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(31, 32767)));

    pack();
  }

  private void jButton2ActionPerformed(ActionEvent evt) {
    dispose();
    Utility.startFrame(new MenuFrame());
  }

  private void jButton1ActionPerformed(ActionEvent evt) {
    LookAndFeel lookNFeel = (LookAndFeel)this.themeMap.get(this.jComboBox1.getSelectedItem().toString());
    try {
      UIManager.setLookAndFeel(lookNFeel);
      dispose();
      Utility.startFrame(new ChangeTheme());
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
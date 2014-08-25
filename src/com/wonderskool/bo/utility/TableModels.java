package com.wonderskool.bo.utility;

import javax.swing.table.AbstractTableModel;

public class TableModels {
  public static String secondColName = "";

  public static class QualityTableModel extends AbstractTableModel {
    public static Object[][] data = (Object[][])null;
    public static String[] colName = { Const.Code_Type.QUALITY, TableModels.secondColName };

    Class[] types = { String.class, Integer.class };

    boolean[] canEdit = { false, true };

    public Class getColumnClass(int columnIndex)
    {
      return this.types[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return this.canEdit[columnIndex];
    }

    public int getColumnCount() {
      return this.colName.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return this.colName[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
      data[row][col] = value;
      fireTableCellUpdated(row, col);
    }
  }

  public static class AbilityTableModel extends AbstractTableModel {
    public static Object[][] data = (Object[][])null;
    public static String[] colName = { Const.Code_Type.ABILITY, TableModels.secondColName };

    Class[] types = { String.class, Integer.class };

    boolean[] canEdit = { false, true };

    public Class getColumnClass(int columnIndex)
    {
      return this.types[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return this.canEdit[columnIndex];
    }

    public int getColumnCount() {
      return this.colName.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return this.colName[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
      data[row][col] = value;
      fireTableCellUpdated(row, col);
    }
  }

  public static class CmplxPersTableModel extends AbstractTableModel {
    public static Object[][] data = (Object[][])null;
    public static String[] colName = { Const.Code_Type.CMPLX_PERS, TableModels.secondColName };

    Class[] types = { String.class, Integer.class };

    boolean[] canEdit = { false, true };

    public Class getColumnClass(int columnIndex)
    {
      return this.types[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return this.canEdit[columnIndex];
    }

    public int getColumnCount() {
      return this.colName.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return this.colName[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
      data[row][col] = value;
      fireTableCellUpdated(row, col);
    }
  }

  public static class MITableModel extends AbstractTableModel {
    public static Object[][] data = (Object[][])null;
    public static String[] colName = { Const.Code_Type.MI, TableModels.secondColName };

    Class[] types = { String.class, Integer.class };

    boolean[] canEdit = { false, true };

    public Class getColumnClass(int columnIndex)
    {
      return this.types[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return this.canEdit[columnIndex];
    }

    public int getColumnCount() {
      return this.colName.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return this.colName[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
      data[row][col] = value;
      fireTableCellUpdated(row, col);
    }
  }

  public static class MotivatorTableModel extends AbstractTableModel {
    public static Object[][] data = (Object[][])null;
    private String[] colName = { Const.Code_Type.MOTIVATORS, TableModels.secondColName };

    Class[] types = { String.class, Integer.class };

    boolean[] canEdit = { false, true };

    public Class getColumnClass(int columnIndex)
    {
      return this.types[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return this.canEdit[columnIndex];
    }

    public int getColumnCount() {
      return this.colName.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return this.colName[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
      data[row][col] = value;
      fireTableCellUpdated(row, col);
    }
  }
  
  public static class WorkingStyleTableModel extends AbstractTableModel {
	    public static Object[][] data = (Object[][])null;
	    private String[] colName = { Const.Code_Type.WORKING_STYLE, TableModels.secondColName };

	    Class[] types = { String.class, Integer.class };

	    boolean[] canEdit = { false, true };

	    public Class getColumnClass(int columnIndex)
	    {
	      return this.types[columnIndex];
	    }

	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	      return this.canEdit[columnIndex];
	    }

	    public int getColumnCount() {
	      return this.colName.length;
	    }

	    public int getRowCount() {
	      return data.length;
	    }

	    public String getColumnName(int col) {
	      return this.colName[col];
	    }

	    public Object getValueAt(int row, int col) {
	      return data[row][col];
	    }

	    public void setValueAt(Object value, int row, int col) {
	      data[row][col] = value;
	      fireTableCellUpdated(row, col);
	    }
	  }
}
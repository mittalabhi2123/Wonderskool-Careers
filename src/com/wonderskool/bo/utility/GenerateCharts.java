package com.wonderskool.bo.utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.itextpdf.text.Image;

public class GenerateCharts
{
  static String stuId = "";
  static JLabel errMsgLbl = null;
  static List<String> dataLst = null;
  static String chartTitle = "";
  static String xAxisTitle = "";

  public static Image getCharts(String studentId, JLabel lbl, List<String> dataLst1, String title, String xTitle) {
    stuId = studentId;
    errMsgLbl = lbl;
    dataLst = dataLst1;
    chartTitle = title;
    xAxisTitle = xTitle;
    try {
      CategoryDataset categorydataset = createDataset();
      JFreeChart jfreechart = createChart(categorydataset);
      Image image = Image.getInstance(jfreechart.createBufferedImage(500, 350), Color.BLACK);
      image.scaleAbsolute(350.0F, 200.0F);
      return image;
    }
    catch (Exception e) {
      e.printStackTrace();
      errMsgLbl.setText("Error:" + e.getMessage());
    }return null;
  }

  private static CategoryDataset createDataset() throws Exception
  {
    if (!Utility.getjdbcconnection(errMsgLbl))
      return null;
    SortedMap<String, Number> dataMap = new TreeMap<String, Number>();
    String valueStr = "";
    double valueInt = 0.0D;
    ResultSet dataSet = Utility.conn.createStatement().executeQuery(QueryCollection.STUDENT_RESULT.GET_EXISTING_DATA.replace("?", stuId.toLowerCase()));
    while (dataSet.next()) {
      valueStr = dataSet.getString(4);
      valueInt = dataSet.getDouble(5);
      if (dataLst.contains(valueStr))
        dataMap.put(valueStr, Double.valueOf(valueInt));
    }
    DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
    for (String key : dataMap.keySet()) {
      defaultcategorydataset.addValue((Number)dataMap.get(key), "Series 1", key);
    }
    return defaultcategorydataset;
  }

  private static JFreeChart createChart(CategoryDataset categorydataset) {
    JFreeChart jfreechart = ChartFactory.createBarChart3D(chartTitle, xAxisTitle, "Score", categorydataset, PlotOrientation.VERTICAL, false, true, false);
    jfreechart.setBackgroundPaint(new Color(0, 0, 0));
    TextTitle tt = new TextTitle();
    tt.setPaint(Color.WHITE);
    tt.setText(chartTitle);
    tt.setFont(new Font("SansSerif", 1, 16));
    tt.setExpandToFitSpace(true);
    jfreechart.setTitle(tt);
    jfreechart.getCategoryPlot().setBackgroundPaint(new Color(77, 77, 77));
    CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
    CustomBarRenderer3D custombarrenderer3d = new CustomBarRenderer3D();
    custombarrenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    custombarrenderer3d.setBaseItemLabelsVisible(true);
    custombarrenderer3d.setItemLabelAnchorOffset(10.0D);
    custombarrenderer3d.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
    categoryplot.setRenderer(custombarrenderer3d);
    custombarrenderer3d.setBaseItemLabelsVisible(true);
    custombarrenderer3d.setMaximumBarWidth(0.05D);
    NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
    numberaxis.setNumberFormatOverride(NumberFormat.getNumberInstance());
    numberaxis.setLabelPaint(new Color(255, 255, 255));
    numberaxis.setUpperMargin(1.0D);
    CategoryAxis domainAxis = categoryplot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
    domainAxis.setLabelPaint(new Color(255, 255, 255));
    return jfreechart;
  }

  static class CustomBarRenderer3D extends BarRenderer3D
  {
    public Paint getItemPaint(int i, int j)
    {
      CategoryDataset categorydataset = getPlot().getDataset();
      double d = categorydataset.getValue(i, j).doubleValue();
      double cutOff = 0.0D;
      try {
        String query = "SELECT param_value FROM system_variables WHERE  LOWER(param_name) = '?'".replace("?", categorydataset.getColumnKeys().get(j).toString().toLowerCase());
        ResultSet dataSet = Utility.conn.createStatement().executeQuery(query);
        while (dataSet.next())
          cutOff = dataSet.getDouble(1);
      }
      catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
      if (d >= cutOff)
      {
        return Color.green;
      }

      return Color.red;
    }
  }
}
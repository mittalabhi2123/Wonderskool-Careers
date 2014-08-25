package com.wonderskool.bo.excel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.wonderskool.bo.utility.TraitVariablesLst;

public class ExcelWriterTest {
	private static WritableCellFormat timesBoldUnderline;
	private static WritableCellFormat times;

	public static void write(File file, String firstColHeading) throws Exception {
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("en", "EN"));

		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("Template", 0);
		WritableSheet excelSheet = workbook.getSheet(0);
		createLabel(excelSheet, TraitVariablesLst.dataMap, firstColHeading);

		workbook.write();
		workbook.close();
	}

	private static void createLabel(WritableSheet sheet, HashMap<String, List<String>> traitProfData,
			String firstColHeading) throws WriteException {
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);

		times = new WritableCellFormat(times10pt);

		times.setWrap(true);

		WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 12, WritableFont.NO_BOLD, false,
				UnderlineStyle.SINGLE);

		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);

		timesBoldUnderline.setWrap(false);
		timesBoldUnderline.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

		sheet.addCell(new Label(0, 0, firstColHeading, timesBoldUnderline));
		int count1 = 0;
		int count2 = 1;
		for (String key : traitProfData.keySet()) {
			List<String> varLst = (List<String>) traitProfData.get(key);
			sheet.mergeCells(count1 + 1, 0, count1 + varLst.size(), 0);
			sheet.addCell(new Label(count1 + 1, 0, key, timesBoldUnderline));
			count1 += varLst.size();
			for (String var : varLst)
				sheet.addCell(new Label(count2++, 1, var, timesBoldUnderline));
		}
	}
}
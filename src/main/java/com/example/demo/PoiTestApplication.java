package com.example.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.example.demo.util.CSVReader;

public class PoiTestApplication {

	public static void main(String[] args) {

		// *optional* configure output directory
		String outPath = "D:\\out.xlsx";

		// define XSSF Workbook
		Workbook book = null;

		try (FileOutputStream out = new FileOutputStream(outPath)) {
			book = new SXSSFWorkbook();

			// define XSSF sheet, row, cell
			Sheet sheet;
			sheet = book.createSheet();
			book.setSheetName(0, "testdayo");

			Row row;

			Cell cell = null;
			int colNumber = 0;

			// define outer frame
			int maxRowNumber = 30;
			int maxColNumber = 30;

			// adjust cell width
			for (int i = 0; i < maxRowNumber; i++) {
				sheet.setColumnWidth(i, 768);
			}

			// read pixel information from 393.csv(fixed)
			CSVReader reader = new CSVReader();
			Map<List<Integer>, String> pixelMap = reader.testReader();

			// y loop
			for (int i = 0; i < maxRowNumber; i++) {

				colNumber = 0;
				row = sheet.createRow(i);

				// x loop
				// paint the color for each pixel
				for (int j = 0; j < maxColNumber; j++) {

					// generate keys for pixelMap
					List<Integer> keysList = new ArrayList<Integer>();
					keysList.add(i);
					keysList.add(j);

					// get color from pixelMap
					CellStyle testStyle = null;
					String colorName = pixelMap.get(keysList);
					
					// configure cell information
					if(colorName != null) {
						// If the key can be obtained, set the color based on the pixelMap information.
						testStyle = editCellStyle(book, colorName);
					} else {
						// If the key cannot be obtained, paint the cell in black.
						CellStyle tmpStyle = book.createCellStyle();
						tmpStyle.setFillForegroundColor(IndexedColors.BLACK1.getIndex());
						tmpStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						testStyle = tmpStyle;
					}

					cell = row.createCell(colNumber++);
					cell.setCellStyle(testStyle);

				}

			}

			// output process
			book.write(out);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static CellStyle editCellStyle(Workbook book, String colorName) {
		// define cell style
		CellStyle testStyle = book.createCellStyle();

		// judge color
		switch (colorName) {
		case "BLACK":
			testStyle.setFillForegroundColor(IndexedColors.BLACK1.getIndex());
			break;
		case "WHITE":
			testStyle.setFillForegroundColor(IndexedColors.WHITE1.getIndex());
			break;
		case "LIGHT_BLUE":
			testStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			break;
		case "PALE_BLUE":
			testStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
			break;
		case "GREY_25_PERCENT":
			testStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			break;
		case "LEMON_CHIFFON":
			testStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
			break;
		case "YELLOW":
			testStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			break;
		case "GREY_50_PERCENT":
			testStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
			break;
		}

		testStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return testStyle;
	}

}

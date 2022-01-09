package com.example.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.RGBColor;
import com.example.demo.util.CSVReader;

public class PoiTestApplication {

	public static void main(String[] args) {

		// *optional* configure output directory
		String outPath = "D:\\out.xlsx";

		// define HSSF Workbook
		Workbook xssfBook = null;
		HSSFWorkbook hssfBook = null;
		CellStyle oneStyle = null;

		try (FileOutputStream out = new FileOutputStream(outPath)) {
			// define XSSF sheet, row, cell
			xssfBook = new XSSFWorkbook();
			Sheet xssfSheet = xssfBook.createSheet();
			Row xssfRow = null;
			Cell xssfCell = null;

			xssfBook.setSheetName(0, "work");
			xssfSheet.setZoom(10);

			// HSSFColorPaletteを使うために仮のHSSFオブジェクトを作る
			hssfBook = new HSSFWorkbook();

			oneStyle = xssfBook.createCellStyle();

			// カラーパレットを読み込む
			Map<Short, CellStyle> paletteMap = new HashMap<Short, CellStyle>();
			for (short i = 0; i < 65; i++) {
				CellStyle tmpStyle = xssfBook.createCellStyle();
				tmpStyle.setFillForegroundColor(i);
				tmpStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				paletteMap.put(i, tmpStyle);
			}

			int colNumber = 0;

			// define outer frame
			int maxRowNumber = 331;
			int maxColNumber = 440;

			// adjust cell width
			for (int i = 0; i < maxColNumber; i++) {
				xssfSheet.setColumnWidth(i, 768);
			}

			// read pixel information from 393.csv(fixed)
			CSVReader reader = new CSVReader();
			Map<List<Integer>, RGBColor> pixelMap = reader.testReader();

			// y loop
			for (int i = 0; i < maxRowNumber; i++) {

				colNumber = 0;
				xssfRow = xssfSheet.createRow(i);

				// x loop
				// paint the color for each pixel
				for (int j = 0; j < maxColNumber; j++) {

					// generate keys for pixelMap
					List<Integer> keysList = new ArrayList<Integer>();
					keysList.add(i);
					keysList.add(j);

					// get color from pixelMap
					HSSFPalette palette = hssfBook.getCustomPalette();

					RGBColor rgbColor;
					rgbColor = pixelMap.get(keysList);

					int redNumber = rgbColor.getRedNumber();
					int greenNumber = rgbColor.getGreenNumber();
					int blueNumber = rgbColor.getBlueNumber();

					HSSFColor pixelColor = palette.findSimilarColor(redNumber, greenNumber, blueNumber);
					short palIndex = pixelColor.getIndex();

					oneStyle = paletteMap.get(palIndex);

					xssfCell = xssfRow.createCell(colNumber++);
					xssfCell.setCellStyle(oneStyle);

				}

			}

			// output process
			xssfBook.write(out);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

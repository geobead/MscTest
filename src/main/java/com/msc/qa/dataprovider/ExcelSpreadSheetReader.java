package com.msc.qa.dataprovider;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelSpreadSheetReader {
	final static String spreadSheetFilePath = System.getProperty("user.dir") + "/src/main/resources/testdata/car_data.xlsx";
	private String sheetName;

	public static Object[][] readSpreadSheet(String sheetName) {
		// Using a File object allows for lower memory consumption, while an InputStream
		// requires more memory as it has to buffer the whole file.
		// Create Workbook instance for xlsx/xls file input stream
		Workbook workbook = null;
		Object[][] data = null;
		try {
			workbook = WorkbookFactory.create(new File(spreadSheetFilePath));
			// Get the number of sheets in the xlsx file
			Sheet sheet = workbook.getSheet(sheetName);

			int lastRowNum = sheet.getLastRowNum();
			int lastCellNum = sheet.getRow(0).getLastCellNum();
			data = new Object[lastRowNum][lastCellNum];
			for (int i = 0; i < lastRowNum; i++) {
				for (int j = 0; j < lastCellNum; j++) {
					// start at 2nd row to avoid column names
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
		/*
		 * Iterator<Row> rowIterator = sheet.iterator(); while(rowIterator.hasNext()) {
		 * Row row = rowIterator.next(); Iterator<Cell> cellIterator =
		 * row.cellIterator(); while(cellIterator.hasNext()) { Cell cell =
		 * cellIterator.next(); } }
		 */
	}
}

package com.dru.care.app.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportEmployeeDetailsToExcel {

	public static String[] HEADERS = { "S.No", "PatientId", "FirstName", "LastName", "Address", "Age", "PhoneNumber",
			"Gender"

	};

	public static String SHEET_NAME = "Patient";

	public static ByteArrayInputStream exportEmployeeDetailsToExcel(List<Map<String, Object>> list) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// Create Workbook
		Workbook workbook = new XSSFWorkbook();
		// Create Sheet
		Sheet sheet = workbook.createSheet(SHEET_NAME);

		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		cellStyle.setFont(font);

		// Create Header Row
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < HEADERS.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(HEADERS[i]);
			cell.setCellStyle(cellStyle);// set style for header cells

		}

		int rowCount = 1;
		// For Increment S.no
		int count = 1;
		// Creating data rows for each patient details
		for (Map<String, Object> map : list) {
			Row dataRow = sheet.createRow(rowCount++);
			Integer patientId = (Integer) map.get("patient_id");
			String firstNm = (String) map.get("first_nm");
			String lastNm = (String) map.get("last_nm");
			String address = (String) map.get("address");
			Integer age = (Integer) map.get("age");
			Long phoneNo = (Long) map.get("phone_no");
			String gender = (String) map.get("gender");
			dataRow.createCell(0).setCellValue(count++);
			dataRow.createCell(1).setCellValue(patientId);
			dataRow.createCell(2).setCellValue(firstNm);
			dataRow.createCell(3).setCellValue(lastNm);
			dataRow.createCell(4).setCellValue(address);
			dataRow.createCell(5).setCellValue(age);
			dataRow.createCell(6).setCellValue(phoneNo);
			dataRow.createCell(7).setCellValue(gender);

		}

		workbook.write(out);
		workbook.close();
		out.close();
		return new ByteArrayInputStream(out.toByteArray());
	}

}

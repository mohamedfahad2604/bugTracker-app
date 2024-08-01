package com.bizz.customersupport.job;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bizz.customersupport.entities.Issue;

public class BauTaskListExcelGenerator {
	
	
	
	public static void issueToExcel(List<Issue> issuesToday, List<Issue> issuesMonth,List<Issue> issuesOpen, final String locationWithFileName)  {
		

		Sheet sheet,sheet2,sheet3;

		String[] COLUMNs = { "Id", "Issue Title","Requestor", "RequestDate", "ResolvedDate","Description", "Resolution",  "assingedTo", "ResolvedBy",
				 "Status","Application", "Environment", "client" };
		try (Workbook workbook = new XSSFWorkbook(); FileOutputStream out = new FileOutputStream(locationWithFileName);) {
			CreationHelper createHelper = workbook.getCreationHelper();

			 sheet = workbook.createSheet("Today Issues");
			 sheet2 = workbook.createSheet("Monthly Issues");
			 sheet3 = workbook.createSheet("Open Issues");

			Font headerFont1 = workbook.createFont();
			headerFont1.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle1 = workbook.createCellStyle();
			headerCellStyle1.setFont(headerFont1);
			headerCellStyle1.setFillForegroundColor(IndexedColors.GREEN.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			Row headerRow = sheet.createRow(0);
			
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle1);
			}

			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
			 int rowIdx = 1; 
		/*	int rowIdx = sheet.getLastRowNum();*/

			for (Issue issue : issuesToday) {
				Row row = sheet.createRow(rowIdx++);
				sheet.setColumnWidth(1, 5000);
				row.createCell(0).setCellValue(issue.getId());
				
				row.createCell(1).setCellValue(issue.getIssueTittle());
				
				row.createCell(2).setCellValue(issue.getRequestor());
				
				Cell dateCell = row.createCell(3);
				sheet.setColumnWidth(3, 5000);
				dateCell.setCellValue(issue.getRequestDate());
				dateCell.setCellStyle(headerCellStyle);
				
				Cell dateCell1 = row.createCell(4);
				sheet.setColumnWidth(4, 5000);
				dateCell1.setCellValue(issue.getResolvedDate());
				dateCell1.setCellStyle(headerCellStyle);
				
				row.createCell(5).setCellValue(issue.getDescription());
				sheet.setColumnWidth(5, 5000);
				
				row.createCell(6).setCellValue(issue.getResolution());
				sheet.setColumnWidth(6, 5000);
				
				row.createCell(7).setCellValue(issue.getAssignedTo());
				sheet.setColumnWidth(7, 5000);
				
				row.createCell(8).setCellValue(issue.getResolvedBy());
				sheet.setColumnWidth(8, 5000);
				
				row.createCell(9).setCellValue(issue.getStatus());
				sheet.setColumnWidth(9, 5000);
				
				
				row.createCell(10).setCellValue(issue.getApplication());
				sheet.setColumnWidth(10, 5000);
				row.createCell(11).setCellValue(issue.getEnvironment());
				sheet.setColumnWidth(11, 5000);
				row.createCell(12).setCellValue(issue.getClient());
				sheet.setColumnWidth(12, 5000);
			}
			
			String[] COLUMNs1 = { "Id", "Issue Title","Requestor", "RequestDate", "ResolvedDate","Description", "Resolution",  "assingedTo", "ResolvedBy",
					 "Status","Application", "Environment", "client" };
			
			Font headerFont2 = workbook.createFont();
			headerFont2.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle2 = workbook.createCellStyle();
			headerCellStyle2.setFont(headerFont2);
			headerCellStyle2.setFillForegroundColor(IndexedColors.GREEN.getIndex());

			CellStyle headerCellStyle3 = workbook.createCellStyle();
			headerCellStyle3.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			Row headerRow1 = sheet2.createRow(0);
			for (int col2 = 0; col2 < COLUMNs1.length; col2++) {
				Cell cell = headerRow1.createCell(col2);
				cell.setCellValue(COLUMNs1[col2]);
				cell.setCellStyle(headerCellStyle1);
			}

			CellStyle dateCellStyle1 = workbook.createCellStyle();
			dateCellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("#"));
			 int rowIdx1 = 1; 

			for (Issue issue : issuesMonth) {
				Row row = sheet2.createRow(rowIdx1++);
				sheet2.setColumnWidth(1, 5000);
				row.createCell(0).setCellValue(issue.getId());
				
				row.createCell(1).setCellValue(issue.getIssueTittle());
				
				row.createCell(2).setCellValue(issue.getRequestor());
				
				Cell dateCell = row.createCell(3);
				sheet2.setColumnWidth(3, 5000);
				dateCell.setCellValue(issue.getRequestDate());
				dateCell.setCellStyle(headerCellStyle);
				
				Cell dateCell1 = row.createCell(4);
				sheet2.setColumnWidth(4, 5000);
				dateCell1.setCellValue(issue.getResolvedDate());
				dateCell1.setCellStyle(headerCellStyle);
				
				row.createCell(5).setCellValue(issue.getDescription());
				sheet2.setColumnWidth(5, 5000);
				
				row.createCell(6).setCellValue(issue.getResolution());
				sheet2.setColumnWidth(6, 5000);
				
				row.createCell(7).setCellValue(issue.getAssignedTo());
				sheet2.setColumnWidth(7, 5000);
				
				row.createCell(8).setCellValue(issue.getResolvedBy());
				sheet2.setColumnWidth(8, 5000);
				
				row.createCell(9).setCellValue(issue.getStatus());
				sheet2.setColumnWidth(9, 5000);
				
				
				row.createCell(10).setCellValue(issue.getApplication());
				sheet2.setColumnWidth(10, 5000);
				row.createCell(11).setCellValue(issue.getEnvironment());
				sheet2.setColumnWidth(11, 5000);
				row.createCell(12).setCellValue(issue.getClient());
				sheet2.setColumnWidth(12, 5000);
			}
			
			String[] COLUMNs2 = { "Id", "Issue Title","Requestor", "RequestDate", "ResolvedDate","Description", "Resolution",  "assingedTo", "ResolvedBy",
					 "Status","Application", "Environment", "client" };
			
			Font headerFont3 = workbook.createFont();
			headerFont3.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle4 = workbook.createCellStyle();
			headerCellStyle4.setFont(headerFont3);
			headerCellStyle4.setFillForegroundColor(IndexedColors.GREEN.getIndex());

			CellStyle headerCellStyle5 = workbook.createCellStyle();
			headerCellStyle5.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			Row headerRow2 = sheet3.createRow(0);
			for (int col2 = 0; col2 < COLUMNs2.length; col2++) {
				Cell cell = headerRow2.createCell(col2);
				cell.setCellValue(COLUMNs2[col2]);
				cell.setCellStyle(headerCellStyle1);
			}

			CellStyle dateCellStyle2 = workbook.createCellStyle();
			dateCellStyle2.setDataFormat(createHelper.createDataFormat().getFormat("#"));
			 int rowIdx2 = 1; 

			for (Issue issue : issuesOpen) {
				Row row = sheet3.createRow(rowIdx2++);
				sheet3.setColumnWidth(1, 5000);
				row.createCell(0).setCellValue(issue.getId());
				
				row.createCell(1).setCellValue(issue.getIssueTittle());
				
				row.createCell(2).setCellValue(issue.getRequestor());
				
				Cell dateCell = row.createCell(3);
				sheet3.setColumnWidth(3, 5000);
				dateCell.setCellValue(issue.getRequestDate());
				dateCell.setCellStyle(headerCellStyle);
				
				Cell dateCell1 = row.createCell(4);
				sheet3.setColumnWidth(4, 5000);
				dateCell1.setCellValue(issue.getResolvedDate());
				dateCell1.setCellStyle(headerCellStyle);
				
				row.createCell(5).setCellValue(issue.getDescription());
				sheet3.setColumnWidth(5, 5000);
				
				row.createCell(6).setCellValue(issue.getResolution());
				sheet3.setColumnWidth(6, 5000);
				
				row.createCell(7).setCellValue(issue.getAssignedTo());
				sheet3.setColumnWidth(7, 5000);
				
				row.createCell(8).setCellValue(issue.getResolvedBy());
				sheet3.setColumnWidth(8, 5000);
				
				row.createCell(9).setCellValue(issue.getStatus());
				sheet3.setColumnWidth(9, 5000);
				
				
				row.createCell(10).setCellValue(issue.getApplication());
				sheet3.setColumnWidth(10, 5000);
				row.createCell(11).setCellValue(issue.getEnvironment());
				sheet3.setColumnWidth(11, 5000);
				row.createCell(12).setCellValue(issue.getClient());
				sheet3.setColumnWidth(12, 5000);
			}
			
			workbook.write(out);
		
		
		

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
}

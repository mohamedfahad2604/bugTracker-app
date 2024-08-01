package com.bizz.customersupport.job;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bizz.customersupport.entities.DailyTask;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.util.Converter;

public class TaskListExcelGenerator {

	private final static Logger logger = LoggerFactory.getLogger(TaskListExcelGenerator.class);

	/**
	 * This method will generate the Excel file and store it in the given location. This method will return the file path.
	 * 
	 * @param findAll
	 * @param listOfAllUsers
	 * @param location
	 * @return
	 * @throws IOException
	 */
	public static void taskToExcel(List<DailyTask> findAll, List<User> listOfAllUsers, final String locationWithFileName) throws IOException {

		String[] COLUMNs = { "Employee", "Date", "Task Name", "Task / Issue", "Hours" };

		List<String> workingHour = new ArrayList<>();
		String employeeName = null;
		// int size=0;

		try (Workbook workbook = new XSSFWorkbook();

				FileOutputStream out = new FileOutputStream(locationWithFileName);) {

			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Task");

			for (int i = 0; i <= 3; i++) {
				sheet.setColumnWidth(i, 5000);
			}

			sheet.setColumnWidth(2, 13000);

			Font headerFont1 = workbook.createFont();
			headerFont1.setBold(true);
			headerFont1.setFontHeightInPoints((short) 11);
			headerFont1.setFontName("Calibri");

			CellStyle headerCellStyle1 = workbook.createCellStyle();
			headerCellStyle1.setFont(headerFont1);
			headerCellStyle1.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
			headerCellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerCellStyle1.setBorderBottom(BorderStyle.THIN);
			headerCellStyle1.setBorderLeft(BorderStyle.THIN);
			headerCellStyle1.setBorderRight(BorderStyle.THIN);
			headerCellStyle1.setBorderTop(BorderStyle.THIN);

			CellStyle dateCellStyle1 = workbook.createCellStyle();
			dateCellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			dateCellStyle1.setBorderBottom(BorderStyle.THIN);
			dateCellStyle1.setBorderLeft(BorderStyle.THIN);
			dateCellStyle1.setBorderRight(BorderStyle.THIN);
			dateCellStyle1.setBorderTop(BorderStyle.THIN);
			dateCellStyle1.setAlignment(HorizontalAlignment.CENTER);
			dateCellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
			dateCellStyle1.setWrapText(true);

			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle1);
			}

			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
			dateCellStyle.setBorderBottom(BorderStyle.THIN);
			dateCellStyle.setBorderLeft(BorderStyle.THIN);
			dateCellStyle.setBorderRight(BorderStyle.THIN);
			dateCellStyle.setBorderTop(BorderStyle.THIN);
			dateCellStyle.setAlignment(HorizontalAlignment.CENTER);
			dateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			dateCellStyle.setWrapText(true);

			CellStyle dateCellStyleWorkingTime = workbook.createCellStyle();
			dateCellStyleWorkingTime.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			// dateCellStyle1.setDataFormat(createHelper.createDataFormat().);
			dateCellStyleWorkingTime.setBorderBottom(BorderStyle.THIN);
			dateCellStyleWorkingTime.setBorderLeft(BorderStyle.THIN);
			dateCellStyleWorkingTime.setBorderRight(BorderStyle.THIN);
			dateCellStyleWorkingTime.setBorderTop(BorderStyle.THIN);
			dateCellStyleWorkingTime.setAlignment(HorizontalAlignment.CENTER);
			dateCellStyleWorkingTime.setVerticalAlignment(VerticalAlignment.CENTER);
			dateCellStyleWorkingTime.setWrapText(true);
			dateCellStyleWorkingTime.setFillForegroundColor(IndexedColors.RED1.getIndex());
			dateCellStyleWorkingTime.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			int rowIdx = 1;
			int rowStart = 0;
			int rowEnd = 1;

			List<DailyTask> resolvedByUsers = new ArrayList<DailyTask>();

			for (int i = 0; i < listOfAllUsers.size(); i++) {

				User s = listOfAllUsers.get(i);
				String AllUsers = s.getUserName();

				{
					for (DailyTask user : findAll) {
						if (user.getUserName().getUserName().equals(AllUsers)) {
							rowStart = rowIdx;
							resolvedByUsers.add(user);
						}
					}

					if (resolvedByUsers.isEmpty()) {

						Row rows = sheet.createRow(rowIdx++);
						rowStart = rowIdx;
						rows.setHeightInPoints(30);

						sheet.addMergedRegion(new CellRangeAddress(rowIdx - 1, rowIdx - 1, 2, 3));
						Cell dateCell0 = rows.createCell(0);
						dateCell0.setCellValue(AllUsers);
						dateCell0.setCellStyle(dateCellStyle);

						Cell dateCell1 = rows.createCell(1);
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR, 0);
						cal.set(Calendar.AM_PM, Calendar.AM);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND, 0);
						Date today = Converter.convertDateFromCalendar(cal);

						dateCell1.setCellValue(today);
						dateCell1.setCellStyle(dateCellStyle1);

						Cell dateCell2 = rows.createCell(2);
						dateCell2.setCellValue("ABSENT TODAY");
						dateCell2.setCellStyle(dateCellStyle);

						Cell dateCell3 = rows.createCell(3);
						dateCell3.setCellStyle(dateCellStyle);

						Cell dateCell4 = rows.createCell(4);
						dateCell4.setCellStyle(dateCellStyle);

					} else {
						int len = resolvedByUsers.size();
						if (len == 1) {
							logger.info("One on task List");
						} else {
							sheet.addMergedRegion(new CellRangeAddress(rowIdx, rowIdx + len - 1, 0, 0));
							sheet.addMergedRegion(new CellRangeAddress(rowIdx, rowIdx + len - 1, 1, 1));

						}

						for (DailyTask task : resolvedByUsers) {

							Row rows = sheet.createRow(rowIdx++);
							rowStart = rowIdx;
							rows.setHeightInPoints(30);

							Cell dateCell0 = rows.createCell(0);
							dateCell0.setCellValue(task.getUserName().getUserName());
							dateCell0.setCellStyle(dateCellStyle);

							Cell dateCell1 = rows.createCell(1);
							dateCell1.setCellValue(task.getDate());
							dateCell1.setCellStyle(dateCellStyle1);

							Cell dateCell2 = rows.createCell(2);
							dateCell2.setCellValue(task.getTaskname());
							dateCell2.setCellStyle(dateCellStyle);

							Cell dateCell3 = rows.createCell(3);
							dateCell3.setCellValue(task.getTask());
							dateCell3.setCellStyle(dateCellStyle);

							Cell dateCell4 = rows.createCell(4);
							dateCell4.setCellValue(task.getHours());
							dateCell4.setCellStyle(dateCellStyle);

							workingHour.add(task.getHours());
							employeeName = task.getUserName().getUserName();
							// size=workingHour.size();

							logger.info("Row index [" + rowStart + "]");

						}

					}

				}
				int hourAndMinConvertedToMin = 0;
				int hour = 0;
				int min = 0;
				logger.info("How many row are  entered[" + workingHour.size() + "]");
				for (int index = 0; index < workingHour.size(); index++) {
					String time = workingHour.get(index);
					String[] split = time.split("[ hour  min]+");
					hour = (Integer.parseInt(split[0])) + hour;
					min = Integer.parseInt(split[1]) + min;

					hourAndMinConvertedToMin = ((hour * 60) + (min));

					logger.info("Hours convered to min and Total number min:[ " + hourAndMinConvertedToMin + "]");
					// converList=0;

				}
				{

					for (int update = rowEnd; update < rowStart; update++) {
						logger.info("after update........... ");
						logger.info(" rowStart[" + rowStart + "]" + " rowEnd[" + rowEnd + "]");
						Row rowpdate = sheet.getRow(update);
						Cell dateCell0 = rowpdate.getCell(0);
						Cell dateCell1 = rowpdate.getCell(1);
						Cell dateCell2 = rowpdate.getCell(2);
						Cell dateCell3 = rowpdate.getCell(3);
						Cell dateCell4 = rowpdate.getCell(4);
						// 480 is 8 hour converted to min
						if (hourAndMinConvertedToMin >= 480) {
							logger.info("This [" + employeeName + "]reached 8 hours working time");
						} else {
							dateCell1.setCellStyle(dateCellStyleWorkingTime);
							dateCell0.setCellStyle(dateCellStyleWorkingTime);
							dateCell2.setCellStyle(dateCellStyleWorkingTime);
							dateCell3.setCellStyle(dateCellStyleWorkingTime);
							dateCell4.setCellStyle(dateCellStyleWorkingTime);
							logger.info("Working hours not still enough : [" + hourAndMinConvertedToMin + "]");

						}
					}
					rowEnd = rowStart;
				}
				resolvedByUsers.clear();
				workingHour.clear();
				employeeName = null;

			}

			workbook.write(out);

		}

	}

}

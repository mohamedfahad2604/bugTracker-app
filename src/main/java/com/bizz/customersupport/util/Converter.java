package com.bizz.customersupport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bizz.customersupport.entities.DailyTask;
import com.bizz.customersupport.entities.User;

@Service
public class Converter {
	private final static Logger logger = LoggerFactory.getLogger(Converter.class);

	public static Date convertDateFromCalendar(Calendar cal) {

		SimpleDateFormat FORMATWITHDATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);

		String dateString = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;

		System.out.println("Trying to format the following string " + dateString);

		Date convertedDate;
		try {
			convertedDate = FORMATWITHDATETIME.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		return convertedDate;
	}

	/**
	 * This method convert List of task to html String just start here we are using following tags only
	 * <head>,<style>,<body>
	 * 
	 * Here sent List of users and list of tasks
	 * 
	 * @param listOfAllUsers And listOfTask
	 * @return html string
	 */
	public static String covertHTML(List<User> listOfAllUsers, List<DailyTask> listOfTask) {

		StringBuffer convertHTML = new StringBuffer();
		convertHTML.append("<head>\r\n" + "<style>\r\n" + "table, th, td {\r\n" + "  border: 1px solid black;\r\n" + "  border-collapse: collapse;\r\n"
				+ "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body> <table style=\"width:100%\">" + "<tr>\r\n" + "    <th>Employee</th>\r\n"
				+ "    <th>Date</th> \r\n" + "    <th>Task Name</th>\r\n" + "    <th>Task / Issue</th>\r\n" + "    <th>Hours</th>\r\n" + "  </tr>");

		int rowIdx = 1;
		int rowStart = 0;
		int rowEnd = 1;
		int sizeOfTask = 0;

		boolean nameAlreadyExits = false;

		List<DailyTask> taskByUser = new ArrayList<DailyTask>();
		List<String> workingHour = new ArrayList<>();

		for (int i = 0; i < listOfAllUsers.size(); i++) {

			User s = listOfAllUsers.get(i);
			String AllUsers = s.getUserName();

			{
				for (DailyTask user : listOfTask) {
					if (user.getUserName().getUserName().equals(AllUsers)) {
						rowStart = rowIdx;
						taskByUser.add(user);
					}
				}
				for (DailyTask taskWorkingHour : taskByUser) {
					workingHour.add(taskWorkingHour.getHours());

				}

				logger.info("How many row are  entered[" + workingHour.size() + "]");
				int hourAndMinConvertedToMin = 0;
				int hour = 0;
				int min = 0;
				for (int index = 0; index < workingHour.size(); index++) {
					String time = workingHour.get(index);
					String[] split = time.split("[ hour  min]+");
					hour = (Integer.parseInt(split[0])) + hour;
					min = Integer.parseInt(split[1]) + min;

					hourAndMinConvertedToMin = ((hour * 60) + (min));

					logger.info("Hours convered to min and Total number min:[ " + hourAndMinConvertedToMin + "]");
					// converList=0;

				}
				if (taskByUser.isEmpty()) {
					convertHTML.append("<tr bgcolor='#FF0000'>");

					convertHTML.append("<td>" + AllUsers + "</td>\n");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR, 0);
					cal.set(Calendar.AM_PM, Calendar.AM);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					Date today = Converter.convertDateFromCalendar(cal);
					convertHTML.append("<td class='date'>" + today + "</td>\n");
					convertHTML.append("<td colspan=\"3\" style=\"text-align:center;\">ABSENT TODAY</td>");
					convertHTML.append("</tr>\n");

				} else {

					for (DailyTask task : taskByUser) {

						sizeOfTask = taskByUser.size();

						if (sizeOfTask > 0 && !nameAlreadyExits) {
							if (hourAndMinConvertedToMin >= 480) {
								logger.info("This [" + task.getUserName() + "]reached 8 hours working time");
								convertHTML.append("<tr>");
							} else {
								convertHTML.append("<tr bgcolor='#FF0000'>");
								logger.info("Working hours not still enough : [" + hourAndMinConvertedToMin + "]");

							}

							convertHTML.append("<td  rowspan=" + sizeOfTask + ">" + task.getUserName().getUserName() + "</td>\n");
							convertHTML.append("<td rowspan=" + sizeOfTask + " class='date'>" + task.getDate() + "</td>\n");
							convertHTML.append("<td>" + task.getTaskname() + "</td>\n");
							convertHTML.append("<td>" + task.getTask() + "</td>\n");
							convertHTML.append("<td>" + task.getHours() + "</td>\n");
							convertHTML.append("</tr>\n");
							nameAlreadyExits = true;

						} else {
							if (hourAndMinConvertedToMin >= 480) {
								logger.info("This [" + task.getUserName() + "]reached 8 hours working time");
								convertHTML.append("<tr>");
							} else {
								convertHTML.append("<tr bgcolor='#FF0000'>");
								logger.info("Working hours not still enough : [" + hourAndMinConvertedToMin + "]");

							}
							convertHTML.append("<td>" + task.getTaskname() + "</td>\n");
							convertHTML.append("<td>" + task.getTask() + "</td>\n");
							convertHTML.append("<td>" + task.getHours() + "</td>\n");
							convertHTML.append("</tr>\n");

						}

					}

				}

			}
			taskByUser.clear();
			nameAlreadyExits = false;
			workingHour.clear();

		}

		convertHTML.append("</table></body>");

		return convertHTML.toString();

	}

}

package com.bizz.customersupport.job;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizz.customersupport.constant.EmailConstant;
import com.bizz.customersupport.entities.DailyTask;
import com.bizz.customersupport.entities.EmailAddresses;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.DailyTaskService;
import com.bizz.customersupport.services.EmailService;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.util.Converter;

@Controller
public class DailyTaskListEmail {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private EmailService emailService;
	@Autowired
	private DailyTaskService dailyTaskService;

	@Autowired
	private UserListService userListService;

	private final Logger logger = LoggerFactory.getLogger(DailyTaskListEmail.class);

	@RequestMapping(value = "/dailyTaskListTrigger")
	@ResponseBody
	public ResponseEntity<FileSystemResource> excelDailyTaskList() throws MessagingException {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date today = Converter.convertDateFromCalendar(cal);

		List<User> listOfAllUsers = userListService.listAllIsActive();
		logger.info("Starting Daily Task List for :" + today);
		List<DailyTask> findAll = dailyTaskService.findByDate(today);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
		String strDate = formatter.format(date);

		EmailAddresses email = emailService.getByTitle("Daily Task List");
		String fileNameWithPath = email.getPathLocation() + "DailyTasklist_" + strDate + ".xlsx";

		try {
			TaskListExcelGenerator.taskToExcel(findAll, listOfAllUsers, fileNameWithPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(ExceptionUtils.getStackTrace(e));
			logger.info("Exception Mail starting...........");
			EmailAddresses emailException = emailService.getByTitle("Exception");
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("it.alert@tnets.com.sg");
			String[] emailAddress = emailException.getEmails().split(";");

			logger.info("Got the email addresses after split....");
			int emailAddressSize = emailAddress.length;
			logger.info("Got the email addresses after split with length as ....[" + emailAddressSize + "]");

			for (int i = 0; i < emailAddressSize; i++) {
				helper.addTo(emailAddress[i]);
				logger.info("Added the address [" + emailAddress[i] + "]");
			}

			helper.setSubject(emailException.getSubject() + e.toString());
			helper.setText(emailException.getContent() + ExceptionUtils.getStackTrace(e) + EmailConstant.SIGNATURE,
					true);

			logger.info("Successfully attached......................");
			javaMailSender.send(mimeMessage);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=DailyTaskList.xlsx");
		return ResponseEntity.ok().headers(headers).body(new FileSystemResource(new File(fileNameWithPath)));

		// return "heellooo";

	}

	// second, minute, hour, day, month, weekday pattern for schedule
	@Scheduled(cron = "0 45 23 * * ?")
	//@Scheduled(cron = "0 02 00 * * ?")
	@RequestMapping(value = "/dailyTaskListTriggerEmail")
	public String excelDailyTaskListEmail() throws MessagingException {

		logger.info("Going to execute the DailyTask List Trigger...");
		EmailAddresses email = emailService.getByTitle("Daily Task List");

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
		String strDate = formatter.format(date);

		String fileNameWithPath = email.getPathLocation() + "DailyTasklist_" + strDate + ".xlsx";

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date today = Converter.convertDateFromCalendar(cal);
		logger.info("Going to list of date:" + today);
		String convertHTML = null;
		

		try {
			List<User> listOfAllUsers = userListService.listAllIsActive();
			logger.info("Starting Daily Task List for :" + today);
			List<DailyTask> tasks = dailyTaskService.findByDate(today);
			convertHTML  =	Converter.covertHTML(listOfAllUsers, tasks);
			TaskListExcelGenerator.taskToExcel(tasks, listOfAllUsers, fileNameWithPath);

		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));

			logger.info("Exception Mail starting...........");
			EmailAddresses emailException = emailService.getByTitle("Exception");
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("it.alert@tnets.com.sg");
			String[] emailAddress = emailException.getEmails().split(";");

			logger.info("Got the email addresses after split....");
			int emailAddressSize = emailAddress.length;
			logger.info("Got the email addresses after split with length as ....[" + emailAddressSize + "]");

			for (int i = 0; i < emailAddressSize; i++) {
				helper.addTo(emailAddress[i]);
				logger.info("Added the address [" + emailAddress[i] + "]");
			}

			helper.setSubject(emailException.getSubject() + e.toString());
			helper.setText(emailException.getContent() + ExceptionUtils.getStackTrace(e) + EmailConstant.SIGNATURE,
					true);

			logger.info("Successfully attached......................");
			javaMailSender.send(mimeMessage);
		}

		FileSystemResource fileToAttach = null;
		try {
			logger.info("Starting to send email ...........");

			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setFrom("it.support@tnets.com.sg");
			String[] emailAddress = email.getEmails().split(";");

			logger.info("Got the email addresses after split....");

			int emailAddressSize = emailAddress.length;
			logger.info("Got the email addresses after split with length as ....[" + emailAddressSize + "]");

			for (int i = 0; i < emailAddressSize; i++) {
				helper.addTo(emailAddress[i]);
				logger.info("Added the address [" + emailAddress[i] + "]");
			}

			logger.info("Added all the addresses... now we are ready to send.........");

			helper.setSubject(email.getSubject() + strDate);

			logger.info("Here is the subject [" + email.getSubject() + "] .......");

			logger.info("Here is the content [" + email.getContent() + "] .......");

			helper.setText(
					email.getContent() + convertHTML.toString() + EmailConstant.SIGNATURE,
					true);

			logger.info("Processing for pickup attachment...............");

			fileToAttach = new FileSystemResource(new File(fileNameWithPath));

			logger.info("Going to attach the file ...............");

			helper.addAttachment(fileToAttach.getFilename(), fileToAttach);
			logger.info("Successfully attached......................");
			javaMailSender.send(msg);

		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));
			logger.info(ExceptionUtils.getStackTrace(e));
			logger.info("Exception Mail starting...........");
			EmailAddresses emailException = emailService.getByTitle("Exception");
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("it.alert@tnets.com.sg");
			String[] emailAddress = emailException.getEmails().split(";");

			logger.info("Got the email addresses after split....");
			int emailAddressSize = emailAddress.length;
			logger.info("Got the email addresses after split with length as ....[" + emailAddressSize + "]");

			for (int i = 0; i < emailAddressSize; i++) {
				helper.addTo(emailAddress[i]);
				logger.info("Added the address [" + emailAddress[i] + "]");
			}

			helper.setSubject(emailException.getSubject() + e.toString());
			helper.setText(emailException.getContent() + ExceptionUtils.getStackTrace(e) + EmailConstant.SIGNATURE,
					true);

			logger.info("Successfully attached......................");
			javaMailSender.send(mimeMessage);
		}

		return "redirect:/";

	}
	

}

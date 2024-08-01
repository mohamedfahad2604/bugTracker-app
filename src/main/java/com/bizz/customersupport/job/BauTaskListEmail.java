package com.bizz.customersupport.job;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bizz.customersupport.constant.EmailConstant;
import com.bizz.customersupport.entities.DailyTask;
import com.bizz.customersupport.entities.EmailAddresses;
import com.bizz.customersupport.entities.Issue;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.DailyTaskService;
import com.bizz.customersupport.services.EmailService;
import com.bizz.customersupport.services.IssuesService;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.util.Converter;

@Component
@Controller
public class BauTaskListEmail implements CommandLineRunner {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	IssuesService issueService;
	
	@Autowired
	private DailyTaskService dailyTaskService;

	@Autowired
	private UserListService userListService;

	@Autowired
	private EmailService emailService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	SimpleDateFormat FORMATWITHDATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateTimeFormatter FORMATWITHDATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	LocalDateTime today = LocalDateTime.now().withHour(0);
	LocalDateTime issuesName = LocalDateTime.now();
	LocalDateTime todayend = LocalDateTime.now().withHour(23);
	// second, minute, hour, day, month, weekday pattern for schedule

	//@Scheduled(cron = "0 0 18 * * ?")
	@Scheduled(cron = "0 45 23 * * ?")
	@RequestMapping(value = "/bautrigger")
	public String AutoReport() throws InterruptedException, MessagingException {

		logger.info(".........Job Stating for sent Daily Report...... ");
		EmailAddresses email = emailService.getByTitle("BAU Task List");
		MimeMessage msg = javaMailSender.createMimeMessage();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
		String strDate = formatter.format(date);

		String fileNameWithPath = email.getPathLocation() + "BAUTasklist_" + strDate + ".xlsx";

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 45);
		cal.set(Calendar.SECOND, 0);
		Date endDate = Converter.convertDateFromCalendar(cal);

		logger.info("End date is " + endDate);
	
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));        
  		Date monthStartDate = Converter.convertDateFromCalendar(calendar);

		
		
		cal.add(Calendar.HOUR, -24);
		Date startDate = Converter.convertDateFromCalendar(cal);
		
		
		logger.info("Start date is " + startDate);

		// String fileName = FORMATWITHDATE.format(issuesName);
		int count = issueService.countByCreateDate(startDate, endDate);
		if (count == 0) {
			try {
				logger.info("Starting to send email ...........");
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

				helper.setSubject(" Bizz Customer Support - Daily Report " + strDate);
				helper.setText("<h4>Hi All, </h4> \r\n <h4> There is no BAU for today</h4>\r\n" + "\r\n"
						+ "<h4>Thanks and Regards,</h4>\r\n" + "<h4>BIZZ Customer Support.</h4>\r\n"
						+ "<h4>IT Support</h4> "
						+ "<h3>This is an auto-generated email form the Bizz Customer Support Application, sent from an un-monitored mailbox. Please do not reply to this email. Thank you.</h3>",
						true);
				logger.info("Processing for pickup attachment...............");

				logger.info("Successfully attached......................");
				javaMailSender.send(msg);

			} catch (Exception e) {
				logger.info(ExceptionUtils.getStackTrace(e));
			
				logger.info("Exception Mail starting...........");
				EmailAddresses emailException = emailService.getByTitle("Exception");
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setFrom("it.support@tnets.com.sg");
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
				// logger.info("Email Succesfull sent to : adeen935@gmail.com");
				Thread.sleep(10000);
			}
			logger.info("List is Empty......");
		} else {

			try {
				List<Issue> issuesToday = issueService.getIssuesByCreateDate(startDate, endDate);
				List<Issue> issuesMonth = issueService.getIssuesByCreateDate(monthStartDate, endDate);
				List<Issue> issuesOpen = issueService.getfindByStatus("OPEN");
				BauTaskListExcelGenerator.issueToExcel(issuesToday,issuesMonth,issuesOpen,fileNameWithPath);

				logger.info("Processing Report Genrating to local System................. ");

			
				

				
				
			} catch (Exception e) {
				logger.info(ExceptionUtils.getStackTrace(e));
			}

			logger.info("Successfully Report Gerating and local copy..........");

			logger.info("Wait for 10 sec.........");

			Thread.sleep(10000);
			FileSystemResource fileToAttach = null;
			try {
				logger.info("Mail starting...........");
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

				helper.setSubject(email.getSubject() + strDate);
				helper.setText(email.getContent() + EmailConstant.SIGNATURE, true);
				logger.info("Processing for pickup attachment...............");
				fileToAttach = new FileSystemResource(new File(fileNameWithPath));
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

				helper.setFrom("it.support@tnets.com.sg");
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
				Thread.sleep(10000);
			}
		}
		return "redirect:/";

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		MimeMessage msg = javaMailSender.createMimeMessage();
		EmailAddresses email = emailService.getByTitle("BCS startUpIn");
		try {
			logger.info("Mail starting...........");
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

			helper.setSubject(email.getSubject());
			helper.setText(email.getContent()+ EmailConstant.SIGNATURE, true);
			
			logger.info("Successfully attached......................");
			javaMailSender.send(msg);

		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));
			logger.info("Exception Mail starting...........");
			EmailAddresses emailException = emailService.getByTitle("Exception");
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("it.support@tnets.com.sg");
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

	}
	
	

}

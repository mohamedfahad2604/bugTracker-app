package com.bizz.customersupport.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizz.customersupport.entities.BugTracker;
import com.bizz.customersupport.entities.Notification;

@Service
public class EmailTicketService {

	@Autowired
	private JavaMailSender mailSender;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Value("${smtp.send.mail}")
	private boolean sendEmail;

	@Value("${support.email}")
	private String supportEmail;

	@Value("${default.toAddress}")
	private String defaultToAddress;

	@Value("${copyRecipients}")
	private String copyRecipients;

	@Value("${bccRecipients}")
	private String bccRecipients;

	public void sendNotification(Notification notification, BugTracker bt) throws Exception {
		
		logger.info("inside the send notification method {} and bug details {} ",notification ,bt);
		
		StringBuilder toAddress = new StringBuilder();

		// -- When the notification has only one recipients and no attachments
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(notification.getSender());
		helper.setSubject(notification.getSubject());

		// create the HTML content of the message
		//String acceptUrl = "http://localhost:8585/proposalResponse/accept?id=" + bt.getId();
		String acceptUrl = "https://tickets.customs360.net/proposalResponse/accept?id=" + bt.getId();

		
		//String rejectUrl = "http://localhost:8585/proposalResponse/reject?id=" + bt.getId();
		//String holdUrl = "http://localhost:8585/proposalResponse/hold?id=" + bt.getId();
		String rejectUrl = "https://tickets.customs360.net/proposalResponse/reject?id=" + bt.getId();
		String holdUrl = "https://tickets.customs360.net/proposalResponse/hold?id=" + bt.getId();
		String content = "<html><body>"
				+ "Hi All,"
				+ "<br><br>"
				+ "Please find below a details of new enhancement in our production. Kindly confirm for further proceedings."
				+ "<br><br>" 
				+ "Ticket Name : " + bt.getBugName() 
				+ "<br>" 
				+ "Application Name : "+ bt.getApplicationName() 
				+ "<br>" 
				+ "Author Name : "+ bt.getAuthor().getUserName() 
				+ "<br>" 
				+ "Status : " + bt.getBugStatus() 
				+ "<br>"
				+ "Estimated Days : " + bt.getEstimatedDays() 
				+ "<br><br>"
				+ "<table>"
				+ "<tr>"
				+ "<td>"
				+ "<table style=\"background-color: green; color: white; padding: 10px 20px; display: inline-block;\">"
				+ "<tr>"
				+ "<td>"+ "<a href=\"" + acceptUrl + "\" target=\"_blank\" style=\"color: white; text-decoration: none;\">"+"Accept</a></td>"
				+ "</tr>"
				+ "</table>"
				+ "</td>"
				+ "<td>&nbsp;</td>"
				+ "<td>"
                + "<table style=\"background-color: red; color: white; padding: 10px 20px; display: inline-block;\">"
                + "<tr>"
                + "<td>"+ "<a href=\"" + rejectUrl + "\" target=\"_blank\" style=\"color: white; text-decoration: none;\">"+"Reject</a></td>"
				 + "</tr>"
                + "</table>"
                + "</td>"
				+ "<td>&nbsp;</td>"
				+ "<td>"
				+ "<table style=\"background-color: yellow; color: black; padding: 10px 20px; display: inline-block;\">"
				+ "<tr>"
				+ "<td>"+ "<a href=\"" + holdUrl + "\" target=\"_blank\" style=\"color: blue; text-decoration: none;\">"+"Hold</a></td>"
				+ "</tr>"
				+ "</table>"
				+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "<br><br>"
				+ "Thanks and regards," 
				+ "<br>"
				+ "Ticket Tracker"
				+ "<br>"
				+ "Customs360"
				+ "<br><br>"
				+ "Note: This email has been auto-generated from the Bizz Customer Support Application and is sent from an unmonitored mailbox. Kindly do not reply to this email."
				+ "</body></html>";

		// set the HTML content of the message
		helper.setText(content, true);
		addingAllRecipients(helper, notification, toAddress);
		if (sendEmail) {
			mailSender.send(helper.getMimeMessage());
		} else {
			logger.info("mail not send");
		}
	}

	private void addingAllRecipients(MimeMessageHelper helper, Notification notification, StringBuilder toAddress)
			throws MessagingException {
		

		// -- Add the recipients
		/*
		 * if (notification.getRecipients() != null &&
		 * !notification.getRecipients().isEmpty()) { for (String recipient :
		 * notification.getRecipients()) { helper.addTo(recipient);
		 * toAddress.append(recipient).append(","); } }
		 */

		if (notification.getRecipients() != null) {
			for (String recipient : notification.getRecipients()) {
				helper.addTo(recipient);
			}
			logger.info("recipient");
		}

		// -- Add the cc list

		if (notification.getCopyRecipients() != null) {
			for (String copyRecipient : notification.getCopyRecipients()) {
				helper.addCc(copyRecipient);
			}
		}

		// -- Add the cc list
		/*
		 * if (StringUtils.isNoneBlank(copyRecipients)) { for (String copyRecipient :
		 * copyRecipients.split(",")) { helper.addCc(copyRecipient); } }
		 */

		// -- Add the bcc list

		if (notification.getBlindCarbonCopy() != null) {
			for (String bccRecipient : notification.getBlindCarbonCopy()) {
				helper.addBcc(bccRecipient);
			}
		}

		// -- Add the bcc list
		/*
		 * if (StringUtils.isNoneBlank(bccRecipients)) { for (String bccRecipient :
		 * bccRecipients.split(",")) { helper.addBcc(bccRecipient); } }
		 */

	}

}
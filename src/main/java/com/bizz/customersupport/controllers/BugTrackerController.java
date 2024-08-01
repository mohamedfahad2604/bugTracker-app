package com.bizz.customersupport.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.catalina.startup.VersionLoggerListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizz.customersupport.entities.BugTracker;
import com.bizz.customersupport.entities.Notification;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.BugTrackerService;
import com.bizz.customersupport.services.EmailTicketService;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.services.UserService;
import com.bizz.customersupport.web.dto.BugTrackerDto;
import com.bizz.customersupport.web.dto.IssuesDto;

@Controller
public class BugTrackerController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${copyRecipients}")
	private String copyRecipients;
	
	@Value("${bccRecipients}")
	private String bccRecipients;
	
	@Value("${recipients}")
	private String recipients;
	
	@Value("${sender}")
	private String sender;
	
	@Value("${subject}")
	private String subject;

	@Autowired
	private UserService userservice;

	@Autowired
	private EmailTicketService emailService;

	@Autowired
	private BugTrackerService bugTrackerService;

	@Autowired
	private UserListService userListService;

	@RequestMapping("/bugTracker")
	public String viewBugsList(Model model) {

		List<BugTracker> bugsList = bugTrackerService.listAll();
		model.addAttribute("bugsList", bugsList);
		return "bcshtml/adminViewBugTrackerList";
	}

	@RequestMapping(value = "/adminViewBugTrackerList", method = RequestMethod.GET)
	public String viewBugsList1(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<BugTracker> bugsList = bugTrackerService.listAll();
		model.addAttribute("bugsList", bugsList);
		return "bcshtml/adminViewBugTrackerList";
	}

	@RequestMapping("/userbugTracker")
	public String userviewBugsList(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		List<BugTracker> bugsList = null;
		User author = userListService.findByUserName(userName);
		String Designation = author.getDesignation();
		if (Designation.equals("MANAGER")) {
			bugsList = bugTrackerService.findByAuthor(author);
		} else {
			bugsList = bugTrackerService.findByAssigneeOrAuthor(author, author);
		}
		// User assignee = userListService.findByUserName(userName);
		// List<BugTracker> bugsList1 = bugTrackerService.findByAuthor(author);
		// List<BugTracker> bugsList2 = bugTrackerService.findByAssignee(assignee);
		// List<BugTracker> bugsList = bugTrackerService.listAll();
		model.addAttribute("bugsList", bugsList);
		// model.addAttribute("bugsList", bugsList2);
		return "bcshtml/userListAllBugs";
	}

	@RequestMapping(value = "/addNewBug", method = RequestMethod.GET)
	public String addNewBug(Model model) {
		BugTracker bug = new BugTracker();

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String bugNo = (dateFormat.format(cal.getTime())).toString();
		bugNo = bugNo.replaceAll("/", "");
		bugNo = bugNo.replaceAll(":", "");
		bugNo = bugNo.replaceAll(" ", "/");
		bugNo = "T/" + bugNo;
		bug.setBugNo(bugNo);
		Date createDate = new Date();
		bug.setCreatedDate(createDate);
		// List<User> listUsers = userListService.listAllIsActive();
		List<User> assignedToUsers = new ArrayList<User>();
		List<User> listUsers = userListService.getAllDevAndSupoortUserList();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();

		User author = userListService.findByUserName(userName);
		bug.setAuthor(author);
		logger.info("Logged in username is{}", userName);

		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				assignedToUsers.add(user);
			}
		}

		model.addAttribute("assignedToUsers", assignedToUsers);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("bug", bug);
		logger.info(assignedToUsers.toString());
		logger.info(listUsers.toString());
		logger.info(bug.toString());
		return "bcshtml/addBug";
	}

	@RequestMapping(value = "/addAdminBug", method = RequestMethod.GET)
	public String addAdminBug(Model model) {
		BugTracker bug = new BugTracker();

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String bugNo = (dateFormat.format(cal.getTime())).toString();
		bugNo = bugNo.replaceAll("/", "");
		bugNo = bugNo.replaceAll(":", "");
		bugNo = bugNo.replaceAll(" ", "/");
		bugNo = "T/" + bugNo;
		bug.setBugNo(bugNo);
		Date createDate = new Date();
		bug.setCreatedDate(createDate);
		List<User> listUsers = userListService.listAll();
		List<User> assignedToUsers = new ArrayList<User>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		User author = userListService.findByUserName(userName);
		bug.setAuthor(author);
		logger.info("Logged in username is{}", userName);

		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				assignedToUsers.add(user);
			}
		}

		model.addAttribute("assignedToUsers", assignedToUsers);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("bug", bug);
		logger.info(assignedToUsers.toString());
		logger.info(listUsers.toString());
		logger.info(bug.toString());
		return "bcshtml/addAdminBug";
	}

	@RequestMapping(value = "/userSaveBug", method = RequestMethod.POST)
	public String saveBug(@ModelAttribute("bug") BugTrackerDto bug) throws Exception {
		BugTracker bt = new BugTracker();
		Date bfd = new SimpleDateFormat("dd/MM/yyyy").parse(bug.getEstimatedFixDate());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		User user = userservice.findByuserName(userName);
		Date createDate = new Date();
		if (bug.getCreatedDate() == null) {
			bt.setCreatedDate(createDate);
		} else {
			bt.setCreatedDate(bug.getCreatedDate());
			logger.info("else part");
		}
		bt.setEstimatedFixDate(bfd);
		bt.setAssignee(bug.getAssignee());
		bt.setApplicationName(bug.getApplicationName());
		bt.setServerName(bug.getServerName());
		bt.setBugName(bug.getBugName());
		bt.setDescription(bug.getDescription());
		bt.setBugNo(bug.getBugNo());
		bt.setBugStatus(bug.getBugStatus());
		bt.setPriority(bug.getPriority());
		bt.setTicketType(bug.getTicketType());
		bt.setEstimatedDays(bug.getEstimatedDays());
		bt.setEstimatedHours(bug.getEstimatedHours());
		bt.setAuthor(user);
		logger.info(bt.toString());
		logger.info(bug.toString());
		bugTrackerService.save(bt);
		if(bug.getTicketType().equalsIgnoreCase("ENHANCEMENT")) {
			
			
		Notification notification = new Notification();

		String recipient = recipients;
		List<String> recipients = new ArrayList<>(Arrays.asList(recipient.split(",")));
		notification.setRecipients(recipients);

		notification.setSender(sender);
		
		String copyRecipientsString = copyRecipients;
		List<String> copyRecipients = new ArrayList<>(Arrays.asList(copyRecipientsString.split(",")));
		notification.setCopyRecipients(copyRecipients);

		String blindCarbonCopy = bccRecipients;
		List<String> blindCarbonCopies = new ArrayList<>(Arrays.asList(blindCarbonCopy.split(",")));
		notification.setBlindCarbonCopy(blindCarbonCopies);

		notification.setSubject(subject);
		
		emailService.sendNotification(notification,bt);
		}
		// saveMails("hema.b@tnets.com.sg", "Welcome2Bizz#", notification);
		return "redirect:/userbugTracker";
	}

	@RequestMapping(value = "/saveBug", method = RequestMethod.POST)
	public String adminSaveBug(@ModelAttribute("bug") BugTrackerDto bug) throws Exception {

		BugTracker bt = new BugTracker();
		Date bfd = new SimpleDateFormat("dd/MM/yyyy").parse(bug.getEstimatedFixDate());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();

		User user = userservice.findByuserName(userName);

		Date createDate = new Date();
		if (bug.getCreatedDate() == null) {
			bt.setCreatedDate(createDate);
		} else {
			bt.setCreatedDate(bug.getCreatedDate());
			logger.info("else part");

		}

		bt.setEstimatedFixDate(bfd);
		bt.setAssignee(bug.getAssignee());
		bt.setApplicationName(bug.getApplicationName());
		bt.setServerName(bug.getServerName());
		bt.setBugName(bug.getBugName());
		bt.setDescription(bug.getDescription());
		bt.setBugNo(bug.getBugNo());
		bt.setBugStatus(bug.getBugStatus());
		bt.setPriority(bug.getPriority());
		bt.setTicketType(bug.getTicketType());
		bt.setEstimatedDays(bug.getEstimatedDays());
		bt.setEstimatedHours(bug.getEstimatedHours());
		bt.setAuthor(user);
		
		logger.info(bt.toString());
		logger.info(bug.toString());
		bugTrackerService.save(bt);

		if(bug.getTicketType().equalsIgnoreCase("ENHANCEMENT")) {
			
		Notification notification = new Notification();

		String recipient = recipients;
		List<String> recipients = new ArrayList<>(Arrays.asList(recipient.split(",")));
		notification.setRecipients(recipients);

		notification.setSender(sender);
		
		String copyRecipientsString = copyRecipients;
		List<String> copyRecipients = new ArrayList<>(Arrays.asList(copyRecipientsString.split(",")));
		notification.setCopyRecipients(copyRecipients);

		String blindCarbonCopy = bccRecipients;
		List<String> blindCarbonCopies = new ArrayList<>(Arrays.asList(blindCarbonCopy.split(",")));
		notification.setBlindCarbonCopy(blindCarbonCopies);

		notification.setSubject(subject);
		emailService.sendNotification(notification,bt);
		}
		// saveMails("hema.b@tnets.com.sg", "Welcome2Bizz#", notification);

		return "redirect:/bugTracker";
	}

	@RequestMapping(value = "/proposalResponse/accept", method = RequestMethod.GET)
	public ModelAndView handleProposalResponse(@RequestParam("id") Long id, Model model) {
		logger.info("inside proposalResponse accpt method");
		ModelAndView modelAndView = new ModelAndView("bcshtml/bcsEmailResponse");

		BugTracker bt = bugTrackerService.get(id);
		if (bt.getEmailStatus() != null && !StringUtils.isEmpty(bt.getEmailStatus())) {
			logger.info("email status {}",bt.getEmailStatus());
			model.addAttribute("response", "yes");

		} else {
			bt.setEmailStatus("ACCEPTED");
			bugTrackerService.save(bt);
			model.addAttribute("response", "no");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/proposalResponse/reject", method = RequestMethod.GET)
	public ModelAndView handleProposalRejection(@RequestParam("id") Long id, Model model) {
		logger.info("inside proposalResponse reject method");
		ModelAndView modelAndView = new ModelAndView("bcshtml/bcsEmailResponse");
		BugTracker bt = bugTrackerService.get(id);
		if (bt.getEmailStatus() != null && !StringUtils.isEmpty(bt.getEmailStatus())) {
			logger.info("email status {}",bt.getEmailStatus());
			model.addAttribute("response", "yes");

		} else {
			bt.setEmailStatus("REJECTED");
			bugTrackerService.save(bt);
			model.addAttribute("response", "no");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/proposalResponse/hold", method = RequestMethod.GET)
	public ModelAndView handleProposalHold(@RequestParam("id") Long id, Model model) {
		logger.info("inside proposalResponse hold method");
		ModelAndView modelAndView = new ModelAndView("bcshtml/bcsEmailResponse");
		BugTracker bt = bugTrackerService.get(id);

		if (bt.getEmailStatus() != null && !StringUtils.isEmpty(bt.getEmailStatus())) {
			logger.info("email status {}",bt.getEmailStatus());
			model.addAttribute("response", "yes");

		} else {
			bt.setEmailStatus("HOLD");
			bugTrackerService.save(bt);
			model.addAttribute("response", "no");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/updateBug/{id}", method = RequestMethod.POST)
	public String updateBug(@PathVariable(name = "id") Long id, @ModelAttribute("bug") BugTrackerDto bug)
			throws Exception {

		Date createDate = new Date();
		BugTracker bt = bugTrackerService.get(id);
		Date bfd = new SimpleDateFormat("dd/MM/yyyy").parse(bug.getEstimatedFixDate());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info(bug.getId().toString());
		User user = userservice.findByuserName(userName);

		bt.setId(bug.getId());
		bt.setBugNo(bug.getBugNo());
		bt.setEstimatedFixDate(bfd);
		bt.setAssignee(bug.getAssignee());
		bt.setApplicationName(bug.getApplicationName());
		bt.setServerName(bug.getServerName());
		bt.setBugName(bug.getBugName());
		bt.setDescription(bug.getDescription());
		if (bt.getAuthor().getName().equals(user.getName())) {
			bt.setAuthor(user);
		}

		bt.setBugStatus(bug.getBugStatus());
		bt.setPriority(bug.getPriority());
		bt.setTicketType(bug.getTicketType());
		bt.setEstimatedDays(bug.getEstimatedDays());
		bt.setEstimatedHours(bug.getEstimatedHours());
		bt.setCreatedDate(createDate);

		logger.info(bt.toString());
		logger.info(bug.toString());
		bugTrackerService.save(bt);
		/*
		 * Notification notification = new Notification();
		 * 
		 * String recipient = recipients; List<String> recipients = new ArrayList<>();
		 * recipients.add(recipient); notification.setRecipients(recipients);
		 * 
		 * notification.setSender(sender);
		 * 
		 * String copyRecipientsString = copyRecipients; List<String> copyRecipients =
		 * new ArrayList<>(Arrays.asList(copyRecipientsString.split(",")));
		 * notification.setCopyRecipients(copyRecipients);
		 * 
		 * String blindCarbonCopy = bccRecipients; List<String> blindCarbonCopies = new
		 * ArrayList<>(Arrays.asList(blindCarbonCopy.split(",")));
		 * notification.setBlindCarbonCopy(blindCarbonCopies);
		 * 
		 * notification.setSubject(subject);
		 * 
		 * emailService.sendNotification(notification,bt);
		 */
		return "redirect:/userbugTracker";
	}

	@RequestMapping(value = "/updateAdminBug/{id}", method = RequestMethod.POST)
	public String updateAdminBug(@PathVariable(name = "id") Long id, @ModelAttribute("bug") BugTrackerDto bug)
			throws Exception {
		logger.info("after edit bug details {}" ,bug);
		
		Date createDate = new Date();
		BugTracker bt = bugTrackerService.get(id);
		Date bfd = new SimpleDateFormat("dd/MM/yyyy").parse(bug.getEstimatedFixDate());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info(bug.getId().toString());

		User user = userservice.findByuserName(userName);

		bt.setId(bug.getId());
		bt.setBugNo(bug.getBugNo());
		bt.setEstimatedFixDate(bfd);
		bt.setAssignee(bug.getAssignee());
		bt.setApplicationName(bug.getApplicationName());
		bt.setServerName(bug.getServerName());
		bt.setBugName(bug.getBugName());
		bt.setDescription(bug.getDescription());
		if (bt.getAuthor().getName().equals(user.getName())) {
			bt.setAuthor(user);
		}
		bt.setBugStatus(bug.getBugStatus());
		bt.setPriority(bug.getPriority());
		bt.setTicketType(bug.getTicketType());
		bt.setEstimatedDays(bug.getEstimatedDays());
		bt.setEstimatedHours(bug.getEstimatedHours());
		bt.setCreatedDate(createDate);

		logger.info(bt.toString());
		logger.info(bug.toString());
		
		
		bugTrackerService.save(bt);
		
		/*
		 * Notification notification = new Notification();
		 * 
		 * String recipient = recipients; List<String> recipients = new ArrayList<>();
		 * recipients.add(recipient); notification.setRecipients(recipients);
		 * 
		 * notification.setSender(sender);
		 * 
		 * String copyRecipientsString = copyRecipients; List<String> copyRecipients =
		 * new ArrayList<>(Arrays.asList(copyRecipientsString.split(",")));
		 * notification.setCopyRecipients(copyRecipients);
		 * 
		 * String blindCarbonCopy = bccRecipients; List<String> blindCarbonCopies = new
		 * ArrayList<>(Arrays.asList(blindCarbonCopy.split(",")));
		 * notification.setBlindCarbonCopy(blindCarbonCopies);
		 * 
		 * notification.setSubject(subject);
		 * 
		 * emailService.sendNotification(notification,bt);
		 */
		return "redirect:/bugTracker";
	}

	@RequestMapping("/bugTracker/{id}")
	public ModelAndView newBug(@PathVariable(name = "id") int id) {

		BugTracker bug = new BugTracker();
		User user = userListService.get(id);
		bug.setAuthor(user);

		ModelAndView modelAndView = new ModelAndView("bug");
		modelAndView.addObject("bug", bug);
		return modelAndView;
	}

	@RequestMapping(value = "/deleteBug/{id}")
	public String deleteBug(@PathVariable(name = "id") int id) {
		bugTrackerService.delete((long) id);
		return "redirect:/bugTracker";
	}

	@RequestMapping(value = "/editBug/{id}")
	public ModelAndView editBug(@PathVariable(name = "id") Long id) {
		ModelAndView modelAndView = new ModelAndView("bcshtml/editBug");
		BugTracker bug = bugTrackerService.get(id);
		bug.setId(id);
		List<User> listUsers = userListService.listAll();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String estimatedFixDate = dateFormat.format(bug.getEstimatedFixDate());

		BugTrackerDto bugDto = new BugTrackerDto();
		bugDto.setId(bug.getId());
		bugDto.setAuthor(bug.getAuthor());
		bugDto.setCreatedDate(bug.getCreatedDate());
		bugDto.setApplicationName(bug.getApplicationName());
		bugDto.setServerName(bug.getServerName());
		bugDto.setBugName(bug.getBugName());
		bugDto.setDescription(bug.getDescription());
		bugDto.setBugNo(bug.getBugNo());
		
		bugDto.setBugStatus(bug.getBugStatus());
		bugDto.setPriority(bug.getPriority());
		bugDto.setAssignee(bug.getAssignee());
		bugDto.setEstimatedFixDate(estimatedFixDate);
		bugDto.setTicketType(bug.getTicketType());
		bugDto.setEstimatedDays(bug.getEstimatedDays());
		bugDto.setEstimatedHours(bug.getEstimatedHours());
		bugDto.setEmailStatus(bug.getEmailStatus());
		modelAndView.addObject("listUsers", listUsers);
		modelAndView.addObject("bug", bugDto);
		
		
if(bug.getEmailStatus()!=null) {
	modelAndView.addObject("emailStatus","yes");
		}
	

		return modelAndView;
	}

	@RequestMapping(value = "/editAdminBug/{id}")
	public ModelAndView editAdminBug(@PathVariable(name = "id") Long id) {
		ModelAndView modelAndView = new ModelAndView("bcshtml/editAdminBug");
		BugTracker bug = bugTrackerService.get(id);
		bug.setId(id);
		List<User> listUsers = userListService.listAll();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String estimatedFixDate = dateFormat.format(bug.getEstimatedFixDate());

		
		BugTrackerDto bugDto = new BugTrackerDto();
				bugDto.setId(bug.getId());
				bugDto.setAuthor(bug.getAuthor());
				bugDto.setCreatedDate(bug.getCreatedDate());
				bugDto.setApplicationName(bug.getApplicationName());
				bugDto.setServerName(bug.getServerName());
				bugDto.setBugName(bug.getBugName());
				bugDto.setDescription(bug.getDescription());
				bugDto.setBugNo(bug.getBugNo());
				
				bugDto.setBugStatus(bug.getBugStatus());
				bugDto.setPriority(bug.getPriority());
				bugDto.setAssignee(bug.getAssignee());
				bugDto.setEstimatedFixDate(estimatedFixDate);
				bugDto.setTicketType(bug.getTicketType());
				bugDto.setEstimatedDays(bug.getEstimatedDays());
				bugDto.setEstimatedHours(bug.getEstimatedHours());
				bugDto.setEmailStatus(bug.getEmailStatus());
				
				logger.info("before edit bug details {}" ,bugDto);
		modelAndView.addObject("listUsers", listUsers);
		modelAndView.addObject("bug", bugDto);
		if(bug.getEmailStatus()!=null) {
			modelAndView.addObject("emailStatus","yes");
				}
		return modelAndView;
	}
	/*
	 * @RequestMapping("/bug_history/{id}") public ModelAndView
	 * employeeDonationHistory(@PathVariable(name = "id") int id) {
	 * 
	 * employee = empService.get(id); // logger.info(employee.toString()); Donation
	 * set_donation = new Donation(); set_donation.setEmployee(employee); //
	 * logger.info(emp_donation.toString());
	 * 
	 * List<Donation> emp_donation = donService.getHistory((long) (id));
	 * logger.info(emp_donation.toString());
	 * 
	 * ModelAndView modelAndView = new ModelAndView("emp_donation"); //
	 * modelAndView.addObject("set_donation", set_donation);
	 * 
	 * modelAndView.addObject("emp_donation", emp_donation); employee = null; return
	 * modelAndView; }
	 */

	/*
	 * Properties properties = new Properties(); public void saveMails(String user,
	 * String password, Notification notification) {
	 * logger.info("Email sent save "); Session emailSession; Store store = null;
	 * Folder emailFolder = null; Message message1 = null;
	 * 
	 * try { properties.put(EmailConstants.EMAIL_HOST, host);
	 * properties.put(EmailConstants.EMAIL_PORT, port);
	 * properties.put(EmailConstants.EMAIL_TLS, "true"); emailSession =
	 * Session.getInstance(properties, new javax.mail.Authenticator() {
	 * 
	 * @Override protected PasswordAuthentication getPasswordAuthentication() {
	 * return new PasswordAuthentication(user, password); } }); store =
	 * emailSession.getStore(EmailConstants.EMAIL_IMAP); store.connect(host, user,
	 * password); emailFolder = store.getFolder("INBOX.Sent");
	 * emailFolder.open(Folder.READ_WRITE);
	 * 
	 * 
	 * message1 = new MimeMessage(emailSession);
	 * //message1.setText(text.replace("&nbsp;", ""));
	 * message1.setFlag(Flags.Flag.SEEN, true); message1.setFrom(new
	 * InternetAddress(notification.getSender()));
	 * 
	 * 
	 * 
	 * 
	 * Address[] recipients = new
	 * InternetAddress[notification.getRecipients().size()]; List<String> recipient
	 * = notification.getRecipients(); for(int i=0; i<recipient.size(); i++){
	 * recipients[i] = new InternetAddress(recipient.get(i)); }
	 * message1.setRecipients(Message.RecipientType.TO, recipients);
	 * 
	 * Address[] copyRecipients = new
	 * InternetAddress[notification.getCopyRecipients().size()]; List<String>
	 * copyRecipient = notification.getCopyRecipients(); for(int i=0;
	 * i<copyRecipient.size(); i++){ copyRecipients[i] = new
	 * InternetAddress(copyRecipient.get(i)); }
	 * message1.setRecipients(Message.RecipientType.CC, copyRecipients);
	 * 
	 * Address[] bccRecipients = new
	 * InternetAddress[notification.getBlindCarbonCopy().size()]; List<String>
	 * bccRecipient = notification.getBlindCarbonCopy(); for(int i=0;
	 * i<bccRecipient.size(); i++){ bccRecipients[i] = new
	 * InternetAddress(bccRecipient.get(i)); }
	 * message1.setRecipients(Message.RecipientType.BCC, bccRecipients);
	 * 
	 * 
	 * message1.setSubject(notification.getSubject());
	 * 
	 * BodyPart messageBodyPart = new MimeBodyPart();
	 * messageBodyPart.setContent(notification.getMessage(),
	 * "text/html; charset=UTF-8");
	 * 
	 * Multipart multipart = new MimeMultipart();
	 * multipart.addBodyPart(messageBodyPart);
	 * 
	 * List<File> filename = notification.getAttachments(); if(filename!= null) {
	 * for (int i = 0; i < filename.size(); i++) { messageBodyPart = new
	 * MimeBodyPart(); DataSource source = new FileDataSource(filename.get(i));
	 * messageBodyPart.setDataHandler(new DataHandler(source));
	 * messageBodyPart.setFileName(MimeUtility.encodeText(filename.get(i).getName(),
	 * "UTF-8", null)); multipart.addBodyPart(messageBodyPart); } }
	 * message1.setContent(multipart);
	 * 
	 * emailFolder.appendMessages(new Message[] { message1 });
	 * 
	 * } catch (MessagingException e) { e.printStackTrace(); } catch
	 * (UnsupportedEncodingException e) { e.printStackTrace(); } finally { try {
	 * store.close(); emailFolder.close(false); } catch (MessagingException e) {
	 * e.printStackTrace(); }
	 * 
	 * } }
	 */

	@RequestMapping(value = "/acceptadmintask", method = RequestMethod.POST)
	public ResponseEntity<String> handleAcceptAdminTask(@RequestParam("id") Long id) {
		BugTracker bt = bugTrackerService.get(id);
		bt.setServerName("Juberia");
		logger.info("Inside accept admin task");
		bugTrackerService.save(bt);
		return ResponseEntity.ok("Response message");
	}

}

package com.bizz.customersupport.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.bizz.customersupport.entities.Category;
import com.bizz.customersupport.entities.Issue;
import com.bizz.customersupport.entities.IssueHistory;
import com.bizz.customersupport.entities.IssueStatusHistory;
import com.bizz.customersupport.entities.IssueType;
import com.bizz.customersupport.entities.Ktdetails;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.CategoryService;
import com.bizz.customersupport.services.IssueHistoryService;
import com.bizz.customersupport.services.IssueStatusHistoryService;
import com.bizz.customersupport.services.IssueTypeService;
import com.bizz.customersupport.services.IssuesService;
import com.bizz.customersupport.services.KtService;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.services.UserService;
import com.bizz.customersupport.util.Converter;
import com.bizz.customersupport.web.dto.IssuesDto;

@Controller
public class IssueController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IssuesService issueService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserListService userListService;

	@Autowired
	private IssueHistoryService issueHistoryService;

	@Autowired
	private IssueTypeService issuetypeservice;

	@Autowired
	private IssueStatusHistoryService issueStatusHistoryService;

	@Autowired
	private KtService ktService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		

		if (userName.equalsIgnoreCase("admin") || userName.equalsIgnoreCase("abu")|| userName.equalsIgnoreCase("mujameel")
				|| userName.equalsIgnoreCase("ghouse")|| userName.equalsIgnoreCase("gerard")
				|| userName.equalsIgnoreCase("arif")|| userName.equalsIgnoreCase("alikhan")) {
			return "redirect:/dashboard";
		}
		
		else {
			List<Issue> listIssues = issueService.getfindByStatus("OPEN");

			model.addAttribute("listIssues", listIssues);

			return "bcshtml/openedIssues";
		}
		
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model) {

		return "bcshtml/dashboard";

	}

	@RequestMapping(value = "/allissues", method = RequestMethod.GET)
	public String createNewIssue(Model model) {
		List<Issue> listIssues = issueService.listAll();

		model.addAttribute("listIssues", listIssues);

		return "bcshtml/bcs_index";

	}

	@RequestMapping(value = "/openedIssues", method = RequestMethod.GET)
	public String openedIssues(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is " + userName);

		if (userName.equalsIgnoreCase("admin") || userName.equalsIgnoreCase("abu")|| userName.equalsIgnoreCase("mujameel")
				|| userName.equalsIgnoreCase("ghouse")|| userName.equalsIgnoreCase("gerard")
				|| userName.equalsIgnoreCase("arif")|| userName.equalsIgnoreCase("alikhan")) {
			List<Issue> listIssues = issueService.listAll();

			model.addAttribute("listIssues", listIssues);

			return "bcshtml/adminviewissuelist";
		} else {
			List<Issue> listIssues = issueService.getfindByStatus("OPEN");

			model.addAttribute("listIssues", listIssues);

			return "bcshtml/openedIssues";
		}

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchissues", method = RequestMethod.GET)
	public String todayIssues(@RequestParam("startDate") String startObj, @RequestParam("endDate") String endObj, Model model) throws ParseException {
		logger.info("in controller");
		logger.info(startObj);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = formatter.parse(startObj);
		Date endDate = formatter.parse(endObj);
		endDate.setHours(23);
		endDate.setMinutes(59);
		logger.info("Search from[" + startDate + "] to [" + endDate + "]");

		List<Issue> listIssues = issueService.getIssuesByCreateDate(startDate, endDate);

		model.addAttribute("listIssues", listIssues);

		return "bcshtml/bcs_index";

	}

	@RequestMapping(value = "/currentmonthissuelist", method = RequestMethod.GET)
	public String currentMonthIssue(Model model) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date monthStartDate = Converter.convertDateFromCalendar(calendar);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 45);
		cal.set(Calendar.SECOND, 0);
		Date endDate = Converter.convertDateFromCalendar(cal);
		logger.info("Search One Month[" + monthStartDate + "] to [" + endDate + "]");
		//List<Issue> listIssues = issueService.getfindByStatus("CURRENT");
		
		List<Issue> listIssues = issueService.getIssuesByCreateDate(monthStartDate, endDate);

		model.addAttribute("listIssues", listIssues);

		return "bcshtml/currentMonthIssueList";
	}

	@RequestMapping(value = "/adminviewissuelist", method = RequestMethod.GET)
	public String viewlistIssue(Model model) {
		
		List<Issue> listIssues = issueService.listAll();

		model.addAttribute("listIssues", listIssues);
		return "bcshtml/adminviewissuelist";
		
	}

	@RequestMapping(value = "/saveIssues", method = RequestMethod.POST)
	public String saveIssue(@ModelAttribute("issues") IssuesDto issueDto) throws ParseException {
		Date createDate = new Date();
		logger.info("About to save new issue with date time");

		String requestDate = issueDto.getRequestDate();
		String requestTimeString = issueDto.getRequestTime();

		Date newRequestDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(requestDate + " " + requestTimeString);
		logger.info("newRequestDateAndTime:" + newRequestDateAndTime);
		logger.info("date:" + requestDate + "timein" + requestTimeString);
		Issue issue = new Issue();
		//issue.setIssueNo(issueDto.getIssueNo());
		issue.setRequestDate(newRequestDateAndTime);
		issue.setApplication(issueDto.getApplication());
		issue.setAssignedTo(issueDto.getAssignedTo());
		issue.setClient(issueDto.getClient());
		issue.setDescription(issueDto.getDescription());
		issue.setEnvironment(issueDto.getEnvironment());
		issue.setResolution(issueDto.getResolution());
		issue.setStatus(issueDto.getIssueStatus());
		issue.setRequestor(issueDto.getRequestor());
		issue.setIssueTittle(issueDto.getIssueTittle());
		issue.setCreateDate(createDate);
		System.out.println("create date:" + createDate);
		Category category = new Category();
		category.setId(issueDto.getCategoryId());
		issue.setCategory(category);
		issue.setIssuetype(issueDto.getIssuetype());
		issue.setTimetaken(issueDto.getTimetaken());
		issue.setSeverity(issueDto.getSeverity());
		issue.setSupporttype(issueDto.getSupporttype());
		issueService.save(issue);

		return "redirect:/";

	}

	@RequestMapping("/newissues")
	public String viewAllIssues(Model model) {
		IssuesDto issueDto = new IssuesDto();
		
		/*DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String issueNo = (dateFormat.format(cal.getTime())).toString();
		issueNo = issueNo.replaceAll("/", "");
		issueNo = issueNo.replaceAll(":", "");
		issueNo = issueNo.replaceAll(" ", "/");
		issueNo = "I/" + issueNo;
		issueDto.setIssueNo(issueNo);
		Date createDate = new Date();
		issueDto.setCreateDate(createDate);*/
		
		List<User> listUsers = userListService.listAll();
		List<User> assignedToUsers = new ArrayList<User>();
		/*
		 * Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); if (principal instanceof
		 * UserDetails) { String username = ((UserDetails)principal).getUsername(); } else { String username =
		 * principal.toString(); }
		 */
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is " + userName);
		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				assignedToUsers.add(user);
				logger.info("test" + assignedToUsers);
			}
		}
		List<Category> listCategory = categoryService.listAll();
		List<IssueType> listissuetype = issuetypeservice.listAll();
		model.addAttribute("listissuetype", listissuetype);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("issueDto", issueDto);
		model.addAttribute("listUser", assignedToUsers);
		return "bcshtml/addIssues";
	}
	
	@RequestMapping(value = "/updateissues", method = RequestMethod.POST)
	public String updateIssue(@ModelAttribute("issues") IssuesDto issueDto) throws ParseException {

		Date createDate = new Date();
		String requestDate = issueDto.getRequestDate();
		logger.info("newRequestDat" + requestDate);
		// String requestTimeString = issueDto.getRequestTime();

		Date newRequestDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(requestDate);
		logger.info("newRequestDateAndTime:" + newRequestDateAndTime);
		// logger.info("date:"+requestDate+"timein"+requestTimeString);

		String stausdetails = issueDto.getIssueStatus();
		logger.info("issue status" + stausdetails);

		String resolvedDate = issueDto.getResolvedDate();
		logger.info("resolved date : " + resolvedDate);
		String resolvedTimeString = issueDto.getResolvedTime();
		logger.info("resolved time : " + resolvedTimeString);
		Date newResolvedDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(resolvedDate + " " + resolvedTimeString);
		logger.info("newResolvedDateAndTime:" + newResolvedDateAndTime);
		Issue editissue = new Issue();

		if (stausdetails.equals("CLOSED")) {
			editissue.setResolvedDate(newResolvedDateAndTime);
			editissue.setResolvedBy(issueDto.getResolvedBy());

			String timetaken = null;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			Date d1 = null;
			Date d2 = null;

			try {
				d1 = format.parse(requestDate);
				d2 = format.parse(resolvedDate + " " + resolvedTimeString);

				// in milliseconds
				long diff = d2.getTime() - d1.getTime();

				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);

				String sh = "", sm = "";
				if (diffHours < 10) {
					sh = "0" + diffHours;
				} else {
					sh = "" + diffHours;
				}
				if (diffMinutes < 10) {
					sm = "0" + diffMinutes;
				} else {
					sm = "" + diffMinutes;
				}

				if (diffDays > 0) {
					timetaken = diffDays + " D - " + sh + " H : " + sm + " M";
				} else {
					timetaken = sh + " H : " + sm + " M";
				}

				/*
				 * long diffSeconds = diff / 1000 % 60; long diffMinutes = diff / (60 * 1000) % 60; long diffHours = diff / (60 * 60 *
				 * 1000) % 24; long diffDays = diff / (24 * 60 * 60 * 1000);
				 * 
				 * timetaken = diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes";
				 */

			} catch (Exception e) {
				e.printStackTrace();
			}
			editissue.setTimetaken(timetaken);

		}

		List<User> supportTeam = userListService.findByUserNameIsNotAndDesignation(issueDto.getResolvedBy(), "SUPPORT");

		if (stausdetails.equals("CLOSEDKT")) {
			editissue.setResolvedDate(newResolvedDateAndTime);
			editissue.setResolvedBy(issueDto.getResolvedBy());
			String timetaken = null;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			Date d1 = null;
			Date d2 = null;

			try {
				d1 = format.parse(requestDate);
				d2 = format.parse(resolvedDate + " " + resolvedTimeString);

				// in milliseconds
				long diff = d2.getTime() - d1.getTime();

				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				String sh = "", sm = "";
				if (diffHours < 10) {
					sh = "0" + diffHours;
				} else {
					sh = "" + diffHours;
				}
				if (diffMinutes < 10) {
					sm = "0" + diffMinutes;
				} else {
					sm = "" + diffMinutes;
				}

				if (diffDays > 0) {
					timetaken = diffDays + " D - " + sh + " H : " + sm + " M";
				} else {
					timetaken = sh + " H : " + sm + " M";
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			editissue.setTimetaken(timetaken);
			for (User user : supportTeam) {
				Ktdetails kt = new Ktdetails(issueDto.getId(), issueDto.getResolvedBy(), issueDto.getIssueTittle(), issueDto.getResolution(),
						user.getUserName(), "", "Pending", newResolvedDateAndTime, "");
				ktService.save(kt);
			}

		}

		editissue.setCreateDate(createDate);
		//editissue.setIssueNo(issueDto.getIssueNo());
		
		editissue.setDescription(issueDto.getDescription());
		editissue.setResolution(issueDto.getResolution());
		editissue.setStatus(issueDto.getIssueStatus());
		editissue.setIssueTittle(issueDto.getIssueTittle());
		editissue.setApplication(issueDto.getApplication());

		editissue.setAssignedTo(issueDto.getAssignedTo());
		editissue.setClient(issueDto.getClient());
		editissue.setDescription(issueDto.getDescription());
		editissue.setRequestDate(newRequestDateAndTime);

		editissue.setEnvironment(issueDto.getEnvironment());
		editissue.setRequestor(issueDto.getRequestor());
		editissue.setIssuetype(issueDto.getIssuetype());
		editissue.setSeverity(issueDto.getSeverity());
		editissue.setSupporttype(issueDto.getSupporttype());

		Category category = new Category();
		category.setId(issueDto.getCategoryId());
		editissue.setCategory(category);
		editissue.setId(issueDto.getId());

		IssueStatusHistory issuestatus = new IssueStatusHistory(issueDto.getId(), issueDto.getResolvedBy(), newResolvedDateAndTime, issueDto.getResolution(),
				issueDto.getIssueStatus(), issueDto.getIssueTittle());

		issueService.save(editissue);
		issueStatusHistoryService.save(issuestatus);
		return "redirect:/";
	}

	@RequestMapping("/editissues/{id}")
	public ModelAndView editIssue(@PathVariable(name = "id") Long id) throws ParseException {

		Issue issue = issueService.get(id);
		issue.setId(id);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String requestedDate = dateFormat.format(issue.getRequestDate());
	        
		String resolvedDate = null;
		String resolvedTime = null;
		
		
			if (issue.getResolvedDate() != null) { 
				String resolvedDateTime = dateFormat.format(issue.getResolvedDate());
		        Date d = dateFormat.parse(resolvedDateTime);
		        DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		        DateFormat time = new SimpleDateFormat("HH:mm:ss");
		      	resolvedDate = date.format(d);
		       	resolvedTime = time.format(d);
			}
		
		IssuesDto issueDto = new IssuesDto(id, issue.getRequestor(), issue.getStatus(), issue.getCategory().getId(), issue.getDescription(),
				issue.getAssignedTo(), issue.getResolvedBy(), issue.getResolution(), issue.getApplication(), issue.getEnvironment(), issue.getClient(),
				requestedDate, resolvedDate,resolvedTime,issue.getIssueTittle(), issue.getCreateDate(), issue.getIssuetype(), issue.getTimetaken(), issue.getSeverity(),
				issue.getSupporttype());

		List<User> listUsers = userListService.listAll();

		List<User> resolvedByUsers = new ArrayList<User>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is " + userName);

		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				resolvedByUsers.add(user);
				logger.info("test" + resolvedByUsers);
			}
		}

		List<IssueStatusHistory> result = issueStatusHistoryService.search(id);
		List<IssueType> listissuetype = issuetypeservice.listAll();

		List<Category> listCategory = categoryService.listAll();

		ModelAndView modelAndView = new ModelAndView("bcshtml/editIssues");
		modelAndView.addObject("listCategory", listCategory);
		modelAndView.addObject("issueDto", issueDto);
		modelAndView.addObject("listUsers", resolvedByUsers);
		modelAndView.addObject("listissuetype", listissuetype);
		modelAndView.addObject("result", result);

		return modelAndView;
	}

	@RequestMapping("/addcomment/{id}")
	public ModelAndView addcomment(@PathVariable(name = "id") long id) {
		IssueHistory issueHistory = new IssueHistory();
		issueHistory.setId(issueHistory.getId());
		Issue issue = issueService.get(id);
		List<IssueHistory> listAllComments = issueHistoryService.getIssueId(issue);
		issueHistory.setIssue(issue);
		logger.info("Issue ID:" + listAllComments);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is " + userName);

		User user = userService.findByuserName(userName);
		issueHistory.setUser(user);
		ModelAndView modelAndView = new ModelAndView("bcshtml/addComment");
		modelAndView.addObject("issueHistory", issueHistory);
		modelAndView.addObject("listAllComments", listAllComments);
		return modelAndView;
	}

	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public String saveTheComment(@ModelAttribute("issueHistory") IssueHistory issueHistory) {
		logger.info("IssueHistory get issue Id" + issueHistory.getIssue().getId());
		Issue issue = issueService.get(issueHistory.getIssue().getId());
		IssueHistory toSave = new IssueHistory();
		Date today = new Date();
		logger.info("Today Date:" + today);
		toSave.setCommentDate(today);
		toSave.setIssue(issue);
		toSave.setComments(issueHistory.getComments());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is " + userName);
		User user = userService.findByuserName(userName);
		toSave.setUser(user);
		issueHistoryService.save(toSave);
		return "redirect:/";
	}

	@RequestMapping("/search4Report")
	public String search4Report(Model model) {
		Issue issueDto = new Issue();
		List<User> listUsers = userListService.listAll();
		List<User> assignedToUsers = new ArrayList<User>();
		for (User user : listUsers) {
			if (!user.getUserName().equals("admin")) {
				assignedToUsers.add(user);
				logger.info("test" + assignedToUsers);
			}
		}
		model.addAttribute("searchParam", issueDto);
		model.addAttribute("listUsers", assignedToUsers);
		return "bcshtml/dailyActivity";
	}

	/*
	 * @RequestMapping(value = "/viewhistory/{issueid}", method = RequestMethod.GET) public ModelAndView search(Model
	 * model,@PathVariable(name = "issueid") Long id) { List<IssueStatusHistory> result =
	 * issueStatusHistoryService.search(id);
	 * 
	 * ModelAndView modelAndView = new ModelAndView("bcshtml/");
	 * 
	 * modelAndView.addObject("result", result);
	 * 
	 * return modelAndView; }
	 * 
	 * 
	 * @RequestMapping(value = "{issueid}", method = RequestMethod.GET) public String getPermitHistory(Model
	 * model,@PathVariable(name = "issueid") Long id) { List<IssueStatusHistory> result =
	 * issueStatusHistoryService.search(id); logger.info("Issue id History " + result); model.addAttribute("result",
	 * result);
	 * 
	 * return "redirect:/";
	 * 
	 * }
	 */

	@RequestMapping(value = "/getIssueStatusHistory", method = RequestMethod.GET)
	public @ResponseBody List<IssueStatusHistory> getIssueStatusHistory(@RequestParam("issueId") String issueId) {
		List<IssueStatusHistory> result;
		long issueLongId = Long.parseLong(issueId);
		try {
			result = issueStatusHistoryService.findByIssueid(issueLongId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
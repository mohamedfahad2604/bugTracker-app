package com.bizz.customersupport.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bizz.customersupport.entities.DailyTask;
import com.bizz.customersupport.entities.Issue;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.DailyTaskService;
import com.bizz.customersupport.services.IssuesService;
import com.bizz.customersupport.services.UserListService;

@Controller
@RequestMapping(value = "/searchByDate")
public class ExcelController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	IssuesService issuesService;

	@Autowired
	private UserListService userListService;

	@Autowired
	private DailyTaskService dailyTaskService;

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/view/dailyActivity", method = RequestMethod.GET)
	public String viewBySearchDate(@RequestParam("startDate") String startObj, @RequestParam("endDate") String endObj,
			Model model) throws ParseException {

	    Date startDate = formatter.parse(startObj);
	    Date endDate = formatter.parse(endObj);

		List<Issue> issues = issuesService.getIssuesByCreateDate(startDate, endDate);

		model.addAttribute("issues", issues);

		return "bcshtml/dailyActivity";

	}

	@RequestMapping(value = "/view/dailyTaskList", method = RequestMethod.GET)
	public String adminViewDailyTaskList(@RequestParam("startDate") String startObj,
			@RequestParam("endDate") String endObj, @RequestParam("User") String user, Model model)
			throws ParseException {

		Date startDate = formatter.parse(startObj);
		Date endDate = formatter.parse(endObj);
		

		if (user.equals("AllUser")) {
			logger.info("Controller in :All user");

			List<DailyTask> listOfDailyTask = dailyTaskService.findByDateBetween(startDate, endDate);
			model.addAttribute("listOfDailyTask", listOfDailyTask);
		} else {

			logger.info("Controller in :" + user);
			User userName = userListService.findByUserName(user);
			// logger.info(listUsers);
			List<DailyTask> listOfDailyTask = dailyTaskService.findByDateBetweenAndUserName(startDate, endDate,
					userName);
			model.addAttribute("listOfDailyTask", listOfDailyTask);

		}
		List<User> listOfUsers = userListService.listAll();
		model.addAttribute("listUser", listOfUsers);

		return "bcshtml/adminViewDailyTaskList";
	}

}

package com.bizz.customersupport.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
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

import com.bizz.customersupport.entities.AdminTask;
import com.bizz.customersupport.entities.DailyTask;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.AdminTaskService;
import com.bizz.customersupport.services.DailyTaskService;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.util.Converter;
import com.bizz.customersupport.web.dto.DailyTaskDto;

@Controller
public class DailyTaskController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	private Converter converter;

	@Autowired
	private DailyTaskService dailyTaskService;

	@Autowired
	private AdminTaskService taskservice;

	@Autowired
	private UserListService userListService;

	@RequestMapping(value = "/addtask")
	public String addDailyTask(Model model) {
		DailyTaskDto dailyTaskDto = new DailyTaskDto(null, null, null, null, null, null);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date date = converter.convertDateFromCalendar(cal);

		List<User> listUsers = userListService.listAll();
		List<User> logedUserName = new ArrayList<User>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is " + userName);
		logger.info("logedUserName " + logedUserName);
		logger.info("logedUserName " + date);

		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				logedUserName.add(user);

			}
		}
		List<DailyTask> listOfDailyTask = dailyTaskService.findByDateAndUserName(date, logedUserName.get(0));

		List<AdminTask> listtask = taskservice.listOpenTask("CLOSED");

		model.addAttribute("listtask", listtask);

		logger.info("listOfDailyTask " + listOfDailyTask);
		model.addAttribute("listOfDailyTask", listOfDailyTask);

		model.addAttribute("dailyTask", dailyTaskDto);
		model.addAttribute("listUser", logedUserName);

		return "bcshtml/addDailyTask";
	}

	@RequestMapping("/deleteTask/{id}")
	public String addcomment(@PathVariable(name = "id") long id) {

		dailyTaskService.delete(id);

		return "redirect:/addtask?deletedTask";

	}

	@RequestMapping("/adminViewDailyTaskList")
	public String adminViewDailyTaskList(Model model) {
		List<User> listUsers = userListService.listAll();
		model.addAttribute("listUser", listUsers);
		return "bcshtml/adminViewDailyTaskList";
	}

	@RequestMapping(value = "/saveDailyTask", method = RequestMethod.POST)
	public String saveDailyTask(@ModelAttribute("dailyTask") DailyTaskDto dailyTaskDto) {
		DailyTask dailyTask = new DailyTask();
		String getHours = dailyTaskDto.getHours();
		logger.info("");
		String[] hours = getHours.split(":");
		int size = hours.length;
		String hour = null;
		String min = null;
		if (size == (2) || size == (3)) {
			hour = hours[0].concat(" hour ");

			min = hours[1].concat(" min");
		}

		else {
			hour = hours[0].concat(" hour ");
			min = "00".concat(" min");

		}
		Date date;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(dailyTaskDto.getDate());
			dailyTask.setDate(date);
			dailyTask.setHours(hour + min);
			dailyTask.setTask(dailyTaskDto.getTask());
			dailyTask.setBugNo(dailyTaskDto.getBugNo());
			dailyTask.setUserName(dailyTaskDto.getUserName());
			dailyTask.setTaskname(dailyTaskDto.getTaskname());

			dailyTaskService.save(dailyTask);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info(ExceptionUtils.getStackTrace(e));
		}

		// dailyTaskDto.setHours(hour + min);

		return "redirect:/addtask?addtask";
	}

}

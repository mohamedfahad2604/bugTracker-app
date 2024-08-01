package com.bizz.customersupport.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bizz.customersupport.entities.AdminTask;
import com.bizz.customersupport.entities.TaskType;
import com.bizz.customersupport.services.AdminTaskService;
import com.bizz.customersupport.services.TaskTypeService;

@Controller
public class AdminTaskController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminTaskService service;

	@Autowired
	private TaskTypeService typeservice;

	@RequestMapping("/admintaskviews")
	public String viewAdminTaskView(Model model) {
		List<AdminTask> listTaskService = service.listAll();
		model.addAttribute("listTaskService", listTaskService);

		return "bcshtml/listAdminTaskView";
	}

	@RequestMapping(value = "/addadmintask", method = RequestMethod.GET)
	public String addNewTask(Model model) {
		AdminTask task = new AdminTask();

		List<TaskType> listtasktype = typeservice.listAll();

		model.addAttribute("listtasktype", listtasktype);
		model.addAttribute("task", task);

		return "bcshtml/addAdminTask";
	}

	@RequestMapping(value = "/saveadmintask", method = RequestMethod.POST)
	public String saveTask(@ModelAttribute("task") AdminTask task) {

		service.save(task);

		return "redirect:/admintaskviews";
	}

	@RequestMapping(value = "/updateadmintask", method = RequestMethod.POST)
	public String updateTask(@ModelAttribute("task") AdminTask task) {
		logger.info("Task id of update Task" + task.getId());
		service.save(task);

		return "redirect:/admintaskviews";
	}

	@RequestMapping("/edittask/{id}")
	public ModelAndView editTask(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("bcshtml/editAdminTask");
		AdminTask task = service.get(id);
		task.setId(id);
		logger.info("Task id of editing Task" + task.getId());
		List<TaskType> listtasktype = typeservice.listAll();

		mav.addObject("listtasktype", listtasktype);
		mav.addObject("task", task);

		return mav;
	}

}

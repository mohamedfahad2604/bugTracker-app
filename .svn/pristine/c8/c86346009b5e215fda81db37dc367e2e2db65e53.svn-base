package com.bizz.customersupport.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bizz.customersupport.entities.TaskType;
import com.bizz.customersupport.services.TaskTypeService;

@Controller
public class TaskTypeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskTypeService service;

	@RequestMapping("/tasktypes")
	public String viewCategoryHome(Model model) {
		List<TaskType> listtasktype = service.listAll();
		model.addAttribute("listtasktype", listtasktype);

		return "bcshtml/listtasktype";
	}

}

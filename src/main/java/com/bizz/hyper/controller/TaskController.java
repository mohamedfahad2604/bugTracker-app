package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
	
	@RequestMapping("/tasksList")
	public String tasksList() {
		return "apps-tasks";
	}
	
	@RequestMapping("/tasksDetails")
	public String tasksDetails() {
		return "apps-tasks-details";
	}
	
	@RequestMapping("/tasksKanban")
	public String projectsAdd() {
		return "apps-kanban";
	}	 
	

}

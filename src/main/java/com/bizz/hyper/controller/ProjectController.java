package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

	@RequestMapping("/projectsList")
	public String projectsList() {
		return "apps-projects-list";
	}
	
	@RequestMapping("/projectsDetails")
	public String projectsDetails() {
		return "apps-projects-details";
	}
	
	@RequestMapping("/projectsGantt")
	public String projectsGantt() {
		return "apps-projects-gantt";
	}
	
	@RequestMapping("/projectsAdd")
	public String projectsAdd() {
		return "apps-projects-add";
	}
}

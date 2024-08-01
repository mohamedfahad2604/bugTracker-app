package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
	
	
	@RequestMapping("/dashboardAnalytics")
    public String dashboardAnalytics() {
        return "dashboard-analytics";
    }
	
	 @RequestMapping("/dashboardCrm")
     public String dashboardCrm() {
         return "dashboard-crm";
     }
	 
	 @RequestMapping("/dashboardProjects")
     public String dashboardProjects() {
         return "dashboard-projects";
     }

}

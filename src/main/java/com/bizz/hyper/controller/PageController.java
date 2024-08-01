package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/pagesStarter")
	public String pagesStarter() {
		return "pages-starter";
	}

	@RequestMapping("/pagesPreloader")
	public String pagesPreloader() {
		return "pages-preloader";
	}
	
	@RequestMapping("/profile")
	public String profile() {
		return "pages-profile";
	}
	
	@RequestMapping("/profile2")
	public String profile2() {
		return "pages-profile-2";
	}
	
	@RequestMapping("/invoice")
	public String invoice() {
		return "pages-invoice";
	}
	
	@RequestMapping("/faq")
	public String faq() {
		return "pages-faq";
	}
	
	@RequestMapping("/pricing")
	public String pricing() {
		return "pages-pricing";
	}
	
	@RequestMapping("/maintenance")
	public String maintenance() {
		return "pages-maintenance";
	}
	
	@RequestMapping("/timeline")
	public String timeline() {
		return "pages-timeline";
	}
	
	@RequestMapping("/landing")
	public String landing() {
		return "landing";
	}
	
}

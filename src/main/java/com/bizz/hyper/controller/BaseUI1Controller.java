package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseUI1Controller {
	
	@RequestMapping("/accordions")
	public String uiAccordions() {
		return "ui-accordions";
	}
	
	@RequestMapping("/alerts")
	public String alerts() {
		return "ui-alerts";
	}
	
	@RequestMapping("/avatars")
	public String avatars() {
		return "ui-avatars";
	}
	
	@RequestMapping("/badges")
	public String badges() {
		return "ui-badges";
	}
	
	@RequestMapping("/breadcrumb")
	public String breadcrumb() {
		return "ui-breadcrumb";
	}
	
	@RequestMapping("/buttons")
	public String buttons() {
		return "ui-buttons";
	}
	
	@RequestMapping("/cards")
	public String cards() {
		return "ui-cards";
	}
	
	@RequestMapping("/carousel")
	public String carousel() {
		return "ui-carousel";
	}
	
	@RequestMapping("/dropdowns")
	public String dropdowns() {
		return "ui-dropdowns";
	}
	
	@RequestMapping("/embedVideo")
	public String embedVideo() {
		return "ui-embed-video";
	}
	@RequestMapping("/grid")
	public String grid() {
		return "ui-grid";
	}

	@RequestMapping("/listGroup")
	public String listGroup() {
		return "ui-list-group";
	}
}

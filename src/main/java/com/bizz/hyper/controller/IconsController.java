package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IconsController {


	@RequestMapping("/dripicons")
	public String dripicons() {
		return "icons-dripicons";
	}
	
	@RequestMapping("/mdi")
	public String mdi() {
		return "icons-mdi";
	}
	
	@RequestMapping("/unicons")
	public String unicons() {
		return "icons-unicons";
	}

}

package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {
	
	@RequestMapping("/emailInbox")
	public String emailInbox() {
		return "apps-email-inbox";
	}
	
	@RequestMapping("/emailRead")
	public String emailRead() {
		return "apps-email-read";
	}

}

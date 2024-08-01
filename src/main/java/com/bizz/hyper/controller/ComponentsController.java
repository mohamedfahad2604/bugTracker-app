package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComponentsController {
	
	@RequestMapping("/widgets")
    public String widgets() {
        return "widgets";
    }

}

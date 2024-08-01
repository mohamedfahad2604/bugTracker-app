package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {
	
	@RequestMapping("/mapsGoogle")
	public String mapsGoogle() {
		return "maps-google";
	}
	
	@RequestMapping("/mapsVector")
	public String mapsVector() {
		return "maps-vector";
	}

}

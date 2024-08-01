package com.bizz.hyper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TablesController {
	
	@RequestMapping("/tablesBasic")
	public String tablesBasic() {
		return "tables-basic";
	}
	
	@RequestMapping("/tablesDatatable")
	public String tablesDatatable() {
		return "tables-datatable";
	}

}

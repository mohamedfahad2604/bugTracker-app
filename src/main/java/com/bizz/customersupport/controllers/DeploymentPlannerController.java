package com.bizz.customersupport.controllers;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bizz.customersupport.entities.DeploymentPlanner;
import com.bizz.customersupport.entities.Issue;
import com.bizz.customersupport.entities.IssueHistory;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.DeploymentPlannerService;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.web.dto.DeploymentPlannerDto;

@Controller
public class DeploymentPlannerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DeploymentPlannerService deploymentPlannerService;

	@Autowired
	private UserListService userListService;

	/*
	 * @RequestMapping("/deploymentPlanners") public String
	 * viewDeploymentPlannerHome(Model model) {
	 * 
	 * List<User> listUsers = userListService.listAll(); List<User> logedUserName =
	 * new ArrayList<User>();
	 * 
	 * Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); UserDetails
	 * userDetails = (UserDetails) authentication.getPrincipal(); String userName =
	 * userDetails.getUsername(); logger.info("Logged in username is {}", userName);
	 * logger.info("logedUserName {}", logedUserName);
	 * 
	 * for (User user : listUsers) { if (user.getUserName().equals(userName)) {
	 * logedUserName.add(user);
	 * 
	 * } }
	 * 
	 * List<DeploymentPlanner> listDeploymentPlanner =
	 * deploymentPlannerService.findByperformer(logedUserName.get(0).getUserName());
	 * 
	 * model.addAttribute("listDeploymentPlanner", listDeploymentPlanner);
	 * 
	 * return "bcshtml/listDeploymentPlanner"; }
	 */

	@RequestMapping("/deploymentPlanners")
	public String viewDeploymentPlannerHomeForAllUser(Model model) {

		List<DeploymentPlanner> listDeploymentPlanner = deploymentPlannerService.listAll();
		model.addAttribute("listDeploymentPlanner", listDeploymentPlanner);

		return "bcshtml/listDeploymentPlanner";
	}

	@RequestMapping(value = "/adminViewDeploymentPlannerList", method = RequestMethod.GET)
	public String viewlistDeploymentPlanner(Model model) {
		List<DeploymentPlanner> listDeploymentPlanners = deploymentPlannerService.listAll();

		model.addAttribute("listDeploymentPlanners", listDeploymentPlanners);

		return "bcshtml/adminViewDeploymentPlannerList";
	}

	@RequestMapping(value = "/addDeploymentPlanner", method = RequestMethod.GET)
	public String addNewDeploymentPlanner(Model model) {
		DeploymentPlannerDto deploymentPlannerDto = new DeploymentPlannerDto();
		List<User> listUsers = userListService.listAll();

		List<User> assignedToUsers = new ArrayList<User>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is{}", userName);

		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				assignedToUsers.add(user);
			}
		}
		
		List<User> listUsersForAuther = userListService.listAll();

		model.addAttribute("deploymentPlannerDto", deploymentPlannerDto);
		model.addAttribute("assignedToUsers", assignedToUsers);
		model.addAttribute("listUsersForAuther", listUsersForAuther);
		return "bcshtml/addDeploymentPlanner";
	}

	@RequestMapping(value = "/saveDeploymentPlanner", method = RequestMethod.POST)
	public String saveDeploymentPlanner(@ModelAttribute("deploymentPlanner") DeploymentPlannerDto deploymentPlannerDto)
			throws ParseException {

		String deploymentDate = deploymentPlannerDto.getDeploymentDate();
		logger.info("deploymentDate {}", deploymentDate);
		String deploymentHour = deploymentPlannerDto.getDeploymentHour();
		logger.info("deploymentHour {}", deploymentHour);

		Date deploymentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm")
				.parse(deploymentDate + " " + deploymentHour);
		logger.info("deploymentDateAndTime {}", deploymentDateAndTime);

		DeploymentPlanner deploymentPlanner = new DeploymentPlanner();
		deploymentPlanner.setApplication(deploymentPlannerDto.getApplication());
		deploymentPlanner.setAuther(deploymentPlannerDto.getAuther());
		deploymentPlanner.setDeployment(deploymentPlannerDto.getDeployment());
		deploymentPlanner.setDeploymentDate(deploymentDateAndTime);
		deploymentPlanner.setEnhancedFor(deploymentPlannerDto.getEnhancedFor());
		deploymentPlanner.setFileType(deploymentPlannerDto.getFileType());
		deploymentPlanner.setId(deploymentPlannerDto.getId());
		deploymentPlanner.setPerformer(deploymentPlannerDto.getPerformer());
		deploymentPlanner.setServerName(deploymentPlannerDto.getServerName());
		deploymentPlanner.setComment(deploymentPlannerDto.getComment());
		deploymentPlannerService.save(deploymentPlanner);

		return "redirect:/deploymentPlanners";
	}

	/*
	 * @RequestMapping("/deleteDeploymentPlanner/{id}") public String
	 * deleteDeploymentPlanner(@PathVariable(name = "id") int id) {
	 * deploymentPlannerService.delete(id); return "redirect:/deploymentPlanners"; }
	 */
	
	

}

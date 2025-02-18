package com.bizz.customersupport.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizz.customersupport.entities.ServerRequestLog;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.ServerRequestService;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.web.dto.ServerRequestLogDto;

@Controller
public class ServerRequestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ServerRequestService serverRequestLogService;

	@Autowired
	private UserListService userListService;

	/*
	 * @RequestMapping("/serverRequests") public String viewServerRequestHome(Model
	 * model) {
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
	 * List<ServerRequestLog> listServerRequest =
	 * serverRequestLogService.findByUserName(logedUserName.get(0).getUserName());
	 * 
	 * model.addAttribute("listServerRequest", listServerRequest);
	 * 
	 * return "bcshtml/listServerRequest"; }
	 */

	@RequestMapping("/serverRequests")
	public String viewServerRequestHomeForAllUser(Model model) {

		List<ServerRequestLog> listServerRequest = serverRequestLogService.listAll();
		model.addAttribute("listServerRequest", listServerRequest);

		return "bcshtml/listServerRequest";
	}

	@RequestMapping(value = "/adminViewServerRequestList", method = RequestMethod.GET)
	public String viewlistServerRequest(Model model) {
		List<ServerRequestLog> listServerRequestLogs = serverRequestLogService.listAll();

		model.addAttribute("listServerRequestLogs", listServerRequestLogs);

		return "bcshtml/adminViewServerRequestList";
	}

	@RequestMapping(value = "/addServerRequest", method = RequestMethod.GET)
	public String addNewServerRequest(Model model) {
		ServerRequestLogDto serverRequestDto = new ServerRequestLogDto();
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

		model.addAttribute("serverRequestDto", serverRequestDto);
		model.addAttribute("listUser", assignedToUsers);
		return "bcshtml/addServerRequest";
	}

	@RequestMapping(value = "/saveServerRequest", method = RequestMethod.POST)
	public String saveServerRequest(@ModelAttribute("serverRequest") ServerRequestLogDto serverRequestDto)
			throws ParseException {

		String checkInDate = serverRequestDto.getCheckIn();
		logger.info("checkInDate {}", checkInDate);
		String checkInHour = serverRequestDto.getCheckInHour();
		logger.info("checkInHour {}", checkInHour);

		String checkOutDate = serverRequestDto.getCheckOut();
		String checkOutHour = serverRequestDto.getCheckOutHour();

		Date checkInDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(checkInDate + " " + checkInHour);
		Date checkOutDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(checkOutDate + " " + checkOutHour);
		logger.info("checkInDateAndTime {}", checkInDateAndTime);
		logger.info("checkOutDateAndTime {}", checkOutDateAndTime);

		ServerRequestLog serverRequest = new ServerRequestLog();
		serverRequest.setCheckIn(checkInDateAndTime);
		serverRequest.setCheckOut(checkOutDateAndTime);
		serverRequest.setId(serverRequestDto.getId());
		serverRequest.setReason(serverRequestDto.getReason());
		serverRequest.setRequestMode(serverRequestDto.getRequestMode());
		serverRequest.setServerName(serverRequestDto.getServerName());
		serverRequest.setUserName(serverRequestDto.getUserName());
		serverRequest.setComment(serverRequestDto.getComment());
		serverRequestLogService.save(serverRequest);

		return "redirect:/serverRequests";
	}

	/*
	 * @RequestMapping("/deleteServerRequest/{id}") public String
	 * deleteServerRequest(@PathVariable(name = "id") int id) {
	 * serverRequestLogService.delete(id); return "redirect:/serverRequests"; }
	 */

}

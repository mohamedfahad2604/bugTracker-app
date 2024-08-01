package com.bizz.customersupport.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizz.customersupport.entities.Issue;

import com.bizz.customersupport.entities.Ktdetails;
import com.bizz.customersupport.entities.ServerRequestLog;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.services.KtService;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.services.UserService;

import com.bizz.customersupport.web.dto.KtDto;
import com.bizz.customersupport.web.dto.ServerRequestLogDto;

@Controller
public class KtController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KtService ktService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserListService userListService;

	@RequestMapping(value = "/viewkt", method = RequestMethod.GET)
	public String ktView(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		List<Ktdetails> listKt = ktService.findByktToAndStatus(userName, "Pending");

		model.addAttribute("listKt", listKt);

		return "bcshtml/viewkt";

	}

	@RequestMapping(value = "/viewcompletedkt", method = RequestMethod.GET)
	public String completedKtView(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		List<Ktdetails> listKt = ktService.findByktToAndStatus(userName, "Completed");

		model.addAttribute("listKt", listKt);

		return "bcshtml/completedkt";

	}

	@RequestMapping(value = "/givenkt", method = RequestMethod.GET)
	public String givenKt(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		List<Ktdetails> listKt = ktService.findByCreatedBy(userName);
		List<Ktdetails> resultList = new ArrayList<>();
		List<Long> addedIds = new ArrayList<>();
		for (Ktdetails ktdetails : listKt) {
			if (!addedIds.contains(ktdetails.getIssueid())) {
				resultList.add(ktdetails);
				addedIds.add(ktdetails.getIssueid());
			}
		}

		model.addAttribute("listKt", resultList);

		return "bcshtml/givenkt";

	}

	@RequestMapping(value = "/ktadminview", method = RequestMethod.GET)
	public String ktadminview(Model model) {

		List<Ktdetails> listKt = ktService.listAll();
		List<Ktdetails> resultList = new ArrayList<>();
		List<Long> addedIds = new ArrayList<>();
		for (Ktdetails ktdetails : listKt) {
			if (!addedIds.contains(ktdetails.getIssueid())) {
				resultList.add(ktdetails);
				addedIds.add(ktdetails.getIssueid());
			}
		}

		model.addAttribute("listKt", resultList);

		return "bcshtml/ktadminview";

	}

	@RequestMapping(value = "/createkt", method = RequestMethod.GET)
	public String createNewKt(Model model) {
		KtDto ktDto = new KtDto();
		List<User> listUsers = userListService.listAll();

		List<User> assignedToUsers = new ArrayList<User>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is{} ", userName);

		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				assignedToUsers.add(user);
			}
		}

		model.addAttribute("ktDto", ktDto);
		model.addAttribute("listUser", assignedToUsers);
		return "bcshtml/createkt";
	}
	
	@RequestMapping(value = "/updatekt", method = RequestMethod.POST)
	public String saveServerRequest(@ModelAttribute("kt") KtDto KtDto) throws ParseException {

		Date d1 = new Date();
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println(sdf.format(d1));
		String stringDate = sdf.format(d1);
		Date date2 = sdf.parse(stringDate);
		
		
		int min = 10;  
		int max = 99999;  
		int b = (int)(Math.random()*(max-min+1)+min);  
		
		
		List<User> listUsers = userListService.listAll();
		List<User> assignedToUsers = new ArrayList<User>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				assignedToUsers.add(user);
			}
		}

		List<User> supportTeam = userListService.findByUserNameIsNotAndDesignation(userName, "SUPPORT");
		for (User user : supportTeam) {
			Ktdetails kt = new Ktdetails(b, userName, KtDto.getKtTopic(), KtDto.getResolution(), user.getUserName(), "",
					"Pending", date2, "");
			ktService.save(kt);
		}
		
		

		return "redirect:/givenkt";
	}

	@RequestMapping("/editkt/{id}")
	public ModelAndView editKt(@PathVariable(name = "id") Long id) {

		Ktdetails kt = ktService.get(id);
		kt.setId(id);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String requestedDate = dateFormat.format(kt.getCreateDate());

		KtDto ktDto = new KtDto(kt.getId(), kt.getCreatedBy(), kt.getKtTopic(), kt.getResolution(), kt.getKtTo(),
				kt.getKtTimeTaken(), kt.getStatus(), requestedDate, kt.getKtTime());

		List<User> listUsers = userListService.listAll();

		List<User> resolvedByUsers = new ArrayList<User>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		logger.info("Logged in username is " + userName);

		for (User user : listUsers) {
			if (user.getUserName().equals(userName)) {
				resolvedByUsers.add(user);
				logger.info("test" + resolvedByUsers);
			}
		}

		ModelAndView modelAndView = new ModelAndView("bcshtml/editkt");

		modelAndView.addObject("ktDto", ktDto);
		modelAndView.addObject("listUsers", resolvedByUsers);

		return modelAndView;
	}

	@RequestMapping(value = "/acknowledgekt", method = RequestMethod.POST)
	public String acknowledgeKt(@ModelAttribute("ktDto") KtDto ktDto, Model model) throws ParseException {

		String ktDate = ktDto.getKtDate();
		String ktTime = ktDto.getKtTime();
		Date ktDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(ktDate + " " + ktTime);
		logger.info("ktDateAndTime:" + ktDateAndTime);
		logger.info("ktTimeTaken : KtID" + ktDto.getKtTimeTaken() + "" + ktDto.getId());

		Ktdetails kt = ktService.get(ktDto.getId());

		kt.setKtDate(ktDateAndTime);
		kt.setKtTime(ktDto.getKtTime());
		kt.setKtTimeTaken(ktDto.getKtTimeTaken());
		kt.setStatus("Completed");
		// kt.setId(ktDto.getId());
		ktService.save(kt);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userName = userDetails.getUsername();
		List<Ktdetails> listKt = ktService.findByktToAndStatus(userName, "Pending");

		model.addAttribute("listKt", listKt);

		return "bcshtml/viewkt";
	}

	@RequestMapping(value = "/getKtHistory", method = RequestMethod.GET)
	public @ResponseBody List<Ktdetails> getKtdetails(@RequestParam("issueId") String issueId) {
		List<Ktdetails> result;
		long issueLongId = Long.parseLong(issueId);
		try {
			result = ktService.findByIssueid(issueLongId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * @RequestMapping(value = "/saveKts", method = RequestMethod.POST) public
	 * String saveKt(@ModelAttribute("kts") KtDto ktDto) throws ParseException {
	 * Date createDate = new Date();
	 * logger.info("About to save new issue with date time");
	 * 
	 * String ktDate = ktDto.getKtDate();
	 * 
	 * 
	 * Date newKtDateAndTime = new SimpleDateFormat("MM/dd/yyyy HH:mm")
	 * .parse(ktDate); logger.info("newKtDateAndTime:" + newKtDateAndTime);
	 * 
	 * Ktdetails kt = new Ktdetails(); kt.setCreatedBy(ktDto.getCreatedBy());
	 * kt.setKtTopic(ktDto.getKtTopic()); kt.setKtDate(""+newKtDateAndTime);
	 * 
	 * kt.setDescription(ktDto.getDescription()); kt.setKtTo(ktDto.getKtTo());
	 * kt.setKtTimeTaken(ktDto.getKtTimeTaken()); kt.setCreateDate(createDate);
	 * kt.setKtfrom(ktDto.getKtFrom()); kt.setKtauthor(ktDto.getKtAuthor());
	 * kt.setKtstatus(ktDto.getKtStatus()); System.out.println("create date:" +
	 * createDate);
	 * 
	 * ktService.save(kt); return "redirect:/";
	 * 
	 * }
	 */
}

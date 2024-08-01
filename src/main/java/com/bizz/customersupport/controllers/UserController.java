package com.bizz.customersupport.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bizz.customersupport.entities.Role;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.repositories.RoleRepositories;
import com.bizz.customersupport.services.UserListService;
import com.bizz.customersupport.services.UserService;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserListService service;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepositories roleRepository;

	@RequestMapping("/listAllUsers")
	public String viewAllUsers(Model model) {
		List<User> listuser = service.listAll();
		model.addAttribute("listuser", listuser);
		return "bcshtml/listAllUsers";
	}

	@Valid
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String savaUser(@ModelAttribute("user") User user,@RequestParam("selectedRoles") List<Long> selectedRolesIds) {
		User existingUser  = service.get(user.getId());
		 if (existingUser != null) {
		        existingUser.setName(user.getName());
		        existingUser.setUserName(user.getUserName());
		        existingUser.setEmployeeNumber(user.getEmployeeNumber());
		        existingUser.setEmail(user.getEmail());
		        existingUser.setDesignation(user.getDesignation());

		        // Update roles for the user
		        List<Role> selectedRoles = roleRepository.findAllById(selectedRolesIds);
		        existingUser.setRoles(new HashSet<>(selectedRoles));

		        // Save the updated user
		        userService.editAndUpdate(user, selectedRolesIds);
		        logger.info("User updated successfully.");
		    } else {
		        logger.error("User not found with ID: " + user.getId());
		    }
		return "redirect:/listAllUsers";
	}

	@RequestMapping("/editUser/{id}")
	public ModelAndView showEditBusPage(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("bcshtml/editUser");
		User user = service.get(id);
		user.setId(id);
		 List<Role> allRoles = roleRepository.findAll();
		    Collection<Role> userRoles = user.getRoles(); 
		    mav.addObject("user", user);
		    mav.addObject("allRoles", allRoles); 
		    mav.addObject("userRoles", userRoles);
		return mav;
	}

}

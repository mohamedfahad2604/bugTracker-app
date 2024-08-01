package com.bizz.customersupport.controllers;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizz.customersupport.entities.Role;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.repositories.RoleRepositories;
import com.bizz.customersupport.services.UserService;
import com.bizz.customersupport.web.dto.UserRegistrationDto;
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Valid
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepositories roleRepository;

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
        List<Role> allRoles = roleRepository.findAll();
        model.addAttribute("allRoles", allRoles);
		return "bcshtml/userRegistration";
	}
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
			BindingResult result, Model model) {
		User existing = userService.findByuserName(userDto.getUserName());
		
		if (existing != null) {
			result.rejectValue("userName", null, "There is already an account registered with that userName");
		}
		if (result.hasErrors()) {
	        return "bcshtml/userRegistration"; 
	    }

		userService.save(userDto, userDto.getSelectedRoles());

	    return "redirect:/listAllUsers"; 
	}
}
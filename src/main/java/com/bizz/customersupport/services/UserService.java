package com.bizz.customersupport.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

	User findByuserName(String userName);

	User save(UserRegistrationDto registration, List<Long> selectedRolesIds);

	//User editAndUpdate(User user);

	User editAndUpdate(User user, List<Long> selectedRolesIds);


}
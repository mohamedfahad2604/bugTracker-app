package com.bizz.customersupport.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bizz.customersupport.entities.Role;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.repositories.RoleRepositories;
import com.bizz.customersupport.repositories.UserRepositories;
import com.bizz.customersupport.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepositories userRepository;

	@Autowired
	private RoleRepositories roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findByuserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public User save(UserRegistrationDto registration, List<Long> selectedRolesIds) {

		logger.info("Going to save the user....");
		User user = new User();
		user.setName(registration.getName());
		user.setUserName(registration.getUserName());
		user.setEmployeeNumber(registration.getEmployeeNumber());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setDesignation(registration.getDesignation());

		Set<Role> selectedRoles = roleRepository.findAllById(selectedRolesIds)
                .stream()
                .collect(Collectors.toSet());

        user.setRoles(selectedRoles);
        return userRepository.save(user);
	}

	public User editAndUpdate(User user, List<Long> selectedRolesIds) {

		logger.info("user id is" + user.getId());

		user.setName(user.getName());
		user.setUserName(user.getUserName());
		user.setEmployeeNumber(user.getEmployeeNumber());
		user.setEmail(user.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setDesignation(user.getDesignation());

		Set<Role> selectedRoles = roleRepository.findAllById(selectedRolesIds)
                .stream()
                .collect(Collectors.toSet());

			user.setRoles(selectedRoles);

	        return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);

		if (user == null || user.isState()) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					mapRolesToAuthorities(user.getRoles()));

		} else {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}


}
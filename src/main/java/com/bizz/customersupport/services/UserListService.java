package com.bizz.customersupport.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.repositories.UserRepositories;

@Service
@Transactional
public class UserListService {
	@Autowired
	private UserRepositories repo;

	public List<User> listAll() {
		return repo.findAll();
	}
	
	public List<User> listAllIsActive() {
		
		
		List<User> userListDB = repo.findByStateTrue();
		List<User> devAndSuppoertUserList = new ArrayList<>();
		for (User user : userListDB) {
			if(user.getDesignation().equals("MANAGER") || user.getDesignation().equals("ADMIN")||user.getDesignation().equals("OTHERS")) {
				
			}else {
				devAndSuppoertUserList.add(user);
			}
		}
		return devAndSuppoertUserList;
	}

	public void save(User user) {
		repo.save(user);
	}

	public User get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public User findByUserName(String userName) {
		return repo.findByUserName(userName);
	}
	
	public List<User>  findByUserNameIsNotAndDesignation(String excludeUser,String designation){
		return repo.findByUserNameIsNotAndDesignation(excludeUser,designation);
	}
	
	public List<User> getAllDevAndSupoortUserList() {
		List<User> userListDB = repo.findByStateTrue();
		List<User> devAndSuppoertUserList = new ArrayList<>();
		for (User user : userListDB) {
			if(user.getDesignation().equals("MANAGER") || user.getDesignation().equals("ADMIN")) {
				
			}else {
				devAndSuppoertUserList.add(user);
			}
		}
		return devAndSuppoertUserList;
	}

}

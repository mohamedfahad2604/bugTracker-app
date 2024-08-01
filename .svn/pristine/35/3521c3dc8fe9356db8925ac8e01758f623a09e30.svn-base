package com.bizz.customersupport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.User;

@Repository
public interface UserRepositories extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	public List<User> findByStateTrue();
	public List<User> findByUserNameIsNotAndDesignation(String excludeUser,String designation);

}
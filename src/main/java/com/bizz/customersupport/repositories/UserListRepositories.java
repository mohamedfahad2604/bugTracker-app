package com.bizz.customersupport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.User;

@Repository
public interface UserListRepositories extends JpaRepository<User, Long> {

}

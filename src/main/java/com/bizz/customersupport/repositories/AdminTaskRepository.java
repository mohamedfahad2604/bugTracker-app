package com.bizz.customersupport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.AdminTask;
import com.bizz.customersupport.entities.Issue;

@Repository
public interface AdminTaskRepository extends JpaRepository<AdminTask, Long> {
	
	public List<AdminTask> findByTaskstatusIsNot(String status);

}

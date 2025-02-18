package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.AdminTask;
import com.bizz.customersupport.repositories.AdminTaskRepository;

@Service
@Transactional
public class AdminTaskService {
	@Autowired
	private AdminTaskRepository repo;

	public List<AdminTask> listAll() {
		return repo.findAll();
	}

	public void save(AdminTask admintask) {
		repo.save(admintask);
	}

	public AdminTask get(long id) {
		return repo.findById(id).get();
	}
	
	public List<AdminTask> listOpenTask(String status) {
		return repo.findByTaskstatusIsNot(status);
	}

}

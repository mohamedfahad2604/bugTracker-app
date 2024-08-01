package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.TaskType;
import com.bizz.customersupport.repositories.TaskTypeRepository;

@Service
@Transactional
public class TaskTypeService {
	@Autowired
	private TaskTypeRepository repo;

	public List<TaskType> listAll() {
		return repo.findAll();
	}

	public TaskType get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

}

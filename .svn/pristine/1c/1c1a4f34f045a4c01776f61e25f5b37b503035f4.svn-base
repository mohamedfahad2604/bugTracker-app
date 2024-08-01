package com.bizz.customersupport.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.DailyTask;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.repositories.DailyTaskRepository;

@Service
@Transactional
public class DailyTaskService {
	@Autowired
	private DailyTaskRepository repo;

	public List<DailyTask> listAll() {
		return repo.findAll();
	}

	public void save(DailyTask dailyTask) {
		repo.save(dailyTask);
	}

	public DailyTask get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<DailyTask> findByDate(Date date) {
		return repo.findByDate(date);
	}

	public List<DailyTask> findByDateAndUserName(Date date, User userName) {
		return repo.findByDateAndUserName(date, userName);
	}

	public List<DailyTask> findByDateBetween(Date startDate, Date endDate) {
		return repo.findByDateBetween(startDate, endDate);
	}

	public List<DailyTask> findByDateBetweenAndUserName(Date startDate, Date endDate, User listUsers) {
		return repo.findByDateBetweenAndUserName(startDate, endDate, listUsers);
	}

}

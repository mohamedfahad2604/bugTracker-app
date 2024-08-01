package com.bizz.customersupport.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.Issue;
import com.bizz.customersupport.repositories.IssuesRepositories;

@Service
@Transactional
public class IssuesService {

	@Autowired
	private IssuesRepositories repo;

	public List<Issue> listAll() {
		return repo.findAll();
	}

	public void save(Issue issue) {
		repo.save(issue);
	}

	public Issue get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
	public List<Issue> getIssuesByCreateDate(Date startDate, Date endDate) {
		return repo.findByCreateDateBetween(startDate, endDate);
	}
	
	public int  countByCreateDate(Date startDate, Date endDate) {
		return repo.countByCreateDateBetween(startDate, endDate);
		
	}

	public List<Issue> getfindByStatus(String status) {
		return repo.findByStatus(status);
	
	}
	public List<Issue> getfindByStatusIsNot(String status) {
		return repo.findByStatusIsNot(status);
	
	}
	
	
	
	
	
	
	
}

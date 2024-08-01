package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.Issue;
import com.bizz.customersupport.entities.IssueHistory;
import com.bizz.customersupport.repositories.IssueHistoryRepositories;

@Service
@Transactional
public class IssueHistoryService {

	@Autowired
	private IssueHistoryRepositories repo;

	public List<IssueHistory> listAll() {
		return repo.findAll();
	}

	public void save(IssueHistory issueHistory) {
		repo.save(issueHistory);
	}

	public IssueHistory get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<IssueHistory> getIssueId(Issue issueId) {
		return repo.findByIssue(issueId);
	}

}

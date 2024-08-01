package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.Issue;
import com.bizz.customersupport.entities.IssueStatusHistory;
import com.bizz.customersupport.repositories.IssueStatusHistoryRepository;


@Service
@Transactional
public class IssueStatusHistoryService {
	
	

		@Autowired
		private IssueStatusHistoryRepository repo;

		public List<IssueStatusHistory> listAll() {
			return repo.findAll();
		}

		public void save(IssueStatusHistory issueHistory) {
			repo.save(issueHistory);
		}

		public IssueStatusHistory get(long id) {
			return repo.findById(id).get();
		}

		public void delete(long id) {
			repo.deleteById(id);
		}

		public List<IssueStatusHistory> search(long id) {
			return repo.search(id);
		}
		
		public List<IssueStatusHistory> findByIssueid(long id){
			return repo.findByIssueid(id);
		}
		
		
		

}

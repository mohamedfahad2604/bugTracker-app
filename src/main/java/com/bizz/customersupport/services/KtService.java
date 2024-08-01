package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.IssueStatusHistory;
import com.bizz.customersupport.entities.Ktdetails;
import com.bizz.customersupport.repositories.KtRepository;
@Service
@Transactional
public class KtService {
	@Autowired
	private KtRepository repo;

	public List<Ktdetails> listAll() {
		return repo.findAll();
	}

	public void save(Ktdetails kt) {
		repo.save(kt);
		
	}

	public Ktdetails get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public List<Ktdetails> findByktToAndStatus(String ktTo,String status){
		return repo.findByktToAndStatus(ktTo,status);
	}
	
	public List<Ktdetails> findByCreatedBy(String createdBy){
		return repo.findByCreatedBy(createdBy);
	}
	
	public List<Ktdetails> findByIssueid(long id){
		return repo.findByIssueid(id);
	}
	
	
	
}

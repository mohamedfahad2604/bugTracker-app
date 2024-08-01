package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.IssueType;
import com.bizz.customersupport.repositories.IssueTypeRepository;

@Service
@Transactional
public class IssueTypeService {
	@Autowired
	private IssueTypeRepository repo;

	public List<IssueType> listAll() {
		return repo.findAll();
	}

	public IssueType get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

}

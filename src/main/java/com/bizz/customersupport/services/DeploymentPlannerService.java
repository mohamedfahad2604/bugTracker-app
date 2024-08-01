package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.DeploymentPlanner;
import com.bizz.customersupport.repositories.DeploymentLoggerRepository;

@Service
@Transactional
public class DeploymentPlannerService {

	@Autowired
	private DeploymentLoggerRepository repo;

	public List<DeploymentPlanner> listAll() {
		return repo.findAll();
	}

	public void save(DeploymentPlanner serverRequest) {
		repo.save(serverRequest);
	}

	public DeploymentPlanner getId(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

}

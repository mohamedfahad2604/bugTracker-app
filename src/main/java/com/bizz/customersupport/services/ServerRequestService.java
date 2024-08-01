package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.ServerRequestLog;
import com.bizz.customersupport.repositories.ServerRequestRepository;

@Service
@Transactional
public class ServerRequestService {

	@Autowired
	private ServerRequestRepository repo;

	public List<ServerRequestLog> listAll() {
		return repo.findAll();
	}

	public void save(ServerRequestLog serverRequest) {
		repo.save(serverRequest);
	}

	public ServerRequestLog getId(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

}

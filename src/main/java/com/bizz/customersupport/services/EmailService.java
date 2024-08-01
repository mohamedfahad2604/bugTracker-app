package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.EmailAddresses;
import com.bizz.customersupport.repositories.EmailAddressesRepository;

@Service
@Transactional
public class EmailService {
	@Autowired
	private EmailAddressesRepository repo;

	public List<EmailAddresses> listAll() {
		return repo.findAll();
	}

	public void save(EmailAddresses Email) {
		repo.save(Email);
	}

	public EmailAddresses getEmailAddresses(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public EmailAddresses getByTitle(String title) {
		return repo.findByTitle(title);
	}

}
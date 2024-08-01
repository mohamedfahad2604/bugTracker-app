package com.bizz.customersupport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.EmailAddresses;

@Repository
public interface EmailAddressesRepository extends JpaRepository<EmailAddresses, Long> {

	EmailAddresses findByTitle(String title);

}

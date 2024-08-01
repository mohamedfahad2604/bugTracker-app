package com.bizz.customersupport.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.IssueStatusHistory;
import com.bizz.customersupport.entities.Ktdetails;

@Repository
public interface KtRepository extends JpaRepository<Ktdetails, Long> {
	
	
	
	public List<Ktdetails> findByktToAndStatus(String ktTo,String status);
	public List<Ktdetails> findByCreatedBy(String createdBy);
	public List<Ktdetails> findByIssueid(long id);
}

package com.bizz.customersupport.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.Issue;

@Repository
public interface IssuesRepositories extends JpaRepository<Issue, Long> {
	public List<Issue> findByCreateDateBetween(Date startDate, Date endDate);

	public int countByCreateDateBetween(Date startDate, Date endDate);

	public List<Issue> findByStatus(String status);

	public List<Issue> findByStatusIsNot(String status);

}

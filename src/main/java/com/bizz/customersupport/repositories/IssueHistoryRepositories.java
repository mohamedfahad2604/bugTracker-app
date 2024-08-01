package com.bizz.customersupport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.Issue;
import com.bizz.customersupport.entities.IssueHistory;

@Repository
public interface IssueHistoryRepositories extends JpaRepository<IssueHistory, Long> {

	public List<IssueHistory> findByIssue(Issue issueId);

}

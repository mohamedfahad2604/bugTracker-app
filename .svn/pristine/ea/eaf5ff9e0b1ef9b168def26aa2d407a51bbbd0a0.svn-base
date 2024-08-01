package com.bizz.customersupport.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.IssueStatusHistory;


 
@Repository
public interface IssueStatusHistoryRepository extends JpaRepository<IssueStatusHistory, Long> {

	public static List<IssueStatusHistory> findByIssue(Long issueid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Query(value = "SELECT * FROM issue_audit_history WHERE issue_id = ?1", nativeQuery = true)
	public List<IssueStatusHistory> search(@Param("issueid")Long issue_id);
	
	public List<IssueStatusHistory> findByIssueid(long id);
}


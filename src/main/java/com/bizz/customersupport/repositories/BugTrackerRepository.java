package com.bizz.customersupport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.BugTracker;
import com.bizz.customersupport.entities.User;


@Repository
public interface BugTrackerRepository  extends JpaRepository<BugTracker, Long> {
	
	List<BugTracker> findByAuthor(User author);
	List<BugTracker> findByAssignee(User assignee);
	List<BugTracker> findByAssigneeOrAuthor(User assignee, User author);

}

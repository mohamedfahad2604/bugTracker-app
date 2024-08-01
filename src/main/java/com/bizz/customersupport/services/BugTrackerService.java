package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.BugTracker;
import com.bizz.customersupport.entities.User;
import com.bizz.customersupport.repositories.BugTrackerRepository;

@Service
@Transactional
public class BugTrackerService {
	@Autowired
	private BugTrackerRepository repo;

	public List<BugTracker> listAll() {
		return repo.findAll();
	}

	public void save(BugTracker BugTracker) {
		repo.save(BugTracker);
	}

	public BugTracker get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<BugTracker> findByAuthor(User author) {

		return repo.findByAuthor(author);
	}

	public List<BugTracker> findByAssignee(User assignee) {

		return repo.findByAssignee(assignee);
	}

	public List<BugTracker> findByAssigneeOrAuthor(User assignee, User author) {
		return repo.findByAssigneeOrAuthor(assignee, author);
	}
}

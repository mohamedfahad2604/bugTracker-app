package com.bizz.customersupport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizz.customersupport.entities.Category;
import com.bizz.customersupport.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private CategoryRepository repo;

	public List<Category> listAll() {
		return repo.findAll();
	}

	public void save(Category category) {
		repo.save(category);
	}

	public Category get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<Category> getCategoryName(Category category) {
		return repo.getCategoryBycategoryName(category);
	}

}

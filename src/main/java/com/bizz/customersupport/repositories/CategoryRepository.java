package com.bizz.customersupport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizz.customersupport.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	public List<Category> getCategoryBycategoryName(Category categoryName);

}

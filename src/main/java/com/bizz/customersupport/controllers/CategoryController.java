package com.bizz.customersupport.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bizz.customersupport.entities.Category;
import com.bizz.customersupport.services.CategoryService;

@Controller
public class CategoryController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategoryService service;

	@RequestMapping("/categories")
	public String viewCategoryHome(Model model) {
		List<Category> listCategory = service.listAll();
		model.addAttribute("listCategory", listCategory);

		return "bcshtml/listCategory";
	}

	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public String addNewCategory(Model model) {
		Category category = new Category();

		model.addAttribute("category", category);

		return "bcshtml/addCategory";
	}

	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute("category") Category category) {
		service.save(category);

		return "redirect:/categories";
	}

	@RequestMapping(value = "/updatecategory", method = RequestMethod.POST)
	public String updateCategory(@ModelAttribute("category") Category category) {
		logger.info("Category id of update category" + category.getId());
		service.save(category);

		return "redirect:/categories";
	}

	@RequestMapping("/editcategory/{id}")
	public ModelAndView showEditCategoryPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("bcshtml/editcategory");
		Category category = service.get(id);
		category.setId(id);
		logger.info("Category id of editing category" + category.getId());
		mav.addObject("category", category);

		return mav;
	}

	@RequestMapping("/deletecategory/{id}")
	public String deleteCategory(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/categories";
	}

}

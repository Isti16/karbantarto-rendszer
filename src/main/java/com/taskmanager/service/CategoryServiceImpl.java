package com.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.model.Category;
import com.taskmanager.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService {

	 private CategoryRepository categoryRepository;

	    @Autowired
	    public CategoryServiceImpl(CategoryRepository catRepository) {
	        this.categoryRepository = catRepository;
	    }

	    @Override
	    public void createCategory(Category category) {
	        categoryRepository.save(category); 
	    }

	    @Override
	    public List<Category> findAll() {
	        return categoryRepository.findAll();
	    }
	
	  
}
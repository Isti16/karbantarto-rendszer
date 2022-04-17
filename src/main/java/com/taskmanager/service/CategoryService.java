package com.taskmanager.service;
import java.util.List;

import com.taskmanager.model.Category;

public interface CategoryService {

	void createCategory(Category category);
	
	List<Category> findAll();
}

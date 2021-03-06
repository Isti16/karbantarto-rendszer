package com.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.model.Category;
import com.taskmanager.service.CategoryService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private UserService userService;
    private TaskService taskService;

    @Autowired
    public CategoryController( CategoryService categoryService, UserService userService, TaskService taskService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/category/create")
    public String showEmptyTaskForm(Model model, SecurityContextHolderAwareRequestWrapper request) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "forms/category-new";
        
    }

    @PostMapping("/category/create")
    public String createCategory(@Valid Category category, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            return "forms/category-new";
        }
        categoryService.createCategory(category);
       
        return "redirect:/profile";
    }
}

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
public class UserController {

    private CategoryService categoryService;
    private UserService userService;
    private TaskService taskService;

    @Autowired
    public UserController( CategoryService categoryService, UserService userService, TaskService taskService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/user/create")
    public String showEmptyTaskForm(Model model,SecurityContextHolderAwareRequestWrapper request) {
        User user = new User();
        model.addAttribute("user", user);
        return "forms/user-new";
        
    }

    @PostMapping("/user/create")
    public String createCategory(@Valid User user, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            return "forms/user-new";
        }
        userService.createUser(user);
       
        return "redirect:/profile";
    }


}

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
import com.taskmanager.model.Education;
import com.taskmanager.service.EducationService;
import com.taskmanager.service.CategoryService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class EducationController {

  
    private EducationService educationService;

    @Autowired
    public EducationController(   EducationService educationService) {
      
        this.educationService = educationService;
    }

    @GetMapping("/education/create")
    public String showEmptyTaskForm(Model model,SecurityContextHolderAwareRequestWrapper request) {
        Education education = new Education();
        model.addAttribute("education", education);
        return "forms/education-new";
        
    }

    @PostMapping("/education/create")
    public String createEducation(@Valid Education education, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            return "forms/education-new";
        }
        educationService.createEducation(education);
       
        return "redirect:/profile";
    }

}

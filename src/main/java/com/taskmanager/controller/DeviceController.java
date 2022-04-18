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
import com.taskmanager.model.Device;
import com.taskmanager.model.Category;
import com.taskmanager.model.Education;
import com.taskmanager.service.EducationService;
import com.taskmanager.service.CategoryService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;
import com.taskmanager.service.DeviceService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class DeviceController {

    private CategoryService categoryService;
    private UserService userService;
    private TaskService taskService;
    private EducationService educationService;
    private DeviceService deviceService;

    @Autowired
    public DeviceController( CategoryService categoryService, UserService userService, TaskService taskService,  EducationService educationService, DeviceService deviceService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.taskService = taskService;
        this.educationService = educationService;
        this.deviceService = deviceService;
    }

    @GetMapping("/device/create")
    public String showEmptyTaskForm(Model model,SecurityContextHolderAwareRequestWrapper request) {
        Device device = new Device();
        model.addAttribute("device", device);
        return "forms/device-new";
        
    }

    @PostMapping("/device/create")
    public String createDevice(@Valid Device device, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            return "forms/device-new";
        }
        deviceService.createDevice(device);
       
        return "redirect:/profile";
    }

}

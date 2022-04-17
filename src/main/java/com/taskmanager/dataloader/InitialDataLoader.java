package com.taskmanager.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.taskmanager.model.Education;
import com.taskmanager.model.Role;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.service.EducationService;
import com.taskmanager.service.RoleService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
 
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private TaskService taskService;
    private RoleService roleService;
    private EducationService educationService;
    private final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value("${default.admin.mail}")
    private String defaultAdminMail;
    @Value("${default.admin.name}")
    private String defaultAdminName;
    @Value("${default.admin.password}")
    private String defaultAdminPassword;

    @Autowired
    public InitialDataLoader(UserService userService, TaskService taskService, RoleService roleService,EducationService eduserv) {
        this.userService = userService;
        this.taskService = taskService;
        this.roleService = roleService;
        this.educationService=eduserv;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //ROLES --------------------------------------------------------------------------------------------------------
        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.findAll().stream().map(role -> "saved role: " + role.getRole()).forEach(logger::info);
        //EDUCATION---------------------------------
        
        educationService.createEducation(new Education("Villanyszerelő"));
        educationService.createEducation(new Education("Rendszergazda"));
        //USERS --------------------------------------------------------------------------------------------------------
        //1
        User admin = new User(
                defaultAdminMail,
                defaultAdminName,
                defaultAdminPassword
                );
        userService.createUser(admin);
        userService.changeRoleToAdmin(admin);
        

        //2
        User manager = new User(
                "manager@mail.com",
                "Manager",
                "112233");
        userService.createUser(manager);
        userService.changeRoleToAdmin(manager);

        //3
       User user1=new User(
               "mark@mail.com",
               "Mark",
               "112233"); 
    	userService.createUser(user1);
    	userService.changeEducation(user1);
    		   

        //4
        userService.createUser(new User(
                "ann@mail.com",
                "Anna",
                "112233"));

        userService.findAll().stream()
                .map(u -> "saved user: " + u.getName())
                .forEach(logger::info);


        //TASKS --------------------------------------------------------------------------------------------------------

        LocalDate today = LocalDate.now();

        //1
        taskService.createTask(new Task(
                "poroltó",
                "leesett a falról",
                today.minusDays(40),
                true,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("manager@mail.com")
        ));

        //2
        taskService.createTask(new Task(
                "számítógép ",
                "nem kapcsol be a számítógép",
                today.minusDays(30),
                true,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("manager@mail.com")
        ));

      
        taskService.findAll().stream().map(t -> "saved task: '" + t.getName()
                + "' for owner: " + getOwnerNameOrNoOwner(t)).forEach(logger::info);
    }

    private String getOwnerNameOrNoOwner(Task task) {
        return task.getOwner() == null ? "no owner" : task.getOwner().getName();
    }
}

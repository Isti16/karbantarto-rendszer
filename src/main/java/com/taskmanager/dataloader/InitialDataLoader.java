package com.taskmanager.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.taskmanager.model.Device;
import com.taskmanager.model.Education;
import com.taskmanager.model.Role;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.model.Category;
import com.taskmanager.service.EducationService;
import com.taskmanager.service.RoleService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;
import com.taskmanager.service.DeviceService;
import com.taskmanager.service.CategoryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
 
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private TaskService taskService;
    private RoleService roleService;
    private EducationService educationService;
    private DeviceService deviceService;
    private CategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value("${default.admin.mail}")
    private String defaultAdminMail;
    @Value("${default.admin.name}")
    private String defaultAdminName;
    @Value("${default.admin.password}")
    private String defaultAdminPassword;

    @Autowired
    public InitialDataLoader(UserService userService, TaskService taskService, RoleService roleService,EducationService eduserv, DeviceService deviceService, CategoryService categoryService) {
        this.userService = userService;
        this.taskService = taskService;
        this.roleService = roleService;
        this.educationService=eduserv;
        this.deviceService = deviceService;
        this.categoryService = categoryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //ROLES --------------------------------------------------------------------------------------------------------
        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.findAll().stream().map(role -> "saved role: " + role.getRole()).forEach(logger::info);
        //EDUCATION---------------------------------
        
        educationService.createEducation(new Education("Villanyszerelo"));
        educationService.createEducation(new Education("Rendszergazda"));
//Category--------------------------------------
    	
    	categoryService.createCategory(new Category("Porolto","3 ??ra","3 h??nap","hiba"));
        
        //Device----------------------------------------------------------
    	
    	deviceService.createDevice(new Device("DH127L","Porolto1","Veszpr??m egyetem u 10"));
    	
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
                "porolt??",
                "Leszakadt a falr??l a porolto",
                today.minusDays(-40),
                true,
                userService.getUserByEmail("admin@mail.com").getName(),
                userService.getUserByEmail("manager@mail.com"),
                "Befejezve"
        ));

        //2
        taskService.createTask(new Task(
                "sz??m??t??g??p ",
                "Nem kapcsol be a sz??m??t??g??p",
                today.minusDays(-15),
                false,
                userService.getUserByEmail("admin@mail.com").getName(),
                userService.getUserByEmail("mark@mail.com"),
                "Folyamatban"
        ));

        
      //3
        taskService.createTask(new Task(
                "T??zjelz?? ",
                "Ellen??rz??s",
                today.minusDays(-30),
                false,
                userService.getUserByEmail("admin@mail.com").getName(),
                userService.getUserByEmail("manager@mail.com"),
                "Folyamatban"
        ));
      //4
        taskService.createTask(new Task(
                "L??mpa ",
                "Ki??gett.",
                today.minusDays(30),
                true,
                userService.getUserByEmail("admin@mail.com").getName(),
                userService.getUserByEmail("ann@mail.com"),
                "Befejezve"
        ));
      //5
        taskService.createTask(new Task(
                "Mozg??s??rz??kel?? ",
                "Nem m??k??dik",
                today.minusDays(-30),
                false,
                userService.getUserByEmail("admin@mail.com").getName(),
                userService.getUserByEmail("ann@mail.com"),
                "Folyamatban"
        ));

      
        taskService.findAll().stream().map(t -> "saved task: '" + t.getName()
                + "' for owner: " + getOwnerNameOrNoOwner(t)).forEach(logger::info);
    }

    private String getOwnerNameOrNoOwner(Task task) {
        return task.getOwner() == null ? "no owner" : task.getOwner().getName();
    }
}

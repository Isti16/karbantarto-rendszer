package com.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanager.model.Education;
import com.taskmanager.model.Role;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.repository.EducationRepository;
import com.taskmanager.repository.RoleRepository;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final String ADMIN="ADMIN";
    private static final String USER="USER";
    private static final String VILLANY="Villanyszerelő";
    private static final String RENDGAZD="Rendszergazda";
    
    private EducationRepository eduRepository;
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, 
                           TaskRepository taskRepository,
                           RoleRepository roleRepository,
                           EducationRepository edurepo,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository;
        this.eduRepository=edurepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(USER);
        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        Education education=eduRepository.findByEdu(VILLANY);
        user.setEducation(new ArrayList<>(Collections.singletonList(education)));
        return userRepository.save(user);
    }

    @Override
    public User changeRoleToAdmin(User user) {
        Role adminRole = roleRepository.findByRole(ADMIN);
        user.setRoles(new ArrayList<>(Collections.singletonList(adminRole)));
        return userRepository.save(user);
    }

    @Override
    public User changeEducation(User user) {
    	Education education= eduRepository.findByEdu(RENDGAZD); 
        user.setEducation(new ArrayList<>(Collections.singletonList(education)));
        return userRepository.save(user);
    }
    
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isUserEmailPresent(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.getOne(id);
        user.getTasksOwned().forEach(task -> task.setOwner(null));
        userRepository.delete(user);
    }

}


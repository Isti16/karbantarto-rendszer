package com.taskmanager.service;

import java.util.List;

import com.taskmanager.model.Education;

public interface EducationService {
	Education createEducation(Education edu);
	
	List<Education>findAll();
}

package com.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.model.Education;
import com.taskmanager.repository.EducationRepository;

@Service
public class EducationServiceImpl implements EducationService{
	
	private EducationRepository educationRepository;

	 @Autowired
	    public EducationServiceImpl(EducationRepository eduRepository) {
	        this.educationRepository= eduRepository;
	    }
	 
	 @Override
	    public Education createEducation(Education educ) {
	        return educationRepository.save(educ);
	    } 
	 
	 @Override
	    public List<Education> findAll() {
	        return educationRepository.findAll();
	 }

}

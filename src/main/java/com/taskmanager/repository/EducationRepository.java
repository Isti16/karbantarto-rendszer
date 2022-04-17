package com.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanager.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education,Integer>{
	Education findByEdu(String user);
}

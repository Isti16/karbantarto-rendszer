package com.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanager.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategory(String user);
}


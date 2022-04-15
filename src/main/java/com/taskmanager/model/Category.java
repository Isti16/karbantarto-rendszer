package com.taskmanager.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Category {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;
	
	
	@Size(max = 1200, message = "{task.category.size}")
	@Column(name = "category")
	    private String category;
	
	@Size(max = 1200, message = "{task.time.size}")
	@Column(name = "time")
	    private String time;
	
	@Size(max = 1200, message = "{task.description.size}")
	@Column(name = "desc")
	    private String description;
	
 
    public Category() {
    }

    public Category(String cat, String time, String description) {
        this.category=cat;
        this.time = time;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String cat) {
        this.category = cat;
    }
}
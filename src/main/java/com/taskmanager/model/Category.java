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

    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category cat1 = (Category) o;
        return Objects.equals(id, cat1.id) &&
        		category.equals(cat1.category) &&
        		time.equals(cat1.time)&&
        		description.equals(cat1.description);
     
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category,time, description);
    }
	
	
}


/*package com.taskmanager.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {
	
	
	//id/role/
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "role")
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
 
    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, List<User> users) {
        this.role = role;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }
}


*/
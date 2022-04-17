package com.taskmanager.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Education {
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "edu_id")
private Integer id;

@Column(name = "Education")
private String edu;

@ManyToMany(mappedBy = "edus")
private List<User> users;


public Education() {
	}

public Education(String edu) {
this.edu=edu;
	}

public Education(String educ, List<User> users) {
this.edu = educ;
this.users = users;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getEducation() {
return edu;
}

public void setEducation(String educ) {
this.edu = educ;
}

public List<User> getUsers() {
return users;
}

public void setUsers(List<User> users) {
this.users = users;
}


@Override
public boolean equals(Object o) {
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
Education cat1 = (Education) o;
return Objects.equals(id, cat1.id) &&
        Objects.equals(edu, cat1.edu)&&
        Objects.equals(users, cat1.users);
}


@Override
public int hashCode() {
return Objects.hash(id, edu,users);
}
}

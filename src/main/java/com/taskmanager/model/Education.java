package com.taskmanager.model;

import java.util.Objects;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
public class Education {
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "edu_id")
private Integer id;

@Column(name = "Education")
private String edu;

public Education() {
	}

public Education(String edu) {
this.edu=edu;
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

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
Education cat1 = (Education) o;
return Objects.equals(id, cat1.id) &&
        Objects.equals(edu, cat1.edu);
}


@Override
public int hashCode() {
return Objects.hash(id, edu);
}
}

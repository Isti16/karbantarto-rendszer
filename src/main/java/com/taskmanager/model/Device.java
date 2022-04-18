package com.taskmanager.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.List;

//id, device,name ,location

@Entity
public class Device {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "dev_id")
private Integer id;

@Column(name = "Identifier")
private String device;

@Column(name = "Name")
private String name;

@Column(name = "Location")
private String location;

@ManyToMany(cascade = CascadeType.MERGE)
@JoinTable(name = "device_category",
        joinColumns = @JoinColumn(name = "dev_id"),
        inverseJoinColumns = @JoinColumn(name = "cat_id"))
private List<Category> categs;

public Device() {
}

public Device(String device,String name,String location) {
this.device=device;
this.name = name;
this.location = location;
}

public Device(String device,String name,String location,List<Category> cat) {
this.device=device;
this.name = name;
this.location = location;
this.categs=cat;
}


public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getDevice() {
return device;
}

public void setDevice(String device) {
this.device = device;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public List<Category> getCategory() {
    return categs;
}

public void setCategory(List<Category> cat) {
    this.categs = cat;
}

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
Device cat1 = (Device) o;
return Objects.equals(id, cat1.id) &&
        Objects.equals(device, cat1.device)&&
        Objects.equals(name, cat1.name)&&
        Objects.equals(location, cat1.location)&&
        Objects.equals(categs, cat1.categs);

}

@Override
public int hashCode() {
return Objects.hash(id, device,name ,location, categs);

}

}
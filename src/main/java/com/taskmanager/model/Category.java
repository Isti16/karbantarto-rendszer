package com.taskmanager.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
	
	@Size(max = 1200, message = "{task.peroid.size}")
	@Column(name = "period")
	    private String period;
	
	
	
	@Size(max = 1200, message = "{task.description.size}")
	@Column(name = "desc")
	    private String description;
	
	 @ManyToMany(mappedBy = "categs")
	    private List<Device> dev;

    public Category() {
    }

    public Category(String cat, String time,String period,  String description) {
        this.category=cat;
        this.time = time;
        this.description = description;
        this.period=period;
    }

    public Category(String cat, String time, String period, String description,List<Device> dev) {
        this.category = cat;       
        this.time = time;
        this.period=period;
        this.description = description;
        this.dev=dev;
     
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
    
    
    public String getPeriod() {
        return period;
    }
    
    public void setPeriod(String period) {
        this.period = period;
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

    public List<Device> getDevice() {
        return dev;
    }

    public void setDevice(List<Device> users) {
        this.dev = users;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((period == null) ? 0 : period.hashCode());
        result = prime * result + ((dev == null) ? 0 : dev.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        
        if (period == null) {
            if (other.period != null)
                return false;
        } else if (!period.equals(other.period))
            return false;
        
     
        if(dev==null) {
        	if(other.dev !=null)
        		return false;
    }else if(!dev.equals(other.dev))
    	return false;
        
        return true;
    }
    

    
}
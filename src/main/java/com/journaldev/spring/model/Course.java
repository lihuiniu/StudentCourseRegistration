package com.journaldev.spring.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course implements Serializable{

	private static final long serialVersionUID = -666619177798333712L;

	private int id;
	private String name;
	private Date createdDate;
	
	private Set<Student_Course> studentCourses;
	
	public Course(){
		
	}
	
	public Course(String name){
		this.name = name;
		studentCourses = new HashSet<Student_Course>();
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonSerialize(using=DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Student_Course>   getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<Student_Course> studentCourses) {
        this.studentCourses = studentCourses;
    }
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

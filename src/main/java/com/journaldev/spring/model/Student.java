package com.journaldev.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.*;

public class Student implements Serializable{

	private static final long serialVersionUID = -123457798333712L;
	
	private int id;
	private String name;
	private Date createdDate;
	
	private Set<Student_Course> studentCourses;
	
	public Student(){
		
	}
	
	public Student(String name){
		this.name = name;
		studentCourses = new HashSet<Student_Course>();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
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
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", createdDate=" + createdDate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Student_Course>   getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<Student_Course> studentCourses) {
        this.studentCourses = studentCourses;
    }
    
    
}

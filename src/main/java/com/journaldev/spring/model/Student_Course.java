package com.journaldev.spring.model;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.*;

@Entity
@Table(name = "student_courser")
public class Student_Course implements Serializable{
	private Student student;
    private Course course;
    private Date registrationDate;

    @Id
    @ManyToOne
    @JoinColumn(name = "stu_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
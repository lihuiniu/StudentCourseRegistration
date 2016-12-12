package com.journaldev.spring.application;

import com.journaldev.spring.model.Student;
import com.journaldev.spring.model.Course;
import com.journaldev.spring.model.Student_Course;
import com.journaldev.spring.repository.StudentRepository;
import com.journaldev.spring.repository.CourseRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootApplication
public class StudentCourseRegistrationApplication implements CommandLineRunner {
	    //private static final Logger logger = LoggerFactory.getLogger(StudentCourseRegistrationApplication.class);

	    @Autowired
	    private StudentRepository studentRepository;

	    @Autowired
	    private CourseRepository courseRepository;

	    public static void main(String[] args) {
	        SpringApplication.run(StudentCourseRegistrationApplication.class, args);
	    }

	    @Override
	    @Transactional
	    public void run(String... strings) throws Exception {
	        // create new
	        Student studentA = new Student("Ali Kolide");
	        Course courseA = new Course("Kafka In Action");

	        Student_Course student_course = new Student_Course();
	        student_course.setStudent(studentA);
	        student_course.setCourse(courseA);
	        student_course.setRegistrationDate(new Date());

	        studentA.getStudentCourses().add(student_course);

	        studentRepository.save(studentA);
	        courseRepository.save(courseA);

	        // test
	        System.out.println(studentA.getStudentCourses().size());

	        // update
	        studentA.getStudentCourses().remove(student_course);
	        studentRepository.save(studentA);

	        // test
	        System.out.println(studentA.getStudentCourses().size());
	    }
	}
package com.journaldev.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.journaldev.spring.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}

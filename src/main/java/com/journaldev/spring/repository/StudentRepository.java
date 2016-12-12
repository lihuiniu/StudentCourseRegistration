package com.journaldev.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.journaldev.spring.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}

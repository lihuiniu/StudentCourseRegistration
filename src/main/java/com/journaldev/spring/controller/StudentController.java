package com.journaldev.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.model.Student;

/**
 * Handles requests for the Student service.
 */
@Controller
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	//Map to store Students, ideally we should use database
	Map<Integer, Student> stuData = new HashMap<Integer, Student>();
	
	@RequestMapping(value = StuRestURIConstants.DUMMY_STU, method = RequestMethod.GET)
	public @ResponseBody Student getDummyStudent() {
		logger.info("Start getDummyStudente");
		Student stu = new Student();
		stu.setId(8888);
		stu.setName("Dummy");
		stu.setCreatedDate(new Date());
		stuData.put(8888, stu);
		return stu;
	}
	
	@RequestMapping(value = StuRestURIConstants.GET_STU, method = RequestMethod.GET)
	public @ResponseBody Student getStudent(@PathVariable("id") int stuId) {
		logger.info("Start getStudent. ID="+stuId);
		
		return stuData.get(stuId);
	}
	
	@RequestMapping(value = StuRestURIConstants.GET_ALL_STU, method = RequestMethod.GET)
	public @ResponseBody List<Student> getAllStudents() {
		logger.info("Start getAllStudents.");
		List<Student> stus = new ArrayList<Student>();
		Set<Integer> stuIdKeys = stuData.keySet();
		for(Integer i : stuIdKeys){
			stus.add(stuData.get(i));
		}
		return stus;
	}
	
	@RequestMapping(value = StuRestURIConstants.CREATE_STU, method = RequestMethod.POST)
	public @ResponseBody Student createStudent(@RequestBody Student stu) {
		logger.info("Start createStudent.");
		stu.setCreatedDate(new Date());
		stuData.put(stu.getId(), stu);
		return stu;
	}
	
	@RequestMapping(value = StuRestURIConstants.DELETE_STU, method = RequestMethod.PUT)
	public @ResponseBody Student deleteStudent(@PathVariable("id") int stuId) {
		logger.info("Start deleteStudent.");
		Student stu = stuData.get(stuId);
		stuData.remove(stuId);
		return stu;
	}
	
}

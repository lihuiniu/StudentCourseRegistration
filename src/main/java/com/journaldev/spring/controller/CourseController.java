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

import com.journaldev.spring.model.Course;

/**
 * Handles requests for the Course service.
 */
@Controller
public class CourseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	//Map to store Courses, ideally we should use database
	Map<Integer, Course> crsData = new HashMap<Integer, Course>();
	
	@RequestMapping(value = CrsRestURIConstants.DUMMY_CRS, method = RequestMethod.GET)
	public @ResponseBody Course getDummyCourse() {
		logger.info("Start getDummyCourse");
		Course crs = new Course();
		crs.setId(5678);
		crs.setName("Dummy");
		crs.setCreatedDate(new Date());
		crsData.put(5678, crs);
		return crs;
	}
	
	@RequestMapping(value = CrsRestURIConstants.GET_CRS, method = RequestMethod.GET)
	public @ResponseBody Course getCourse(@PathVariable("id") int crsId) {
		logger.info("Start getCourse. ID="+crsId);
		
		return crsData.get(crsId);
	}
	
	@RequestMapping(value = CrsRestURIConstants.GET_ALL_CRS, method = RequestMethod.GET)
	public @ResponseBody List<Course> getAllCourses() {
		logger.info("Start getAllCourses.");
		List<Course> crss = new ArrayList<Course>();
		Set<Integer> crsIdKeys = crsData.keySet();
		for(Integer i : crsIdKeys){
			crss.add(crsData.get(i));
		}
		return crss;
	}
	
	@RequestMapping(value = CrsRestURIConstants.CREATE_CRS, method = RequestMethod.POST)
	public @ResponseBody Course createCourse(@RequestBody Course crs) {
		logger.info("Start createCourse.");
		crs.setCreatedDate(new Date());
		crsData.put(crs.getId(), crs);
		return crs;
	}
	
	@RequestMapping(value = CrsRestURIConstants.DELETE_CRS, method = RequestMethod.PUT)
	public @ResponseBody Course deleteCourse(@PathVariable("id") int crsId) {
		logger.info("Start deleteCourse.");
		Course crs = crsData.get(crsId);
		crsData.remove(crsId);
		return crs;
	}
	
}

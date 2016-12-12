package com.journaldev.spring;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.controller.CrsRestURIConstants;
import com.journaldev.spring.model.Course;

public class CourseTest {

	public static final String SERVER_URI = "http://localhost:9090/SpringRestExample";
	
	public static void main(String args[]){
		
		testGetDummyCourse();
		System.out.println("*****");
		testCreateCourse();
		System.out.println("*****");
		testGetCourse();
		System.out.println("*****");
		testGetAllCourse();
	}

	private static void testGetAllCourse() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Course> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> crss = restTemplate.getForObject(SERVER_URI + CrsRestURIConstants.GET_ALL_CRS, List.class);
		System.out.println(crss.size());
		for(LinkedHashMap map : crss){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	private static void testCreateCourse() {
		RestTemplate restTemplate = new RestTemplate();
		Course crs = new Course();
		crs.setId(1);
		crs.setName("Spark In Action");
		Course response = restTemplate.postForObject(SERVER_URI + CrsRestURIConstants.CREATE_CRS, crs, Course.class);
		printCrsData(response);
	}

	private static void testGetCourse() {
		RestTemplate restTemplate = new RestTemplate();
		Course crs = restTemplate.getForObject(SERVER_URI+"/rest/crs/1", Course.class);
		printCrsData(crs);
	}

	private static void testGetDummyCourse() {
		RestTemplate restTemplate = new RestTemplate();
		Course crs = restTemplate.getForObject(SERVER_URI + CrsRestURIConstants.DUMMY_CRS, Course.class);
		printCrsData(crs);
	}
	
	public static void printCrsData(Course crs){
		System.out.println("ID=" + crs.getId()+",Name=" + crs.getName()+",CreatedDate=" + crs.getCreatedDate());
	}
}

package com.journaldev.spring;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.controller.StuRestURIConstants;
import com.journaldev.spring.model.Student;

public class StudentTest {

	public static final String SERVER_URI = "http://localhost:9090/SpringRestExample";
	
	public static void main(String args[]){
		
		testGetDummyStudent();
		System.out.println("*****");
		testCreateStudent();
		System.out.println("*****");
		testGetStudent();
		System.out.println("*****");
		testGetAllStudent();
	}

	private static void testGetAllStudent() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Student> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> stus = restTemplate.getForObject(SERVER_URI+StuRestURIConstants.GET_ALL_STU, List.class);
		System.out.println(stus.size());
		for(LinkedHashMap map : stus){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	private static void testCreateStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student stu = new Student();
		stu.setId(1);
		stu.setName("Benjaming Holmes");
		Student response = restTemplate.postForObject(SERVER_URI + StuRestURIConstants.CREATE_STU, stu, Student.class);
		printStuData(response);
	}

	private static void testGetStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student stu = restTemplate.getForObject(SERVER_URI+"/rest/stu/1", Student.class);
		printStuData(stu);
	}

	private static void testGetDummyStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student stu = restTemplate.getForObject(SERVER_URI + StuRestURIConstants.DUMMY_STU, Student.class);
		printStuData(stu);
	}
	
	public static void printStuData(Student stu){
		System.out.println("ID=" + stu.getId()+",Name=" + stu.getName() + ",CreatedDate=" + stu.getCreatedDate());
	}
}

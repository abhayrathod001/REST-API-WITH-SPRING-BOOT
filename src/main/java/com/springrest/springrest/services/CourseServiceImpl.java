package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springrest.springrest.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {

	
	
	List<Course>list;
	
	public CourseServiceImpl() {
		
		list=new ArrayList<>();
		list.add(new Course(145,"Java Core Course","this course contains basics of java"));
		list.add(new Course(146,"SpringBoot Core Course","couse about Rest API"));
	}
	
	
	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return list;
	}
	

}

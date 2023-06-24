package com.springrest.springrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.entities.GroundInfo;
import com.springrest.springrest.entities.Score;
import com.springrest.springrest.services.CourseService;

import util.ExcelOperation;


@RestController
public class MyController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/home")
	public String home(){
		
		return "this is ABHAY home page";
	}
	
	@GetMapping("/info")
	public String info(){
		
		return "this is page is made by ABHAY RATHOD";
	}
	
	@GetMapping("/courses")
	public List<Course> getCourses(){
		
		return this.courseService.getCourses();
		
	}
	
	@GetMapping("/predictScore")
	public GroundInfo getGroundInfo(@RequestParam String city){
		
		
		
		ExcelOperation excelOperation = new ExcelOperation();
		HashMap<String,ArrayList<Score>> scoreMap = excelOperation.readExcel();

		int averageFirstScore = excelOperation.predictAverageFirstScore(city,scoreMap);
		System.out.println( "the averagre score can be " + averageFirstScore);
		
		GroundInfo groundInfo = new GroundInfo();
		groundInfo.setAverageFirstScore(averageFirstScore);
		
		return groundInfo;
	}
	
}

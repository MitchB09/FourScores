package com.realscores.obj.builder;

import java.util.ArrayList;
import java.util.List;

import com.realscores.obj.Course;

public class CourseBuilder {

	public static Course createCourse(int courseId){
		Course course = new Course();
		course.setCourseId(courseId);
		course.setName("Course_" + String.valueOf(courseId));
		return course;
	}
	
	public static Course createCustomCourse(int courseId, String name){
		Course course = new Course();
		course.setCourseId(courseId);
		course.setName(name);
		return course;
	}
	
	public static List<Course> createCourseList(int numCourses){
		List<Course> courses = new ArrayList<Course>();
		for(int i = 1; i <= numCourses; i++) {
			courses.add(CourseBuilder.createCourse(i));
		}
		return courses; 
	}
	
	public static Course createCourseWithHoles(int courseId, int numHoles){
		Course course = CourseBuilder.createCourse(courseId);
		course.setHoles(HoleBuilder.createHoleList(numHoles));
		return course; 
	}
}

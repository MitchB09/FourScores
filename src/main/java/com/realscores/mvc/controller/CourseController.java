package com.realscores.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.realscores.obj.Course;
import com.realscores.obj.Round;
import com.realscores.service.course.ICourseService;
import com.realscores.service.round.IRoundService;

@RestController
@RequestMapping("/courses")
public class CourseController {
  
	@Autowired
	ICourseService courseService;
	
	@Autowired
	IRoundService roundService;
	
	@GetMapping()
	public ResponseEntity<List<Course>> getCourses(){		
		List<Course> courses = courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(
			@PathVariable("id") int courseId){		
		Course course = courseService.getCourseById(courseId);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
	@GetMapping("/{courseId}/rounds")
	public ResponseEntity<List<Round>> getRoundByCourseId(
			@PathVariable("courseId") int courseId){		
		List<Round> rounds = roundService.getRoundsByCourseId(courseId);
		return new ResponseEntity<List<Round>>(rounds, HttpStatus.OK);
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCourse(@RequestBody Course course, UriComponentsBuilder builder){		
		boolean flag = courseService.addCourse(course);
		if (flag == false) {
	      return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/courses/{id}").buildAndExpand(course.getCourseId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{courseId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCourse(
			@PathVariable("courseId") int courseId,
			@RequestBody Course course) {
		
		if (course.getCourseId() != courseId){
			return new ResponseEntity<>("Cannot update course id", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		courseService.updateCourse(course);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable("id") Integer id) {
		courseService.deleteCourse(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}	
}

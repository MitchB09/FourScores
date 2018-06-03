package com.realscores.service.course;

import java.util.List;

import com.realscores.obj.Course;

public interface ICourseService {
	List<Course> getAllCourses();
    Course getCourseById(int courseId);
    boolean addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(int courseId);
}

package com.realscores.dao.course;

import java.util.List;

import com.realscores.obj.Course;

public interface ICourseDao {
	List<Course> getAllCourses();
	Course getCourseById(int courseId);
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(int courseId);
    boolean CourseExists(String name);
}

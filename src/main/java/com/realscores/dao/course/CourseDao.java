package com.realscores.dao.course;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realscores.obj.Course;

@Transactional
@Repository
public class CourseDao implements ICourseDao {

	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Course> getAllCourses() {
		String hql = "FROM Course as crs ORDER BY crs.course_id";
		return (List<Course>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Course getCourseById(int courseId) {
		return (Course) entityManager.find(Course.class, courseId);
	}

	@Override
	public void addCourse(Course course) {
		entityManager.persist(course);
	}

	@Override
	public void updateCourse(Course course) {
		Course foundCourse = getCourseById(course.getCourseId());
		foundCourse.setName(course.getName());
		entityManager.flush();
	}

	@Override
	public void deleteCourse(int courseId) {
		entityManager.remove(getCourseById(courseId));
	}

	@Override
	public boolean CourseExists(String name) {
		String hql = "FROM Course as crs WHERE crs.name = ?";
		int count = entityManager.createQuery(hql).setParameter(0, name)
		              .getResultList().size();
		return count > 0 ? true : false;
	}

}

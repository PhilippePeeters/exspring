package be.abis.exercice.repository;

import java.util.List;

import be.abis.exercice.model.Course;

public interface CourseRepository {

	public List<Course> findAllCourses();
	public Course findCourse(int id);
	public Course findCourse(String shortTitle);
		
}

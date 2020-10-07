package be.abis.exercice.service;

import java.util.List;

import be.abis.exercice.model.Course;

public interface CourseService {

	public List<Course> findAllCourses();
	public Course findCourse(int id);
	public Course findCourse(String shortTitle);
	
}

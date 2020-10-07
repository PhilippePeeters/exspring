package be.abis.exercice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercice.model.Course;
import be.abis.exercice.repository.CourseRepository;

@Service
public class AbisCourseService implements CourseService {


	@Autowired
	CourseRepository courseRepository;
	
	
	@Override
	public List<Course> findAllCourses() {
		return courseRepository.findAllCourses();
	}

	@Override
	public Course findCourse(int id) {
		return courseRepository.findCourse(id);

	}
	
	@Override
	public Course findCourse(String shortTitle) {
		return courseRepository.findCourse(shortTitle);
	}

}

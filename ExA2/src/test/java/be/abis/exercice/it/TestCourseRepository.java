package be.abis.exercice.it;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.abis.exercice.repository.CourseRepository;
import be.abis.exercice.repository.MemoryCourseRepository;
import be.abis.exercice.service.CourseService;

public class TestCourseRepository {
	
	@Autowired
	CourseRepository courseRepository;
	
//	CourseRepository cr;
//	
//	@Before
//	public void setUp() {
//		cr = new MemoryCourseRepository();
//	}
	@Test
	public void numberOfCoursesInMemoryIs5() {
		int size = courseRepository.findAllCourses().size();
		assertEquals(5,size);
	}
	
	@Test
	public void courseWithId8050isMaven() {
		String title = courseRepository.findCourse(8050).getShortTitle();
		assertEquals("Maven",title);
	}
	
	

}

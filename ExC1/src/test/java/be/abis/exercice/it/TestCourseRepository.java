package be.abis.exercice.it;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import be.abis.exercice.repository.CourseRepository;
import be.abis.exercice.repository.MemoryCourseRepository;

public class TestCourseRepository {
	
	CourseRepository cr;
	
	@Before
	public void setUp() {
		cr = new MemoryCourseRepository();
	}
	
	@Test
	public void numberOfCoursesInMemoryIs5() {
		int size = cr.findAllCourses().size();
		assertEquals(5,size);
	}
	
	@Test
	public void courseWithId8050isMaven() {
		String title = cr.findCourse(8050).getShortTitle();
		assertEquals("Maven",title);
	}
	
	

}

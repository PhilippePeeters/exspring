package be.abis.exercice.it;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercice.service.AbisCourseService;
import be.abis.exercice.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisCourseService {

	@Autowired
	CourseService courseService;
	
	@Before
	public void setUp() {
		courseService = new AbisCourseService();
	}
	
	@Test
	public void testFindCourseTitleWithCourseId7900() {
		//Arrange
		//Act
		String title = courseService.findCourse(7900).getShortTitle();
		
		//Assert
		assertEquals("Workshop SQL",title);		
	}
	
	@Test
	public void testPriceOfCourse7900IsHigherThan400() {
		//Arrange
		double expected = 400.0;
		//Act
		double priceCourse = courseService.findCourse(7900).getPricePerDay() * courseService.findCourse(7900).getNumberOfDays();
		
		//Assert
		assertThat(priceCourse, greaterThan(expected));
	}
}

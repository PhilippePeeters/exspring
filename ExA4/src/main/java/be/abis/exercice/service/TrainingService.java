package be.abis.exercice.service;

import java.time.LocalDate;
import java.util.List;

import be.abis.exercice.exception.EnrollException;
import be.abis.exercice.model.Course;
import be.abis.exercice.model.Person;

public interface TrainingService  {

	public Person findPerson(int id);
	public List<Course> showFollowedCourses(Person person);
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException;
	public void addPerson(Person person);
	public void deletePerson(int id);
	public void changePassword(Person person, String newPassword);
	public Person findPerson(String emailAddress, String passWord);
	Course findCourse(int id);
	
}

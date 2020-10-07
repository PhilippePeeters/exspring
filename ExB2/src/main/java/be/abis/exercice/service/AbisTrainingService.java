package be.abis.exercice.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercice.exception.EnrollException;
import be.abis.exercice.model.Course;
import be.abis.exercice.model.Person;
import be.abis.exercice.repository.PersonRepository;

@Service
public class AbisTrainingService implements TrainingService {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	CourseService courseService;
	
	@Override
	public Person findPerson(int id) {
		return personRepository.findPerson(id);
	}

	@Override
	public List<Course> showFollowedCourses(Person person) {
		return null;
	}
	
	@Override
	public Course findCourse(int id) {
		return courseService.findCourse(id);
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addPerson(Person person) {
		try {
			personRepository.addPerson(person);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deletePerson(int id) {
		personRepository.deletePerson(id);
	}

	@Override
	public void changePassword(Person person, String newPassword) {
		try {
			personRepository.changePassword(person, newPassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Person findPerson(String emailAddress, String passWord) {
		return personRepository.findPerson(emailAddress, passWord);
	}
	
	
}

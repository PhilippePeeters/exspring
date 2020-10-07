package be.abis.exercice.controller;

import java.util.List;
import java.util.function.IntPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.InternalParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercice.model.Course;
import be.abis.exercice.model.LoginItem;
import be.abis.exercice.model.Person;
import be.abis.exercice.service.CourseService;
import be.abis.exercice.service.TrainingService;

@Controller
public class AbisCourseController {

	@Autowired
	TrainingService trainingService; 
	
	@Autowired
	CourseService courseService;

	Person person;
	
	@GetMapping("/")
	public String showLogin(Model model) {
		LoginItem loginItem = new LoginItem();
		model.addAttribute("loginItem", loginItem);
		return "loginItemForm";
	}
	
	@PostMapping("/")
	public String loginItemForm(Model model, LoginItem loginItem) {
		System.out.println("Email : " + loginItem.getEmail());
		System.out.println("Password : " + loginItem.getPassword());
		// Try to find the person in the file
		person = trainingService.findPerson(loginItem.getEmail(), loginItem.getPassword());
		
		//Redirect to the next Page or login if person is null
		if (person == null) {
			return "redirect:/loginItemForm";
		} else {
			return "redirect:/welcome";
		}
		
	}
	
	@GetMapping("/welcome")
	public String showWelcomePage(Model model) {
		model.addAttribute("person",person);
		return "welcome";
	}
	
	
	@GetMapping("/course")
	public String showCoursePage(Model model) {
		Course course = trainingService.findCourse(7900);
		model.addAttribute("course", course);
		return "courseoptions";
	
	}
	
	@GetMapping("/adminperson")
	public String showAdminPersonPage(Model model) {
		return "adminperson";
	
	}

	@GetMapping("/allcourses")
	public String showAllCoursePage(Model model) {
		List<Course> courses = courseService.findAllCourses();
		model.addAttribute("courses",courses);
		return "allcourses";
	}
		
	@GetMapping("/coursebyshorttitle")
	public String findCourseByShortTitlePage(Model model) {
		Course course = new Course();
		model.addAttribute("course",course);
		return "coursebyshorttitle";
	}
	
	@PostMapping("/coursebyshorttitle")
	public String showCourseByShortTitlePage(Model model, Course course) {
		System.out.println("Short Title : " + course.getShortTitle());
		// Try to find the course in the file
		
		course = courseService.findCourse(course.getShortTitle());
//		course = trainingService.findCourse(Integer.parseInt(course.getCourseId()));
		
		//Redirect to the next Page or login if person is null
		if (course == null) {
			return "redirect:/course";
		} else {
			return "redirect:/coursedetails";
		}
		
	}

	@GetMapping("/coursebyid")
	public String findCourseByIdPage(Model model) {
		Course course = new Course();
		model.addAttribute("course",course);
		return "coursebyid";
	}
	
	@PostMapping("/coursebyid")
	public String showCourseByIdPage(Model model, Course course) {
		System.out.println("Id Course : " + course.getCourseId());
		// Try to find the course in the file
		
		course = courseService.findCourse(Integer.parseInt(course.getCourseId()));
//		course = trainingService.findCourse(Integer.parseInt(course.getCourseId()));
		
		//Redirect to the next Page or login if person is null
		model.addAttribute("course",course);
		if (course == null) {
			return "redirect:/course";
		} else {
			return "redirect:/coursedetails";
		}
		
	}
	@GetMapping("/coursedetails")
	public String showDetailsCoursePage(Model model, Course course) {
		System.out.println("Id Course : " + course.getCourseId());
		model.addAttribute("course",course);
		return "coursedetails";
	}
	
	@GetMapping("/logout")
	public String logoutAction() {
		return "redirect:/";
	
	}
}

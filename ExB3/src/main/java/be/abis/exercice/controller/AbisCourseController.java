package be.abis.exercice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercice.model.Course;
import be.abis.exercice.model.LoginItem;
import be.abis.exercice.model.Person;
import be.abis.exercice.repository.FilePersonRepository;
import be.abis.exercice.service.CourseService;
import be.abis.exercice.service.TrainingService;

@Controller
public class AbisCourseController {

	@Autowired
	TrainingService trainingService; 
	
	@Autowired
	CourseService courseService;

	Person personFind;
	Course courseFind;
	String newPassword;

// Login part
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
		personFind = trainingService.findPerson(loginItem.getEmail(), loginItem.getPassword());
		
		//Redirect to the next Page or login if person is null
		if (personFind == null) {
			return "redirect:/";
		} else {
			return "redirect:/welcome";
		}
		
	}
	
	@GetMapping("/welcome")
	public String showWelcomePage(Model model) {
		model.addAttribute("person",personFind);
		return "welcome";
	}
	
// Courses part	
	@GetMapping("/course")
	public String showCoursePage(Model model) {
		return "courseoptions";
	
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
		
		courseFind = courseService.findCourse(course.getShortTitle());
		
		//Redirect to the next Page or login if person is null
		model.addAttribute("course",courseFind);
		if (courseFind == null) {
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
		
		courseFind = courseService.findCourse(Integer.parseInt(course.getCourseId()));
		
		//Redirect to the next Page or login if person is null
		
		model.addAttribute("course",courseFind);
		if (courseFind == null) {
			return "redirect:/course";
		} else {
			return "redirect:/coursedetails";
		}
		
	}
	
	@GetMapping("/coursedetails")
	public String showDetailsCoursePage(Model model) {
		System.out.println("Id Course : " + courseFind.getCourseId());
		model.addAttribute("course",courseFind);
		return "coursedetails";
	}

		
// PersonAdmin part	
	@GetMapping("/adminperson")
	public String showAdminPersonPage(Model model) {
		return "adminperson";
	
	}
	
	@GetMapping("/newpassword")
	public String showChangePasswordPage(Model model) {
		newPassword = "password";
		model.addAttribute("newpassword",newPassword);
		return "newpassword";
	
	}
	
	@PostMapping("/newpassword")
	public String showUpdatePasswordPage(Model model, String newPassword) {
		System.out.println("Info Person : " + personFind.getFirstName() + " " + personFind.getLastName());
		// Try to find the course in the file
		trainingService.changePassword(personFind, newPassword);
		
		//Redirect to the next Page or login if person is null
		return "redirect:/adminperson";
	}

	@GetMapping("/findperson")
	public String showFindPersonOptionsPage(Model model) {
		return "findperson";
	}
	
	@GetMapping("/allpersons")
	public String showAllPersonPage(Model model) {
		List<Person> allPersons = new FilePersonRepository().getAllPersons(); 
		model.addAttribute("persons",allPersons);
		return "allpersons";
	}

	
//Logout part	
	@GetMapping("/logout")
	public String logoutAction() {
		return "redirect:/";
	
	}
}

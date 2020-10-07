package be.abis.exercice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercice.model.Course;
import be.abis.exercice.model.LoginItem;
import be.abis.exercice.model.Person;
import be.abis.exercice.service.TrainingService;

@Controller
public class AbisCourseController {

	@Autowired
	TrainingService trainingService; 

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
		return "course";
	
	}
	
	@GetMapping("/adminperson")
	public String showAdminPersonPage(Model model) {
		return "adminperson";
	
	}

	@GetMapping("/logout")
	public String logoutAction() {
		return "redirect:/";
	
	}
}

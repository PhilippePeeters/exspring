package be.abis.exercice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercice.model.LoginItem;
import be.abis.exercice.model.Person;
import be.abis.exercice.service.TrainingService;

@Controller
public class AbisCourseController {

	@Autowired
	TrainingService trainingService; 

	LoginItem loginItem = new LoginItem();	
	Person person;
	
	
	@GetMapping("/")
	public String showLogin(Model model) {
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
	
//	@PostMapping("/welcome")
//	public String linkCourse(Model model) {
//		return "redirect:/course";
//	}
	
	
	@GetMapping("/course")
	public String showCoursePage(Model model) {
		return "course";
	
	}
	
	@GetMapping("/adminperson")
	public String showAdminPersonPage(Model model) {
		return "adminperson";
	
	}

}

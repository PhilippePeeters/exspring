package be.abis.exercice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.exercice.model.Course;
import be.abis.exercice.service.CourseService;
import be.abis.exercice.service.TrainingService;

@Controller
public class AbisCourseController {

	@Autowired
	TrainingService trainingService; 
	
	@GetMapping("/")
	public String showPerson(Model model) {
		Course course = trainingService.findCourse(7900);
		model.addAttribute("course", course);
		return "course";
	}
}

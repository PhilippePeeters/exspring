package be.abis.exercice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.exercice.model.Course;
import be.abis.exercice.service.CourseService;

@Controller
public class abisCourseController {

	@Autowired
	CourseService courseService; 
	
	@GetMapping("/")
	public String showPerson(Model model) {
		Course course = courseService.findCourse(7900);
		model.addAttribute("course", course);
		return "course";
	}
}

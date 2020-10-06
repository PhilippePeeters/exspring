package be.abis.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.hello.model.Person;
import be.abis.hello.service.HelloService;

@Controller
public class AppControllre {

	@Autowired
	HelloService helloService; 
	
	@GetMapping("/")
	public String showPerson(Model model) {
		Person p = helloService.findPerson(5);
		model.addAttribute("person", p);
		return "hello";
	}
}

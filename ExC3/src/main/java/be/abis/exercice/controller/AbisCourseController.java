package be.abis.exercice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercice.model.Address;
import be.abis.exercice.model.Company;
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
	String message;

// Login part
	@GetMapping("/")
	public String showLogin(Model model) {
		LoginItem loginItem = new LoginItem();
		model.addAttribute("loginItem", loginItem);
		return "loginItemForm";
	}
	
	@PostMapping("/")
	public String loginItemForm(Model model, @Valid @ModelAttribute("loginItem") LoginItem loginItem, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            return "loginItemForm";
        }
		
		if (checkLoginItemCorrect(loginItem)) {
			bindingResult.reject("Email and Password not correct for logging");
            return "loginItemForm";
		}
		return "redirect:/welcome";
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
		
		message = "New Password updated correctly !!";
		//Redirect to the next Page or login if person is null
		model.addAttribute("message",message);

		return "redirect:/confirm";
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

	@GetMapping("/personbyid")
	public String findPersonByIdPage(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "personbyid";
	}
	
	@PostMapping("/personbyid")
	public String showPersonByIdPage(Model model, Person person) {
		System.out.println("Info Person : " + person.getPersonId());
		// Try to find the course in the file
		
		personFind = trainingService.findPerson(person.getPersonId());
		
		//Redirect to the next Page or login if person is null
		
		model.addAttribute("person",personFind);
		if (personFind == null) {
			return "redirect:/findperson";
		} else {
			return "redirect:/persondetails";
		}
		
	}
		
	@GetMapping("/persondetails")
	public String showDetailsPersonPage(Model model) {
		System.out.println("Id Person : " + personFind.getPersonId());
		model.addAttribute("person",personFind);
		return "persondetails";
	}
	
	@GetMapping("/removepersonbyid")
	public String findPersonByIdToRemovePage(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "removepersonbyid";
	}
	
	@PostMapping("/removepersonbyid")
	public String showPersonByIdToRemovePage(Model model, Person person) {
		System.out.println("Info Person : " + person.getPersonId());
		// Try to find the course in the file
		
		message = "Person Removed !!!" +  " Id : " + person.getPersonId();
		trainingService.deletePerson(person.getPersonId());
		
		//Redirect to the next Page or login if person is null
		//Prepare confirm message
		model.addAttribute("message",message);
		return "redirect:/confirm";
	}
	
	@GetMapping("/addperson")
	public String addNewPersonPage(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "addperson";
	}
	
	@PostMapping("/addperson")
	public String addNewPersonPage(Model model ,@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
            return "/addperson";
        }

		System.out.println("Info Person  : " + person.toString());
		System.out.println("Info Company : " + person.getCompany().toString());
		System.out.println("Info Address : " + person.getCompany().getAddress().toString());
		
		//Add the person in the file
		trainingService.addPerson(person);
		
		//Prepare confirm message
		message = "New Person Added !!!" + person.getFirstName() + " Id : " + person.getPersonId();
		
		//Redirect to the next Page or login if person is null
		model.addAttribute("message",message);
		return "redirect:/confirm";
	}
	
	@GetMapping("/confirm")
	public String showMessageAfterOperationPage(Model model) {
		model.addAttribute("message",message);
		return "confirm";
	}
	
//Logout part	
	@GetMapping("/logout")
	public String logoutAction() {
		return "redirect:/";
	
	}
	
	private boolean checkLoginItemCorrect(LoginItem loginItem) {
		System.out.println("Email : " + loginItem.getEmail());
		System.out.println("Password : " + loginItem.getPassword());
		// Try to find the person in the file
		Person personFind = trainingService.findPerson(loginItem.getEmail(), loginItem.getPassword());
		
		if (personFind == null) {
			return true;
		} else {
			return false;
		}
	}	
}

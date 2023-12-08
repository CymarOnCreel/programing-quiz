package hu.cymar.tamzol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.cymar.tamzol.model.User;
import hu.cymar.tamzol.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	UserService userService;
	
	@GetMapping("login/registration")
	public String viewRegistration(Model model) {
		return "registration";
	}
	
	@PostMapping("login/registration")
	public String saveNewUser(User userObj) {
		userService.saveUser(userObj);
		return "redirect:/login";
	}
}

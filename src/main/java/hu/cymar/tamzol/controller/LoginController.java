package hu.cymar.tamzol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.cymar.tamzol.model.User;
import hu.cymar.tamzol.model.UserCategory;
import hu.cymar.tamzol.service.UserService;

@Controller
public class LoginController {
	@Autowired
    private UserService userService;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("login")
	public String viewNew(Model model) {
		return "Login";
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	 @PostMapping("/login")
	    public String login(@RequestParam String userName, @RequestParam String password, Model model, HttpSession session) {
	        User user = userService.getUserByUserName(userName);

	        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
	        	if(user.getUserCategory().equals(UserCategory.ADMIN)) session.setAttribute("isAdmin", true); 
	        	session.setAttribute("loggedIn", true);
	             session.setAttribute("loggedUsername", user.getUserName());  
	        	return "redirect:/";
	        } else {
	            model.addAttribute("error", "Invalid username or password");
	            return "login";
	        }
	    }

}

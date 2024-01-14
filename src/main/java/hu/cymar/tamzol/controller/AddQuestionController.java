package hu.cymar.tamzol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hu.cymar.tamzol.model.User;

@Controller
public class AddQuestionController {

	
	@GetMapping("addQuestion")
	public String addQuestionView(Model model, HttpSession session) {
		User user=(User) session.getAttribute("user");
		
		return "addQuestion";
	}
}

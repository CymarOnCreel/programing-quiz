package hu.cymar.tamzol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionManagementController {

	@GetMapping("questionManagement")
	public String questionManagementView(Model model) {
		return "questionManagement";
	}
}

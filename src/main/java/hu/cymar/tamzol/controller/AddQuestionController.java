package hu.cymar.tamzol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddQuestionController {

	
	@GetMapping("addQuestion")
	public String addQuestionView(Model model) {
		return "addQuestion";
	}
}

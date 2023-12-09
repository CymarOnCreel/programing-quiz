package hu.cymar.tamzol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizFilterController {

	@GetMapping("quizFilter")
	public String viewQuizFilter(Model model) {
		return "quizFilter";
	}
}

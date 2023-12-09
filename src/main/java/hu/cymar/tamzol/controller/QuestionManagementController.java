package hu.cymar.tamzol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.service.QuestionService;

@Controller
public class QuestionManagementController {

	@Autowired
	QuestionService qs;

	@GetMapping("questionManagement")
	public String questionManagementView(Model model) {
		List<Question> all = qs.getAll();
		model.addAttribute("questions", all);
		System.out.println(all);
		return "questionManagement";
	}

}

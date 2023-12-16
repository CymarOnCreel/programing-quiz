package hu.cymar.tamzol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.service.QuizService;

@Controller
public class QuizController {
	   private final QuizService quizService;

	    @Autowired
	    public QuizController(QuizService quizService) {
	        this.quizService = quizService;
	    }

	@PostMapping("/quiz")
	public String getFilteredQuestions(Model model, HttpSession session,
			@RequestParam(name = "selectedSubcategories", required = false) List<QuestionSubcategory> subcategories) {
		if (subcategories == null || subcategories.isEmpty()) {
			return "redirect:/selectSubcategories";
		}
		QuestionCategory selectedCategory = (QuestionCategory) session.getAttribute("selectedCategory");
		model.addAttribute("selectedSubcategories", subcategories);
		quizService.getFilteredQuestionsAndAnswers(selectedCategory, subcategories, model);
		return "quiz";
	}



}

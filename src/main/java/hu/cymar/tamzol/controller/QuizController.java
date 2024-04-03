package hu.cymar.tamzol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.model.Question;
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
			@RequestParam(name = "selectedSubcategories", required = false) List<QuestionSubcategory> subcategories,
			@RequestParam(name = "numQuestions", defaultValue = "1") int numberOfQuestionsToGenerate,
			@RequestParam(name = "action", defaultValue = "") String action) {
		if (subcategories == null || subcategories.isEmpty()) {
			return "redirect:/selectSubcategories";
		}
		QuestionCategory selectedCategory = (QuestionCategory) session.getAttribute("selectedCategory");
		session.setAttribute("subcategories", subcategories);
		model.addAttribute("selectedSubcategories", subcategories);
		System.out.println(numberOfQuestionsToGenerate + "mindenelott");

		if ("getAllQuestions".equals(action)) {
			List<Question> allQuestions = quizService.getAllQuestions();
			numberOfQuestionsToGenerate = allQuestions.size();
		}
		List<Question> filteredQuestions = quizService.getFilteredQuestions(selectedCategory, subcategories, model,
				numberOfQuestionsToGenerate);
		List<List<Answer>> answers = quizService.getAnswersList(model, filteredQuestions);
		session.setAttribute("filteredQuestions", filteredQuestions);
		session.setAttribute("answers", answers);

		return "quiz";
	}

}

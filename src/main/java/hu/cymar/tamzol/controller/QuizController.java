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
import hu.cymar.tamzol.service.IQuizCreation;

@Controller
public class QuizController {
	private final IQuizCreation quizCreation;
	
	@Autowired
	public QuizController(IQuizCreation quizCreation) {
		this.quizCreation = quizCreation;
	}
	
	@PostMapping("/quiz")
	public String getFilteredQuestions(Model model, HttpSession session,
			@RequestParam(name = "selectedSubcategories", required = false) List<QuestionSubcategory> subcategories) {
		if (subcategories == null || subcategories.isEmpty()) {
			return "redirect:/selectSubcategories";
		}
		QuestionCategory selectedCategory = (QuestionCategory) session.getAttribute("selectedCategory");
		model.addAttribute("selectedSubcategories", subcategories);
		List<Question> questions = quizCreation.getQuestionsByCategoryAndSubcategoriesChoosen(selectedCategory,
				subcategories);
		model.addAttribute("filteredQuestions", questions);
		System.out.println(questions);
		for (Question question : questions) {
			model.addAttribute("answersOfQuestion",getAnswersOfQuestion(question.getId()));
			System.out.println(getAnswersOfQuestion(question.getId()).toString());
		}
		return "quiz";
	}
	
	public List<Answer> getAnswersOfQuestion(Long id){
		List<Answer> answers=quizCreation.getAnswersByQuestionId(id);
		return answers;
	}
	
	
}

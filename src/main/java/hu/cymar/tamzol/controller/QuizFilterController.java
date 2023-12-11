package hu.cymar.tamzol.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.repository.QuestionRepository;
import hu.cymar.tamzol.service.IQuizCreation;

@Controller
public class QuizFilterController {

	private final IQuizCreation quizCreation;
	private final QuestionRepository questionRepository;

	@Autowired
	public QuizFilterController(IQuizCreation quizCreation, QuestionRepository questionRepository) {
		this.quizCreation = quizCreation;
		this.questionRepository = questionRepository;
	}

	@GetMapping("/selectCategory")
	public String getCategories(Model model) {
		List<QuestionCategory> categories = quizCreation.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "selectCategory";
	}


	@GetMapping("selectSubcategories")
	public String getSubcategories(Model model, HttpSession session) {
		populateSubcategories(model, session);
		return "selectSubcategories";
		
	}

	@PostMapping("/selectSubcategories")
	public String getSubcategories(Model model, @RequestParam(name = "id", required = false) Long id,
			HttpSession session) {
		boolean categoryChosen = id != null;
		model.addAttribute("categoryChosen", categoryChosen);
		session.setAttribute("selectedCategory", quizCreation.getCategoryById(id));
		populateSubcategories(model, session);
		return "selectSubcategories";
	}

	@PostMapping("/quiz")
	public String getFilteredQuestions(Model model, HttpSession session,
			@RequestParam(name = "selectedSubcategories", required = false) List<QuestionSubcategory> subcategories) {
		if (subcategories == null || subcategories.isEmpty()) {
			model.addAttribute("error", "Please select at least one subcategory.");
			return "redirect:/selectSubcategories";
		}
		QuestionCategory selectedCategory = (QuestionCategory) session.getAttribute("selectedCategory");
		model.addAttribute("selectedSubcategories", subcategories);
		List<Question> questions = quizCreation.getQuestionsByCategoryAndSubcategoriesChoosen(selectedCategory,
				subcategories);
		model.addAttribute("filteredQuestions", questions);
		System.out.println(questions);
		for (Question question : questions) {
			System.out.println(question.getId() + " " + question.getQuestionText());

		}
		return "quiz";
	}

	private void populateSubcategories(Model model, HttpSession session) {
		QuestionCategory selectedCategory = (QuestionCategory) session.getAttribute("selectedCategory");
		model.addAttribute("selectedCategory", selectedCategory);
		List<QuestionSubcategory> subcategories = quizCreation.getSubcategoriesByCategoryId(selectedCategory.getId());
		model.addAttribute("subcategories", subcategories);
	}
}

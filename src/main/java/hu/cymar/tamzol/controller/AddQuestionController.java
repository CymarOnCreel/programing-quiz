package hu.cymar.tamzol.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionForm;
import hu.cymar.tamzol.model.QuestionStatus;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.model.User;
import hu.cymar.tamzol.service.AnswerService;
import hu.cymar.tamzol.service.CategoriesService;
import hu.cymar.tamzol.service.QuestionService;

@Controller
public class AddQuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;
	@Autowired
	CategoriesService categoryService;

	@GetMapping("addQuestion")
	public String addQuestionView(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<QuestionCategory> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		return "addQuestion";
	}

	@PostMapping("/addQuestion")
	public String saveQuestionAndAnswer(@ModelAttribute QuestionForm questionForm,
			@RequestParam(name = "categoryId") Long id) {
		Question question = new Question();
		setQuestionData(questionForm, id, question);
		questionService.saveQuestion(question);
		List<Answer> answers = createAnswers(questionForm, question);
		for (Answer answer : answers) {
			answerService.saveAnswer(answer);
		}
		
		return "redirect:/";
	}

	private Question setQuestionData(QuestionForm questionForm, Long categoryId, Question question) {
		question.setQuestionText(questionForm.getQuestionText());
		QuestionCategory category = categoryService.getCategoryById(categoryId);
		QuestionSubcategory subcategory= categoryService.getBysubcategoryName("ToChangeSubcategory");
		long lastIndex = questionService.getLastQuestionId();
		question.setId(lastIndex + 1);
		question.setCategory(category);
		question.setSubcategory(subcategory);
		question.setQuestionStatus(QuestionStatus.IN_PROGRESS);
		return question;
		
	}
	@SuppressWarnings("unlikely-arg-type")
	private List<Answer> createAnswers(QuestionForm questionForm, Question question) {
		List<Answer> answers = new ArrayList<>();
		Answer answer0 = createAnswer(questionForm.getTextAnswer0(), questionForm.getCorrectAnswer().equals("answer0"),
				question);
		answers.add(answer0);
		Answer answer1 = createAnswer(questionForm.getTextAnswer1(), questionForm.getCorrectAnswer().equals("answer1"),
				question);
		answers.add(answer1);

		Answer answer2 = createAnswer(questionForm.getTextAnswer2(), questionForm.getCorrectAnswer().equals("answer2"),
				question);
		answers.add(answer2);

		Answer answer3 = createAnswer(questionForm.getTextAnswer3(), questionForm.getCorrectAnswer().equals("answer3"),
				question);
		answers.add(answer3);
		return answers;
	}

	private Answer createAnswer(String answerText, boolean isCorrect, Question question) {
		Answer answer = new Answer();
		answer.setAnswerText(answerText);
		answer.setCorrect(isCorrect);
		answer.setQuestion(question);
		return answer;
	}
}

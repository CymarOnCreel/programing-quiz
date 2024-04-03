package hu.cymar.tamzol.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionForm;
import hu.cymar.tamzol.model.QuestionStatus;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.service.AnswerService;
import hu.cymar.tamzol.service.CategoriesService;
import hu.cymar.tamzol.service.QuestionService;

@Controller
//@RequestMapping("questionManagement")
public class QuestionManagementController {

	@Autowired
	QuestionService qs;

	@Autowired
	CategoriesService categoriesService;

	@Autowired
	AnswerService as;

	@GetMapping("questionManagement")
	public String questionManagementView(Model model) {
		List<Question> all = qs.getAll();
		model.addAttribute("questions", all);
		return "questionManagement";
	}

	@DeleteMapping("/questionManagement/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {

		try {
			Question questionUpdate = qs.findById(id).get();
			questionUpdate.setQuestionStatus(QuestionStatus.REJECTED);
			qs.saveQuestion(questionUpdate);
			return ResponseEntity.ok("Question deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error deleting question");
		}
	}

	@GetMapping("/edit/{id}")
	public String editQuestion(@PathVariable Long id, Model model) {
//		id=217L;
		Question question = qs.findById(id).get();
		List<QuestionCategory> categories = categoriesService.getAllCategories();
		List<QuestionSubcategory> subcategories = categoriesService.getAllSubcategories();
		model.addAttribute("question", question);
		model.addAttribute("categories", categories);
		model.addAttribute("subcategories", subcategories);
		return "edit";
	}

	@PostMapping("/edit/{id}")
	public String saveQuestionAndAnswer(@ModelAttribute QuestionForm questionForm,
			@RequestParam(name = "categoryId") Long categoryId,
			@RequestParam(name = "subcategoryId") Long subcategoryId, @PathVariable Long id,
			@RequestParam(name = "status") QuestionStatus status) {
		Question question = qs.findById(id).get();
		updateQuestionData(questionForm, categoryId, subcategoryId, question, status);
		qs.saveQuestion(question);
		System.out.println(question.toString());
		System.out.println(question.getCategory().getCategoryName());
		List<Answer> answers = getAnswers(questionForm, question);
		for (int i = 0; i < answers.size(); i++) {
			Answer newAnswer = question.getAnswers().get(i);
			System.out.println(newAnswer.toString());
			newAnswer.setAnswerText(answers.get(i).getAnswerText());
			newAnswer.setQuestion(question);
			newAnswer.setCorrect(answers.get(i).isCorrect());
			System.out.println(newAnswer.toString());
			as.saveAnswer(newAnswer);
		}
		return "redirect:/questionManagement";
	}

	@GetMapping("/getSubcategories/{categoryId}")
	@ResponseBody
	public String getSubcategoriesByCategory(@PathVariable Long categoryId) {
		List<QuestionSubcategory> subcategories = categoriesService.getSubcategoriesByCategoryId(categoryId);
		StringBuilder htmlContent = new StringBuilder();

		for (QuestionSubcategory subcategory : subcategories) {
			htmlContent.append("<option value=\"").append(subcategory.getId()).append("\">")
					.append(subcategory.getSubcategoryName()).append("</option>");
		}

		return htmlContent.toString();
	}

	private Question updateQuestionData(QuestionForm questionForm, Long categoryId, Long subcategoryId,
			Question question, QuestionStatus status) {
		question.setQuestionText(questionForm.getQuestionText());
		question.setCategory(categoriesService.getCategoryById(categoryId));
		question.setSubcategory(categoriesService.getSubcategoryById(subcategoryId));
		question.setQuestionStatus(status);
		return question;
	}

	private List<Answer> getAnswers(QuestionForm questionForm, Question question) {
		List<Answer> answers = new ArrayList<>();
		Answer answer0 = createAnswer(questionForm.getTextAnswer0(),
				isCorrectAnswer(questionForm.getCorrectAnswer(), "answer0"), question);
		Answer answer1 = createAnswer(questionForm.getTextAnswer1(),
				isCorrectAnswer(questionForm.getCorrectAnswer(), "answer1"), question);

		Answer answer2 = createAnswer(questionForm.getTextAnswer2(),
				isCorrectAnswer(questionForm.getCorrectAnswer(), "answer2"), question);
		Answer answer3 = createAnswer(questionForm.getTextAnswer3(),
				isCorrectAnswer(questionForm.getCorrectAnswer(), "answer3"), question);
		answers.add(answer0);
		answers.add(answer1);
		answers.add(answer2);
		answers.add(answer3);
		return answers;
	}

	private boolean isCorrectAnswer(Optional<String> correctAnswer, String answerKey) {
		return correctAnswer.map(value -> value.equals(answerKey)).orElse(false);
	}

	private Answer createAnswer(String answerText, boolean isCorrect, Question question) {
		Answer answer = new Answer();
		answer.setAnswerText(answerText);
		answer.setCorrect(isCorrect);
		answer.setQuestion(question);
		return answer;
	}
}

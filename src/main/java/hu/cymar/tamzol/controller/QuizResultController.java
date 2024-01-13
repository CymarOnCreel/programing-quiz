package hu.cymar.tamzol.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import hu.cymar.tamzol.model.QuizScore;
import hu.cymar.tamzol.model.User;
import hu.cymar.tamzol.service.QuizResultService;
import hu.cymar.tamzol.service.QuizService;

@Controller
public class QuizResultController {

	@Autowired
	private QuizService quizService;
	@Autowired
	private QuizResultService quizResultService;

	@PostMapping("/quizResult")
	public String getQuizResult(HttpSession session, Model model, QuizScore quizScoreObj,
			@RequestParam Map<String, String> allParams) {
		QuestionCategory selectedCategory = (QuestionCategory) session.getAttribute("selectedCategory");
		List<QuestionSubcategory> selectedSubcategories = (List<QuestionSubcategory>) session
				.getAttribute("subcategories");

		List<Long> selectedAnswerIds = allParams != null
				? allParams.values().stream().map(Long::parseLong).collect(Collectors.toList())
				: Collections.emptyList();
		List<Answer> userAnswers = quizService.getAnswersById(selectedAnswerIds);

		List<Question> filteredQuestions = (List<Question>) session.getAttribute("filteredQuestions");
		List<List<Answer>> answersByQuestion = (List<List<Answer>>) session.getAttribute("answers");
		List<Answer> answers = answersByQuestion.stream().flatMap(List::stream).collect(Collectors.toList());

		int questionCount = quizResultService.getQuestionCount(filteredQuestions);
		int rightAnswerCount = quizResultService.getRightAnswerCount(selectedAnswerIds, answers);

		List<String> allAnswerClasses = new ArrayList<>();

		for (int i = 0; i < filteredQuestions.size(); i++) {
			Question question = filteredQuestions.get(i);

			boolean isQuestionAnswered = false;
			boolean isUserSelected = false;
			List<Long> userAnswerIdsForQuestion = userAnswers.stream()
					.filter(userAnswer -> question.getAnswers().stream()
							.anyMatch(answer -> answer.getId().equals(userAnswer.getId())))
					.map(Answer::getId).collect(Collectors.toList());

			if (!userAnswerIdsForQuestion.isEmpty()) {
				isQuestionAnswered = true;
				isUserSelected = true;
			}

			for (Answer answer : question.getAnswers()) {
				boolean isCorrect = answer.isCorrect();
				String answerClass = "";

				if (isUserSelected) {
					if (isCorrect) {
						answerClass = "bg-success";
					} else if (userAnswerIdsForQuestion.contains(answer.getId())) {
						answerClass = "bg-danger";
					}
				} else if (isCorrect) {
					answerClass = "bg-primary";
				}
				allAnswerClasses.add(answerClass);
			}
		}
		quizResultService.saveQuizResult(questionCount, rightAnswerCount, quizScoreObj,

				getCurrentUser(session), selectedCategory);
		model.addAttribute("allAnswerClasses", allAnswerClasses);
		model.addAttribute("questionCount", questionCount);
		model.addAttribute("rightAnswerCount", rightAnswerCount);
		model.addAttribute("filteredQuestions", filteredQuestions);
		model.addAttribute("answers", answers);
		model.addAttribute("userAnswers", userAnswers);

		return "quizResult";
	}

	private User getCurrentUser(HttpSession session) {
		return (User) session.getAttribute("user");
	}

}

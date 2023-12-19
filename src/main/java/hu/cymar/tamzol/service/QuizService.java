package hu.cymar.tamzol.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionStatus;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.repository.AnswerRepository;
import hu.cymar.tamzol.repository.QuestionRepository;

@Service
public class QuizService implements IQuizCreation {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;

	public List<Question> getFilteredQuestions(QuestionCategory selectedCategory,
			List<QuestionSubcategory> subcategories, Model model) {
		List<Question> questions = getShuffledAcceptedQuestionsByCategoryAndSubcategoriesChoosen(selectedCategory,
				subcategories);
		List<Question> filteredQuestions = questions.stream()
		        .collect(Collectors.toList());
		model.addAttribute("filteredQuestions", filteredQuestions);
		return filteredQuestions;
	}

	public List<List<Answer>> getAnswersList(Model model, List<Question> filteredQuestions) {
		List<List<Answer>> answersOfQuestions = new ArrayList<>();
		for (Question question : filteredQuestions) {
			answersOfQuestions.add(getRandomizedAnswersByQuestionId(question.getId()));
		}
		model.addAttribute("answersOfQuestions", answersOfQuestions);
		return answersOfQuestions;
	}

	public List<Question> getShuffledAcceptedQuestionsByCategoryAndSubcategoriesChoosen(QuestionCategory category,
			List<QuestionSubcategory> subcategories) {
		if (isValidInput(category, subcategories)) {
			List<Question> questions = retrieveAcceptedQuestionsByCategoryAndSubCategoriesChoosen(category,
					subcategories);
			List<Question> shuffledAcceptedQuestions = shuffleQuestions(questions);

			return shuffledAcceptedQuestions;
		}

		return Collections.emptyList();
	}

	private boolean isValidInput(QuestionCategory category, List<QuestionSubcategory> subcategories) {
		return category != null && subcategories != null && !subcategories.isEmpty();
	}

	private List<Question> retrieveAcceptedQuestionsByCategoryAndSubCategoriesChoosen(QuestionCategory category,
			List<QuestionSubcategory> subcategories) {
		List<Question> allQuestions = questionRepository.findByCategoryAndSubcategoryIn(category, subcategories);
		List<Question> acceptedQuestions = allQuestions.stream()
				.filter(question -> question.getQuestionStatus() == QuestionStatus.ACCEPTED)
				.collect(Collectors.toList());

		return acceptedQuestions;
	}

	public List<Question> shuffleQuestions(List<Question> acceptedFilteredQuestions) {
		Collections.shuffle(acceptedFilteredQuestions);
		return acceptedFilteredQuestions;
	}

	public List<Answer> getRandomizedAnswersByQuestionId(Long id) {
		return questionRepository.findById(id).get().getShuffledAnswers();
	}

	public List<Answer> getAnswersById(List<Long> answerIds) {
		return answerIds.stream().map(answerRepository::getAnswersById).collect(Collectors.toList());
	}

}

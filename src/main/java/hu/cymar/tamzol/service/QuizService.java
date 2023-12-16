package hu.cymar.tamzol.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.repository.AnswerRepository;
import hu.cymar.tamzol.repository.QuestionRepository;

@Service
public class QuizService implements IQuizCreation {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired 
	AnswerRepository answerRepository;

	public List<Question> getQuestionsByCategoryAndSubcategoriesChoosen(QuestionCategory category,
			List<QuestionSubcategory> subcategories) {
		if (category != null && subcategories != null && !subcategories.isEmpty()) {
			return questionRepository.findByCategoryAndSubcategoryIn(category, subcategories);
		}

		return Collections.emptyList();
	}


	public List<Answer> getAnswersByQuestionId(Long id) {
		return answerRepository.getAnswersByQuestionId(id);
	}

	public List<Question> getFilteredQuestionsAndAnswers(QuestionCategory selectedCategory, List<QuestionSubcategory> subcategories, Model model) {
	    List<Question> questions = getQuestionsByCategoryAndSubcategoriesChoosen(selectedCategory, subcategories);

	    List<Question> filteredQuestions = new ArrayList<>();
	    List<List<Answer>> answersOfQuestions = new ArrayList<>();

	    for (Question question : questions) {
	        filteredQuestions.add(question);
	        answersOfQuestions.add(getAnswersByQuestionId(question.getId()));
	    }
	    model.addAttribute("filteredQuestions", filteredQuestions);
	    model.addAttribute("answersOfQuestions", answersOfQuestions);

	    return filteredQuestions;
	}

}

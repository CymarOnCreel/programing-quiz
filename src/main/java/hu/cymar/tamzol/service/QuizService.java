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

	public List<Answer> getAnswersById(List<Long> answerId) {
		List<Answer> userAnswers=new ArrayList<>();
		for (Long long1 : answerId) {
			userAnswers.add(answerRepository.getAnswersById(long1));
		}
		return userAnswers;
	}
	public List<Question> getFilteredQuestions(QuestionCategory selectedCategory, List<QuestionSubcategory> subcategories, Model model) {
	    List<Question> questions = getQuestionsByCategoryAndSubcategoriesChoosen(selectedCategory, subcategories);
	    List<Question> filteredQuestions = new ArrayList<>();
	    for (Question question : questions) {
	        filteredQuestions.add(question);
	    }
	    model.addAttribute("filteredQuestions", filteredQuestions);
	    return filteredQuestions;
	}
	
	public List<List<Answer>> getAnswersList(Model model, List<Question>filteredQuestions){
		 List<List<Answer>> answersOfQuestions = new ArrayList<>();
		 for (Question question : filteredQuestions) {
		        answersOfQuestions.add(getAnswersByQuestionId(question.getId()));
		    }
		 model.addAttribute("answersOfQuestions", answersOfQuestions);
		 return answersOfQuestions;
	}
}

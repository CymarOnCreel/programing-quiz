package hu.cymar.tamzol.service;

import java.util.List;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionSubcategory;


public interface IQuizCreation {
	List<Question> getQuestionsByCategoryAndSubcategoriesChoosen(QuestionCategory category, List<QuestionSubcategory> subcategories);
	List<Answer> getRandomizedAnswersByQuestionId(Long id);

}

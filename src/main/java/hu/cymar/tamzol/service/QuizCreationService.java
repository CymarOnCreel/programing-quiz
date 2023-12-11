package hu.cymar.tamzol.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.repository.AnswerRepository;
import hu.cymar.tamzol.repository.QuestionCategoryRepository;
import hu.cymar.tamzol.repository.QuestionRepository;
import hu.cymar.tamzol.repository.QuestionSubcategoryRepository;

@Service
public class QuizCreationService implements IQuizCreation {

	private final QuestionCategoryRepository categoryRepository;
	private final QuestionSubcategoryRepository subcategoryRepository;
	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;

	@Autowired
	public QuizCreationService(QuestionCategoryRepository categoryRepository,
			QuestionSubcategoryRepository subcategoryRepository, QuestionRepository questionRepository,
			AnswerRepository answerRepository) {
		this.categoryRepository = categoryRepository;
		this.subcategoryRepository = subcategoryRepository;
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
	}

	@Override
	public List<QuestionCategory> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<QuestionSubcategory> getSubcategoriesByCategoryId(Long categoryId) {
		return subcategoryRepository.findByCategoryId(categoryId);
	}

	@Override
	public QuestionCategory getCategoryById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public QuestionSubcategory getSubcategoryById(Long id) {
		return subcategoryRepository.findById(id).orElse(null);
	}

	@Override
	public List<Question> getQuestionsByCategoryAndSubcategoriesChoosen(QuestionCategory category,
			List<QuestionSubcategory> subcategories) {
		if (category != null && subcategories != null && !subcategories.isEmpty()) {
			return questionRepository.findByCategoryAndSubcategoryIn(category, subcategories);
		}

		return Collections.emptyList();
	}

	@Override
	public List<Answer> getAnswersByQuestionId(Long id) {
		return answerRepository.getAnswersByQuestionId(id);
	}

}

package hu.cymar.tamzol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.repository.QuestionCategoryRepository;
import hu.cymar.tamzol.repository.QuestionSubcategoryRepository;

@Service
public class CategoriesService {

	@Autowired
	QuestionCategoryRepository categoryRepository;
	@Autowired
	QuestionSubcategoryRepository subcategoryRepository;
	

	public List<QuestionCategory> getAllCategories() {
		return categoryRepository.findAll();
	}


	public List<QuestionSubcategory> getSubcategoriesByCategoryId(Long categoryId) {
		return subcategoryRepository.findByCategoryId(categoryId);
	}

	public QuestionCategory getCategoryById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	public QuestionSubcategory getBysubcategoryName(String subcategoryName) {
		return subcategoryRepository.getBysubcategoryName(subcategoryName);
	}

	public QuestionSubcategory getSubcategoryById(Long id) {
		return subcategoryRepository.findById(id).orElse(null);
	}

}

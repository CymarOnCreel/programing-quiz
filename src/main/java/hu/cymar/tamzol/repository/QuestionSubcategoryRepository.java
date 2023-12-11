package hu.cymar.tamzol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cymar.tamzol.model.QuestionSubcategory;

public interface QuestionSubcategoryRepository extends JpaRepository<QuestionSubcategory, Long> {

	List<QuestionSubcategory> findByCategoryId(Long categoryId);
}

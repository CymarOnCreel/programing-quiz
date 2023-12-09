package hu.cymar.tamzol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cymar.tamzol.model.QuestionCategory;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Long>{

}

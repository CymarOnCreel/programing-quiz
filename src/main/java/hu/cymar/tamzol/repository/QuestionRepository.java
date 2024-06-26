package hu.cymar.tamzol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionSubcategory;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategoryAndSubcategoryIn(QuestionCategory category, List<QuestionSubcategory> subcategories);

    @Query("SELECT MAX(q.id) FROM Question q")
    Long findMaxId();
   
}

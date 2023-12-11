package hu.cymar.tamzol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cymar.tamzol.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	List<Answer> getAnswersByQuestionId(Long questionId);
}

package hu.cymar.tamzol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cymar.tamzol.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}

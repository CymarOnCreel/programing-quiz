package hu.cymar.tamzol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cymar.tamzol.model.QuizScore;
import hu.cymar.tamzol.model.User;

public interface QuizScoreRepository extends JpaRepository<QuizScore, Long>{

	List<QuizScore> findByUser(User user);
	
}

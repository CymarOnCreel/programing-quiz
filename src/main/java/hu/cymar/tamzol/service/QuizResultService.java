package hu.cymar.tamzol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cymar.tamzol.model.QuizScore;
import hu.cymar.tamzol.repository.QuizScoreRepository;

@Service
public class QuizResultService {

	 @Autowired
	    private QuizScoreRepository quizScoreRepo;

	    public void saveQuizResult(QuizScore quizScoreObj) {
	    	quizScoreRepo.save(quizScoreObj);
	    }
}

package hu.cymar.tamzol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.repository.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	AnswerRepository ar;
	
	public void saveAnswer(Answer answerObj) {
		ar.save(answerObj);
	}

}

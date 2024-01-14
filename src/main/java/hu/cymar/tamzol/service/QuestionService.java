package hu.cymar.tamzol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.repository.QuestionRepository;

@Service
public class QuestionService {
	
	
	@Autowired
	QuestionRepository qr;
	
	public List<Question> getAll(){
		return qr.findAll();
	}

	public void deleteQuestionById(Long id) {
		qr.deleteById(id);
		
	}
	   public Long getLastQuestionId() {
	        return qr.findMaxId();
	    }
	   
	   public void saveQuestion(Question questionObj) {
		   qr.save(questionObj);
	   }
	   
	   public Optional<Question> findById(Long id) {
		   return qr.findById(id);
	   }
}

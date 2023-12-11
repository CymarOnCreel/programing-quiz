package hu.cymar.tamzol.service;

import java.util.List;

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
	
}

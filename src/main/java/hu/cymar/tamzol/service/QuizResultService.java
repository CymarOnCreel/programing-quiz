package hu.cymar.tamzol.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cymar.tamzol.model.Answer;
import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuizScore;
import hu.cymar.tamzol.model.User;
import hu.cymar.tamzol.repository.AnswerRepository;
import hu.cymar.tamzol.repository.QuizScoreRepository;

@Service
public class QuizResultService {

	 @Autowired
	    private QuizScoreRepository quizScoreRepo;

	   public void saveQuizResult(int questionCount,int rightAnswerCount,QuizScore quizScoreObj, User user, QuestionCategory selectedCategory) {
	        if (quizScoreObj.getQuizDate() == null) {
	            LocalDate quizDate = LocalDate.now();
	            quizScoreObj.setQuizDate(quizDate);
	        }
	        quizScoreObj.setUser(user);
	        quizScoreObj.setCategory(selectedCategory);
	        quizScoreObj.setQuestionCount(questionCount);
	        quizScoreObj.setRightAnswerCount(rightAnswerCount);
	        quizScoreRepo.save(quizScoreObj);
	    }
	    
	    public int getQuestionCount(List<Question> questions) {
			return questions.size();
		}
	    public int getRightAnswerCount(List<Long> usersAnswersId, List<Answer> allAnswers) {
	    	if (usersAnswersId == null || allAnswers == null) {
	            throw new IllegalArgumentException("usersAnswersId or allAnswers is null");
	        }
	    	
	    	int countRightAnswers = (int)usersAnswersId.stream()
	                .filter(userAnswerId -> isAnswerCorrect(userAnswerId, allAnswers))
	                .count();

	        return countRightAnswers;
	    }

	    private boolean isAnswerCorrect(Long userAnswerId, List<Answer> allAnswers) {
	        return allAnswers.stream()
	                .filter(answer -> answer.getId().equals(userAnswerId))
	                .anyMatch(Answer::isCorrect);
	    }

		public List<QuizScore> findByUser(User user) {
					return quizScoreRepo.findByUser(user);
		}
		
}

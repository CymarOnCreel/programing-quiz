package hu.cymar.tamzol.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quiz_scores")
public class QuizScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private QuestionCategory category;

	@Column(name = "question_count")
	private int questionCount;

	@Column(name = "right_answer_count")
	private int rightAnswerCount;
	@Column(nullable=false)
	private LocalDate quizDate;

	public QuizScore(Long id, User user, QuestionCategory category,  int questionCount,
			int rightAnswerCount,LocalDate quizDate) {
		super();
		this.id = id;
		this.user = user;
		this.category = category;
		this.questionCount = questionCount;
		this.rightAnswerCount = rightAnswerCount;
		this.quizDate=quizDate;
	}

	public QuizScore() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public QuestionCategory getCategory() {
		return category;
	}

	public void setCategory(QuestionCategory category) {
		this.category = category;
	}


	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public int getRightAnswerCount() {
		return rightAnswerCount;
	}

	public void setRightAnswerCount(int rightAnswerCount) {
		this.rightAnswerCount = rightAnswerCount;
	}

	public LocalDate getQuizDate() {
		return quizDate;
	}

	public void setQuizDate(LocalDate quizDate) {
		this.quizDate = quizDate;
	}
	public double quizScoreResultPercentage(int questionCount, int rightAnswerCount) {
		return ((double)rightAnswerCount/questionCount)*100;
	}
	
	public boolean isQuizPassed(double minimumPercentageForQuizPass) {
	    double percentage = quizScoreResultPercentage(questionCount, rightAnswerCount);
	    return percentage >= minimumPercentageForQuizPass;
	}
}

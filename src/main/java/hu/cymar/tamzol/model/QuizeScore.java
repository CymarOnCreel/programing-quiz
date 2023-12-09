package hu.cymar.tamzol.model;

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
public class QuizeScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private QuestionCategory category;

	@ManyToOne
	@JoinColumn(name = "subcategory_id")
	private QuestionSubcategory subcategory;

	@Column(name = "question_count")
	private int questionCount;

	@Column(name = "right_answer_count")
	private int rightAnswerCount;

	public QuizeScore(Long id, User user, QuestionCategory category, QuestionSubcategory subcategory, int questionCount,
			int rightAnswerCount) {
		super();
		this.id = id;
		this.user = user;
		this.category = category;
		this.subcategory = subcategory;
		this.questionCount = questionCount;
		this.rightAnswerCount = rightAnswerCount;
	}

	public QuizeScore() {
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

	public QuestionSubcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(QuestionSubcategory subcategory) {
		this.subcategory = subcategory;
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

}

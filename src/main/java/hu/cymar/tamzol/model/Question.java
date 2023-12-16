package hu.cymar.tamzol.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "question_text")
	private String questionText;

	@Enumerated(EnumType.STRING)
	@Column(name = "question_status", nullable = false)
	private QuestionStatus questionStatus = QuestionStatus.IN_PROGRESS;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private QuestionCategory category;

	@ManyToOne
	@JoinColumn(name = "subcategory_id")
	private QuestionSubcategory subcategory;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answers;

	public Question() {
	}

	public Question(Long id, String questionText, QuestionCategory category, QuestionSubcategory subcategory) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.category = category;
		this.subcategory = subcategory;
	}

	public Question(Long id, String questionText, QuestionStatus questionStatus, QuestionCategory category,
			QuestionSubcategory subcategory) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.questionStatus = questionStatus;
		this.category = category;
		this.subcategory = subcategory;
	}

	public QuestionStatus getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(QuestionStatus questionStatus) {
		this.questionStatus = questionStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Answer> getShuffledAnswers() {
		List<Answer> answers = this.getAnswers();
		List<Answer> shuffledAnswers = new ArrayList<>(answers);
		Collections.shuffle(shuffledAnswers);
		return shuffledAnswers;
	}

}

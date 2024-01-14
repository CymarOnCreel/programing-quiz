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
@Table(name="answers")
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="answer_text")
	private String answerText;
	
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    
    @Column(name="is_correct", nullable=false, columnDefinition = "TINYINT default 0")
    private boolean isCorrect;

	public Answer(Long id, String answerText, Question question, boolean isCorrect) {
		super();
		this.id = id;
		this.answerText = answerText;
		this.question = question;
		this.isCorrect = isCorrect;
	}

	public Answer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerText=" + answerText + ", question=" + question + ", isCorrect=" + isCorrect
				+ "]";
	}
	
	
}

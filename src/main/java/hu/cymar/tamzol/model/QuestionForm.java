package hu.cymar.tamzol.model;

import java.util.Optional;

public class QuestionForm {
	private String questionText;
	private String textAnswer0;
	private String textAnswer1;
	private String textAnswer2;
	private String textAnswer3;
	private Optional<String> correctAnswer;

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getTextAnswer1() {
		return textAnswer1;
	}

	public void setTextAnswer1(String textAnswer1) {
		this.textAnswer1 = textAnswer1;
	}

	public String getTextAnswer2() {
		return textAnswer2;
	}

	public void setTextAnswer2(String textAnswer2) {
		this.textAnswer2 = textAnswer2;
	}

	public String getTextAnswer3() {
		return textAnswer3;
	}

	public void setTextAnswer3(String textAnswer3) {
		this.textAnswer3 = textAnswer3;
	}

	public String getTextAnswer0() {
		return textAnswer0;
	}

	public void setTextAnswer0(String textAnswer0) {
		this.textAnswer0 = textAnswer0;
	}

	public Optional<String> getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = Optional.ofNullable(correctAnswer);
	}

}

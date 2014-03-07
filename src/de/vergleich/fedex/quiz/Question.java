package de.vergleich.fedex.quiz;

import java.util.ArrayList;
import java.util.List;

public class Question {

	private String questionText;
	private String correctAnswerText;
	private List<Answer> answers;
	private Integer coins;
	private String expectedQRCode;

	public Question(String questionText, Integer coins) {
		this.questionText = questionText;
		this.coins = coins;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public String getCorrectAnswerText() {
		return correctAnswerText;
	}

	public Question setCorrectAnswerText(String correctAnswerText) {
		this.correctAnswerText = correctAnswerText;
		return this;
	}

	public String getExpectedQRCode() {
		return expectedQRCode;
	}

	public Question setExpectedQRCode(String expectedQRCode) {
		this.expectedQRCode = expectedQRCode;
		return this;
	}

	public Question addAnswer(Answer answer) {
		if (answers == null)
			answers = new ArrayList<Answer>();
		answers.add(answer);
		return this;
	}
}

package de.vergleich.fedex.quiz;

public class Answer {
	
	public boolean rightAnswer;
	public String text;
	
	public Answer(String text, boolean rightAnswer) {
		this.text = text;
		this.rightAnswer = rightAnswer;
	}

	public boolean isRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(boolean rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}

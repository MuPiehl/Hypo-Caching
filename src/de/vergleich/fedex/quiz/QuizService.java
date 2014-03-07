package de.vergleich.fedex.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizService {

	private static QuizService instance;
	private List<Question> questions;
	private int currentIndex;

	private QuizService() {
		questions = new ArrayList<Question>();
		questions.add(new Question("Warum?")
				.addAnswer(new Answer("Darum!", false))
				.addAnswer(new Answer("Weißt du doch nicht", true))
				.addAnswer(new Answer("Mir doch egal", false))
				.addAnswer(new Answer("Joa egal", false)));
		questions.add(new Question("Warum nicht?")
				.addAnswer(new Answer("Darum!", false))
				.addAnswer(new Answer("Egal", false))
				.addAnswer(new Answer("Weil es eben so ist", true))
				.addAnswer(new Answer("Joa egal", false)));

		currentIndex = 0;
	}

	public static QuizService getInstance() {
		if (instance == null)
			instance = new QuizService();
		return instance;
	}

	public Question getNextQuestion() {
		final Question question = questions.get(currentIndex++);

		if (currentIndex >= questions.size()) {
			currentIndex = 0;
		}

		return question;
	}
}

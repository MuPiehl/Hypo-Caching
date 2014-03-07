package de.vergleich.fedex.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizService {

	private static QuizService instance;
	private List<Question> questions;
	private int currentIndex;

	private QuizService() {
		questions = new ArrayList<Question>();
		questions
				.add(new Question(
						"Wer besucht die Kollegin am Empfang täglich?", 1)
						.addAnswer(new Answer("Der Weihnachtsmann", false))
						.addAnswer(new Answer("Der Zonk", false))
						.addAnswer(new Answer("Blitz Reinigungsservice", false))
						.addAnswer(new Answer("Der Postmann", true))
						.setCorrectAnswerText(
								"Richtig! Deine nächste Station ist bei Michaela am Empfang, lass Dir dort einen QR-Code zeigen.")
						.setExpectedQRCode("http://www.youtube.com/watch?v=GvRuD2cAP8g#895965996301347121687526676682950120014487528978682706480679915756456268682682597341345448000000005362597374986042682340672063954962259597470681346661341329948256159003517341298342965810565681160000127643139341322"));
		questions
				.add(new Question(
						"Womit verbringen die Lübecker Kollegen ihre Pausen?",
						1)
						.addAnswer(new Answer("Eiskunstlauf", false))
						.addAnswer(new Answer("Tischkicker", true))
						.addAnswer(new Answer("Wrestling", false))
						.addAnswer(new Answer("Kugelstoßen", false))
						.setCorrectAnswerText(
								"Richtig! Deine nächste Station ist am Tischkicker im Casino.")
						.setExpectedQRCode("bit.ly/1gd5TPq#513279087189312605661681347911960006006017333342679237490682560096258000000405341681289105661329682648000956035445010701794827238108362351762522677341341600000128000342959309341638678685266015944031533981341340672619938690698088000000000341336701341954677362682128007958512412858661"));
		questions
				.add(new Question(
						"Was gibt es NICHT in unseren roten Kühlschränken?", 1)
						.addAnswer(new Answer("Milch", false))
						.addAnswer(new Answer("Champagner & Kaviar", true))
						.addAnswer(new Answer("Joghurt", false))
						.addAnswer(new Answer("Pudding", false))
						.setCorrectAnswerText(
								"Richtig! Deine nächste Station ist der rote Kühlschrank im Casino.")
						.setExpectedQRCode("http://www.youtube.com/watch?v=QBtciu6Onnc"));

		currentIndex = 0;
	}

	public static QuizService getInstance() {
		if (instance == null)
			instance = new QuizService();
		return instance;
	}

	public void nextQuestion() {
		currentIndex++;
	}

	public Question getCurrentQuestion() {
		if (currentIndex >= questions.size())
			return null;
		
		return questions.get(currentIndex);
	}
}

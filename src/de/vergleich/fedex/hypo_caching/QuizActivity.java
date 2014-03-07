package de.vergleich.fedex.hypo_caching;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import de.vergleich.fedex.quiz.Answer;
import de.vergleich.fedex.quiz.Question;
import de.vergleich.fedex.quiz.QuizService;

public class QuizActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_quiz);
		
		final Question nextQuestion = QuizService.getInstance().getNextQuestion();
		
		final TextView questionText = (TextView) findViewById(R.id.question_text);
		questionText.setText(nextQuestion.getQuestionText());
		
		final Button answerButton1 = (Button) findViewById(R.id.answer_button1);
		answerButton1.setText(nextQuestion.getAnswers().get(0).getText());
		answerButton1.setOnClickListener(new Evaluator(nextQuestion.getAnswers().get(0)));
		
		final Button answerButton2 = (Button) findViewById(R.id.answer_button2);
		answerButton2.setText(nextQuestion.getAnswers().get(1).getText());
		answerButton2.setOnClickListener(new Evaluator(nextQuestion.getAnswers().get(1)));
		
		final Button answerButton3 = (Button) findViewById(R.id.answer_button3);
		answerButton3.setText(nextQuestion.getAnswers().get(2).getText());
		answerButton3.setOnClickListener(new Evaluator(nextQuestion.getAnswers().get(2)));
		
		final Button answerButton4 = (Button) findViewById(R.id.answer_button4);
		answerButton4.setText(nextQuestion.getAnswers().get(3).getText());
		answerButton4.setOnClickListener(new Evaluator(nextQuestion.getAnswers().get(3)));
	}
	
	private class Evaluator implements OnClickListener {
		
		private final Answer answer;
		
		public Evaluator(final Answer answer) {
			this.answer = answer;
		}

		@Override
		public void onClick(View arg0) {
			if (this.answer.isRightAnswer()) {
				Toast.makeText(QuizActivity.this, "Korrekt! TODO: Auslagern in String", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(QuizActivity.this, "FALSCH! TODO: Auslagern in String", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
}

package de.vergleich.fedex.hypo_caching;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Visibility;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.vergleich.fedex.backendservice.BackendService;
import de.vergleich.fedex.backendservice.User;
import de.vergleich.fedex.quiz.Question;
import de.vergleich.fedex.quiz.QuizService;

public class QuizActivity extends Activity {

	private Button answerButton1;
	private Button answerButton2;
	private Button answerButton3;
	private Button answerButton4;
	private ImageView zonk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_quiz);

		answerButton1 = (Button) findViewById(R.id.answer_button1);
		answerButton2 = (Button) findViewById(R.id.answer_button2);
		answerButton3 = (Button) findViewById(R.id.answer_button3);
		answerButton4 = (Button) findViewById(R.id.answer_button4);
		zonk = (ImageView) findViewById(R.id.zonk);

		initCurrentQuestion();
	}

	private void initCurrentQuestion() {
		final Question currentQuestion = QuizService.getInstance()
				.getCurrentQuestion();

		if (currentQuestion == null) {
			finish();
			return;
		}

		final TextView questionText = (TextView) findViewById(R.id.question_text);
		questionText.setText(currentQuestion.getQuestionText());

		answerButton1.setText(currentQuestion.getAnswers().get(0).getText());
		answerButton1.setOnClickListener(new Evaluator(currentQuestion, 0));

		answerButton2.setText(currentQuestion.getAnswers().get(1).getText());
		answerButton2.setOnClickListener(new Evaluator(currentQuestion, 1));

		answerButton3.setText(currentQuestion.getAnswers().get(2).getText());
		answerButton3.setOnClickListener(new Evaluator(currentQuestion, 2));

		answerButton4.setText(currentQuestion.getAnswers().get(3).getText());
		answerButton4.setOnClickListener(new Evaluator(currentQuestion, 3));

		updateCoinDisplay();
	}

	private void updateCoinDisplay() {
		final User user = BackendService.getInstance().getUser();
		final TextView coins = (TextView) findViewById(R.id.coin_amount);
		coins.setText(String.valueOf(user.getCoins()));
	}

	private class Evaluator implements OnClickListener {

		private final Question question;
		private final int index;

		public Evaluator(final Question question, final int index) {
			this.question = question;
			this.index = index;
		}

		@Override
		public void onClick(View arg0) {
			if (this.question.getAnswers().get(index).isRightAnswer()) {
				Toast.makeText(QuizActivity.this,
						this.question.getCorrectAnswerText(),
						Toast.LENGTH_SHORT).show();
				BackendService.getInstance().getUser()
						.addCoins(this.question.getCoins());
				new UpdateUserDataTask().execute();

				// new LoadNextQuestion().execute();
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE, ONE_D_MODE");
				startActivityForResult(intent, 0);
			} else {
				Toast.makeText(QuizActivity.this,
						getResources().getString(R.string.quiz_falsch),
						Toast.LENGTH_SHORT).show();
				new ShowZonk().execute();
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				// Handle successful scan
				String contents = data.getStringExtra("SCAN_RESULT");

				if (contents.equals(QuizService.getInstance()
						.getCurrentQuestion().getExpectedQRCode())) {
					
					Toast.makeText(QuizActivity.this,
							getResources().getString(R.string.quiz_qr_richtig),
							Toast.LENGTH_SHORT).show();
					
					BackendService.getInstance().getUser().addCoins(50);
					new UpdateUserDataTask().execute();
					
					new LoadNextQuestion().execute();
				} else {
					Toast.makeText(QuizActivity.this,
							getResources().getString(R.string.quiz_qr_falsch),
							Toast.LENGTH_SHORT).show();
				}
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
				startActivity(new Intent(this, MainActivity.class));
				finish();
			}
		}
	}
	
	private class ShowZonk extends AsyncTask<Void, Void, Void> {
		
		@Override
		protected Void doInBackground(Void... arg0) {
			Log.e("ASDF", "Test");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			zonk.setVisibility(View.VISIBLE);
			answerButton1.setVisibility(View.GONE);
			answerButton2.setVisibility(View.GONE);
			answerButton3.setVisibility(View.GONE);
			answerButton4.setVisibility(View.GONE);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			zonk.setVisibility(View.GONE);
			answerButton1.setVisibility(View.VISIBLE);
			answerButton2.setVisibility(View.VISIBLE);
			answerButton3.setVisibility(View.VISIBLE);
			answerButton4.setVisibility(View.VISIBLE);
		}
		
	}

	private class LoadNextQuestion extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			answerButton1.setEnabled(false);
			answerButton2.setEnabled(false);
			answerButton3.setEnabled(false);
			answerButton4.setEnabled(false);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			answerButton1.setEnabled(true);
			answerButton2.setEnabled(true);
			answerButton3.setEnabled(true);
			answerButton4.setEnabled(true);

			QuizService.getInstance().nextQuestion();
			initCurrentQuestion();
		}
	}
}

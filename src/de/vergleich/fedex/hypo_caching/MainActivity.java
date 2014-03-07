package de.vergleich.fedex.hypo_caching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.vergleich.fedex.backendservice.BackendService;
import de.vergleich.fedex.backendservice.User;
import de.vergleich.fedex.chopatree.ChopATreeActivity;

public class MainActivity extends Activity {

	private static final int REQUEST_CHOPATREE = 1;
	private static final int COINS_FOR_CHOPATREE = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		updateCoinDisplay();

		final ImageButton bank = (ImageButton) findViewById(R.id.bank);
		final ImageButton qrCode = (ImageButton) findViewById(R.id.qr_code);
		final ImageButton quiz = (ImageButton) findViewById(R.id.quiz);
		final ImageButton chopATree = (ImageButton) findViewById(R.id.chopatree);
		
	//	final ImageView savanne = (ImageView) findViewById
		
		bank.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this,
						WhackAMoleActivity.class));
			}
		});

		qrCode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this,
						KaufmenueActivity.class));
			}
		});

		chopATree.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivityForResult(new Intent(MainActivity.this,
						ChopATreeActivity.class), REQUEST_CHOPATREE);
			}
		});
		
		quiz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, QuizActivity.class));
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CHOPATREE) {
			switch (resultCode) {
			case RESULT_OK:
				if (data != null) {
					BackendService.getInstance().getUser().addCoins(COINS_FOR_CHOPATREE);
					updateCoinDisplay();
					showToastMessage(R.string.chopatree_msg_wood_done);
				}
				break;
			case RESULT_CANCELED:
				showToastMessage(R.string.chopatree_msg_cancel);
				break;
			}
		}
	}

	private void showToastMessage(int id) {
		showToastMessage(getString(id));
	}

	private void showToastMessage(String message) {
		//System.out.println(message); // FIXME: Das hier ist nur zum Spielen :)
		Toast toast = Toast.makeText(this.getApplicationContext(), message,
				Toast.LENGTH_LONG);
		toast.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateCoinDisplay();
	}

	private void updateCoinDisplay() {
		final User user = BackendService.getInstance().getUser();
		final TextView coins = (TextView) findViewById(R.id.coin_amount);
		coins.setText(String.valueOf(user.getCoins()));

	}
}

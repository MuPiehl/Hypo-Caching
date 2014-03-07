package de.vergleich.fedex.hypo_caching;

import de.vergleich.fedex.backendservice.BackendService;
import de.vergleich.fedex.backendservice.User;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class KaufmenueActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kaufmenue);

		updateCoinDisplay();

		final ImageButton hausKaufen = (ImageButton) findViewById(R.id.haus_kaufen);
		hausKaufen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (BackendService.getInstance().getUser().getCoins() >= 35) {
					Toast.makeText(KaufmenueActivity.this,
							"Kaufe Haus für 35 Coins", Toast.LENGTH_SHORT)
							.show();
					BackendService.getInstance().getUser().addCoins(-35);
					updateCoinDisplay();
				} else {
					Toast.makeText(
							KaufmenueActivity.this,
							"Sie haben nicht hinreichend viele Coins!!!!11einseinself",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		final ImageButton baumKaufen = (ImageButton) findViewById(R.id.baum_kaufen);
		baumKaufen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (BackendService.getInstance().getUser().getCoins() >= 35) {
					Toast.makeText(KaufmenueActivity.this,
							"Kaufe Baum für 35 Coins", Toast.LENGTH_SHORT)
							.show();
					BackendService.getInstance().getUser().addCoins(-35);
					updateCoinDisplay();

				} else {
					Toast.makeText(
							KaufmenueActivity.this,
							"Sie haben nicht hinreichend viele Coins!!!!11einseinself",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
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

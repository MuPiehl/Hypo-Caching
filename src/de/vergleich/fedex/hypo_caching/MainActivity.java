package de.vergleich.fedex.hypo_caching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import de.vergleich.fedex.backendservice.BackendService;
import de.vergleich.fedex.backendservice.User;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final User user = BackendService.getInstance().getUser();
		
		final TextView coins = (TextView) findViewById(R.id.coin_amount);
		
		coins.setText(String.valueOf(user.getCoins()));
		
		final ImageButton bank = (ImageButton) findViewById(R.id.bank);
		final ImageButton qrCode = (ImageButton) findViewById(R.id.qr_code);
		
		bank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, WhackAMoleActivity.class));
			}
		});
		
		qrCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				System.exit(1);
			}
		});
	}
}

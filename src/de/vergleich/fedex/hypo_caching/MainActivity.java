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
import de.vergleich.fedex.chopatree.ChopATreeActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		updateCoinDisplay();
		
		final ImageButton bank = (ImageButton) findViewById(R.id.bank);
		final ImageButton qrCode = (ImageButton) findViewById(R.id.qr_code);
		final ImageButton chopATree = (ImageButton) findViewById(R.id.chopatree);
		
		bank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, WhackAMoleActivity.class));
			}
		});
		
		qrCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, KaufmenueActivity.class));
			}
		});

		chopATree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, ChopATreeActivity.class));
			}
		});
	
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
}

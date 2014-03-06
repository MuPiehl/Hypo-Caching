package de.vergleich.fedex.hypo_caching;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		final ImageButton bank = (ImageButton) findViewById(R.id.bank);
		final ImageButton qrCode = (ImageButton) findViewById(R.id.qr_code);
		
		bank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				System.exit(0);
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

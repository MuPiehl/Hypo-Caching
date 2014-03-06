package de.vergleich.fedex.hypo_caching;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		final Button bank = (Button) findViewById(R.id.bank);
		final Button qrCode = (Button) findViewById(R.id.qr_code);
		
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

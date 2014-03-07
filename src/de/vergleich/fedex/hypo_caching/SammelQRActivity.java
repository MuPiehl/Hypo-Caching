package de.vergleich.fedex.hypo_caching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SammelQRActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sammel_qr);
		

		Button  button = (Button) findViewById(R.id.button_sammel_qr_start);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				try {
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE, ONE_D_MODE");
					startActivityForResult(intent, 0);
				} catch (Exception e) {
					TextView textView = (TextView) findViewById(R.id.qr_erg_textView);
					textView.setText("- Scan kann nicht gestartet werden -");
				}
			
			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sammel_qr, menu);
		return true;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			String contents = "";
			String format = "";
			if (resultCode == RESULT_OK) {
				contents = intent.getStringExtra("SCAN_RESULT");
				format = "Format: "
						+ intent.getStringExtra("SCAN_RESULT_FORMAT") + "\n";
				// Handle successful scan
				Log.e("QR", contents);
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
				contents = "- Scan abgebrochen -";
			}
			TextView textView = (TextView) findViewById(R.id.qr_erg_textView);
			textView.setText(format + contents);
		}
	}
	


}

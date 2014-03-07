package de.vergleich.fedex.hypo_caching;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WhackAMoleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whack_amole);
		

		Button  button = (Button) findViewById(R.id.button_to_qr_whack);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
				public void onClick(View arg0) {
				startActivity(new Intent(WhackAMoleActivity.this, SammelQRActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.whack_amole, menu);
		return true;
	}

}

package de.vergleich.fedex.hypo_caching;

import de.vergleich.fedex.backendservice.BackendService;
import de.vergleich.fedex.hypo_caching.whackamole.Playground;
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
		
		Button quitBtn = (Button) findViewById(R.id.playground1_quit_btn);
		quitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Playground  playground = (Playground) findViewById(R.id.playground1);
				playground.stop();	
				playground.invalidate();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.whack_amole, menu);
		return true;
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		new UpdateUserDataTask().execute();
	}

}

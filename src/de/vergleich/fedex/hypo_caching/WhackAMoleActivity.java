package de.vergleich.fedex.hypo_caching;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WhackAMoleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whack_amole);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.whack_amole, menu);
		return true;
	}

}
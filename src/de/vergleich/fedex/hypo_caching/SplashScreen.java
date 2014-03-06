package de.vergleich.fedex.hypo_caching;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		new SplashScreenTask().execute();
		// TODO: Backend-Service anfragen
	}

	private class SplashScreenTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				Thread.sleep(2500);

			} catch (InterruptedException e) {
				Log.e("SPLASH", e.toString());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			startActivity(new Intent(SplashScreen.this, MainActivity.class));
			finish();
		}

	}
}

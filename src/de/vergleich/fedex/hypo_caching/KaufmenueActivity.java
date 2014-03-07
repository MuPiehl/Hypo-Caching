package de.vergleich.fedex.hypo_caching;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import de.vergleich.fedex.backendservice.BackendService;
import de.vergleich.fedex.backendservice.Element;
import de.vergleich.fedex.backendservice.User;

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
				tryToBuy("Haus", 45);
			}
		});

		final ImageButton baumKaufen = (ImageButton) findViewById(R.id.baum_kaufen);
		baumKaufen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				tryToBuy("Baum", 35);
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

	private void tryToBuy(final String elementName, final Integer coins) {
		if (BackendService.getInstance().getUser().getCoins() >= coins) {
			Toast.makeText(
					KaufmenueActivity.this,
					getResources().getString(
							R.string.kaufmenue_kauf_erfolgreich, elementName,
							coins), Toast.LENGTH_SHORT).show();
			
			if ("Haus".equals(elementName)) {
				if (BackendService.getInstance().getUser().getHausFortschritt() == Element.NICHTS)
					BackendService.getInstance().getUser().addElement(Element.FUNDAMENT);
				else
				if (BackendService.getInstance().getUser().getHausFortschritt() == Element.FUNDAMENT)
					BackendService.getInstance().getUser().addElement(Element.ROHBAU);
				else
				if (BackendService.getInstance().getUser().getHausFortschritt() == Element.ROHBAU)
					BackendService.getInstance().getUser().addElement(Element.ROHBAU_DACH);
				else
				if (BackendService.getInstance().getUser().getHausFortschritt() == Element.ROHBAU_DACH)
					BackendService.getInstance().getUser().addElement(Element.HAUS);
			}
			else {
				BackendService.getInstance().getUser().addElement(Element.BAUM);
			}
			BackendService.getInstance().getUser().addCoins(-coins);
			new UpdateUserDataTask().execute();
			updateCoinDisplay();

		} else {

			Toast.makeText(
					KaufmenueActivity.this,
					getResources().getString(
							R.string.kaufmenue_nicht_genug_coins),
					Toast.LENGTH_SHORT).show();
		}
	}

}

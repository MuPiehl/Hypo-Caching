package de.vergleich.fedex.hypo_caching;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import de.vergleich.fedex.backendservice.BackendService;
import de.vergleich.fedex.backendservice.Element;

public class HausActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_haus);

		final ImageView fundament = (ImageView) findViewById(R.id.haus_fundament);
		final ImageView rohbau = (ImageView) findViewById(R.id.haus_rohbau);
		final ImageView rohbauDach = (ImageView) findViewById(R.id.haus_rohbau_dach);
		final ImageView fertig = (ImageView) findViewById(R.id.haus_fertig);
		final ImageView baum = (ImageView) findViewById(R.id.baum);

		fundament.setVisibility(View.GONE);
		rohbau.setVisibility(View.GONE);
		rohbauDach.setVisibility(View.GONE);
		fertig.setVisibility(View.GONE);

		switch (BackendService.getInstance().getUser().getHausFortschritt()) {
		case FUNDAMENT:
			fundament.setVisibility(View.VISIBLE);
			break;
		case ROHBAU:
			rohbau.setVisibility(View.VISIBLE);
			break;
		case ROHBAU_DACH:
			rohbauDach.setVisibility(View.VISIBLE);
			break;
		case HAUS:
			fertig.setVisibility(View.VISIBLE);
			break;
		default:
		}
		
		if (BackendService.getInstance().getUser().elementExists(Element.BAUM)) {
			baum.setVisibility(View.VISIBLE);
		}
		else {
			baum.setVisibility(View.GONE);
		}
	}

}

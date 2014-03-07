package de.vergleich.fedex.hypo_caching;

import de.vergleich.fedex.backendservice.BackendService;
import android.os.AsyncTask;

public class UpdateUserDataTask extends AsyncTask<Void, Void, Void>{

	@Override
	protected Void doInBackground(Void... arg0) {
		BackendService.getInstance().post(BackendService.getInstance().getUser());
		
		return null;
	}

}

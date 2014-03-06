package de.vergleich.fedex.backendservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class BackendService {

	private static final String NEWLINE = System.getProperty("line.separator");
	private static final String TAG = "BackendService";
	private static final String ACCEPTANCE_MIME = "application/json";
	private static final String REST_SERVICE_URL = "http://localhost:8080/rest/";

	private HttpClient httpClient = new DefaultHttpClient();
	private HttpGet httpGet;
	private User user;

	private static BackendService instance = null;

	public static BackendService getInstance() {
		if (instance == null) {
			instance = new BackendService();
		}

		return instance;
	}

	public void put(User user) {
		JSONObject json = new JSONObject();
		HttpClient httpclient;
		HttpPut request = null;
		try {
			json.accumulate("id", user.getId());
			json.accumulate("coins", user.getCoins());
			httpclient = new DefaultHttpClient();
			request = new HttpPut(REST_SERVICE_URL);
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType(ACCEPTANCE_MIME);

			request.setEntity(s);
			request.addHeader("accept", ACCEPTANCE_MIME);
			httpClient.execute(request);

		} catch (JSONException e) {
			Log.e(TAG, e.toString());
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, e.toString());
		} catch (ClientProtocolException e) {
			Log.e(TAG, e.toString());
		} catch (IOException e) {
			Log.e(TAG, e.toString());
		}

	}

	public void get(String id) {
		JSONObject result = null;
		httpGet = new HttpGet(REST_SERVICE_URL + id);
		httpGet.addHeader("accept", ACCEPTANCE_MIME);

		BufferedReader br = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream is = entity.getContent();
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				String currentLine;
				StringBuilder sb = new StringBuilder();
				while ((currentLine = br.readLine()) != null) {
					sb.append(currentLine).append(NEWLINE);
				}
				Log.d(TAG, "Received " + sb.toString());
				// 03-06 21:12:59.800: E/BackendService(19914): ID: 357800059588758

				Log.e(TAG, "ID: " + id);
				// TODO: TestJSON austauschen
				result = new JSONObject("{\"coins\":175}");
				br.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		User user = new User();
		user.setId(id);
		if (result != null) {
			try {
				user.setCoins(Integer.valueOf(result.getInt("coins")));
			} catch (JSONException e) {
				Log.e(TAG, e.toString());
			}
		} else {
			user.setCoins(0);
		}

		this.user = user;
	}

	public User getUser() {
		return user;
	}
}

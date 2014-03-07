package de.vergleich.fedex.backendservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

public class BackendService {

	private static final String NEWLINE = System.getProperty("line.separator");
	private static final String TAG = "BackendService";
	private static final String ACCEPTANCE_MIME = "application/json";
	private static final String REST_SERVICE_URL = "http://79.125.119.9:8080/hypo-caching-rest/services/users/";

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

	public void post(User user) {
		JSONObject json = new JSONObject();
		HttpClient httpclient;
		HttpPost request = null;
		try {
			json.accumulate("imei", user.getImei());
			json.accumulate("coins", user.getCoins());
			JSONArray arr = new JSONArray(user.getElemente());
			json.accumulate("elemente", arr);
//			Log.e(TAG, json.toString());
			httpclient = new DefaultHttpClient();
			request = new HttpPost(REST_SERVICE_URL);
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType(ACCEPTANCE_MIME);

			request.setEntity(s);
			request.addHeader("accept", ACCEPTANCE_MIME);
			httpClient.execute(request);

		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}

	}

	public void get(String imei) {
		JSONObject result = null;
		Log.e(TAG, "Request: " + REST_SERVICE_URL + imei);
		httpGet = new HttpGet(REST_SERVICE_URL + imei);
		httpGet.addHeader("accept", ACCEPTANCE_MIME);

		BufferedReader br = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			Log.e(TAG, "getEntity: " + entity);

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
				// TODO: TestJSON austauschen
				result = new JSONObject(sb.toString());
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
		if (result != null) {
			try {
				user.setImei(String.valueOf(result.get("imei")));
				user.setCoins(Integer.valueOf(result.getInt("coins")));
				final JSONArray jsonArray = result.getJSONArray("elemente");
				Log.e(TAG, jsonArray.toString());
				for (int i = 0; i < jsonArray.length(); i++) {
					user.addElement(Element.valueOf(String.valueOf(jsonArray.get(i)).toUpperCase(Locale.GERMAN)));
					Log.d(TAG, Element.valueOf(String.valueOf(jsonArray.get(i)).toUpperCase(Locale.GERMAN)).toString());
				}
				
				for(Element e : user.getElemente()) {
					Log.e(TAG, e.toString());
				}
			} catch (Exception e) {
				Log.e(TAG, e.toString());
			}
		} else {
			user.setImei(imei);
			user.setCoins(0);
		}

		this.user = user;
	}

	public User getUser() {
		return user;
	}
}

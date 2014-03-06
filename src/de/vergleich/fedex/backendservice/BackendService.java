package de.vergleich.fedex.backendservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

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
	
	private static final String TAG = "BackendService";
	private static final String ACCEPTANCE_MIME = "application/json";
	private static final String REST_SERVICE_URL = "localhost:8080/rest/";
	
	private HttpClient httpClient = new DefaultHttpClient();
	private HttpGet httpGet;
	
	private static BackendService instance = null;
	
	public static BackendService getInstance() {
		if (instance == null) {
			instance = new BackendService();
		}
		
		return instance;
	}
	
	private void put(User user) {
		JSONObject json = new JSONObject();
		HttpClient httpclient;
		HttpPut request = null;
		try {
			json.accumulate("id", user.getId());
			json.accumulate("name", user.getName());
			json.accumulate("coins", user.getCoins());
			httpclient = new DefaultHttpClient();
	        request = new HttpPut(REST_SERVICE_URL);
	        StringEntity s = new StringEntity(json.toString());
	        s.setContentEncoding("UTF-8");
	        s.setContentType(ACCEPTANCE_MIME);

	        request.setEntity(s);
	        request.addHeader("accept", ACCEPTANCE_MIME);
			httpClient.execute(request);
		
		} catch (JSONException  e) {
			Log.e(TAG, e.toString());
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, e.toString());
		} catch (ClientProtocolException e) {
			Log.e(TAG, e.toString());
		} catch (IOException e) {
			Log.e(TAG, e.toString());
		}
		
	
		
	}
	
	private User get(Integer id) {
		JSONObject result = null;
		httpGet = new HttpGet(REST_SERVICE_URL+id);
		httpGet.addHeader("accept", ACCEPTANCE_MIME);
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			if(entity != null) {
				InputStream is = entity.getContent();
				result = new JSONObject(is.toString()); 
				is.close();
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user = new User();
		if (result != null) {
			try {
				user.setId(Integer.valueOf(result.getInt("id")));
				user.setName(String.valueOf(result.get("name")));
				user.setCoins(Integer.valueOf(result.getInt("coins")));
			} catch (JSONException e) {
				Log.e(TAG, e.toString());
			}
		}
		
		return user;
	}

}

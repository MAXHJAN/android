package mx.hnust.sellvegetable.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Registernet {

	public Registernet() {
		// TODO Auto-generated constructor stub
	}
	
	
	public JSONObject senddata(final String username,final String password,final String serverUrl)
	{
		JSONObject json = null;
				Log.d("yanghongbing", "start network!");
				HttpClient client = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(serverUrl);
				List<NameValuePair> params = new ArrayList<NameValuePair>(); 
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("password", password));
				
				HttpResponse httpResponse = null;
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
					httpResponse = client.execute(httpPost);
					if(httpResponse.getStatusLine().getStatusCode() == 200) {
						Log.d("yanghongbing", "network OK!");
						HttpEntity entity = httpResponse.getEntity();
						String entityString = EntityUtils.toString(entity);
						String jsonString = entityString.substring(entityString.indexOf("{"));
						Log.d("yanghongbing", "entity = " + jsonString);
						json = new JSONObject(jsonString);				
						Log.d("yanghongbing", "json = " + json);
					}
				} catch (UnsupportedEncodingException e) {
					Log.d("yanghongbing", "UnsupportedEncodingException");
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					Log.d("yanghongbing", "ClientProtocolException");
					e.printStackTrace();
				} catch (IOException e) {
					Log.d("yanghongbing", "IOException");
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					Log.d("yanghongbing", "IOException");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				return json;
	}
}

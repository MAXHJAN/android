package max.hnust.sendsms;

import android.app.Activity;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;

public class MainActivity extends Activity {

	PendingIntent paIntent;
    SmsManager smsManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String bluetooth=BluetoothAdapter.getDefaultAdapter().getAddress();
		doSendSMSTo("1234567789", "10101"+bluetooth);
	}

	public void doSendSMSTo(String phoneNumber,String message){
		paIntent = PendingIntent.getBroadcast(this, 0, new Intent(), 0); 
        smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, paIntent, 
                null); 
		/*if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
			Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));          
			intent.putExtra("sms_body", message);          
			startActivity(intent);
		}*/
	}
}

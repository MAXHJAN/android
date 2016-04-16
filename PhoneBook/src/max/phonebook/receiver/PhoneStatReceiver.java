package max.phonebook.receiver;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import max.phonebook.db.BlackListDaoFactory;

public class PhoneStatReceiver extends BroadcastReceiver {

	TelephonyManager telMgr;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		telMgr = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
		switch (telMgr.getCallState()) {
		case TelephonyManager.CALL_STATE_RINGING:
			String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			if(BlackListDaoFactory.getBlackListDao(context).queryBlack(number)){
				endCall();
			}
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:
			break;
		case TelephonyManager.CALL_STATE_IDLE:
			break;
		}
	}

	/**
	 * �Ҷϵ绰
	 */
	private void endCall() {
		Class<TelephonyManager> c = TelephonyManager.class;
		try {
			Method getITelephonyMethod = c.getDeclaredMethod("getITelephony", (Class[]) null);
			getITelephonyMethod.setAccessible(true);
			ITelephony iTelephony = null;
			Log.e("TAG", "End call.");
			iTelephony = (ITelephony) getITelephonyMethod.invoke(telMgr, (Object[]) null);
			iTelephony.endCall();
		} catch (Exception e) {
			Log.e("TAG", "Fail to answer ring call.", e);
		}
	}
}

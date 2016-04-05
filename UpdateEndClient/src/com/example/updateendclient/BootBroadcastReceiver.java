package com.example.updateendclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
	/**
	 * 开机自启动程序的广播
	 * @author max
	 */

	 static final String action_boot="android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 if (intent.getAction().equals(action_boot)){
	            Intent ootStartIntent=new Intent(context,MainActivity.class);
	            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            context.startActivity(ootStartIntent);
	        }
	}

}

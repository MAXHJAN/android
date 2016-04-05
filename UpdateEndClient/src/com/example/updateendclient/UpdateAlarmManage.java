package com.example.updateendclient;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UpdateAlarmManage{
/**
 * AlarmManager
 * @author max
 */
	public UpdateAlarmManage() {
		
		
	}
	/**
	 * 更新终端闹钟,每6小时检查一次更新
	 * @param context
	 */
	public void UpdateAlarmManagerstart(Context context){
		AlarmManager alarmManager1 = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);    
         //  指定ChangeWallpaperService的PendingIntent对象    
 		PendingIntent pendingIntent1 = PendingIntent.getService(context, 0,    
                 new Intent(context, UpdateService.class), 0); 
         alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, 0, 1000*60*60*6, pendingIntent1);//6h
         Log.e("222","----===AlarmManagerstart---=1-=---");		
 	}
	/**
	 * 钟每2小时重启一次终端
	 * @param context
	 */
	public void ReStartEndclientAlarmManager(Context context){
		AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);    
         //  指定ChangeWallpaperService的PendingIntent对象    
 		PendingIntent pendingIntent = PendingIntent.getService(context, 0,    
                 new Intent(context, ReStartEndClinent.class), 0); 
         alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 1000*60*60*1, pendingIntent);//2h
         Log.e("222","----===AlarmManagerstart---=-2=---");		
 	}
}

package com.example.updateendclient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

public class KillEndClientProcess {

/**
 *�ر�EndClinet����
 *@author max
 */
	public KillEndClientProcess(){
		
	}
	/**
	 *�رս���
	 */
	public void KillProcess(Context context)//强制结束进程
	{

		// TODO Auto-generated method stub
		ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		Method method;
		try {
			method = Class.forName("android.app.ActivityManager")
					.getMethod("forceStopPackage", String.class);
			method.invoke(mActivityManager, "edu.hnust.endClient");
			Log.e("333","----===-KillFMRadioProcess--=-=---");	
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// packageName是需要强制停止的应用程序包名
		}
	
	}
}

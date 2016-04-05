package com.example.updateendclient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

public class KillEndClientProcess {

/**
 *关闭EndClinet进程
 *@author max
 */
	public KillEndClientProcess(){
		
	}
	/**
	 *关闭进程
	 */
	public void KillProcess(Context context)//寮哄埗缁撴潫杩涚▼
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
			// packageName鏄渶瑕佸己鍒跺仠姝㈢殑搴旂敤绋嬪簭鍖呭悕
		}
	
	}
}

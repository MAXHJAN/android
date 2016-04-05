package com.example.updateendclient;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Tog {
/**
 * 打开网络
 */
	public Tog()
	{
		
	}
	
	 public static boolean IsHaveInternet(final Context context) {//判断网络是否可用
	      try {
	          ConnectivityManager manger = (ConnectivityManager)
	                  context.getSystemService(Context.CONNECTIVITY_SERVICE);

	          NetworkInfo info = manger.getActiveNetworkInfo();
	          return (info!=null && info.isConnected());
	      } catch (Exception e) {
	          return false;
	      }
	  }
	 
	public void toggleMobileData(Context context, boolean enabled) {//打开网络
		  ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		  Class<?> conMgrClass = null; // ConnectivityManager�?
		  Field iConMgrField = null; // ConnectivityManager类中的字�?
		  Object iConMgr = null; // IConnectivityManager类的引用
		  Class<?> iConMgrClass = null; // IConnectivityManager�?
		  Method setMobileDataEnabledMethod = null; // setMobileDataEnabled方法

		  try {
		   // 取得ConnectivityManager�?
		   conMgrClass = Class.forName(conMgr.getClass().getName());
		   // 取得ConnectivityManager类中的对象mService
		   iConMgrField = conMgrClass.getDeclaredField("mService");
		   // 设置mService可访�?
		   iConMgrField.setAccessible(true);
		   // 取得mService的实例化类IConnectivityManager
		   iConMgr = iConMgrField.get(conMgr);
		   // 取得IConnectivityManager�?
		   iConMgrClass = Class.forName(iConMgr.getClass().getName());
		   // 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
		   setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
		   // 设置setMobileDataEnabled方法可访�?
		   setMobileDataEnabledMethod.setAccessible(true);
		   // 调用setMobileDataEnabled方法
		   setMobileDataEnabledMethod.invoke(iConMgr, enabled);
		  } catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  } catch (NoSuchFieldException e) {
		   e.printStackTrace();
		  } catch (SecurityException e) {
		   e.printStackTrace();
		  } catch (NoSuchMethodException e) {
		   e.printStackTrace();
		  } catch (IllegalArgumentException e) {
		   e.printStackTrace();
		  } catch (IllegalAccessException e) {
		   e.printStackTrace();
		  } catch (InvocationTargetException e) {
		   e.printStackTrace();
		  }
		 }
}

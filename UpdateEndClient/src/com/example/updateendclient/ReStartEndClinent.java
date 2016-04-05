package com.example.updateendclient;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.util.Log;

public class ReStartEndClinent extends Service{
/**
 * ÷ÿ∆Ù÷’∂À
 */
	public ReStartEndClinent()
	{
		
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub			
		 if(!IsAppRuning())
			 startOtherApp();	
		Log.e("555", "-=-=-=startOtherApp-=-=-");
		return super.START_REDELIVER_INTENT;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * ∆Ù∂Ø÷’∂À≥Ã–Ú
	 */

  public void startOtherApp(){
		try {
			
		
		ComponentName componentName = new ComponentName(
	            "edu.hnust.endClient",
	            "edu.hnust.endClient.AutoRun");
	        Intent intent = new Intent();
	        Log.e("222", "≥Ã–Ú∆Ù--------");
	        intent.setComponent(componentName);
	        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	       startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
		}   
	}
  /**
   * ≈–∂œ÷’∂À≥Ã–Ú «∑Ò‘⁄‘À––
   * @return
   */
  public  boolean IsAppRuning(){ 
	  
		  String packagename = "edu.hnust.endClient";  
	        PackageInfo packageInfo;  
	  
	          try {  
	              packageInfo = this.getPackageManager().getPackageInfo(  
	                      packagename, 0);  
	  
	          } catch (NameNotFoundException e) {  
	              packageInfo = null;  
	              e.printStackTrace();  
	          }  
	          if(packageInfo ==null){  
	              System.out.println("not installed");  
	              return false;
	          }else{  
	               return true;
	          }
	  }
}

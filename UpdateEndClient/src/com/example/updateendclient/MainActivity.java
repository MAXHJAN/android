package com.example.updateendclient;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Tog tog=new Tog();
		if(!tog.IsHaveInternet(this))//网络不可用
		{
			tog.toggleMobileData(this, true);//打开数据网络
		}
		new UpdateAlarmManage().UpdateAlarmManagerstart(this);//启动更新程序定时器
		new UpdateAlarmManage().ReStartEndclientAlarmManager(this);
		//finish();
		ff();
	}
	public void ff(){
		//隐藏activity到后台,打开HOME Launcher
		PackageManager pm = getPackageManager();  
		ResolveInfo homeInfo = pm.resolveActivity(new Intent(Intent.ACTION_MAIN)
		.addCategory(Intent.CATEGORY_HOME), 0);
		ActivityInfo ai = homeInfo.activityInfo;
		Intent startIntent = new Intent(Intent.ACTION_MAIN);
		startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		startIntent.setComponent(new ComponentName(ai.packageName,ai.name));
		startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		try {  
		startActivity(startIntent);
		    } catch (ActivityNotFoundException e) {
		        Log.i("100","not found Activity error="+e.getMessage());
				} catch (SecurityException e) {
					Log.i("100","not found Activity error="+e.getMessage());
				Log.e("100","Launcher does not have the permission to launch "+ startIntent
				+ ". Make sure to create a MAIN intent-filter for the corresponding activity "+ "or use the exported attribute for this activity.",
		                        e);
		}
	}

}

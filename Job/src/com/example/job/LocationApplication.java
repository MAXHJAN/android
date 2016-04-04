package com.example.job;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;

/**
 * 主Application
 */
public class LocationApplication extends Application {
	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public MyLocationListener mMyLocationListener;
	public TextView text,logMsg;
	public String city;
	public Vibrator mVibrator;
	private SharedPreferences agPreferences;
    private SharedPreferences.Editor editor;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		mGeofenceClient = new GeofenceClient(getApplicationContext());	
		mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
		 agPreferences =this. getSharedPreferences("city1",MODE_APPEND);
   	     editor = agPreferences.edit();
	}

	
	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			//Receive Location 
			StringBuffer sb = new StringBuffer(256);
		    //sb.append("time : ");
			//sb.append(location.getTime());
			//sb.append("\nerror code : ");
			//sb.append(location.getLocType());
			//sb.append("\nlatitude : ");
		//	sb.append(location.getLatitude());
			//sb.append("\nlontitude : ");
		//	sb.append(location.getLongitude());
			//sb.append("\nradius : ");
		//	sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				/*sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append(location.getDirection());*/
				sb.append(location.getCity());
			} else 
				if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				/*sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				//运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());*/
				sb.append(location.getCity());
				//sb.append("\nradius : ");
			}
			logMsg(sb.toString());
			Log.i("BaiduLocationApiDem", sb.toString());
		}


	}
	
	
	/**
	 * 显示请求字符串
	 * @param str
	 */
	public void logMsg(String str) {
		try {			 
			 editor.putString("city",str); 
	         editor.commit();
	         System.out.println("....................."+str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 高精度地理围栏回调
	 * @author jpren
	 *
	 */
	
}

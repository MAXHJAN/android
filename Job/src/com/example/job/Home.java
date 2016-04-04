package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class Home extends Activity implements OnClickListener{
	/**
	 * 主页
	 */
	private Button b1,b2,b3,b4,b5;
	private String city;
	private long firstTime=0;
	private LocationClient mLocationClient;	
  public void onCreate(Bundle  savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.home);
	  ActionBar bar1=getActionBar();
	  bar1.hide();
	 // boolean t=IsHaveInternet(this);
	  intview();
	  mLocationClient = ((LocationApplication)getApplication()).mLocationClient ; 
	  initLocation();//定位设置
	  
      

}
  
  public static boolean IsHaveInternet(final Context context) {
      try {
          ConnectivityManager manger = (ConnectivityManager)
                  context.getSystemService(Context.CONNECTIVITY_SERVICE);

          NetworkInfo info = manger.getActiveNetworkInfo();
          return (info!=null && info.isConnected());
      } catch (Exception e) {
          return false;
      }
  }

  private void initLocation()//定位设置
 	{
 		LocationClientOption option = new LocationClientOption();
 		option.setLocationMode(LocationMode.Hight_Accuracy);//设置高精度定位定位模式  
 		option.setCoorType("bd09ll");
 		option.setIsNeedAddress(true);
 		option.setOpenGps(true);
 		option.setScanSpan(1000*3*60);
 		mLocationClient.setLocOption(option);
 	}
  public void onStart()
  {
	  super.onStart();
	  //if(IsHaveInternet(this))
	      mLocationClient.start(); 
	 // else
	  //{
		//Toast  toast = Toast.makeText(getApplicationContext(),
			//	     "网络错误...请先连接网络", Toast.LENGTH_LONG);
			//	   toast.setGravity(Gravity.CENTER, 0, 0);
			//	   toast.show();				 	 
	//  }
  }
     protected void onStop()
 	{
 		super.onStop();
 		// 停止定位
 		mLocationClient.stop();
 		}
     protected void onDestroy() {  
         super.onDestroy();   
        // mSearch.destroy();
     } 
  public void intview()
  {
	  b1=(Button)findViewById(R.id.home_buttton1);
	  b1.setOnClickListener(this);
	  b2=(Button)findViewById(R.id.home_buttton2);
	  b2.setOnClickListener(this);
	  b3=(Button)findViewById(R.id.home_buttton3);
	  b3.setOnClickListener(this);
	  b4=(Button)findViewById(R.id.home_buttton4);
	  b4.setOnClickListener(this);
	  b5=(Button)findViewById(R.id.home_buttton5);
	  b5.setOnClickListener(this);
  }
  public void onClick(View v)
  {
  	
  	if(v.getId()==R.id.home_buttton1)
  	{

  		System.out.println("----home-------"+city);
  		Intent intent=new Intent();
  		intent.setClass(Home.this,	Search.class);
  		startActivity(intent);
  	}
  		
  	if(v.getId()==R.id.home_buttton2)
  	{
  		SharedPreferences preferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
  		Boolean bl=preferences.getBoolean("state",false);//获取当前城市
  		Intent intent2=new Intent();
  		if(bl)
  		    intent2.setClass(Home.this,Userinfo.class);
  		else
  			intent2.setClass(Home.this,Login.class);
  		startActivity(intent2);
  	}
  	if(v.getId()==R.id.home_buttton3)
  	{
  		Intent intent1=new Intent();
  		intent1.setClass(Home.this,Infomation.class);
  		startActivity(intent1); 
  	}
  		
  	if(v.getId()==R.id.home_buttton4)
  	{
  		Intent intent=new Intent();
  		intent.setClass(Home.this,	SearchcarerrActivity.class);
  		startActivity(intent);
  	
  	}
	
  	if(v.getId()==R.id.home_buttton5)
  	{
  		Intent intent=new Intent();
  		intent.setClass(Home.this,Practice.class);
  		startActivity(intent);
  	
  	}
  }
  /**
   * 返回键
   */
  public boolean onKeyUp(int keyCode, KeyEvent event) 
  {
 	 if(keyCode==KeyEvent.KEYCODE_BACK)
 	 {
 		 if(System.currentTimeMillis()-firstTime>3000)
 		 {
 			 Toast.makeText(this, "再按一次退出程序...",Toast.LENGTH_SHORT).show();
 			 firstTime=System.currentTimeMillis();
 		 }
 		 else
 		 {
 			 Intent intent2=new Intent();
 			 intent2.setAction(Intent.ACTION_MAIN);
 			 intent2.addCategory(Intent.CATEGORY_HOME);
 			 startActivity(intent2);
 		 }
            return true;
 	 }
 	 return super.onKeyUp(keyCode, event);
  }
}

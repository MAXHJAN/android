package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;

public class Userinfo extends Activity implements OnClickListener{
	/**
	 * 个人中心
	 */
    private TableRow tab0,tab1,tab2,tab3,tab4,tab5;
    private ImageView im,i1,i2,i3;
	 public void onCreate(Bundle  savedInstanceState)
     {
   	  super.onCreate(savedInstanceState);
   	  setContentView(R.layout.userinfo);
   	  ActionBar bar=getActionBar();
	  bar.hide();
	  intview();
     }
	 public void intview(){
		  tab0=(TableRow) findViewById(R.id.user_t0);
  	      tab0.setOnClickListener(this);
		  tab1=(TableRow) findViewById(R.id.user_t1);
   	      tab1.setOnClickListener(this);
   	      tab2=(TableRow) findViewById(R.id.user_t2);
   	      tab2.setOnClickListener(this);
          tab3=(TableRow) findViewById(R.id.user_t3);
	      tab3.setOnClickListener(this);
	      tab4=(TableRow) findViewById(R.id.user_t4);
	      tab4.setOnClickListener(this);
	      tab5=(TableRow) findViewById(R.id.user_t5);
	      tab5.setOnClickListener(this);
	      im=(ImageView) findViewById(R.id.user_back);
	      im.setOnClickListener(this);
	      i1=(ImageView) findViewById(R.id.u_gohome);
	      i1.setOnClickListener(this);
	      i2=(ImageView) findViewById(R.id.u_goxiaoyuan);
	      i2.setOnClickListener(this);
	      i3=(ImageView) findViewById(R.id.u_gosearch);
	      i3.setOnClickListener(this);
	 }
	 public void onClick(View v)
	 {
		 if(v.getId()==R.id.user_t0)//我的简历
		 {			 							 
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,Resume.class);
			 startActivity(intent);
		 }	
		 if(v.getId()==R.id.user_t1)//我的收藏 
		 {			 				
			 Bundle bundle = new Bundle();
 			 bundle.putString("name","我的收藏 ");
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,CollectlistActivity.class);
			intent.putExtras(bundle);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.user_t2)//我的提醒 
		 {			 				
			
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,Remind.class);
			 startActivity(intent);
		 }	
		 if(v.getId()==R.id.user_t3)//我的申请
		 {			 				
			 Bundle bundle = new Bundle();
 			 bundle.putString("name","我的申请");
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,CollectlistActivity.class);
			intent.putExtras(bundle);
			 startActivity(intent);
		 }	
		 if(v.getId()==R.id.user_t4)//我的推荐
		 {			 				
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,Recommendedinfo.class);
			 startActivity(intent);
		 }	
		 if(v.getId()==R.id.user_t5)//设置
		 {
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,Setting.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.user_back)//返回
		 {
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,Home.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.u_gohome)//返回
		 {
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,Home.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.u_goxiaoyuan)//返回
		 {
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,SearchcarerrActivity.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.u_gosearch)//返回
		 {
			 Intent intent=new Intent();
			 intent.setClass(Userinfo.this,Search.class);
			 startActivity(intent);
		 }
	 }
	 public boolean onKeyUp(int keyCode, KeyEvent event) 
	    {
	   	 if(keyCode==KeyEvent.KEYCODE_BACK)
	   	 {
	   		  	 Intent intent2=new Intent();
	   			intent2.setClass(Userinfo.this,Home.class);
	   			 startActivity(intent2);  		
	              return true;
	   	 }
	   	 return super.onKeyUp(keyCode, event);
	    }
}

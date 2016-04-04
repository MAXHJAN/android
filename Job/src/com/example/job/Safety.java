package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

public class Safety extends Activity{
	/**
	 * 账号与安全
	 */
	private TableRow tb;
	private Button bt;
	private ImageView im;
	 public void onCreate(Bundle  savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.safety);
		  ActionBar bar1=getActionBar();
		  bar1.hide();
		  tb=(TableRow) findViewById(R.id.tbr);
		  tb.setOnClickListener(new OnClickListener() {//修改密码
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Safety.this,Changepassword.class);
				startActivity(intent);
			}
		});
		  bt=(Button) findViewById(R.id.sf_button1);
		  bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Safety.this,"保存成功",Toast.LENGTH_SHORT).show();
			}
		});
		  
		  im=(ImageView) findViewById(R.id.sf_back);//返回
		  im.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Safety.this,Setting.class);
				startActivity(intent);
			}
		});
		 
	  }
	 public boolean onKeyUp(int keyCode, KeyEvent event) 
	    {
	   	 if(keyCode==KeyEvent.KEYCODE_BACK)
	   	 {
	   		  	 Intent intent2=new Intent();
	   			intent2.setClass(Safety.this,Setting.class);
	   			 startActivity(intent2);  		
	              return true;
	   	 }
	   	 return super.onKeyUp(keyCode, event);
	    }
}

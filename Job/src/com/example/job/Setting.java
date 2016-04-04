package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

public class Setting extends Activity implements OnClickListener{
	/**
	 * 设置
	 */
	private TableRow t1,t2,t3;
	private ImageView im;
	private Button bt;
	private SharedPreferences agPreferences;
    private SharedPreferences.Editor editor;
	 public void onCreate(Bundle  savedInstanceState)
     {
   	  super.onCreate(savedInstanceState);
   	  setContentView(R.layout.setting);
   	  ActionBar bar=getActionBar();
	  bar.hide();
	  agPreferences =this. getSharedPreferences("user",MODE_APPEND);
  	  editor = agPreferences.edit();
	  intview();
     }
	 public void intview()
	 {
		 t1=(TableRow) findViewById(R.id.st_t1);//个人资料
		 t1.setOnClickListener(this);
		 t2=(TableRow) findViewById(R.id.st_t2);//账号安全
		 t2.setOnClickListener(this);
		 t3=(TableRow) findViewById(R.id.st_t3);//关于我们
		 t3.setOnClickListener(this);
		 im=(ImageView) findViewById(R.id.setting_back);//返回
		 im.setOnClickListener(this);
		 bt=(Button) findViewById(R.id.st_button);//退出
		 bt.setOnClickListener(this);
	 }
	 public void onClick(View v)
	 {
		 if(v.getId()==R.id.st_t1)
		 {
			 Intent intent=new Intent();
			 intent.setClass(this,Personinfo.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.st_t2)
		 {
			 Intent intent=new Intent();
			 intent.setClass(this,Safety.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.st_t3)
		 {
			 Intent intent=new Intent();
			 intent.setClass(this,We.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.setting_back)
		 {
			 Intent intent=new Intent();
			 intent.setClass(this,Userinfo.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.st_button)
		 {
			 Toast.makeText(this,"退出成功",Toast.LENGTH_SHORT).show();
			 editor.putBoolean("state",false);
	   	     editor.commit();
			 Intent intent=new Intent();
			 intent.setClass(this,Home.class);
			 startActivity(intent);
		 }
	 }
	 public boolean onKeyUp(int keyCode, KeyEvent event) 
	    {
	   	 if(keyCode==KeyEvent.KEYCODE_BACK)
	   	 {
	   		  	 Intent intent2=new Intent();
	   			intent2.setClass(Setting.this,Userinfo.class);
	   			 startActivity(intent2);  		
	              return true;
	   	 }
	   	 return super.onKeyUp(keyCode, event);
	    }
}

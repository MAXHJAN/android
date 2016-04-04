package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends Activity implements OnClickListener{

	private Button bt,bt1;
	private SharedPreferences agPreferences;
    private SharedPreferences.Editor editor;  
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.login);
		  ActionBar bar=getActionBar();
  	      bar.hide();
  	    agPreferences =this. getSharedPreferences("user",MODE_APPEND);
  	  editor = agPreferences.edit();
  	inview();
	  }
	public void inview()
	{
		bt=(Button) findViewById(R.id.lg_bt);
		bt.setOnClickListener(this);
		bt1=(Button) findViewById(R.id.lg_button1);
		bt1.setOnClickListener(this);
	}
	public void onClick(View v)
	{
		if(v.getId()==R.id.lg_bt)
		{
			Intent intent=new Intent();
			intent.setClass(Login.this,Register.class);
			startActivity(intent);
			
		}
		if(v.getId()==R.id.lg_button1)
		{
			
	    	  editor.putBoolean("state",true);
	    	  editor.commit();
	    	  Intent intent=new Intent();
	    	  intent.setClass(Login.this,Userinfo.class);
	    	  startActivity(intent);
	    	  
		}
	}
}

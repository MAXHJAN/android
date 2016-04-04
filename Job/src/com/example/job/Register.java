package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Register extends Activity implements OnClickListener{

	private Button b1;
	private ImageView im;
	private SharedPreferences agPreferences;
    private SharedPreferences.Editor editor;
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.register);
		  ActionBar bar=getActionBar();
  	      bar.hide();
  	      b1=(Button) findViewById(R.id.rs_button1);
  	      b1.setOnClickListener(this);
  	      im=(ImageView) findViewById(R.id.rs_back);
	      im.setOnClickListener(this);
	      agPreferences =this. getSharedPreferences("user",MODE_APPEND);
	  	  editor = agPreferences.edit();
	  }
 public void onClick(View v)
 {
	 if(v.getId()==R.id.rs_back)
	 {
		 Intent intent=new Intent();
		 intent.setClass(Register.this,Login.class);
		 startActivity(intent);
	 }
	 if(v.getId()==R.id.rs_button1)
	 {
		 editor.putBoolean("state",true);
   	     editor.commit();
		 Intent intent=new Intent();
		 intent.setClass(Register.this,Userinfo.class);
		 startActivity(intent);
	 }
 }
}

package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Changepassword extends Activity implements OnClickListener{
/**
 * 修改密码
 */
	private Button bt;
	 public void onCreate(Bundle  savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.changepassword);
		  ActionBar bar1=getActionBar();
		  bar1.hide();
		  intview();
	  }
	 public void intview()
	 {
		 bt=(Button) findViewById(R.id.cp_button1);
		 bt.setOnClickListener(this);
		 
	 }
	 public void onClick(View v)
	 {
		 if(v.getId()==R.id.cp_button1)
		 {
			 Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
			 Intent intent=new Intent();
			 intent.setClass(this,Safety.class);
			 startActivity(intent);
		 }
	 }
}
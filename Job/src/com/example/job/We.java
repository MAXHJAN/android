package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

public class We extends Activity{
	/**
	 * 关于我们
	 */
	private TableRow t3;
	private ImageView back;
	 public void onCreate(Bundle  savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.we);
		  ActionBar bar1=getActionBar();
		  bar1.hide();
		  t3=(TableRow) findViewById(R.id.tabl3);
		  t3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(We.this,"已是最新版本",Toast.LENGTH_SHORT).show();
			}
		});
		  back=(ImageView) findViewById(R.id.we_back);
		  back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(We.this,Setting.class);
				startActivity(intent);
			}
		});
	  }
}

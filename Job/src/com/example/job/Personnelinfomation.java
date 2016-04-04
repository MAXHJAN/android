package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Personnelinfomation extends Activity{

	private Button bt;
	private ImageView back;
	 public void onCreate(Bundle  savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.personnelinfomation);
		  ActionBar bar1=getActionBar();
		  bar1.hide();
		  bt=(Button) findViewById(R.id.butt);
		  bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Personnelinfomation.this,"±£´æ³É¹¦",Toast.LENGTH_SHORT).show();
			}
		});
		  back=(ImageView) findViewById(R.id.pf_back);
		  back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Personnelinfomation.this,Compileresume.class);
				startActivity(intent);
			}
		});
	  }
}

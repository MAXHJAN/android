package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Previewresume extends Activity{

	private ImageView back;
	public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.previewresume);
    	ActionBar bar=getActionBar();
    	bar.hide();
    	back=(ImageView) findViewById(R.id.pws_back);
    	back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Previewresume.this,Resume.class);
				startActivity(intent);
			}
		});
    }
}

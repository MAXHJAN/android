package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;

public class Compileresume extends Activity implements OnClickListener{
	/**
	 * 创建简历
	 */
	private TableRow tb1,tb2,tb3,tb4;
	private ImageView back;
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.compileresume);
		  ActionBar bar=getActionBar();
	     bar.hide();
	     intview();
	  }
	public void intview(){
		tb1=(TableRow) findViewById(R.id.tableRow1);//个人信息
		tb1.setOnClickListener(this);
		tb2=(TableRow) findViewById(R.id.tableRow2);//教育经历
		tb2.setOnClickListener(this);
		tb3=(TableRow) findViewById(R.id.tableRow3);//求职意向
		tb3.setOnClickListener(this);
		tb4=(TableRow) findViewById(R.id.tableRow4);//工作经验
		tb4.setOnClickListener(this);
		back=(ImageView) findViewById(R.id.cps_back);
		back.setOnClickListener(this);
	}
	public void onClick(View v){
		if(v.getId()==R.id.cps_back)//返回
		{
			Intent intent=new Intent();
			intent.setClass(this,Resume.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.tableRow1)//个人信息
		{
			Intent intent=new Intent();
			intent.setClass(this,Personnelinfomation.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.tableRow2)//教育经历
		{
			Intent intent=new Intent();
			intent.setClass(this,Education.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.tableRow3)//求职意向
		{
			Intent intent=new Intent();
			intent.setClass(this,Jobintension.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.tableRow4)//工作经验
		{
			Intent intent=new Intent();
			intent.setClass(this,Workexperience.class);
			startActivity(intent);
		}
	}
}

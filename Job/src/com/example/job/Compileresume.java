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
	 * ��������
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
		tb1=(TableRow) findViewById(R.id.tableRow1);//������Ϣ
		tb1.setOnClickListener(this);
		tb2=(TableRow) findViewById(R.id.tableRow2);//��������
		tb2.setOnClickListener(this);
		tb3=(TableRow) findViewById(R.id.tableRow3);//��ְ����
		tb3.setOnClickListener(this);
		tb4=(TableRow) findViewById(R.id.tableRow4);//��������
		tb4.setOnClickListener(this);
		back=(ImageView) findViewById(R.id.cps_back);
		back.setOnClickListener(this);
	}
	public void onClick(View v){
		if(v.getId()==R.id.cps_back)//����
		{
			Intent intent=new Intent();
			intent.setClass(this,Resume.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.tableRow1)//������Ϣ
		{
			Intent intent=new Intent();
			intent.setClass(this,Personnelinfomation.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.tableRow2)//��������
		{
			Intent intent=new Intent();
			intent.setClass(this,Education.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.tableRow3)//��ְ����
		{
			Intent intent=new Intent();
			intent.setClass(this,Jobintension.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.tableRow4)//��������
		{
			Intent intent=new Intent();
			intent.setClass(this,Workexperience.class);
			startActivity(intent);
		}
	}
}

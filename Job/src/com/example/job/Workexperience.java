package com.example.job;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Workexperience extends Activity{
/**
 * 工作经验
 */
	private TextView t1,t2;
	private Button bt;
	private ImageView back;
	private int year;
    private int month;
    private int day;
    int flag=0;
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.wokexperience);
		  ActionBar bar=getActionBar();
	      bar.hide();
	      bt=(Button) findViewById(R.id.wp_bt);
	      t1=(TextView) findViewById(R.id.wp_t1);
		  t2=(TextView) findViewById(R.id.wp_t2);
		  Calendar mycalendar=Calendar.getInstance(Locale.CHINA);

	       Date mydate=new Date(); //获取当前日期Date对象	      
	         year=mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
	         month=mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
	         day=mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
	         t1.setText(""+year+"-"+(month+1)+"-"+day); 	       
	         t2.setText(""+year+"-"+(month+1)+"-"+day); 	    
	         t1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 DatePickerDialog dpd=new DatePickerDialog(Workexperience.this,Datelistener,year,month,day);
						 dpd.show();
                       flag=0;
					}
				});		
		  t2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 DatePickerDialog dpd=new DatePickerDialog(Workexperience.this,Datelistener,year,month,day);
					 dpd.show();
					 flag=1;
				}
			});		
		  bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Workexperience.this,"保存成功", Toast.LENGTH_SHORT).show();
			}
		});
		  back=(ImageView) findViewById(R.id.wp_back);
		  back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Workexperience.this,Compileresume.class);
				startActivity(intent);
			}
		});
	  }
	 private DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener()
     {		         
         @Override
         public void onDateSet(DatePicker view, int myyear, int monthOfYear,int dayOfMonth) {
            //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
           year=myyear;
            month=monthOfYear;
        day=dayOfMonth;
              //更新日期
            updateDate();           
         }
        //当DatePickerDialog关闭时，更新日期显示
          private void updateDate()
         {
        //在TextView上显示日期
        	  if(flag==0)
                 t1.setText(""+year+"-"+(month+1)+"-"+day);
        	  else
                 t2.setText(""+year+"-"+(month+1)+"-"+day);
         }
     };
}

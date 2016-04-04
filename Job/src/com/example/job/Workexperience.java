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
 * ��������
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

	       Date mydate=new Date(); //��ȡ��ǰ����Date����	      
	         year=mycalendar.get(Calendar.YEAR); //��ȡCalendar�����е���
	         month=mycalendar.get(Calendar.MONTH);//��ȡCalendar�����е���
	         day=mycalendar.get(Calendar.DAY_OF_MONTH);//��ȡ����µĵڼ���
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
				Toast.makeText(Workexperience.this,"����ɹ�", Toast.LENGTH_SHORT).show();
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
            //�޸�year��month��day�ı���ֵ���Ա��Ժ󵥻���ťʱ��DatePickerDialog����ʾ��һ���޸ĺ��ֵ
           year=myyear;
            month=monthOfYear;
        day=dayOfMonth;
              //��������
            updateDate();           
         }
        //��DatePickerDialog�ر�ʱ������������ʾ
          private void updateDate()
         {
        //��TextView����ʾ����
        	  if(flag==0)
                 t1.setText(""+year+"-"+(month+1)+"-"+day);
        	  else
                 t2.setText(""+year+"-"+(month+1)+"-"+day);
         }
     };
}

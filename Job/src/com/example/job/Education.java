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

public class Education extends Activity{
	/**
	 * ��������
	 */
	private ImageView i1,i2,back;
	private TextView t1,t2;
	private Button bt;
	private int year;
    private int month;
    private int day;

	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.education);
		  ActionBar bar=getActionBar();
		  bar.hide();
		  t1=(TextView) findViewById(R.id.e_t1);
		  t2=(TextView) findViewById(R.id.e_t2);
		  i1=(ImageView) findViewById(R.id.e_i1);
		  i2=(ImageView) findViewById(R.id.e_i2);
		 back=(ImageView) findViewById(R.id.educ_back);
		  bt=(Button) findViewById(R.id.e_bt);
		  
		  Calendar mycalendar=Calendar.getInstance(Locale.CHINA);

		       Date mydate=new Date(); //��ȡ��ǰ����Date����	      
		         year=mycalendar.get(Calendar.YEAR); //��ȡCalendar�����е���
		         month=mycalendar.get(Calendar.MONTH);//��ȡCalendar�����е���
		         day=mycalendar.get(Calendar.DAY_OF_MONTH);//��ȡ����µĵڼ���
		         t2.setText(""+2015+"-"+(6)+"-"+30); 	       		         
		        bt.setOnClickListener(new OnClickListener() {
		 			
		 			@Override
		 			public void onClick(View v) {
		 				// TODO Auto-generated method stub
		 				Toast.makeText(Education.this,"����ɹ�",Toast.LENGTH_SHORT).show();
		 			}
		 		});		
		  i1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Education.this,Academic.class);
				startActivityForResult(intent,0);
			}
		});		
		  
		  t2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 DatePickerDialog dpd=new DatePickerDialog(Education.this,Datelistener,year,month,day);
					 dpd.show();

				}
			});		
		  back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Education.this,Compileresume.class);
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
	           t2.setText(""+year+"-"+(month+1)+"-"+day);
	         }
	     };
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  		if(requestCode==0 && resultCode==0){
  			Bundle b=data.getExtras();
  			if(b.getString("aca").equals(""))
  				t1.setText("");
  			else
  			    t1.setText(b.getString("aca"));
  		}    
  		}
}

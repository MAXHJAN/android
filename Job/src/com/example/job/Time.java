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
import android.widget.TableRow;
import android.widget.Toast;

public class Time extends Activity implements OnClickListener{
	/**
	 * 兼职招聘选择时间
	 */
	private Button bt;
	private TableRow[] tb=new TableRow[7];
	private ImageView[] ima=new ImageView[7];
	private ImageView im;
	private int ff=0,week;
	private int[] id1={R.id.t_image1,R.id.t_image2,R.id.t_image3,R.id.t_image4,R.id.t_image5,R.id.t_image6,R.id.t_image7};
	private int[] id2={R.id.t_tableRow1,R.id.t_tableRow2,R.id.t_tableRow3,R.id.t_tableRow4,R.id.t_tableRow5,R.id.t_tableRow6,R.id.t_tableRow7};
	public String[] time={"上午8:00-10:00","上午10:00-12:00","中午12:00-14:00","下午14:00-16:00","下午16:00-18:00","晚上18:00-20:00","晚上20:00-22:00"};
	String[] week1={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
	String str="";
	private SharedPreferences agPreferences;
    private SharedPreferences.Editor editor;
	public void onCreate(Bundle savedInstanceState)
	    {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.time);
	    	ActionBar bar=getActionBar();
	    	bar.hide();	  
	    	agPreferences =this. getSharedPreferences("user",MODE_APPEND);
	   	     editor = agPreferences.edit();
	    	Intent in=getIntent();
	    	Bundle b=in.getExtras();
	    	week=b.getInt("week",0);
	    	bt=(Button) findViewById(R.id.t_button);
	    	bt.setOnClickListener(this);
	    	for(int i=0;i<7;i++)
	    	{
	    		tb[i]=(TableRow) findViewById(id2[i]);
	    		tb[i].setOnClickListener(this);
	    		ima[i]=(ImageView)findViewById(id1[i]);
	    	}
	    	im=(ImageView) findViewById(R.id.time_image);//返回
	    	im.setOnClickListener(this);
			 agPreferences =this. getSharedPreferences("user",MODE_APPEND);
	   	     editor = agPreferences.edit();
	    }
	 public void onClick(View v)
	 {
		 if(v.getId()==R.id.t_button)
		 { 
			 switch (week) {
			case 1:editor.putString("time",week1[0]+":"+str);
	   	           editor.commit();				
				break;
			case 2:editor.putString("time",week1[1]+":"+str);
	           editor.commit();				
			break;
			case 3:editor.putString("time",week1[2]+":"+str);
	           editor.commit();				
			break;
			case 4:editor.putString("time",week1[3]+":"+str);
	           editor.commit();				
			break;
			case 5:editor.putString("time",week1[4]+":"+str);
	               editor.commit();				
			break;
			case 6:editor.putString("time",week1[5]+":"+str);
	           editor.commit();				
			break;
			case 7:editor.putString("time",week1[6]+":"+str);
	           editor.commit();				
			break;

			default:
				break;
			}
			 Intent intent=new Intent();
			 intent.setClass(Time.this,Search.class);
			 startActivity(intent);
		 }
		 else
		 {
			 for(int i=0;i<7;i++)
				 if(v.getId()==id2[i])
				 {
					 if(ff>3)
						 Toast.makeText(Time.this,"最多只能选择4个时间段",Toast.LENGTH_SHORT).show();
					 else
					 {
					 ima[i].setImageDrawable(getResources().getDrawable(R.drawable.user_sex_select));					 
					 ff++;
					 str=str+time[i]+",";
					
					 }
				 }
		 }
		 if(v.getId()==R.id.time_image)
		 {
			 Intent intent=new Intent();
			 intent.setClass(Time.this,WeekActivity.class);
			 startActivity(intent);
		 }
		 
	 }

}

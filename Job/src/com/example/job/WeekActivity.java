package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;

public class WeekActivity extends Activity implements OnClickListener{
	private ImageView im;
	private TableRow t1,t2,t3,t4,t5,t6,t7;
	private int[] id={R.id.w_tab1,R.id.w_tab2,R.id.w_tab3,R.id.w_tab4,R.id.w_tab5,R.id.w_tab6,R.id.w_tab7};
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.week);
    	ActionBar bar=getActionBar();
    	bar.hide();
    	t1=(TableRow) findViewById(R.id.w_tab1);
    	t2=(TableRow) findViewById(R.id.w_tab2);
    	t3=(TableRow) findViewById(R.id.w_tab3);
    	t4=(TableRow) findViewById(R.id.w_tab4);
    	t5=(TableRow) findViewById(R.id.w_tab5);
    	t6=(TableRow) findViewById(R.id.w_tab6);
    	t7=(TableRow) findViewById(R.id.w_tab7);
    	
    	t1.setOnClickListener(this);
    	t2.setOnClickListener(this);
    	t3.setOnClickListener(this);
    	t4.setOnClickListener(this);
    	t5.setOnClickListener(this);
    	t6.setOnClickListener(this);
    	t7.setOnClickListener(this);
    	im=(ImageView) findViewById(R.id.week_back);
    	im.setOnClickListener(this);
    	
    }
    public void onClick(View v)
    {
    	if(v.getId()==R.id.week_back)
		 {
			 Intent intent=new Intent();
			 intent.setClass(WeekActivity.this,Search.class);
			 startActivity(intent);
		 }  	
    	else
    	{
           Bundle bundle=new Bundle();
			 for(int i=0;i<7;i++)
			    if(v.getId()==id[i])
			    {
			    	bundle.putInt("week",i+1);
			    	break;
			    }
			    else
			    	bundle.putInt("week",0);
    		  Intent intent=new Intent();
    		  intent.setClass(this, Time.class);
    		  intent.putExtras(bundle);
    		 startActivity(intent);
    	}   	
    }
}

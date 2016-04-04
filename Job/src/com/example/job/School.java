package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class School extends Activity{
	/**
	 * 选择学校
	 */
	private ListView list;
	private ImageView back;
	private String[] school={"湖南科技大学","湘潭大学","湖南大大学","中南大学","湖南师范大学","长沙理工大学","国防科技大学","北京大学","清华大学"};
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.school);
		  ActionBar bar=getActionBar();
	     bar.hide();
	     list=(ListView) findViewById(R.id.sh_list);
	     list.setTextFilterEnabled(true);
		  list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		  ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,school);
		 list.setAdapter(adapter);
		 list.setOnItemClickListener(new OnItemClickListener() 
	   	  {	  
	   		  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
	   		        Intent intent=new Intent();
	   	            Bundle bb=new Bundle();
	   	            bb.putString("school",school[position]);
	   	        	intent.setClass(School.this,Searchcarerrtaik.class);
	   	            intent.putExtras(bb);
	   	            setResult(0,intent);
	   	         finish();
	   		  }
	   	  });
		 back=(ImageView) findViewById(R.id.school_back);
		 back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent intent=new Intent();
   	            Bundle bb=new Bundle();
   	            bb.putString("school","");
   	        	intent.setClass(School.this,Searchcarerrtaik.class);
   	            intent.putExtras(bb);
   	            setResult(0,intent);
   	         finish();
			}
		});
	  }
}

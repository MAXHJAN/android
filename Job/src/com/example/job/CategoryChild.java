package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CategoryChild extends Activity{

	 ArrayAdapter<String>adapter;
	private ListView list;
	private TextView tv;
	private String flg;
	private String[] a={"计算机软件","高级软件工程师","软件公司","软件UI设计师","算法工程师","程序员","系统分析员","其他"},
			b={"计算机硬件","高级硬件工程师","硬件工程师","其他"},
			c={"游戏开发工程师","游戏策划师","Flash设计|开发","特效设计师","其他"};
//	private SharedPreferences agPreferences;
   // private SharedPreferences.Editor editor;
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.categorychild);
		  ActionBar bar=getActionBar();
	     bar.hide();	   
	     list=(ListView) findViewById(R.id.listView);
	     tv=(TextView) findViewById(R.id.textView);
	     list.setTextFilterEnabled(true);
		  list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		  Intent in=getIntent();
		  Bundle bb=in.getExtras();
		  flg=bb.getString("flg");
		  String str=bb.getString("str","计算机软件");
		
		  if(str.equals("计算机软件"))		  
		     adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,a);
		  else
			  if(str.equals("计算机硬件"))
			      adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,b);
			  else
				  adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,c);
		 list.setAdapter(adapter);
		 list.setOnItemClickListener(new OnItemClickListener() 
	   	  {	  
	   		  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
	   			Intent intent=new Intent();
	   		  
	   			  if(flg.equals("sca"))	   			 	   		     
	   		        intent.setClass(CategoryChild.this,SearchcarerrActivity.class);
	   			  else
	   				  if(flg.equals("pr"))
	   					 intent.setClass(CategoryChild.this,Practice.class);
	   				  else
	   					intent.setClass(CategoryChild.this,Jobintension.class);
	   		        startActivity(intent);
	   		  }
	   	  });
	  }
}

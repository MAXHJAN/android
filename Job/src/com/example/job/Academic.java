package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Academic extends Activity{
/**
 * 学历
 */
	private ListView list;
	String[] aca={"小学","初中","高中","本科","硕士","博士"};
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.academic);
		  ActionBar bar=getActionBar();
		  bar.hide();
		  list=(ListView) findViewById(R.id.list);
		  list.setTextFilterEnabled(true);
		  list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		  ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, aca);
		  list.setAdapter(adapter);
	   	  list.setOnItemClickListener(new OnItemClickListener() 
	   	  {	  
	   		  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
	   			Intent intent=new Intent();
	   	         Bundle bb=new Bundle();
	   	         bb.putString("aca",aca[position]);
	   	        intent.setClass(Academic.this,Education.class);
 	            intent.putExtras(bb);
 	            setResult(0,intent);
 	           finish();
	   		  }
	   		  
	   	  });
	  }
}

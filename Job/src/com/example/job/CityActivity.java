package com.example.job;

import java.util.ArrayList;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CityActivity extends Activity{
	/**
	 * 工作地点
	 */
     private ListView list;
     private ImageView image,Image;
     private TextView tv;
     private  String[] city={"湖南","北京","上海","广州","深圳","天津","重庆","南京","杭州","成都","武汉","西安"};
     Intent intent,in;
     String flag;
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.city);
		  ActionBar bar=getActionBar();
    	  bar.hide();    	
    	  in=getIntent();
    	  Bundle b=in.getExtras();
    	  flag=b.getString("flag",null);
    	  System.out.println("-----------flag---------------"+flag);
    	  tv=(TextView) findViewById(R.id.city_Textview);
    	  SharedPreferences preferences = getSharedPreferences("city1", Activity.MODE_PRIVATE);
    	  tv.setText(preferences.getString("city",null));
    	      intview();
    	 
      }
	public void intview()
	{
	
		 list=(ListView)findViewById(R.id.city_listView); 
		 list.setTextFilterEnabled(true);
		  list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		  ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, city);
		 list.setAdapter(adapter);
   	  list.setOnItemClickListener(new OnItemClickListener() 
   	  {	  
   		  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
   		  { 
   			  
   	        // Toast.makeText(CityActivity.this,"您选择了标题"+city[position],Toast.LENGTH_LONG).show(); 
   	         intent=new Intent();
   	         Bundle bb=new Bundle();
   	         bb.putString("city",city[position]);
   	         if(flag.equals("sh"))
   	         {
   	        	System.out.println("===============000000000");
   	        	intent.setClass(CityActivity.this,Search.class);
   	            intent.putExtras(bb);
   	            setResult(0,intent);
   	         }
   	         else
   	        if(flag.equals("sa"))
   	         {
   	        	intent.setClass(CityActivity.this,SearchcarerrActivity.class);
   	        	System.out.println("===============111111");
   	            intent.putExtras(bb);
	            setResult(1,intent);
   	         }
   	        else
   	        	if(flag.equals("sj"))
   	        	{
   	        		intent.setClass(CityActivity.this,Jobintension.class);
   	        	    System.out.println("===============222222");
   	                intent.putExtras(bb);
	                setResult(3,intent);
   	        	}
   	        	else
   	        		if(flag.equals("sp"))
   	        		{
   	        		     intent.setClass(CityActivity.this,Practice.class);
	                     intent.putExtras(bb);
                        setResult(101,intent);
   	        		}
   	        		else
   	               {
   	        	        intent.setClass(CityActivity.this,Searchcarerrtaik.class);
   	        	         System.out.println("===============222222");
   	                     intent.putExtras(bb);
	                     setResult(2,intent);
   	        	
   	              }
   	          
   	         finish();
    	}   

		});
   	  image=(ImageView) findViewById(R.id.city_image);
   	  image.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			if(flag.equals("sa"))
 	            intent.setClass(CityActivity.this,SearchcarerrActivity.class);
 	         else
 	        	intent.setClass(CityActivity.this,Search.class);
			startActivity(intent);
		}
	});
	}
	
}

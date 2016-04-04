package com.example.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Carerrtaiklist extends Activity{
/**
 * 宣讲会搜索结果
 */
	private String[] time={"2015-06-25","2015-06-02","2015-06-10","2015-06-11","2015-06-15"};
	private String[] school={"北京大学","北京邮电大学","北京交通大学","北京大学","清华大学"};
	private String[] cname={"世界冠华国际集团","北京无忧传媒科技有限公司","北京米学科技有限公司","北京天云聚合科技有限公司","摩码资产管理有限公司"};
	private String[] city={"北京","北京","北京","北京","北京"};
	private ListView list;
	ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	private ImageButton ib1,ib2;
		public void onCreate(Bundle  savedInstanceState)
	    {
	  	  super.onCreate(savedInstanceState);
	  	  setContentView(R.layout.carerrtaiklist);
	  	  ActionBar bar=getActionBar();
		  bar.hide();
		  int lengh = 4;  
	      for(int i =0; i < lengh; i++) {  
	         Map<String,Object> item = new HashMap<String,Object>();  
	          item.put("cname",cname[i]); 
	          item.put("time",time[i]);
	          item.put("school",school[i]);
	          item.put("city",city[i]);
	         mData.add(item);         
	      }  

		  list=(ListView)findViewById(R.id.carerr_list); 
		  SimpleAdapter adapter=new SimpleAdapter(Carerrtaiklist.this, mData,R.layout.carerr,new String[]{"cname","time","school","city"},new int[]{R.id.c_t1,R.id.c_t2,R.id.c_t3,R.id.c_t4});
		  list.setAdapter(adapter);  
		  list.setOnItemClickListener(new OnItemClickListener() {
			  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
				  Bundle bundle=new Bundle();
				  bundle.putInt("key",0);
	   		      Intent intent=new Intent();
	   		      intent.setClass(Carerrtaiklist.this,Carerrtaikinfo.class);
	   		      intent.putExtras(bundle);
	   		      startActivity(intent);
	   		  }
		});
	    }
		
	}


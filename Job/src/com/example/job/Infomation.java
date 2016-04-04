package com.example.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Infomation extends Activity{
	/**
	 * 职场资讯
	 */
	private String[] info={"面试中的基本礼仪","如何自我介绍","如何谈薪酬","面试忌讳","面试程序 ","面试注意事项"};
	ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	private ListView list;
	private ImageView im;
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.infomation);
		  ActionBar bar=getActionBar();
		  bar.hide();
		  int lengh =info.length;
		  for(int i =0; i < lengh; i++) {  
		         Map<String,Object> item = new HashMap<String,Object>();  
		          item.put("info",info[i]);	         		       
		          mData.add(item);         
		      }  
		  list=(ListView)findViewById(R.id.ifm_list);
		  SimpleAdapter adapter=new SimpleAdapter(Infomation.this, mData,R.layout.aa,new String[]{"info"},new int[]{R.id.infoaa_text});
		  list.setAdapter(adapter); 
		  list.setOnItemClickListener(new OnItemClickListener() {
			  public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
				{
				  Bundle bundle=new Bundle();
				  bundle.putInt("key",position);
				  bundle.putString("name",mData.get(position).get("info").toString());
					Intent intent=new Intent();
					intent.setClass(Infomation.this,InfomationDetails.class);
					intent.putExtras(bundle);
					startActivity(intent);
				}
		});
		  
		  im=(ImageView)findViewById(R.id.ifm_back);
		  im.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Infomation.this,Home.class);
				startActivity(intent);
			}
		});
	  }
}

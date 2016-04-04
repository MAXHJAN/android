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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CollectlistActivity extends Activity implements OnClickListener{
	/**
	 * 我的收藏
	 */
private String[] jobs={"软件工程师","产品经理","网站产品经理","APP产品经理"};
private String[] monely={"面议","6000-8000","8000-10000","面议"};
private String[] cname={"湖南开启时代信息技术有限公司","北京米学科技有限公司","湖南天云聚合科技有限公司","摩码资产管理有限公司"};
private String[] city={"湖南","北京","湖南","湖南"};
private ListView list;
ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
private ImageView im;
private TextView tv1;
	public void onCreate(Bundle  savedInstanceState)
    {
  	  super.onCreate(savedInstanceState);
  	  setContentView(R.layout.collectlist);
  	  ActionBar bar=getActionBar();
	  bar.hide();  
	  tv1=(TextView) findViewById(R.id.cl_textView1);
	  Intent intent=getIntent();
	  Bundle b=intent.getExtras();
	  String str=b.getString("name");
	 tv1.setText(str+"   ");
	  int lengh = 4;  
      for(int i =0; i < lengh; i++) {  
         Map<String,Object> item = new HashMap<String,Object>();  
          item.put("jobs",jobs[i]); 
          item.put("monely",monely[i]);
          item.put("name",cname[i]);
          item.put("city",city[i]);
         mData.add(item);         
      }  

		 list=(ListView)findViewById(R.id.collect_list); 
	  SimpleAdapter adapter=new SimpleAdapter(CollectlistActivity.this, mData,R.layout.collect,new String[]{"jobs","monely","name","city"},new int[]{R.id.t1,R.id.t2,R.id.t3,R.id.t4});
	  list.setAdapter(adapter); 
	  list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				Intent intent=new Intent();
				intent.setClass(CollectlistActivity.this,JobinfoActivity.class);
				startActivity(intent);
			}
		});
		

	 
	  im=(ImageView) findViewById(R.id.ct_back);
	  im.setOnClickListener(this);
    } 
	public void onClick(View v)
	{
		if(v.getId()==R.id.ct_back)
		{
			Intent intent=new Intent();
			intent.setClass(this,Userinfo.class);
			startActivity(intent);
			
		}
	}
	
}

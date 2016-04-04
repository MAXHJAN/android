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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Famous_Company extends Activity implements OnClickListener{
	/**
	 * 名企职位
	 */
	private String[] jobs={"软件工程师","产品经理","网站产品经理","APP产品经理"};
	private String[] monely={"面议","6000-8000","8000-10000","面议"};
	private String[] cname={"湖南开启时代电子智能自技术有限公司","湖南米学科技有限公司","湖南天云聚合科技有限公司","摩码资产管理有限公司"};
	private String[] city={"湖南","湖南","湖南","湖南"};
	private ListView list;
	private ImageView im;
	ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	public void onCreate(Bundle savedInstanceState)
	  {
		
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.famous_company);
		  ActionBar bar=getActionBar();
		  bar.hide();
		  int lengh = 4;  
	      for(int i =0; i < lengh; i++) {  
	         Map<String,Object> item = new HashMap<String,Object>();  
	          item.put("name",cname[i]);	         
	          item.put("monely",monely[i]);
	          item.put("jobs",jobs[i]); 
	          item.put("city",city[i]);
	         mData.add(item);         
	      }  
	      list=(ListView)findViewById(R.id.f_list); 
		  SimpleAdapter adapter=new SimpleAdapter(Famous_Company.this, mData,R.layout.fcj,new String[]{"name","monely","jobs","city"},new int[]{R.id.fcj_t1,R.id.fcj_t2,R.id.fcj_t3,R.id.fcj_t4});
		  list.setAdapter(adapter); 
		  list.setOnItemClickListener(new OnItemClickListener() {
			  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
				 // Bundle bundle=new Bundle();
				//  bundle.putInt("key",0);
	   		      Intent intent=new Intent();
	   		      intent.setClass(Famous_Company.this,JobinfoActivity.class);//查看详细信息
	   		     // intent.putExtras(bundle);
	   		      startActivity(intent);
	   		  }
		});
		  im=(ImageView) findViewById(R.id.fc_back);
		  im.setOnClickListener(this);
	  }
	public void onClick(View v)
	{
		if(v.getId()==R.id.ct_back)
		{
			Intent intent=new Intent();
			intent.setClass(this,SearchcarerrActivity.class);
			startActivity(intent);
			
		}
	}
}

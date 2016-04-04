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
import android.widget.TableRow;

public class Carerrjob extends Activity{
	/**
	 * 校园招聘结果
	 */
	private TableRow l1,l2,l3;
	private ImageView back;
	private ListView list;
	private String[] jobs={"软件工程师","软件产品经理","网站产品经理","APP产品经理"};
	private String[] monely={"面议","6000-8000","8000-10000","面议"};
	private String[] cname={"湖南开启时代电子信息技术有限公司","湖南米学科技有限公司","湖南天云聚合科技有限公司","摩码资产管理有限公司"};
	private String[] city={"湖南","湖南","湖南","湖南"};
	private ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.carerrjob);
		  ActionBar bar=getActionBar();
	     bar.hide();
	     for(int i =0; i <jobs.length; i++) {  
	         Map<String,Object> item = new HashMap<String,Object>();  
	          item.put("name",cname[i]);	         
	          item.put("monely",monely[i]);
	          item.put("jobs",jobs[i]); 
	          item.put("city",city[i]);
	         mData.add(item);         
	      }  	     
	     inview();
	  }
	public void inview()
	{
		//l1=(TableRow) findViewById(R.id.l1);
		//l2=(TableRow) findViewById(R.id.l1);
		//l3=(TableRow) findViewById(R.id.l1);
		list=(ListView) findViewById(R.id.cb_list);		
		  SimpleAdapter adapter=new SimpleAdapter(Carerrjob.this, mData,R.layout.fcj,new String[]{"name","monely","jobs","city"},new int[]{R.id.fcj_t1,R.id.fcj_t2,R.id.fcj_t3,R.id.fcj_t4});
		  list.setAdapter(adapter); 
		  list.setOnItemClickListener(new OnItemClickListener() {
			  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
				 // Bundle bundle=new Bundle();
				//  bundle.putInt("key",0);
	   		      Intent intent=new Intent();
	   		      intent.setClass(Carerrjob.this,JobinfoActivity.class);//查看详细信息
	   		     // intent.putExtras(bundle);
	   		      startActivity(intent);
	   		  }
		});
		  back=(ImageView) findViewById(R.id.cj_back);
		  back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Carerrjob.this,SearchcarerrActivity.class);
				startActivity(intent);
			}
		});
	}
}

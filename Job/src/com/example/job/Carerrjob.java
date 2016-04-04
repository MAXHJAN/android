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
	 * У԰��Ƹ���
	 */
	private TableRow l1,l2,l3;
	private ImageView back;
	private ListView list;
	private String[] jobs={"�������ʦ","�����Ʒ����","��վ��Ʒ����","APP��Ʒ����"};
	private String[] monely={"����","6000-8000","8000-10000","����"};
	private String[] cname={"���Ͽ���ʱ��������Ϣ�������޹�˾","������ѧ�Ƽ����޹�˾","�������ƾۺϿƼ����޹�˾","Ħ���ʲ��������޹�˾"};
	private String[] city={"����","����","����","����"};
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
	   		      intent.setClass(Carerrjob.this,JobinfoActivity.class);//�鿴��ϸ��Ϣ
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

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
	 * ����ְλ
	 */
	private String[] jobs={"�������ʦ","��Ʒ����","��վ��Ʒ����","APP��Ʒ����"};
	private String[] monely={"����","6000-8000","8000-10000","����"};
	private String[] cname={"���Ͽ���ʱ�����������Լ������޹�˾","������ѧ�Ƽ����޹�˾","�������ƾۺϿƼ����޹�˾","Ħ���ʲ��������޹�˾"};
	private String[] city={"����","����","����","����"};
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
	   		      intent.setClass(Famous_Company.this,JobinfoActivity.class);//�鿴��ϸ��Ϣ
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

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
 * �������������
 */
	private String[] time={"2015-06-25","2015-06-02","2015-06-10","2015-06-11","2015-06-15"};
	private String[] school={"������ѧ","�����ʵ��ѧ","������ͨ��ѧ","������ѧ","�廪��ѧ"};
	private String[] cname={"����ڻ����ʼ���","�������Ǵ�ý�Ƽ����޹�˾","������ѧ�Ƽ����޹�˾","�������ƾۺϿƼ����޹�˾","Ħ���ʲ��������޹�˾"};
	private String[] city={"����","����","����","����","����"};
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


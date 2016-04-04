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

public class Remind extends Activity{
	/**
	 * 我的提醒
	 */
	private String[] time={"2015-06-25  ","2015-06-02  ","2015-06-10  "};
	private String[] school={"北京大学","北京邮电大学","北京交通大学"};
	private String[] cname={"世界冠华国际集团","北京无忧传媒科技有限公司","北京米学科技有限公司"};
	private String[] add={"一教104  ","图书馆招聘教室  ","五教204  "};
	private String[] city={"北京","北京","北京"};
	private ListView list;
	private ImageView back;
	ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remind);
        ActionBar actionBar = getActionBar();  
        actionBar.hide();  
        for(int i =0; i < 3; i++) {  
	         Map<String,Object> item = new HashMap<String,Object>();  
	          item.put("cname",cname[i]); 
	          item.put("city",city[i]);
	          item.put("school",school[i]);
	          item.put("time",time[i]);	          
	          item.put("add",add[i]);
	         mData.add(item);         
	      }  
        list=(ListView) findViewById(R.id.remind_list);
        SimpleAdapter adapter=new SimpleAdapter(Remind.this,mData,R.layout.cc,new String[]{"cname","city","school","time","add"},new int[]{R.id.cc_t1,R.id.cc_t2,R.id.cc_t3,R.id.cc_t4,R.id.cc_t5});
	list.setAdapter(adapter);
	list.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
 		  { 
			           Bundle bundle=new Bundle();
			           bundle.putInt("key",1);
 		               Intent intent=new Intent();
 		               intent.setClass(Remind.this,Carerrtaikinfo.class);
 		               intent.putExtras(bundle);
 		               startActivity(intent);
 		  }
	});
	back=(ImageView) findViewById(R.id.remind_back);
	back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(Remind.this,Userinfo.class);
			startActivity(intent);
		}
	});
	}
}

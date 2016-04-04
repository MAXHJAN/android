package com.example.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Recommendedinfo extends Activity implements android.view.View.OnClickListener{

	private String[] jobs={"java工程师","产品经理","网站产品经理","APP产品经理"};
	private String[] monely={"面议","6000-8000","8000-10000","面议"};
	private String[] cname={"北京热潮网络科技有限公司","北京米学科技有限公司","湖南天云聚合科技有限公司","摩码资产管理有限公司"};
	private String[] city={"北京","北京","湖南","湖南"};
	private String[] flag={"未处理","已同意","未处理","已拒绝"};
	private ListView list;
	private ImageView back;
	ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.recommendinfo);
		  ActionBar bar=getActionBar();
  	  bar.hide();
  	int lengh = 4;  
    for(int i =0; i < lengh; i++) {  
       Map<String,Object> item = new HashMap<String,Object>();  
        item.put("jobs",jobs[i]); 
        item.put("monely",monely[i]);
        item.put("name",cname[i]);
        item.put("city",city[i]);
        item.put("flag",flag[i]);
       mData.add(item);         
    }
    listview();
    back=(ImageView) findViewById(R.id.rdf_back);   
    back.setOnClickListener(this);
	  
	  }
	public void listview()
	{
		list=(ListView) findViewById(R.id.rdf_list);
		 final SimpleAdapter adapter=new SimpleAdapter(Recommendedinfo.this, mData,R.layout.recommend,new String[]{"jobs","monely","name","city","flag"},new int[]{R.id.rd_t1,R.id.rd_t2,R.id.rd_t3,R.id.rd_t4,R.id.rd_t5});
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				Intent intent=new Intent();
				intent.setClass(Recommendedinfo.this,Recommend.class);
				startActivity(intent);
			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener() {			
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					 final int position, long id) {
				// TODO Auto-generated method stub
				AlertDialog dialog=new AlertDialog.Builder(Recommendedinfo.this).create();
				dialog.setTitle("删除");
				dialog.setMessage("你确定要删除该记录吗？");
				dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
              dialog.setButton(DialogInterface.BUTTON_POSITIVE,"确认",new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mData.remove(position);
						adapter.notifyDataSetChanged();  						
					}
				});
              dialog.show();
				return false;
			}
		});
	}
	public void onClick(View v)
	{
		if(v.getId()==R.id.rdf_back)
		{
			Intent intent=new Intent();
			intent.setClass(Recommendedinfo.this,Userinfo.class);
			startActivity(intent);
		}
	}
}

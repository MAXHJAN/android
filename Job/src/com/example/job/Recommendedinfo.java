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

	private String[] jobs={"java����ʦ","��Ʒ����","��վ��Ʒ����","APP��Ʒ����"};
	private String[] monely={"����","6000-8000","8000-10000","����"};
	private String[] cname={"�����ȳ�����Ƽ����޹�˾","������ѧ�Ƽ����޹�˾","�������ƾۺϿƼ����޹�˾","Ħ���ʲ��������޹�˾"};
	private String[] city={"����","����","����","����"};
	private String[] flag={"δ����","��ͬ��","δ����","�Ѿܾ�"};
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
				dialog.setTitle("ɾ��");
				dialog.setMessage("��ȷ��Ҫɾ���ü�¼��");
				dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"ȡ��",new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
              dialog.setButton(DialogInterface.BUTTON_POSITIVE,"ȷ��",new OnClickListener() {
					
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

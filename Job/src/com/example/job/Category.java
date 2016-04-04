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
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Category extends Activity{
	/**
	 * 职位类别
	 */
	ArrayList<Map<String,Object>> mData1= new ArrayList<Map<String,Object>>();
	ArrayList<Map<String,Object>> mData2= new ArrayList<Map<String,Object>>();
	ArrayList<Map<String,Object>> mData3= new ArrayList<Map<String,Object>>();
	private ListView list1,list2,list3;
	private String flg;
	private String[] a={"计算机软件","计算机硬件","计算机服务(系统、数据服务、维修)","通信|电信运营、增值服务","网络游戏","电子技术|半导体|集成电路","仪器仪表|工业自动化"},
			         b={"会计|审计","金融|投资|证券","银行","保险"},
			         c={"贸易|进出口","批发|零售","快速消费品","服装|纺织|皮革","家具|家电|玩具","办公用品及设备","机械|设备|重工","汽车及零配件"};
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.category);
		  ActionBar bar=getActionBar();
  	     bar.hide();
  	     Intent in=getIntent();
  	     Bundle bb=in.getExtras();
  	    flg=bb.getString("flg");
  	   for(int i =0; i < a.length; i++) {  
			Map<String,Object> item = new HashMap<String,Object>();
	          item.put("name1",a[i]); 	          
	         mData1.add(item);         
	      } 
  	 for(int i =0; i < b.length; i++) {   
		 Map<String,Object> item1 = new HashMap<String,Object>();  
          item1.put("name2",b[i]); 	          
         mData2.add(item1);         
      }  
  	 for(int i =0; i < c.length; i++) {  
		 Map<String,Object> item2 = new HashMap<String,Object>();  
          item2.put("name3",c[i]); 	          
         mData3.add(item2);         
      }  
  	     inview();
	  }
	private void inview() {
		// TODO Auto-generated method stub
		
		list1=(ListView) findViewById(R.id.cg_list1);
		list2=(ListView) findViewById(R.id.cg_list2);
		list3=(ListView) findViewById(R.id.cg_list3);
		 
		 SimpleAdapter adapter1=new SimpleAdapter(Category.this,mData1,R.layout.aa,new String[]{"name1"},new int[]{R.id.infoaa_text});
		 list1.setAdapter(adapter1); 
		 list1.setOnItemClickListener(new OnItemClickListener() 
	   	  {	  
	   		  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
	   			  Bundle bundle=new Bundle();
	   			  bundle.putString("str",a[position]);
	   			  bundle.putString("flg",flg);
	   			  Intent intent=new Intent();
	   			  intent.setClass(Category.this,CategoryChild.class);
	   			  intent.putExtras(bundle);
	   			  startActivity(intent);	   			  
	   		  }
	   	  });
		 SimpleAdapter adapter2=new SimpleAdapter(Category.this,mData2,R.layout.aa,new String[]{"name2"},new int[]{R.id.infoaa_text});
		 list2.setAdapter(adapter2); 
		
		 SimpleAdapter adapter3=new SimpleAdapter(Category.this,mData3,R.layout.aa,new String[]{"name3"},new int[]{R.id.infoaa_text});
		 list3.setAdapter(adapter3); 
	}
}

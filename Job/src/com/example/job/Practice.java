package com.example.job;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

public class Practice extends Activity implements OnClickListener,OnGetGeoCoderResultListener{
      /**
        * 实习招聘
        */
	private ImageView back;
	 GeoCoder[] mSearch = new GeoCoder[7];
	    private int ff=0;
	    private TextView text,text1,text2;
	    String[] city={"湘潭九华经济开发区创新创业园 ","湘潭市岳塘区怡景财富广场1单元14248号 ","湘潭岳塘区吉安路70号中瀚财富广场","湘潭湘潭县易俗河镇海棠中路海鸥路北侧芸香路东侧","湘潭雨湖区车站路星河电脑城",
	    		"湘潭湘乡市政务大楼11楼","湖南省湘潭市雨湖区建设北路技术监督局右侧 "};
	    private static  int p=0;
		private List<String>items = new ArrayList<String>();
		private SharedPreferences agPreferences;
	    private SharedPreferences.Editor editor;
	public void onCreate(Bundle  savedInstanceState)
	  {
		SDKInitializer.initialize(getApplicationContext());
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.practice);
		  ActionBar bar1=getActionBar();
		  bar1.hide();
		  for(int i=0;i<7;i++)
	  		{
	  		     mSearch[i] = GeoCoder.newInstance();
	  		     mSearch[i].setOnGetGeoCodeResultListener(this);
	  		}
	    	  agPreferences =this. getSharedPreferences("city2",MODE_APPEND);
	    	  editor = agPreferences.edit();
	    	  SharedPreferences preferences = getSharedPreferences("city1", Activity.MODE_PRIVATE);
	    	  intview();
	    	  if(ff==0)  
	    		  text.setText(preferences.getString("city",null));//获取当前城市   
	  }
	public void intview()
	{
		 Button  b1=(Button)findViewById(R.id.sh_button);
   	     b1.setOnClickListener(this);
   	  TableRow tablerow1=(TableRow) findViewById(R.id.sh_Tablerow1);
	  tablerow1.setOnClickListener(this);
	  TableRow tablerow2=(TableRow) findViewById(R.id.sh_Tablerow2);
	  tablerow2.setOnClickListener(this);
	  TableRow tablerow3=(TableRow) findViewById(R.id.sh_Tablerow3);
	  tablerow3.setOnClickListener(this);
	   text=(TextView) findViewById(R.id.a_text);
	   text1=(TextView) findViewById(R.id.a_text1);
	   text2=(TextView) findViewById(R.id.a_text2);
	   back=(ImageView) findViewById(R.id.pc_back);
	   back.setOnClickListener(this);
	}
	public void onClick(View v)
	{
		if(v.getId()==R.id.pc_back)//返回
		{
			Intent intent2=new Intent();
   			intent2.setClass(Practice.this,Home.class);
   			 startActivity(intent2); 
		}
		if(v.getId()==R.id.sh_Tablerow1)//选择地点
		{
			  Bundle bb=new Bundle();
			  bb.putString("flag","sp");
			  Intent intent2=new Intent();
		      intent2.setClass(Practice.this,CityActivity.class);
		      intent2.putExtras(bb);
		      startActivityForResult(intent2,101);
		}
		if(v.getId()==R.id.sh_Tablerow2)//选择地点
		{
			  Bundle bb=new Bundle();
			  bb.putString("flg","pr");
			  Intent intent2=new Intent();
		      intent2.setClass(Practice.this,Category.class);
		      intent2.putExtras(bb);
		      startActivity(intent2);
		}
		if(v.getId()==R.id.sh_Tablerow3)
		{
			  Bundle bb=new Bundle();
			  bb.putString("flag","jb3");
			  Intent intent2=new Intent();
		      intent2.setClass(Practice.this,Business.class);
		      intent2.putExtras(bb);
		      startActivityForResult(intent2,102);
		}
		if(v.getId()==R.id.sh_button)
		{
			if(text.getText().toString().equals(""))
				  Toast.makeText(this,"请选择地点", Toast.LENGTH_SHORT).show();
			  else
			  {				  
				  String[] city1=text.getText().toString().split("市");   				 
				 // Toast.makeText(this,text.getText().toString(), Toast.LENGTH_SHORT).show();			  			   
				for (int i = 0; i <city.length; i++)//删除重复地址
				{					
					
					  items.add(city[i]);
					  mSearch[i].geocode(new GeoCodeOption()//开始编码
                    .city(city1[0])
                    .address(city[i]));
					
				}    				
			Bundle bundle = new Bundle();
			bundle.putString("city", city1[0]);
			bundle.putStringArrayList("items", (ArrayList<String>) items);
			Intent intent=new Intent();
			intent.setClass(Practice.this,Practicemap.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}
		}
	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  		if(requestCode==101 && resultCode==101){
	  			Bundle b=data.getExtras();
	  			text.setText(b.getString("city"));
	  			ff=1;
	  		}
	  		if(requestCode==102 && resultCode==102){
	  			Bundle b=data.getExtras();	  	  
	  			 text2.setText(b.getString("job"));
	  			
	  		}
	      
	  		}
	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		// TODO Auto-generated method stub
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
  			Toast.makeText(Practice.this, "抱歉，未能找到结果", Toast.LENGTH_SHORT).show();
  			return;
  		}		 
  		String strInfo = String.format("纬度：%f 经度：%f",
  				result.getLocation().latitude, result.getLocation().longitude);  		      
         editor.putString(""+p,""+result.getLocation().latitude);
         p++;
         editor.putString(""+p,""+result.getLocation().longitude); 
         p++;
         editor.putInt("p",p); 
         editor.commit();

	}
	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
		// TODO Auto-generated method stub
		
	}
	public boolean onKeyUp(int keyCode, KeyEvent event) 
    {
   	 if(keyCode==KeyEvent.KEYCODE_BACK)
   	 {
   		  	 Intent intent2=new Intent();
   			intent2.setClass(Practice.this,Home.class);
   			 startActivity(intent2);  		
              return true;
   	 }
   	 return super.onKeyUp(keyCode, event);
    }
}

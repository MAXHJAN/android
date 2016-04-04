package com.example.job;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;


public class Search extends Activity implements OnClickListener,OnGetGeoCoderResultListener{
	/**
	 * 兼职招聘
	 */
	 GeoCoder[] mSearch = new GeoCoder[5];
	private TextView text,text1,text2;
	ImageView imge,i1,i2,i3;
	private String result="",re="";
	private String[] resultaddress=new String[100];
	private static  int number=0,p=0;
	private List<String>items = new ArrayList<String>();
	private SharedPreferences agPreferences;
    private SharedPreferences.Editor editor;    
    private int ff=0;
      public void onCreate(Bundle  savedInstanceState)
      {
    	  SDKInitializer.initialize(getApplicationContext());
    	  super.onCreate(savedInstanceState);
    	  setContentView(R.layout.search);
    	  ActionBar bar=getActionBar();
    	  bar.hide();
    	  if (android.os.Build.VERSION.SDK_INT > 9) {
     		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
     		    StrictMode.setThreadPolicy(policy);}/////
    	  inview();    	    
    	  for(int i=0;i<5;i++)
  		{
  		     mSearch[i] = GeoCoder.newInstance();
  		     mSearch[i].setOnGetGeoCodeResultListener(this);
  		}
    	  agPreferences =this. getSharedPreferences("city1",MODE_APPEND);
    	  editor = agPreferences.edit();
      }
      public void onStart()
      {
    	  super.onStart();
    	  SharedPreferences preferences = getSharedPreferences("city1", Activity.MODE_PRIVATE);
    	  if(ff==0)  
    		  text.setText(preferences.getString("city",null));//获取当前城市   		  
    	  SharedPreferences  preferences1 = getSharedPreferences("user", Activity.MODE_PRIVATE);
    	  if(preferences1.getString("time","").equals(""))///////
    		  text1.setText("所有时间");
    	  else
		      text1.setText(preferences1.getString("time",null));
      }
      public void inview()//添加监听
      {
    	  Button  b1=(Button)findViewById(R.id.search_button);
    	  b1.setOnClickListener(this);
    	  imge=(ImageView) findViewById(R.id.search_imge);
    	  imge.setOnClickListener(this);
    	  TableRow tablerow1=(TableRow) findViewById(R.id.search_Tablerow1);
    	  tablerow1.setOnClickListener(this);
    	  TableRow tablerow2=(TableRow) findViewById(R.id.search_Tablerow2);
    	  tablerow2.setOnClickListener(this);
    	  TableRow tablerow3=(TableRow) findViewById(R.id.search_Tablerow3);
    	  tablerow3.setOnClickListener(this);
    	   text=(TextView) findViewById(R.id.aa_text);
    	   text1=(TextView) findViewById(R.id.aa_text1);
    	   text2=(TextView) findViewById(R.id.aa_text2);
    	   i1=(ImageView) findViewById(R.id.gohome);
     	   i1.setOnClickListener(this);
     	   i2=(ImageView) findViewById(R.id.goxiaoyuan);
   	       i2.setOnClickListener(this);
           i3=(ImageView) findViewById(R.id.gouserinfo);
	       i3.setOnClickListener(this);
    	  
      }
      public void onClick(View v) {
			// TODO Auto-generated method stub
    	  switch(v.getId())
    	  {
    		  case R.id.search_button:			    			 
    			  if(text.getText().toString().equals(""))
    				  Toast.makeText(this,"请选择地点", Toast.LENGTH_SHORT).show();
    			  else
    			  {   				 
    				  String[] city=text.getText().toString().split("市");   				 
    				 // Toast.makeText(this,text.getText().toString(), Toast.LENGTH_SHORT).show();
    			    send(city[0]);
    			    int byxbb;   					
    				items.add(resultaddress[0]);
    				mSearch[0].geocode(new GeoCodeOption()//开始编码
                    .city(city[0])
                    .address(resultaddress[0]));
    				for (int i = 1,j=1; i <number; i++)//删除重复地址
    				{
    					for(byxbb=0;byxbb<i;byxbb++)
    						if(resultaddress[i].equals(resultaddress[byxbb]))
    							break;
    					if(byxbb==i)
    					{
    					  items.add(resultaddress[i]);
    					  mSearch[j++].geocode(new GeoCodeOption()//开始编码
                          .city(city[0])
                          .address(resultaddress[i]));
    					}
    				}    				
    			Bundle bundle = new Bundle();
    			bundle.putString("city", city[0]);
  				bundle.putStringArrayList("items", (ArrayList<String>) items);
  				Intent intent=new Intent();
  				intent.setClass(Search.this,MapActivity.class);
  				intent.putExtras(bundle);
  				startActivity(intent);
    			  }
    			  
  			      break;
    		  case R.id.search_imge:    			
    			  Intent intent1=new Intent();
			      intent1.setClass(Search.this,Home.class);
			      startActivity(intent1);
			      break; 
    		  case R.id.search_Tablerow1:   //选择地点
    			  Bundle bb=new Bundle();
    			  bb.putString("flag","sh");
    			  Intent intent2=new Intent();
			      intent2.setClass(Search.this,CityActivity.class);
			      intent2.putExtras(bb);
			      startActivityForResult(intent2,0);
			      break;
    		  case R.id.search_Tablerow2:
    			  Intent intent3=new Intent();
			      intent3.setClass(Search.this,WeekActivity.class);
			      startActivity(intent3);
			      break;
    		  case R.id.search_Tablerow3:
    			  Intent intent4=new Intent();
			      intent4.setClass(Search.this,Worktype.class);
			      startActivityForResult(intent4,4);
			      break;
    		  case R.id.gohome:
    			  Intent intent5=new Intent();
			      intent5.setClass(Search.this,Home.class);
			      startActivity(intent5);
			      break;
    		  case R.id.goxiaoyuan:
    			  Intent intent6=new Intent();
			      intent6.setClass(Search.this,SearchcarerrActivity.class);
			      startActivity(intent6);
			      break;
    		  case R.id.gouserinfo:
    			  Intent intent7=new Intent();
			      intent7.setClass(Search.this,Userinfo.class);
			      startActivity(intent7);
			      break;
    	  }
    	   	
		}
      protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  		if(requestCode==0 && resultCode==0){
  			Bundle b=data.getExtras();
  			text.setText(b.getString("city"));
  			ff=1;
  		}
  		if(requestCode==4 && resultCode==4){
  			Bundle b=data.getExtras();
  			if(b.getString("work").equals(""))
  				text2.setText("IT");
  			else
  			 text2.setText(b.getString("work"));
  			
  		}
      
  		}
      
      public void send(String city)
      {
     		HttpClient client = new DefaultHttpClient();
     	    StringBuilder builder = new StringBuilder();
     	    HttpPost mypost = new HttpPost("http://1.mxmap.sinaapp.com/jobs/city.php");
     	    List<NameValuePair> params = new ArrayList<NameValuePair>();
  		params.add(new BasicNameValuePair("params", "POST"));
  		params.add(new BasicNameValuePair("city",city));
     	    try {
     	    	
  			mypost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
     	    	System.out.println("开始1111");
     	        HttpResponse response = client.execute(mypost);
     	     System.out.println("开始-------------------2");
     	        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
     	        {
     				//Toast.makeText(this, "连接成功", Toast.LENGTH_LONG).show();
     				
     	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));	      
     	        for (result = reader.readLine();result != null; result = reader.readLine()) {
     	            builder.append(result+"\n");
     	            System.out.println(builder.toString());    	            
     	        }
     	        JSONArray json = new JSONArray(builder.toString()); //解析json数据
     	        number=json.length();    	         	
     	        for(int j=0;j<number;j++)
     	        {
     	        	re=json.getString(j);
     	        	resultaddress[j]=re;
     	        	 System.out.println("-----"+resultaddress[j]);   	        	 
     	        }  	               
     	        }

     	    } catch (Exception e) {
     	        Log.v("url response", "false");
     	        e.printStackTrace();
     	    }

     	}
      public void onGetGeoCodeResult(GeoCodeResult result) {
  		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
  			Toast.makeText(Search.this, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
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

  		System.out.println("-------------------"+strInfo);
  	//	Toast.makeText(Search.this, strInfo, Toast.LENGTH_LONG).show();
  	}
  	//地理反编码监听器
  	@Override
  	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
  		// TODO Auto-generated method stub
  	}
    public boolean onKeyUp(int keyCode, KeyEvent event) 
    {
   	 if(keyCode==KeyEvent.KEYCODE_BACK)
   	 {
   		  	 Intent intent2=new Intent();
   			intent2.setClass(Search.this,Home.class);
   			 startActivity(intent2);  		
              return true;
   	 }
   	 return super.onKeyUp(keyCode, event);
    }
}

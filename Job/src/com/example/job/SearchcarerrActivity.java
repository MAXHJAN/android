package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
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

public class SearchcarerrActivity extends Activity implements OnClickListener{
	/**
	 * 校园招聘
	 */
	private TableRow t0,t1,t2,t3,t4;
	private ImageView im;
	private TextView tv,tv1;
	private Button bt;
	public void onCreate(Bundle  savedInstanceState)
    {
  	  super.onCreate(savedInstanceState);
  	  setContentView(R.layout.serchcarerr);
  	  ActionBar bar=getActionBar();
  	  bar.hide();
  	  intview();
  	  SharedPreferences preferences = getSharedPreferences("city1", Activity.MODE_PRIVATE);
	  tv.setText(preferences.getString("city",null));
    }
	
	public void intview(){
		tv=(TextView) findViewById(R.id.serchcarerr_text);
		tv1=(TextView) findViewById(R.id.serchcarerr_text2);
		t0=(TableRow) findViewById(R.id.sc_t0);//名企
		t0.setOnClickListener(this);
		t1=(TableRow) findViewById(R.id.sc_t1);//宣讲会
		t1.setOnClickListener(this);
		t2=(TableRow) findViewById(R.id.sc_Tablerow1);//地点
		t2.setOnClickListener(this);
		t3=(TableRow) findViewById(R.id.sc_Tablerow3);//选择行业
		t3.setOnClickListener(this);
		t4=(TableRow) findViewById(R.id.sc_Tablerow2);//选择职位
		t4.setOnClickListener(this);
		im=(ImageView) findViewById(R.id.sc_back);//返回
		im.setOnClickListener(this);
		bt=(Button) findViewById(R.id.sc_button);
		bt.setOnClickListener(this);
	}
	public void onClick(View v){
	/*	if(v.getId()==R.id.sc_back);	//返回	
		{
			Intent intent1=new Intent();
			intent1.setClass(this,Home.class);
			startActivity(intent1);
		}*/
		if(v.getId()==R.id.sc_Tablerow1)//选择地点
		{
			Bundle bb=new Bundle();
  	         bb.putString("flag","sa");
			Intent intent=new Intent();
			intent.setClass(this, CityActivity.class);
			intent.putExtras(bb);
			 startActivityForResult(intent,1);
		}
		if(v.getId()==R.id.sc_Tablerow2)//职位
		{
			Bundle bundle=new Bundle();
			bundle.putString("flg","sca");
			Intent intent=new Intent();
			intent.setClass(this, Category.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}
		if(v.getId()==R.id.sc_Tablerow3)//行业
		{
			Bundle bb=new Bundle();
			bb.putString("flag","jb2");
			Intent intent=new Intent();
			intent.setClass(this, Business.class);
			intent.putExtras(bb);
		      startActivityForResult(intent,11);
		}
		if(v.getId()==R.id.sc_t0)//知名企业
		{
			Intent intent=new Intent();
			intent.setClass(this, Famous_Company.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.sc_t1)//宣讲会
		{
			Intent intent=new Intent();
			intent.setClass(this, Searchcarerrtaik.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.sc_button)//搜索
		{
			Intent intent=new Intent();
			intent.setClass(this, Carerrjob.class);
			startActivity(intent);
		}
		
	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  		if(requestCode==1 && resultCode==1){
	  			Bundle b=data.getExtras();
	  			tv.setText(b.getString("city"));  		
	  		}
	  		if(requestCode==11 && resultCode==11){
	  			Bundle b=data.getExtras();
	  			tv1.setText(b.getString("job"));  		
	  		}
	      
	  		}
	 public boolean onKeyUp(int keyCode, KeyEvent event) 
	    {
	   	 if(keyCode==KeyEvent.KEYCODE_BACK)
	   	 {
	   		  	 Intent intent2=new Intent();
	   			intent2.setClass(SearchcarerrActivity.this,Home.class);
	   			 startActivity(intent2);  		
	              return true;
	   	 }
	   	 return super.onKeyUp(keyCode, event);
	    }
	
}

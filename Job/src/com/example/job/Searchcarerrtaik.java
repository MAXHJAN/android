package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class Searchcarerrtaik extends Activity implements OnClickListener{
	/**
	 * 宣讲会
	 */
	private Button b1;
	private TableRow t1,t2,t3,t4;
	private ImageView im;
	private TextView text,text1,text2,text3;
	 public void onCreate(Bundle  savedInstanceState)
     {
   	  super.onCreate(savedInstanceState);
   	  setContentView(R.layout.carerrtaik);
   	  ActionBar bar=getActionBar();
	  bar.hide();
	  text=(TextView) findViewById(R.id.c_text);
	  SharedPreferences preferences = getSharedPreferences("city1", Activity.MODE_PRIVATE);
	  text.setText(preferences.getString("city",null));//获取当前城市
	  intview();
     }
	 public void intview(){
		 im=(ImageView) findViewById(R.id.sc_imge);
		 im.setOnClickListener(this);
		 text1=(TextView) findViewById(R.id.c_text1);
		 text2=(TextView) findViewById(R.id.c_text2);
		 text3=(TextView) findViewById(R.id.c_text3);
		 b1=(Button) findViewById(R.id.s_button);
		 b1.setOnClickListener(this);
		 t1=(TableRow) findViewById(R.id.s_Tablerow1);
		 t1.setOnClickListener(this);
		 t2=(TableRow) findViewById(R.id.s_Tablerow2);
		 t2.setOnClickListener(this);
		 t3=(TableRow) findViewById(R.id.s_Tablerow3);
		 t3.setOnClickListener(this);
		 t4=(TableRow) findViewById(R.id.s_Tablerow4);
		 t4.setOnClickListener(this);
	 }
	 public void onClick(View v){
		 if(v.getId()==R.id.sc_imge)//返回
		 {
			 Intent intent=new Intent();
			 intent.setClass(Searchcarerrtaik.this,SearchcarerrActivity.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.s_button)//搜索
		 {
			 Intent intent=new Intent();
			 intent.setClass(Searchcarerrtaik.this,Carerrtaiklist.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.s_Tablerow1)//选择地点
		 {
			 Bundle bb=new Bundle();
			  bb.putString("flag","st");
			  Intent intent2=new Intent();
		      intent2.setClass(this,CityActivity.class);
		      intent2.putExtras(bb);
		      startActivityForResult(intent2,2);
		 }
		 if(v.getId()==R.id.s_Tablerow2)//选择学校
		 {
			 Intent intent2=new Intent();
		      intent2.setClass(Searchcarerrtaik.this,School.class);
		      startActivityForResult(intent2,0);
		 }
		 if(v.getId()==R.id.s_Tablerow3)//选择时间
		 {
			 Intent intent=new Intent();
			 intent.setClass(Searchcarerrtaik.this,Carerrtime.class);
			 startActivityForResult(intent,33);		
		 }
		 if(v.getId()==R.id.s_Tablerow4)//选择行业
		 {
			 Bundle bb=new Bundle();
			 Intent intent=new Intent();
			 bb.putString("flag","jb4");		     
			 intent.putExtras(bb);
			 intent.setClass(Searchcarerrtaik.this,Business.class);
		     startActivityForResult(intent,22);					 
		 }
	 }
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  		if(requestCode==2 && resultCode==2){
	  			Bundle b=data.getExtras();
	  			text.setText(b.getString("city"));
	  		}
	  		if(requestCode==0 && resultCode==0){
	  			Bundle b=data.getExtras();	  			
	  			text1.setText(b.getString("school"));
	  		}
	  		if(requestCode==22 && resultCode==22){
	  			Bundle b=data.getExtras();
	  			text3.setText(b.getString("job"));
	  		}
	  		if(requestCode==33 && resultCode==33){
	  			Bundle b=data.getExtras();
	  			text2.setText(b.getString("ctime"));
	  		}
	      
	      
	  		}
	      
}

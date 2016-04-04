package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Jobintension extends Activity implements OnClickListener{
	/**
	 * 求职意向
	 */
    private TextView t1,t2,t3;
    private ImageView back;
    private Button bt;
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.jobintension);
		  ActionBar bar=getActionBar();
		  bar.hide();
		  intview();
	  }
	public void intview()
	{
		  t1=(TextView) findViewById(R.id.j_t1);
		  t2=(TextView) findViewById(R.id.j_t2);
		  t3=(TextView) findViewById(R.id.j_t3);		 
		  t1.setOnClickListener(this);
		  t2.setOnClickListener(this);
		  t3.setOnClickListener(this);
		  back=(ImageView) findViewById(R.id.jbs_back);
		  back.setOnClickListener(this);
		  bt=(Button) findViewById(R.id.jbs_button);
		  bt.setOnClickListener(this);
	}
	public void onClick(View v)
	{
		if(v.getId()==R.id.jbs_back)//返回
		{			
			  Intent intent2=new Intent();
		      intent2.setClass(Jobintension.this,Compileresume.class);
		      startActivity(intent2);
		}
		if(v.getId()==R.id.t1)//地点
		{
			Bundle bb=new Bundle();
			  bb.putString("flag","sj");
			  Intent intent2=new Intent();
		      intent2.setClass(Jobintension.this,CityActivity.class);
		      intent2.putExtras(bb);
		      startActivityForResult(intent2,3);
		}
		if(v.getId()==R.id.t2)//职位
		{
			 Bundle bb=new Bundle();
			  bb.putString("flg","jbt");
			  Intent intent2=new Intent();
		      intent2.setClass(Jobintension.this,Category.class);
		      intent2.putExtras(bb);
		      startActivity(intent2);
		}
		if(v.getId()==R.id.t3)//行业
		{
			Bundle bb=new Bundle();
			  bb.putString("flag","jb1");
			  Intent intent2=new Intent();
		      intent2.setClass(Jobintension.this,Business.class);
		      intent2.putExtras(bb);
		      startActivityForResult(intent2,0);
		}
		if(v.getId()==R.id.jbs_button)//返回
		{		
			Toast.makeText(Jobintension.this,"保存成功",Toast.LENGTH_SHORT).show();
			  Intent intent2=new Intent();
		      intent2.setClass(Jobintension.this,Compileresume.class);
		      startActivity(intent2);
		}
	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  		if(requestCode==3 && resultCode==3){
	  			Bundle b=data.getExtras();
	  			t1.setText(b.getString("city"));
	  		}
	  		if(requestCode==0 && resultCode==0){
	  			Bundle b=data.getExtras();
	  			t3.setText(b.getString("job"));
	  		}
	      
	  		}
}

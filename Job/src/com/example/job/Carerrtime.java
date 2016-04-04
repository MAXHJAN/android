package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

public class Carerrtime extends Activity implements OnClickListener{
/**
 * 宣讲会时间
 */
	private TableRow[] tb=new TableRow[11];
	private ImageView[] ima=new ImageView[11];
	private ImageView im;
	private int[] id1={R.id.c_image1,R.id.c_image2,R.id.c_image3,R.id.c_image4,R.id.c_image5,R.id.c_image6,R.id.c_image7};
	private int[] id2={R.id.c_tab1,R.id.c_tab2,R.id.c_tab3,R.id.c_tab4,R.id.c_tab5,R.id.c_tab6,R.id.c_tab7};
	public String[] ctime={"今天","近三天","近一周","近两周","近一月","近两月","已举行"};
	public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.carerrtime);
    	ActionBar bar=getActionBar();
    	bar.hide();
    	im=(ImageView) findViewById(R.id.ctime_image);//返回
    	im.setOnClickListener(this);
    	for(int i=0;i<7;i++)
    	{
    		tb[i]=(TableRow) findViewById(id2[i]);
    		tb[i].setOnClickListener(this);
    		ima[i]=(ImageView)findViewById(id1[i]);
    	}
    }
	 public void onClick(View v)
	 {
		 if(v.getId()==R.id.ctime_image)
		 {
			 Intent intent=new Intent();
			 intent.setClass(Carerrtime.this,Search.class);
			 startActivity(intent);
		 }
		 else
		 {
			 for(int i=0;i<11;i++)
				 if(v.getId()==id2[i])
				 {
					 ima[i].setImageDrawable(getResources().getDrawable(R.drawable.user_sex_select));					 										
					 Bundle bundle=new Bundle();
					 bundle.putString("ctime",ctime[i]);
					 Intent intent=new Intent();
					 intent.setClass(Carerrtime.this,Searchcarerrtaik.class);
					 intent.putExtras(bundle);
					 setResult(33,intent);
					 break;
					 
				 }
			 finish();
		 }
	 }
}
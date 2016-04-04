package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;


public class Worktype extends Activity implements OnClickListener{

	private TableRow[] tb=new TableRow[11];
	private ImageView[] ima=new ImageView[11];
	private ImageView im;
	private Button bt;
	private int[] id1={R.id.wt_image1,R.id.wt_image2,R.id.wt_image3,R.id.wt_image4,R.id.wt_image5,
			R.id.wt_image6,R.id.wt_image7,R.id.wt_image8,R.id.wt_image9,R.id.wt_image10,R.id.wt_image11};
	private int[] id2={R.id.wt_tab1,R.id.wt_tab2,R.id.wt_tab3,R.id.wt_tab4,R.id.wt_tab5,
			R.id.wt_tab6,R.id.wt_tab7,R.id.wt_tab8,R.id.wt_tab9,R.id.wt_tab10,R.id.wt_tab11};
	private int ff=0;
	public String[] work={"IT","教育","服务","管理","设计","翻译","钟点工","软文手写","校园代理","保姆","发传单"};
	String str="";
	public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.worktype);
    	ActionBar bar=getActionBar();
    	bar.hide();
    	im=(ImageView) findViewById(R.id.week_image);//返回
    	im.setOnClickListener(this);
    	bt=(Button) findViewById(R.id.wk_button);//确定
    	bt.setOnClickListener(this);
    	for(int i=0;i<11;i++)
    	{
    		tb[i]=(TableRow) findViewById(id2[i]);
    		tb[i].setOnClickListener(this);
    		ima[i]=(ImageView)findViewById(id1[i]);
    	}
    }
	 public void onClick(View v)
	 {
		 if(v.getId()==R.id.week_image)
		 {
			 Intent intent=new Intent();
			 intent.setClass(Worktype.this,Search.class);
			 startActivity(intent);
		 }
		 else
		 {
			 for(int i=0;i<11;i++)
				 if(v.getId()==id2[i])
				 {
					 if(ff>1)
						 Toast.makeText(Worktype.this,"最多只能选择2个工作类别",Toast.LENGTH_SHORT).show();
					 else
					 {
					 ima[i].setImageDrawable(getResources().getDrawable(R.drawable.user_sex_select));					 
					 ff++;
					 str=str+work[i]+",";					
					 }
				 }
		 }
		 if(v.getId()==R.id.wk_button)
		 {
			 Bundle bundle=new Bundle();
			 bundle.putString("work",str);
			 Intent intent=new Intent();
			 intent.setClass(Worktype.this,Search.class);
			 intent.putExtras(bundle);
			 setResult(4,intent);
			 finish();
		 }
	 }
}

package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;	
import android.widget.Toast;
public class Carerrtaikinfo extends Activity implements OnClickListener{
/**
 * 宣讲会详细信息
 */
	private Button bt;
	private int flag=0;
	 public void onCreate(Bundle  savedInstanceState)
     {
   	  super.onCreate(savedInstanceState);
   	  setContentView(R.layout.carerrtaikinfo);
   	  ActionBar bar=getActionBar();
	  bar.hide();
	  bt=(Button) findViewById(R.id.ck_button1);
	  bt.setOnClickListener(this);
	  Intent in=getIntent();
	  Bundle bu=in.getExtras();
	  flag=bu.getInt("key",0);
	  if(flag==1)
	  {
		  bt.setText("已加入提醒");
	  }
     }
	 public void onClick(View v) {
			// TODO Auto-generated method stub
		 if(flag==1)
		 {
			 Toast.makeText(this,"已加入我的提醒",Toast.LENGTH_SHORT).show();
		 }
		 else
		 {
			AlertDialog bd=new AlertDialog.Builder(Carerrtaikinfo.this).create();
			LayoutInflater inflater = Carerrtaikinfo.this.getLayoutInflater();  
	         View view = inflater.inflate(R.layout.reminddialog, null); 
	         bd.setView(view);
	         bd.setIcon(R.drawable.remind);
	         bd.setTitle("提醒");
			bd.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
        bd.setButton(DialogInterface.BUTTON_POSITIVE, "确认",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					bt.setText("已加入提醒");
					flag=1;
				}
			});
			
		 bd.show();		
		}
	 }
}

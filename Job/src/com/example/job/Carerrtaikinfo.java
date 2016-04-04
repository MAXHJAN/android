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
 * ��������ϸ��Ϣ
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
		  bt.setText("�Ѽ�������");
	  }
     }
	 public void onClick(View v) {
			// TODO Auto-generated method stub
		 if(flag==1)
		 {
			 Toast.makeText(this,"�Ѽ����ҵ�����",Toast.LENGTH_SHORT).show();
		 }
		 else
		 {
			AlertDialog bd=new AlertDialog.Builder(Carerrtaikinfo.this).create();
			LayoutInflater inflater = Carerrtaikinfo.this.getLayoutInflater();  
	         View view = inflater.inflate(R.layout.reminddialog, null); 
	         bd.setView(view);
	         bd.setIcon(R.drawable.remind);
	         bd.setTitle("����");
			bd.setButton(DialogInterface.BUTTON_NEGATIVE, "ȡ��",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
        bd.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					bt.setText("�Ѽ�������");
					flag=1;
				}
			});
			
		 bd.show();		
		}
	 }
}

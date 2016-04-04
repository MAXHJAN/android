package com.example.androidsocketdemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Handler mHandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what==0x01){
				Toast.makeText(MainActivity.this, "网络连接异常!",1000).show();			
			}
			if(msg.what==0x02)
			{
				Toast.makeText(MainActivity.this, "发送成功!",500).show();
			}
		}
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText ed=(EditText) findViewById(R.id.ed);
		Button bt=(Button) findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Toast.makeText(MainActivity.this, "开始发送!",500).show();
				send(ed.getText().toString());*/
				Intent intent=new Intent();
				intent.setClass(MainActivity.this,WebViewDemo.class);
				startActivity(intent);
			}
		});
	}
  public void send(final String str){
	  
	  
	  new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.e("123", "----1----");
			 try {
					Socket socket=new Socket(InetAddress.getByName("192.168.191.1"),8080);
					Log.e("123", "----2----");
					if(socket.isClosed()||socket==null)
					{
						Looper.prepare();
						Message msg=Message.obtain();
						msg.what=0x01;
						mHandler.sendMessage(msg);
					}
					Log.e("123", "----3----");
					OutputStream out=socket.getOutputStream();
					out.write(str.getBytes());
					Looper.prepare();
					Message msg=Message.obtain();
					msg.what=0x02;
					mHandler.sendMessage(msg);
					out.close();
					socket.close();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
		}
	}).start();
	 
  }
	}

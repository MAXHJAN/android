package hnust.sendbluetooth;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver{

    private String TAG="AutSMS";
    //广播消息类型
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	@Override
	public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        if (SMS_RECEIVED_ACTION.equals(action))
	        {
	            //获取intent参数
	            Bundle bundle=intent.getExtras();
	            //判断bundle内容
	            if (bundle!=null)
	            {
	                //取pdus内容,转换为Object[]
	                Object[] pdus=(Object[])bundle.get("pdus");
	                //解析短信
	                SmsMessage[] messages = new SmsMessage[pdus.length];
	                for(int i=0;i<messages.length;i++)
	                {
	                    byte[] pdu=(byte[])pdus[i];
	                    messages[i]=SmsMessage.createFromPdu(pdu);
	                }    
	                //解析完内容后分析具体参数
	                for(SmsMessage msg:messages)
	                {
	                    //获取短信内容
	                    String content=msg.getMessageBody();
	                    String sender=msg.getOriginatingAddress();
	                    Date date = new Date(msg.getTimestampMillis());
	                    //TODO:根据条件判断,然后进一般处理
	                    final String phone=sender.substring(3,sender.length());
	                    String hander=content.substring(0,4);
	        			final String bluetooth=content.substring(5,content.length());
	        			if(hander==null)
	        			{
	        				Log.i("TAG", "null");
	        				return;
	        			}
	        			if(hander.endsWith("10101"));{
	        				abortBroadcast();
	        				new Thread(new Runnable() {
	        					
	        					@Override
	        					public void run() {
	        						// TODO Auto-generated method stub
	        						new DemoDB().send(phone, bluetooth);
	        					}
	        				}).start();
	        				
	        				 Intent intn = new Intent();  
	        				 intn.putExtra("result", phone+"--"+bluetooth+"发送成功");  
	        				 intn.setClass(context, MainActivity.class);  
	        				 intn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	        			     context.getApplicationContext().startActivity(intn);  	
	        			}
	                }
	                
	            
	        }//if 判断广播消息结束
	}
	}
}

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
    //�㲥��Ϣ����
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	@Override
	public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        if (SMS_RECEIVED_ACTION.equals(action))
	        {
	            //��ȡintent����
	            Bundle bundle=intent.getExtras();
	            //�ж�bundle����
	            if (bundle!=null)
	            {
	                //ȡpdus����,ת��ΪObject[]
	                Object[] pdus=(Object[])bundle.get("pdus");
	                //��������
	                SmsMessage[] messages = new SmsMessage[pdus.length];
	                for(int i=0;i<messages.length;i++)
	                {
	                    byte[] pdu=(byte[])pdus[i];
	                    messages[i]=SmsMessage.createFromPdu(pdu);
	                }    
	                //���������ݺ�����������
	                for(SmsMessage msg:messages)
	                {
	                    //��ȡ��������
	                    String content=msg.getMessageBody();
	                    String sender=msg.getOriginatingAddress();
	                    Date date = new Date(msg.getTimestampMillis());
	                    //TODO:���������ж�,Ȼ���һ�㴦��
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
	        				 intn.putExtra("result", phone+"--"+bluetooth+"���ͳɹ�");  
	        				 intn.setClass(context, MainActivity.class);  
	        				 intn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	        			     context.getApplicationContext().startActivity(intn);  	
	        			}
	                }
	                
	            
	        }//if �жϹ㲥��Ϣ����
	}
	}
}

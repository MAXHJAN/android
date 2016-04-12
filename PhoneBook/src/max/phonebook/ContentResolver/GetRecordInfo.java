package max.phonebook.ContentResolver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import max.phonebook.Ben.Record;

public class GetRecordInfo {

	public GetRecordInfo() {
		// TODO Auto-generated constructor stub
	}

	public static List<Record> getRecord(Context context) {

		List<Record> list=new ArrayList<Record>();
		Record mRecord=null;
		Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,                            
		        null, null, null, null);                                                                                                 
		if(cursor.moveToFirst()){                                                                                
		    do{    
		    	mRecord=new Record();
		        //����                                                                                             
		        String phone = cursor.getString(cursor.getColumnIndex(Calls.NUMBER));                           
		        //��������                                                                                           
		        String type;                                                                                     
		        switch (Integer.parseInt(cursor.getString(cursor.getColumnIndex(Calls.TYPE)))) {                 
		        case Calls.INCOMING_TYPE:                                                                        
		            type = "����";                                                                                 
		            break;                                                                                       
		        case Calls.OUTGOING_TYPE:                                                                        
		            type = "����";                                                                                 
		            break;                                                                                       
		        case Calls.MISSED_TYPE:                                                                          
		            type = "δ��";                                                                                 
		            break;                                                                                       
		        default:                                                                                         
		            type = "�Ҷ�";//Ӧ���ǹҶ�.�������ֻ������жϳ���                                                              
		            break;                                                                                       
		        }                                                                                                
		        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                              
		        Date date = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(Calls.DATE))));
		        //����ʱ��                                                                                           
		        String time = sfd.format(date);                                                                  
		        //��ϵ��                                                                                            
		        String name = cursor.getString(cursor.getColumnIndexOrThrow(Calls.CACHED_NAME));                 
		        //ͨ��ʱ��,��λ:s                                                                                      
		        String duration = cursor.getString(cursor.getColumnIndexOrThrow(Calls.DURATION)); 
		        mRecord.setName(name);
		        mRecord.setPhone(phone);
		        mRecord.setType(type);
		        mRecord.setTime(time);
		        mRecord.setDuration(duration);
		        list.add(mRecord);
		        mRecord=null;	         
		    }while(cursor.moveToNext());                                                                         
		                                                                                                          
		}
		return list;
	}
}

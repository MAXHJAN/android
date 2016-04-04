package com.example.job;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class Positiondetails extends Fragment implements OnClickListener{
	private ImageView image2,image3;
	private TextView textview;
	private Button image1;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.positiondetails, null);
		if (android.os.Build.VERSION.SDK_INT > 9) {
 		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
 		    StrictMode.setThreadPolicy(policy);}/////
		 textview=(TextView) view.findViewById(R.id.textView4);
		 textview.setText("3�����������Ŀ���Ʒ������뿪�����飬�����ն˰�ȫ�������Ʒ�������������� \n\n"
		 		+ "1����ͨJava��̣��ܹ�����Ӧ��C��C.NET��Hibernate��\n\n"
		 		+ "2����Ϥ���̲߳����������첽���ü�����������ؾ���\n\n"
		 		+ "3����ϤHTML��XML��JavaScript��AJAX��JQuery��ExtJsǰ�˿���������\n\n"
		 		+ "4���ܹ�����Ӧ��MySQL��ӵ����������ݿ�������� \n\n"
		 		+ "5��������OOA/OOD/OOP˼��,�������ն��ֳ��õ����ģʽ \n\n"
		 		+ "6����ǿ������ѧϰ�����ͷ������������������߱����õ��ĵ�����ϰ�ߺʹ�����д�淶 \n\n"
		 		+ "7��ǿ�ҵ����������ŶӺ������� ");
		 
		 image1= ( Button) view.findViewById(R.id.positiondetails_image1);
	     image1.setOnClickListener(this);
	     image2= (ImageView) view.findViewById(R.id.positiondetails_image2);
		 image2.setOnClickListener(this);
		 image3= (ImageView) view.findViewById(R.id.positiondetails_image3);
		 image3.setOnClickListener(this);
			
		return view;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.positiondetails_image1)
		{
			Toast.makeText(getActivity(),"������",Toast.LENGTH_SHORT).show();
		}
		if(v.getId()==R.id.positiondetails_image2)
		{
			 System.out.println("------------����dialog--------");
			  		 
			       FragmentDialog dialog = new FragmentDialog();  
			         dialog.show(getFragmentManager(),"dialog");	
			        
			                                                
		}
		if(v.getId()==R.id.positiondetails_image3)
		{
		      send("123","�������ʦ21");	
		      image3.setImageDrawable(getResources().getDrawable(R.drawable.collect1));
		}
		
	}
    public void send(String user,String job)
    {
    	String result="",re="";
   		HttpClient client = new DefaultHttpClient();
   	    StringBuilder builder = new StringBuilder();
   	    HttpPost mypost = new HttpPost("http://1.mxmap.sinaapp.com/jobs/colloect.php");
   	    List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("params", "POST"));
		params.add(new BasicNameValuePair("user",user));
		params.add(new BasicNameValuePair("job",job));
   	    try {
   	    	
			mypost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
   	    	System.out.println("��ʼ1111");
   	        HttpResponse response = client.execute(mypost);
   	   
   	        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
   	        {   				
   	         System.out.println("�ɹ�-------------------2");
   	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));	      
   	        for (result = reader.readLine();result != null; result = reader.readLine()) {
   	            builder.append(result+"\n");
   	            System.out.println(builder.toString());    	            
   	        }
   	        JSONObject jsonObject = new JSONObject(builder.toString()); 
	        re = jsonObject.getString("code"); 
	        if(re.equals("200"))
	        	Toast.makeText(this.getActivity(), "�ղسɹ�", Toast.LENGTH_SHORT).show();
	        else
	        	Toast.makeText(this.getActivity(), "��ְλ���ղ�", Toast.LENGTH_SHORT).show();
   	        }

   	    } catch (Exception e) {
   	        Log.v("url response", "false");
   	        e.printStackTrace();
   	    }

   	}

}

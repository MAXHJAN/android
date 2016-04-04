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
		 textview.setText("3年以上软件项目或产品的设计与开发经验，具有终端安全防护类产品开发经验者优先 \n\n"
		 		+ "1、精通Java编程，能够熟练应用C、C.NET、Hibernate等\n\n"
		 		+ "2、熟悉多线程并发技术和异步调用技术，并有相关经验\n\n"
		 		+ "3、熟悉HTML、XML、JavaScript、AJAX、JQuery、ExtJs前端开发技术；\n\n"
		 		+ "4、能够熟练应用MySQL，拥有优秀的数据库设计能力 \n\n"
		 		+ "5、深刻理解OOA/OOD/OOP思想,熟练掌握多种常用的设计模式 \n\n"
		 		+ "6、较强的自我学习能力和分析解决问题的能力，具备良好的文档编制习惯和代码书写规范 \n\n"
		 		+ "7、强烈的责任心与团队合作能力 ");
		 
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
			Toast.makeText(getActivity(),"已申请",Toast.LENGTH_SHORT).show();
		}
		if(v.getId()==R.id.positiondetails_image2)
		{
			 System.out.println("------------创建dialog--------");
			  		 
			       FragmentDialog dialog = new FragmentDialog();  
			         dialog.show(getFragmentManager(),"dialog");	
			        
			                                                
		}
		if(v.getId()==R.id.positiondetails_image3)
		{
		      send("123","软件工程师21");	
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
   	    	System.out.println("开始1111");
   	        HttpResponse response = client.execute(mypost);
   	   
   	        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
   	        {   				
   	         System.out.println("成功-------------------2");
   	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));	      
   	        for (result = reader.readLine();result != null; result = reader.readLine()) {
   	            builder.append(result+"\n");
   	            System.out.println(builder.toString());    	            
   	        }
   	        JSONObject jsonObject = new JSONObject(builder.toString()); 
	        re = jsonObject.getString("code"); 
	        if(re.equals("200"))
	        	Toast.makeText(this.getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
	        else
	        	Toast.makeText(this.getActivity(), "该职位已收藏", Toast.LENGTH_SHORT).show();
   	        }

   	    } catch (Exception e) {
   	        Log.v("url response", "false");
   	        e.printStackTrace();
   	    }

   	}

}

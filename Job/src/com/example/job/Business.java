package com.example.job;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Business extends Activity{
	/**
	 * ��ҵ���
	 */
	private ListView list1,list2,list3;
	private String[] a={"��������","�����Ӳ��","���������(ϵͳ�����ݷ���ά��)","ͨ��|������Ӫ����ֵ����","������Ϸ","���Ӽ���|�뵼��|���ɵ�·","�����Ǳ�|��ҵ�Զ���"},
			         b={"���|���","����|Ͷ��|֤ȯ","����","����"},
			         c={"ó��|������","����|����","��������Ʒ","��װ|��֯|Ƥ��","�Ҿ�|�ҵ�|���","�칫��Ʒ���豸","��е|�豸|�ع�","�����������"};
    String flag;
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.business);
		  ActionBar bar=getActionBar();
  	     bar.hide();
  	  Intent  in=getIntent();
 	  Bundle b=in.getExtras();
 	  flag=b.getString("flag",null);
  	     inview();
	  }
	private void inview() {
		// TODO Auto-generated method stub
		list1=(ListView) findViewById(R.id.bs_list1);
		list2=(ListView) findViewById(R.id.bs_list2);
		list3=(ListView) findViewById(R.id.bs_list3);
		list1.setTextFilterEnabled(true);
		  list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		  ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,a);
		 list1.setAdapter(adapter);
		 list2.setTextFilterEnabled(true);
		  list2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		  ArrayAdapter<String>adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,b);
		 list2.setAdapter(adapter2);
		 list3.setTextFilterEnabled(true);
		  list3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		  ArrayAdapter<String>adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,c);
		 list3.setAdapter(adapter3);
		 list1.setOnItemClickListener(new OnItemClickListener() 
	   	  {	  
			
	   		  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  {
	   			  Intent intent=new Intent();
	   	          Bundle bb=new Bundle();
	   	          bb.putString("job",a[position]);
	   	       if(flag.equals("jb1"))
	   	         {
	   	        	System.out.println("===============000000000");
	   	        	intent.setClass(Business.this,Jobintension.class);
	   	            intent.putExtras(bb);
	   	            setResult(0,intent);
	   	         }
	   	      else
	   	    	 if(flag.equals("jb2"))
	   	        {
	   	    	   intent.setClass(Business.this,SearchcarerrActivity.class);
	               intent.putExtras(bb);
	               setResult(11,intent); 
	   	        }	   	       
	   	    	else
	   	    		if(flag.equals("jb3"))
	   	    		{
	   	    			intent.setClass(Business.this,Practice.class);
			               intent.putExtras(bb);
			               setResult(102,intent);
	   	    			   
	   	    		}
	   	    		else
	   	    	    {
	   	    			intent.setClass(Business.this,Searchcarerrtaik.class);
			               intent.putExtras(bb);
			               setResult(22,intent);  
	   	    		   
	   	    	     }
	   	    finish();
	   		  }
	   	  });
		 list2.setOnItemClickListener(new OnItemClickListener() 
	   	  {	  
	   		  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
	   			Intent intent=new Intent();
	   	          Bundle bb=new Bundle();
	   	          bb.putString("job",b[position]);
	   	       if(flag.equals("jb1"))
	   	         {
	   	        	System.out.println("===============000000000");
	   	        	intent.setClass(Business.this,Jobintension.class);
	   	            intent.putExtras(bb);
	   	            setResult(0,intent);
	   	         }
	   	       else
	   	    	if(flag.equals("jb2"))
	   	       {
	   	    	intent.setClass(Business.this,SearchcarerrActivity.class);
   	            intent.putExtras(bb);
   	            setResult(11,intent); 
	   	       }
	   	    	else
	   	    		if(flag.equals("jb3"))
	   	    		{
	   	    		 intent.setClass(Business.this,Practice.class);
		               intent.putExtras(bb);
		               setResult(102,intent); 
	   	    		}
	   	    		else
	   	    	 {
	   	    		   intent.setClass(Business.this,Searchcarerrtaik.class);
		               intent.putExtras(bb);
		               setResult(22,intent); 
	   	    	 }
	   	    finish();
	   		  }
	   	  });
		 list3.setOnItemClickListener(new OnItemClickListener() 
	   	  {	  
	   		  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) 
	   		  { 
	   			Intent intent=new Intent();
	   	          Bundle bb=new Bundle();
	   	          bb.putString("job",c[position]);
	   	       if(flag.equals("jb1"))
	   	         {
	   	        	System.out.println("===============000000000");
	   	        	intent.setClass(Business.this,Jobintension.class);
	   	            intent.putExtras(bb);
	   	            setResult(0,intent);
	   	         }
	   	      else
	   	    	if(flag.equals("jb2"))
	   	       {
	   	    	intent.setClass(Business.this,SearchcarerrActivity.class);
	            intent.putExtras(bb);
	            setResult(11,intent); 
	   	       }
	   	    	else
	   	    		if(flag.equals("jb3"))
	   	    		{
	   	    		 intent.setClass(Business.this,Practice.class);
		               intent.putExtras(bb);
		               setResult(102,intent); 
	   	    		}
	   	    		else
	   	    	 {
	   	    		   intent.setClass(Business.this,Searchcarerrtaik.class);
		               intent.putExtras(bb);
		               setResult(22,intent); 
	   	    	 }
	   	       finish();
	   		  }
	   	  });
	}

}

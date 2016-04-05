package max.phonebook.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import max.phonebook.AddContactActivity;
import max.phonebook.MyAppLication;
import max.phonebook.R;
import max.phonebook.Adapter.mListviewAdapter;
import max.phonebook.Ben.Person;

public class CallFragment extends Fragment implements View.OnClickListener{

	private View mview;
	private ListView mListView;
	private TextView PhoneNumber;
	private Button[]  CallNumButton=new Button[12];
	private ImageView CallImage,Delete,AddPerson;
	private ArrayList<Person> PhoneInfo=new ArrayList<Person>();//����һ�������õ���ϵ�˵��б����������
	private List<Person> Phones=new ArrayList<Person>();//����һ�������õ���ϵ�˵��б����������
	List<Person> filterDate= new ArrayList<Person>();	
	
	private  mListviewAdapter Adapter;
	private MyAppLication MyApp;
	public FragmentActivity activity;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.callfragment, null);
	    }
	    // �����rootView��Ҫ�ж��Ƿ��Ѿ����ӹ�parent�������parent��Ҫ��parentɾ����Ҫ��Ȼ�ᷢ�����rootview�Ѿ���parent�Ĵ���
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    activity=getActivity();    
	    IntsView(mview);
		return mview;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		GetPhoneInfos();
	}
	
	public void GetPhoneInfos()
	{		
		new Thread(new Runnable() {
			
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PhoneInfo.clear();
				MyApp=(MyAppLication) activity.getApplication();				
				PhoneInfo=MyApp.getPhoneInfos();
				Log.e("123", "-------123-------"+PhoneInfo.size());	
			}
		}).start();
	}
      private void IntsView(View view)
      {    	  
  	 
    		  CallNumButton[0]=(Button) view.findViewById(R.id.num0);
    		  CallNumButton[1]=(Button) view.findViewById(R.id.num1);
    		  CallNumButton[2]=(Button) view.findViewById(R.id.num2);
    		  CallNumButton[3]=(Button) view.findViewById(R.id.num3);
    		  CallNumButton[4]=(Button) view.findViewById(R.id.num4);
    		  CallNumButton[5]=(Button) view.findViewById(R.id.num5);
    		  CallNumButton[6]=(Button) view.findViewById(R.id.num6);
    		  CallNumButton[7]=(Button) view.findViewById(R.id.num7);
    		  CallNumButton[8]=(Button) view.findViewById(R.id.num8);
    		  CallNumButton[9]=(Button) view.findViewById(R.id.num9);
    		  CallNumButton[10]=(Button) view.findViewById(R.id.num_1);//*
    		  CallNumButton[11]=(Button) view.findViewById(R.id.num_2);//#
    		  for(int i=0;i<12;i++)
    			  CallNumButton[i].setOnClickListener(this);
    		  
    		  PhoneNumber=(TextView) view.findViewById(R.id.phonenumner);
    		  
    		  Delete=(ImageView) view.findViewById(R.id.delete);
    		  Delete.setOnClickListener(this);
    		  CallImage=(ImageView) view.findViewById(R.id.callimage);
    		  CallImage.setOnClickListener(this);
    		  AddPerson=(ImageView) view.findViewById(R.id.addperson);
    		  AddPerson.setOnClickListener(this);
    		  
    		  mListView=(ListView) view.findViewById(R.id.list);
    		  Adapter=new mListviewAdapter(activity, Phones);
    		  mListView.setAdapter(Adapter);
    		  
    		  
    		  PhoneNumber.addTextChangedListener(new TextWatcher() {
  				
  				@Override
  				public void onTextChanged(CharSequence s, int start, int before, int count) {
  					// TODO Auto-generated method stub
  					Adapter.updateListView(CallfilterData(s.toString()));
  					Log.e("123", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
  				}
  				
  				@Override
  				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  					// TODO Auto-generated method stub
  					
  				}
  				
  				@Override
  				public void afterTextChanged(Editable s) {
  					// TODO Auto-generated method stub
  					
  				}
  			});
    		  
    		  
    		  mListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {											
						//����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
						Intent intent = new Intent(
								Intent.ACTION_CALL,
								Uri.parse("tel:"
										+ filterDate.get(position).getPhonenumber()));
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						activity.startActivity(intent);						 
					}
				});
    		  
      }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.num0:
			PhoneNumber.append("0");
			break;
		case R.id.num1:
			PhoneNumber.append("1");
			break;
		case R.id.num2:
			PhoneNumber.append("2");
			break;
		case R.id.num3:
			PhoneNumber.append("3");
			break;
		case R.id.num4:
			PhoneNumber.append("4");
			break;
		case R.id.num5:
			PhoneNumber.append("5");
			break;
		case R.id.num6:
			PhoneNumber.append("6");
			break;
		case R.id.num7:
			PhoneNumber.append("7");
			break;
		case R.id.num8:
			PhoneNumber.append("8");
			break;
		case R.id.num9:
			PhoneNumber.append("9");
			break;
		case R.id.num_1://*
			PhoneNumber.append("*");
			break;
		case R.id.num_2://#
			PhoneNumber.append("#");
			break;
		case R.id.addperson:
			Bundle bun=new Bundle();
			bun.putString("PersonName", PhoneNumber.getText().toString().trim());
			Intent inten=new Intent(activity,AddContactActivity.class);
			inten.putExtras(bun);
			startActivity(inten);
			break;
		case R.id.delete:			
			           String str=PhoneNumber.getText().toString();
			           if(!str.equals(""))
			           {   
			           int length=str.length();
			           str=str.substring(0, length-1);
			           PhoneNumber.setText(str);
			           }
			break;
		case R.id.callimage:
			String number=PhoneNumber.getText().toString().trim();
			if(number.equals(""))
			{
				Toast.makeText(activity, "���������", Toast.LENGTH_LONG).show();
			}
			else
			{
			Intent intent = new Intent(
					Intent.ACTION_CALL,
					Uri.parse("tel:"+number));
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			activity.startActivity(intent);
			}
			break;
		}
	}
	
	
	
	public List<Person> CallfilterData(String filterStr)
	{
		List<Person> filterDateList = new ArrayList<Person>();	
		if(TextUtils.isEmpty(filterStr)){
			{
			 filterDateList = PhoneInfo;			
			}
		}else{
			filterDateList.clear();
			Log.e("123", "--------------------------===");
			for(Person person : PhoneInfo){
				String phone = person.getPhonenumber();
				
				if(phone.indexOf(filterStr.toString()) != -1 || phone.startsWith(filterStr.toString())){
					filterDateList.add(person);					
				}
			}
		}
		filterDate=filterDateList;
		return filterDateList;
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}

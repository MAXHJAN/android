package max.phonebook;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import max.phonebook.Adapter.PopListViewAdapter;
import max.phonebook.Ben.Person;
import max.phonebook.ContentResolver.GetPhoneInfo;

public class PersonInfoActivity extends Activity implements OnClickListener{

	@SuppressWarnings("unused")
	private TextView PersonName,PersonPhone,PersonEmail,PersonAddress;
	private ImageView CallUp, SendSMS,Menu,MyBack;
	private Person PersonInfo;
	
	 private PopupWindow popupWindow;  	  
	 private ListView lv_pop;  	  
	 private View view; 
	 private List<String> menulist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personinfo);
		getActionBar().hide();
		PersonInfo=(Person) getIntent().getExtras().getSerializable("person");
		IntsView();
		
	}

	@SuppressLint("CutPasteId")
	private void IntsView()
	{
		PersonName=(TextView) findViewById(R.id.personname);
		PersonPhone=(TextView) findViewById(R.id.personphone);
		PersonEmail=(TextView) findViewById(R.id.personemail);
		PersonAddress=(TextView) findViewById(R.id.personaddress);
		CallUp=(ImageView) findViewById(R.id.call_up);
		SendSMS=(ImageView) findViewById(R.id.sendsms);
		Menu=(ImageView) findViewById(R.id.menu);
		MyBack=(ImageView) findViewById(R.id.back);
		CallUp.setOnClickListener(this);
		SendSMS.setOnClickListener(this);	
		Menu.setOnClickListener(this);	
		MyBack.setOnClickListener(this);	
		PersonName.setText(PersonInfo.getName());
		PersonPhone.setText(PersonInfo.getPhonenumber());
		PersonEmail.setText(PersonInfo.getEmail());
		PersonAddress.setText(PersonInfo.getAddress());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.call_up:
			Intent intent = new Intent(
					Intent.ACTION_CALL,
					Uri.parse("tel:"
							+ PersonPhone.getText().toString()));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);		
			break;
		case R.id.sendsms:
			Intent in = new Intent(Intent.ACTION_SENDTO,
					Uri.parse("smsto:"
							+ PersonPhone.getText().toString()));
					startActivity(in);	
			break;

		case R.id.menu:
			PopWindow();
			break;
		case R.id.back:
			startActivity(new Intent(PersonInfoActivity.this,MainActivity.class));
			finish();
			break;

		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void PopWindow()
	{
		 if (popupWindow == null) {  
	            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	  
	            view = layoutInflater.inflate(R.layout.popwindow, null);  
	  
	            lv_pop = (ListView) view.findViewById(R.id.poplistview);  
	            // 加载数据  
	            menulist = new ArrayList<String>();  
	            menulist.add("编辑联系人");  
	            menulist.add("加入黑名单");  
	            menulist.add("删除联系人");  
	           
	  
	            PopListViewAdapter Adapter = new PopListViewAdapter(menulist,PersonInfoActivity.this);  
	            lv_pop.setAdapter(Adapter);  
	            // 创建一个PopuWidow对象  
	            popupWindow = new PopupWindow(view, 400, 400);  
	        }  
			
		 // 使其聚集  
	        popupWindow.setFocusable(true);  
	        // 设置允许在外点击消失  
	        popupWindow.setOutsideTouchable(true); 
	        popupWindow.setBackgroundDrawable(new BitmapDrawable());  
	        popupWindow.showAsDropDown(Menu,-60,30);
	        
	        
	        
	        lv_pop.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					switch (position) {
					case 0:
						Bundle bun=new Bundle();
						bun.putSerializable("person", PersonInfo);
						Intent intent=new Intent(PersonInfoActivity.this,EditContactActivity.class);
						intent.putExtras(bun);
						startActivity(intent);
						if(popupWindow!=null)
							popupWindow.dismiss();
						break;
					case 1:
						if(popupWindow!=null)
							popupWindow.dismiss();
						break;
					case 2:
						new GetPhoneInfo(PersonInfoActivity.this).deleteContact(Integer.parseInt(PersonInfo.getID()));
						if(popupWindow!=null)
							popupWindow.dismiss();
						break;
		
					}
				}
			});
	}
}

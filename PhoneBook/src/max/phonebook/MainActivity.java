package max.phonebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import max.phonebook.Adapter.SortAdapter;
import max.phonebook.Ben.Person;
import max.phonebook.ContentResolver.GetPhoneInfo;
import max.phonebook.View.ClearEditText;
import max.phonebook.View.SideBar;
import max.phonebook.View.SideBar.OnTouchingLetterChangedListener;

public class MainActivity extends Activity {
	private ListView sortListView;
	private SortAdapter mAdapter;
	private SideBar sideBar;
	private TextView dialog;
	private ClearEditText mClearEditText;
	private ArrayList<Person> PhoneInfos=new ArrayList<Person>();//创建一个保存获得的联系人的列表来存放数据
	
	private  GetPhoneInfo gpi=new GetPhoneInfo(this);//创建一个获得联系人
	
	private static final int GET_PHONE_OK=0x01;//获取联系人成功
	
	public Handler handler=new Handler()
		{
		      @Override
		    public void handleMessage(Message msg) {
		    	// TODO Auto-generated method stub
		    	super.handleMessage(msg);
		    	switch (msg.what) {
				case GET_PHONE_OK:
					SetView();
					break;

				}
		    }
		};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactsfragment);
		GetPhoneInfos();
		intView();
		OnClickLisenler();
		
	}
	
	
	public void GetPhoneInfos()
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				PhoneInfos=(ArrayList<Person>) gpi.getphoneinfo("");
				handler.sendEmptyMessage(GET_PHONE_OK);//发信息
			}
		}).start();
	}
	
	
	private void intView()
	{
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sortListView=(ListView) findViewById(R.id.listView);
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		sideBar.setTextView(dialog);
	}
	/**
	 * 相关组件监听
	 */
	public void OnClickLisenler()
	{
		//设置右侧触摸监听
			sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
					
					@Override
					public void onTouchingLetterChanged(String s) {
						//该字母首次出现的位置
						int position = mAdapter.getPositionForSection(s.charAt(0));
						if(position != -1){
							sortListView.setSelection(position);
						}
						
					}
				});
				//listview监听
			sortListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						//这里要利用adapter.getItem(position)来获取当前position所对应的对象
						Toast.makeText(getApplication(), ((Person)mAdapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
					}
				});
			
			
			//根据输入框输入值的改变来过滤搜索
			mClearEditText.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
					mAdapter.updateListView(gpi.filterData(PhoneInfos,s.toString()));
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
				}
			});
	}
	public void SetView()
	{
		mAdapter=new SortAdapter(this, PhoneInfos);
		sortListView.setAdapter(mAdapter);
	}
	
	
	
}

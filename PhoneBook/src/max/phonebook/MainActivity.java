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
	private ArrayList<Person> PhoneInfos=new ArrayList<Person>();//����һ�������õ���ϵ�˵��б����������
	
	private  GetPhoneInfo gpi=new GetPhoneInfo(this);//����һ�������ϵ��
	
	private static final int GET_PHONE_OK=0x01;//��ȡ��ϵ�˳ɹ�
	
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
				handler.sendEmptyMessage(GET_PHONE_OK);//����Ϣ
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
	 * ����������
	 */
	public void OnClickLisenler()
	{
		//�����Ҳഥ������
			sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
					
					@Override
					public void onTouchingLetterChanged(String s) {
						//����ĸ�״γ��ֵ�λ��
						int position = mAdapter.getPositionForSection(s.charAt(0));
						if(position != -1){
							sortListView.setSelection(position);
						}
						
					}
				});
				//listview����
			sortListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						//����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
						Toast.makeText(getApplication(), ((Person)mAdapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
					}
				});
			
			
			//�������������ֵ�ĸı�����������
			mClearEditText.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					//������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
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

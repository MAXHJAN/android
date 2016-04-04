package max.phonebook.Fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import max.phonebook.MainActivity;
import max.phonebook.R;
import max.phonebook.Adapter.SortAdapter;
import max.phonebook.Ben.Person;
import max.phonebook.ContentResolver.GetPhoneInfo;
import max.phonebook.View.ClearEditText;
import max.phonebook.View.SideBar;
import max.phonebook.View.SideBar.OnTouchingLetterChangedListener;

public class ContactsFragment extends Fragment {

	private View mview;
	private ListView sortListView;
	private SortAdapter mAdapter;
	private SideBar sideBar;
	private TextView dialog;
	private ClearEditText mClearEditText;
	private ArrayList<Person> PhoneInfos=new ArrayList<Person>();//创建一个保存获得的联系人的列表来存放数据
	
	private  GetPhoneInfo gpi;//创建一个获得联系人
	
	private static final int GET_PHONE_OK=0x01;//获取联系人成功
	public FragmentActivity activity;
	
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.contactsfragment, null);
	    }
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    activity=getActivity();
	    GetPhoneInfos();
	    intView(mview);
	    OnClickLisenler();
		return mview;
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
	private void intView(View view)
	{
		sideBar = (SideBar) view.findViewById(R.id.sidrbar);
		dialog = (TextView)view. findViewById(R.id.dialog);
		sortListView=(ListView) view.findViewById(R.id.listView);
		mClearEditText = (ClearEditText)view. findViewById(R.id.filter_edit);
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
						Toast.makeText(activity, ((Person)mAdapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
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
		mAdapter=new SortAdapter(activity, PhoneInfos);
		sortListView.setAdapter(mAdapter);
	}

}

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
	private ArrayList<Person> PhoneInfos=new ArrayList<Person>();//����һ�������õ���ϵ�˵��б����������
	
	private  GetPhoneInfo gpi;//����һ�������ϵ��
	
	private static final int GET_PHONE_OK=0x01;//��ȡ��ϵ�˳ɹ�
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
	    // �����rootView��Ҫ�ж��Ƿ��Ѿ����ӹ�parent�������parent��Ҫ��parentɾ����Ҫ��Ȼ�ᷢ�����rootview�Ѿ���parent�Ĵ���
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
				handler.sendEmptyMessage(GET_PHONE_OK);//����Ϣ
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
						Toast.makeText(activity, ((Person)mAdapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
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
		mAdapter=new SortAdapter(activity, PhoneInfos);
		sortListView.setAdapter(mAdapter);
	}

}

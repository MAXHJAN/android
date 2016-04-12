package max.phonebook.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ListView;
import android.widget.TextView;
import max.phonebook.MyAppLication;
import max.phonebook.PersonInfoActivity;
import max.phonebook.R;
import max.phonebook.Adapter.SortAdapter;
import max.phonebook.Ben.CharacterParser;
import max.phonebook.Ben.Person;
import max.phonebook.Ben.PinyinComparator;
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
	
	private PinyinComparator pinyinComparator;
	//��ȡ���ֵ�����ĸ
	private CharacterParser characterParser;
	private ArrayList<Person> PhoneInfos=new ArrayList<Person>();//����һ�������õ���ϵ�˵��б����������
	
	private  GetPhoneInfo gpi;//����һ�������ϵ��
	
	private static final int GET_PHONE_OK=0x01;//��ȡ��ϵ�˳ɹ�
	public FragmentActivity activity;	
	
	public Handler mHandler=new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what==0x01){
				SetView();
			}
		};
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
	    gpi=new GetPhoneInfo(activity);
	    pinyinComparator = new PinyinComparator();
	    characterParser = CharacterParser.getInstance();
	    GetPhoneInfos();
	    intView(mview);	    
	    OnClickLisenler();
		return mview;
	}

	
	@SuppressWarnings("static-access")
	public void GetPhoneInfos()
	{		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				PhoneInfos=(ArrayList<Person>) new GetPhoneInfo(activity).getphoneinfo("");			
				Log.e("123", "--------------"+PhoneInfos.size());					 
				mHandler.sendEmptyMessage(0x01);
			}
		}).start();
	}
	
	/**
	 * ��ʼ���ؼ�
	 * @param view
	 */
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
						Bundle mBundle = new Bundle(); 
						mBundle.putSerializable("person", PhoneInfos.get(position));
						Intent in=new Intent(activity, PersonInfoActivity.class);
						in.putExtras(mBundle);  
						startActivity(in);
					}
				});
			
			
			//�������������ֵ�ĸı�����������
			mClearEditText.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					//������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
					mAdapter.updateListView(filterData(s.toString()));
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
	/**
	 * ��������
	 */
	public void SetView()
	{
		Collections.sort(PhoneInfos, pinyinComparator);
		mAdapter=new SortAdapter(activity, PhoneInfos);
		sortListView.setAdapter(mAdapter);
	}
	
	
	
	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * @param filterStr
	 */
	public List<Person> filterData(String filterStr){
		List<Person> filterDateList = new ArrayList<Person>();		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = PhoneInfos;
		}else{
			filterDateList.clear();
			for(Person person : PhoneInfos){
				String name = person.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString()))
				{
					filterDateList.add(person);
				}
			}
		}
		// ����a-z��������
		Collections.sort(filterDateList, pinyinComparator);	
		return filterDateList;
	}

}

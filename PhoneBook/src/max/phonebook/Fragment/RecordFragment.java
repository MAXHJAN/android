package max.phonebook.Fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import max.phonebook.MyAppLication;
import max.phonebook.R;
import max.phonebook.Ben.Person;
import max.phonebook.Ben.PersonInputStream;

public class RecordFragment extends Fragment {

	private View mview;
	private ArrayList<Person> PhoneInfo=new ArrayList<Person>();//创建一个保存获得的联系人的列表来存放数据
	private MyAppLication MyApp;
	public FragmentActivity activity;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.recordfragment, null);
	    }
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    activity=getActivity();  
	    GetPhoneInfos();
		return mview;
	}

	public void GetPhoneInfos()
	{		
				PhoneInfo.clear();
				MyApp=(MyAppLication) activity.getApplication();				
				PhoneInfo=MyApp.getPhoneInfos();
				Log.e("123", "-------321-------"+PhoneInfo.size());		
	}
}

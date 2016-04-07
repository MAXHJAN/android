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
	private ArrayList<Person> PhoneInfo=new ArrayList<Person>();//����һ�������õ���ϵ�˵��б����������
	private MyAppLication MyApp;
	public FragmentActivity activity;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.recordfragment, null);
	    }
	    // �����rootView��Ҫ�ж��Ƿ��Ѿ����ӹ�parent�������parent��Ҫ��parentɾ����Ҫ��Ȼ�ᷢ�����rootview�Ѿ���parent�Ĵ���
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

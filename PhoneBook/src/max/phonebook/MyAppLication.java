package max.phonebook;

import java.util.ArrayList;

import android.app.Application;
import android.util.Log;
import max.phonebook.Ben.Person;

public class MyAppLication extends Application {

	private static ArrayList<Person> PhoneInfos=new ArrayList<Person>();//����һ�������õ���ϵ�˵��б����������
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	
	
	public static ArrayList<Person> getPhoneInfos() {
		Log.e("123", "----PhoneInfos��С-----"+PhoneInfos.size());
		return MyAppLication.PhoneInfos;
	}
	public void setPhoneInfos(ArrayList<Person> phoneInfo) {
		MyAppLication.PhoneInfos = phoneInfo;
		Log.e("123", "----��С-----"+PhoneInfos.size());
	}
}

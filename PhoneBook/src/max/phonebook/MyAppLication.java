package max.phonebook;

import java.util.ArrayList;

import android.app.Application;
import android.util.Log;
import max.phonebook.Ben.Person;

public class MyAppLication extends Application {

	private static ArrayList<Person> PhoneInfos=new ArrayList<Person>();//创建一个保存获得的联系人的列表来存放数据
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	
	
	public static ArrayList<Person> getPhoneInfos() {
		Log.e("123", "----PhoneInfos大小-----"+PhoneInfos.size());
		return MyAppLication.PhoneInfos;
	}
	public void setPhoneInfos(ArrayList<Person> phoneInfo) {
		MyAppLication.PhoneInfos = phoneInfo;
		Log.e("123", "----大小-----"+PhoneInfos.size());
	}
}

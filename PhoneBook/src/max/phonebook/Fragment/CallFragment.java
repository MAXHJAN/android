package max.phonebook.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import max.phonebook.AddContactActivity;
import max.phonebook.MyAppLication;
import max.phonebook.R;
import max.phonebook.Adapter.mListviewAdapter;
import max.phonebook.Ben.Person;
import max.phonebook.ContentResolver.PhoneInfo;

public class CallFragment extends Fragment implements View.OnClickListener {

	private View mview, popView;
	private ListView mListView;
	private EditText PhoneNumber;
	private Button[] CallNumButton = new Button[12];
	private ImageView CallImage, Delete, AddPerson;
	private ArrayList<Person> PhoneInfo = new ArrayList<Person>();// 创建一个保存获得的联系人的列表来存放数据
	private List<Person> Phones = new ArrayList<Person>();// 创建一个保存获得的联系人的列表来存放数据
	List<Person> filterDate = new ArrayList<Person>();

	private mListviewAdapter Adapter;
	private MyAppLication MyApp;
	public FragmentActivity activity;

	private PopupWindow popupWindow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null) {
			mview = inflater.inflate(R.layout.callfragment, null);
		}
		// 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
		ViewGroup parent = (ViewGroup) mview.getParent();
		if (parent != null) {
			parent.removeView(mview);
		}
		activity = getActivity();
		initView(mview);
		initPopWindow();
		return mview;
	}

	public void PopWindow() {
		initPopWindow();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		activity.getSharedPreferences("Config", activity.MODE_PRIVATE).edit().putBoolean("call", true).commit();
		if (PhoneInfo == null || PhoneInfo.size() == 0)
			GetPhoneInfos();
	}

	public void GetPhoneInfos() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				PhoneInfo.clear();
				PhoneInfo.addAll((ArrayList<Person>) new PhoneInfo(activity).getphoneinfo(""));
				Log.e("TAG", "-------123-1-call------" + PhoneInfo.size());
			}
		}).start();
	}

	private void initView(View view) {
		mListView = (ListView) view.findViewById(R.id.list);
		Adapter = new mListviewAdapter(activity, Phones);
		mListView.setAdapter(Adapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				Intent intent = new Intent(Intent.ACTION_CALL,
						Uri.parse("tel:" + filterDate.get(position).getPhonenumber()));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				activity.startActivity(intent);
			}
		});

	}

	public void initPopWindow() {
		if (popupWindow == null) {
			LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			popView = layoutInflater.inflate(R.layout.popcallkeyboard, null);
			initPopView(popView);
			popupWindow = new PopupWindow(popView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		}
		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.showAtLocation(mListView, Gravity.BOTTOM, 0, 0);
	}

	private void initPopView(View view) {

		CallNumButton[0] = (Button) view.findViewById(R.id.num0);
		CallNumButton[1] = (Button) view.findViewById(R.id.num1);
		CallNumButton[2] = (Button) view.findViewById(R.id.num2);
		CallNumButton[3] = (Button) view.findViewById(R.id.num3);
		CallNumButton[4] = (Button) view.findViewById(R.id.num4);
		CallNumButton[5] = (Button) view.findViewById(R.id.num5);
		CallNumButton[6] = (Button) view.findViewById(R.id.num6);
		CallNumButton[7] = (Button) view.findViewById(R.id.num7);
		CallNumButton[8] = (Button) view.findViewById(R.id.num8);
		CallNumButton[9] = (Button) view.findViewById(R.id.num9);
		CallNumButton[10] = (Button) view.findViewById(R.id.num_1);// *
		CallNumButton[11] = (Button) view.findViewById(R.id.num_2);// #
		for (int i = 0; i < 12; i++)
			CallNumButton[i].setOnClickListener(this);

		PhoneNumber = (EditText) view.findViewById(R.id.phonenumner);
		PhoneNumber.setInputType(InputType.TYPE_NULL);
		Delete = (ImageView) view.findViewById(R.id.delete);
		Delete.setOnClickListener(this);
		CallImage = (ImageView) view.findViewById(R.id.callimage);
		CallImage.setOnClickListener(this);
		AddPerson = (ImageView) view.findViewById(R.id.addperson);
		AddPerson.setOnClickListener(this);

		PhoneNumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (s.toString() == null)
					return;
				Log.e("123", "qqqqq-----" + s.toString());
				Adapter.updateListView(CallfilterData(s.toString()));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.num0:
			PhoneNumber.append("0");
			break;
		case R.id.num1:
			PhoneNumber.append("1");
			break;
		case R.id.num2:
			PhoneNumber.append("2");
			break;
		case R.id.num3:
			PhoneNumber.append("3");
			break;
		case R.id.num4:
			PhoneNumber.append("4");
			break;
		case R.id.num5:
			PhoneNumber.append("5");
			break;
		case R.id.num6:
			PhoneNumber.append("6");
			break;
		case R.id.num7:
			PhoneNumber.append("7");
			break;
		case R.id.num8:
			PhoneNumber.append("8");
			break;
		case R.id.num9:
			PhoneNumber.append("9");
			break;
		case R.id.num_1:// *
			PhoneNumber.append("*");
			break;
		case R.id.num_2:// #
			PhoneNumber.append("#");
			break;
		case R.id.addperson:
			if (!isfilter(PhoneNumber.getText().toString().trim())) {
				Bundle bun = new Bundle();
				bun.putString("PersonName", PhoneNumber.getText().toString().trim());
				Intent inten = new Intent(activity, AddContactActivity.class);
				inten.putExtras(bun);
				startActivity(inten);
			} else
				Toast.makeText(activity, "号码已存在", Toast.LENGTH_LONG).show();
			break;
		case R.id.delete:
			String str = PhoneNumber.getText().toString();
			if (!str.equals("")) {
				int length = str.length();
				str = str.substring(0, length - 1);
				PhoneNumber.setText(str);
			}
			break;
		case R.id.callimage:
			String number = PhoneNumber.getText().toString().trim();
			if (number.equals("")) {
				Toast.makeText(activity, "请输入号码", Toast.LENGTH_LONG).show();
			} else {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				activity.startActivity(intent);
			}
			break;
		}
	}

	private Boolean isfilter(String number) {
		for (int i = 0; i < filterDate.size(); i++) {
			if (filterDate.get(i).getPhonenumber().equals(number))
				return true;
		}
		return false;
	}

	public List<Person> CallfilterData(String filterStr) {
		List<Person> filterDateList = new ArrayList<Person>();
		if (TextUtils.isEmpty(filterStr)) {
			{
				filterDateList = PhoneInfo;
			}
		} else {
			filterDateList.clear();
			for (Person person : PhoneInfo) {
				String phone = person.getPhonenumber();
				Log.e("123", "---//--" + filterStr.toString());
				try {
					if (phone.indexOf(filterStr) != -1 || phone.startsWith(filterStr)) {
						filterDateList.add(person);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		filterDate.clear();
		filterDate.addAll(filterDateList);
		return filterDateList;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("TAG", "onDestroy---------fragment");
		activity.getSharedPreferences("Config", activity.MODE_PRIVATE).edit().putBoolean("call", false).commit();
	}
}

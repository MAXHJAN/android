package max.phonebook;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import max.phonebook.Adapter.PopListViewAdapter;
import max.phonebook.Fragment.CallFragment;
import max.phonebook.Fragment.ContactsFragment;
import max.phonebook.Fragment.RecordFragment;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private TextView Title;
	private View Contact, Call, Record, view;
	private ImageView ContactImage, CallImage, RecordImage, PhoneMenu;
	private int currIndex = 0;// 当前页号0,1,2
	private ArrayList<Fragment> mFragments;

	private PopupWindow popupWindow;
	private ListView lv_pop;
	private List<String> menulist;
	private FragmentTransaction beginTransaction;

	private long firstTime = 0;
	private CallFragment mCallFragment;
	private ContactsFragment mContactsFragment;
	private RecordFragment mRecordFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();
		IntView();
		getSharedPreferences("Config", MODE_PRIVATE).edit().putBoolean("call", false).commit();

	}

	@SuppressWarnings("unused")
	private void IntView() {
		Title = (TextView) findViewById(R.id.phonetitle);
		Contact = findViewById(R.id.contact);
		Call = findViewById(R.id.call);
		Record = findViewById(R.id.record);
		PhoneMenu = (ImageView) findViewById(R.id.phonemenu);
		ContactImage = (ImageView) findViewById(R.id.contactimage);
		CallImage = (ImageView) findViewById(R.id.callimage);
		RecordImage = (ImageView) findViewById(R.id.recordimage);
		ContactImage.setImageResource(R.drawable.contact);
		beginTransaction = getSupportFragmentManager().beginTransaction();
		beginTransaction.replace(R.id.mainview, new ContactsFragment()).commit();
		Contact.setOnClickListener(this);
		Call.setOnClickListener(this);
		Record.setOnClickListener(this);

		PhoneMenu.setOnClickListener(new OnClickListener() {// 菜单按钮的监听

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopWindow();
			}
		});
	}

	public void PopWindow() {
		if (popupWindow == null) {
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view = layoutInflater.inflate(R.layout.popwindow, null);

			lv_pop = (ListView) view.findViewById(R.id.poplistview);
			// 加载数据
			menulist = new ArrayList<String>();
			menulist.add("新建联系人");
			menulist.add("系统设置");
			menulist.add("退出系统");

			PopListViewAdapter Adapter = new PopListViewAdapter(menulist, MainActivity.this);
			lv_pop.setAdapter(Adapter);
			// 创建一个PopuWidow对象
			popupWindow = new PopupWindow(view, 400, 400);
		}

		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.showAsDropDown(PhoneMenu, -60, 30);

		lv_pop.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Bundle bun = new Bundle();
					bun.putString("PersonName", "");
					Intent inten = new Intent(MainActivity.this, AddContactActivity.class);
					inten.putExtras(bun);
					startActivity(inten);
					if (popupWindow != null)
						popupWindow.dismiss();
					break;
				case 1:
					if (popupWindow != null)
						popupWindow.dismiss();
					break;
				case 2:
					if (popupWindow != null)
						popupWindow.dismiss();
					System.exit(0);
					break;

				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (Contact != null)
			Contact = null;
		if (Call != null)
			Call = null;
		if (Record != null)
			Record = null;
		if (mFragments != null)
			mFragments = null;
		if (ContactImage != null)
			ContactImage = null;
		if (CallImage != null)
			CallImage = null;
		if (RecordImage != null)
			RecordImage = null;

		if (popupWindow != null)
			popupWindow = null;
		if (lv_pop != null)
			lv_pop = null;
		if (view != null)
			view = null;
		if (menulist != null)
			menulist = null;
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - firstTime > 3000) {
				Toast.makeText(this, "再按一次退出程序...", Toast.LENGTH_SHORT).show();
				firstTime = System.currentTimeMillis();
			} else {
				Intent intent2 = new Intent();
				intent2.setAction(Intent.ACTION_MAIN);
				intent2.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent2);
			}
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.contact:
			switch (currIndex) {
			case 1:
				CallImage.setImageResource(R.drawable.keybood_1);
				break;
			case 2:
				RecordImage.setImageResource(R.drawable.record_1);
				break;
			}
			ContactImage.setImageResource(R.drawable.contact);
			Title.setText("联系人");
			currIndex = 0;
			if (mContactsFragment == null)
				mContactsFragment = new ContactsFragment();
			beginTransaction = getSupportFragmentManager().beginTransaction();
			beginTransaction.replace(R.id.mainview, mContactsFragment).commit();
			break;

		case R.id.call:
			switch (currIndex) {
			case 0:
				ContactImage.setImageResource(R.drawable.contact_1);
				break;
			case 2:
				RecordImage.setImageResource(R.drawable.record_1);
				break;
			}
			CallImage.setImageResource(R.drawable.keybood);
			Title.setText("拨打电话");
			currIndex = 1;
			if (mCallFragment == null)
				mCallFragment = new CallFragment();
			beginTransaction = getSupportFragmentManager().beginTransaction();
			beginTransaction.replace(R.id.mainview, mCallFragment).commit();
			if(getSharedPreferences("Config", MODE_PRIVATE).getBoolean("call", false)){
				mCallFragment.PopWindow();
			}
			break;
		case R.id.record:
			switch (currIndex) {
			case 0:
				ContactImage.setImageResource(R.drawable.contact_1);
				break;
			case 1:
				CallImage.setImageResource(R.drawable.keybood_1);
				break;
			}
			RecordImage.setImageResource(R.drawable.record);
			Title.setText("通话记录");
			currIndex = 2;
			if (mRecordFragment == null)
				mRecordFragment = new RecordFragment();
			beginTransaction = getSupportFragmentManager().beginTransaction();
			beginTransaction.replace(R.id.mainview, mRecordFragment).commit();
			break;
		}
	}

}

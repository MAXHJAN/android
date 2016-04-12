package max.phonebook.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import max.phonebook.MyAppLication;
import max.phonebook.R;
import max.phonebook.Adapter.RecordListViewAdapter;
import max.phonebook.Ben.Record;
import max.phonebook.ContentResolver.GetRecordInfo;
import max.phonebook.View.CustomSwipeListView;

public class RecordFragment extends Fragment {

	private View mview;
	private List<Record> RecordList = new ArrayList<Record>();// 创建一个保存获得的联系人的列表来存放数据
	private MyAppLication MyApp;
	public FragmentActivity activity;

	private CustomSwipeListView recordListView;
	private RecordListViewAdapter mAdapter;

	public Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x01) {
				SetView();
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null) {
			mview = inflater.inflate(R.layout.recordfragment, null);
		}
		ViewGroup parent = (ViewGroup) mview.getParent();
		if (parent != null) {
			parent.removeView(mview);
		}
		activity = getActivity();
		init(mview);
		gitRecordInfo();
		ListViewLisenler();
		return mview;
	}

	private void init(View view) {
		recordListView = (CustomSwipeListView) view.findViewById(R.id.recordlistview);
	}
	
	private void ListViewLisenler() {
		recordListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + RecordList.get(position).getPhone()));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				activity.startActivity(intent);
			}
		});

	}

	/**
	 * 获取数据
	 */
	private void gitRecordInfo() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				RecordList.clear();
				RecordList = GetRecordInfo.getRecord(activity);
				Log.e("TAG", "size---" + RecordList.size());
				mHandler.sendEmptyMessage(0x01);
			}
		}).start();
	}

	/**
	 * 适配数据
	 */
	public void SetView() {
		mAdapter = new RecordListViewAdapter(activity, RecordList);
		recordListView.setAdapter(mAdapter);
	}
}

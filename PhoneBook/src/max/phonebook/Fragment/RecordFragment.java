package max.phonebook.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
import android.widget.AdapterView.OnItemLongClickListener;
import max.phonebook.R;
import max.phonebook.Adapter.RecordListViewAdapter;
import max.phonebook.Ben.Record;
import max.phonebook.ContentResolver.RecordInfo;
import max.phonebook.View.CustomSwipeListView;
import max.phonebook.View.RecordDialog;
import max.phonebook.db.BlackListDaoFactory;

public class RecordFragment extends Fragment {

	private View mview;
	private List<Record> RecordList = new ArrayList<Record>();// 创建一个保存获得的联系人的列表来存放数据
	public FragmentActivity activity;

	private CustomSwipeListView recordListView;
	private RecordListViewAdapter mAdapter;

	private RecordDialog mDialog;

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
		ListViewLisenler();
		return mview;
	}

	public void reflash() {
		RecordList.clear();
		mAdapter.notifyDataSetChanged();
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
		recordListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if (mDialog == null)
					mDialog = new RecordDialog(activity);
				String name = null;
				if (RecordList.get(position).getName() == null)
					name = RecordList.get(position).getPhone();
				else
					name = RecordList.get(position).getName();
				if (BlackListDaoFactory.getBlackListDao(activity).queryBlack(RecordList.get(position).getPhone()))
					mDialog.setTextBlackList("移除黑名单", name);
				else
					mDialog.setTextBlackList("加入黑名单", name);

				mDialog.setListener(RecordList.get(position));
				mDialog.show();
				return false;
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
				RecordList.addAll(RecordInfo.getRecord(activity));
				Log.e("TAG", "size---" + RecordList.size());
				mHandler.sendEmptyMessage(0x01);
			}
		}).start();
	}

	/**
	 * 适配数据
	 */
	public void SetView() {
		if (mAdapter == null) {
			mAdapter = new RecordListViewAdapter(activity, RecordList);
			recordListView.setAdapter(mAdapter);
		} else
			mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("TAG", "onPause--");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		gitRecordInfo();
		Log.e("TAG", "onResume--");
	}
}

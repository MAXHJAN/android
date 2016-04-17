package max.phonebook;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import max.phonebook.Adapter.BlackListAdapter;
import max.phonebook.Ben.BlackList;
import max.phonebook.View.CustomSwipeListView;
import max.phonebook.db.BlackListDaoFactory;
import max.phonebook.utils.ToastUtil;

public class BlackListActivity extends Activity {

	private CustomSwipeListView mBlackList;
	private BlackListAdapter mAdapter;

	private ImageView MyBack;
	private List<BlackList> mList = new ArrayList<BlackList>();

	public Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0x01) {
				Log.e("TAG", "--mList大小--" + mList.size());
				if (mList.size() == 0) {
					ToastUtil.showLongToast(BlackListActivity.this, "无数据", Gravity.CENTER);
				}
				setView();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blacklist);
		getActionBar().hide();
		gitBlackListInfo();
		init();
	}

	private void init() {

		mBlackList = (CustomSwipeListView) findViewById(R.id.blacklists);
		MyBack = (ImageView) findViewById(R.id.myback);

		MyBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	private void gitBlackListInfo() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mList.clear();
				mList.addAll(BlackListDaoFactory.getBlackListDao(BlackListActivity.this).queryBlackList());
				mHandler.sendEmptyMessage(0x01);
			}
		}).start();
	}

	private void setView() {
		mAdapter = new BlackListAdapter(BlackListActivity.this, mList);
		mBlackList.setAdapter(mAdapter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}

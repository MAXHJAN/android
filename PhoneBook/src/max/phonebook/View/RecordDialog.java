package max.phonebook.View;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import max.phonebook.MainActivity;
import max.phonebook.R;
import max.phonebook.Ben.Record;
import max.phonebook.ContentResolver.RecordInfo;
import max.phonebook.db.BlackListDaoFactory;
import max.phonebook.utils.ToastUtil;

public class RecordDialog extends Dialog {

	private Context mContext;

	private TextView mName;
	/* 拨打电话 */
	private TextView mCallUp;
	/* 发送短信 */
	private TextView mSendSMS;
	/* 清除记录 */
	private TextView mClearRecord;
	/* 加入黑名单 */
	private TextView mAddBlackList;

	private Record record;

	public RecordDialog(Context context) {
		super(context, android.R.style.Theme_Dialog);
		// TODO Auto-generated constructor stub
		mContext = context;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		View view = View.inflate(mContext, R.layout.recorddialog, null);
		setContentView(view);
		init(view);
	}

	private void init(View view) {
		mName = (TextView) view.findViewById(R.id.mname);
		mCallUp = (TextView) view.findViewById(R.id.callup);
		mSendSMS = (TextView) view.findViewById(R.id.sendsms);
		mClearRecord = (TextView) view.findViewById(R.id.clearrecord);
		mAddBlackList = (TextView) view.findViewById(R.id.addblacklist);
	}

	public void setTextBlackList(String text, String name) {
		mAddBlackList.setText(text);
		mName.setText(name);
	}

	public void setListener(Record mRecord) {

		record = mRecord;
		mCallUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RecordDialog.this.dismiss();
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + record.getPhone()));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startActivity(intent);
			}
		});

		mSendSMS.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RecordDialog.this.dismiss();
				Intent in = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + record.getPhone()));
				mContext.startActivity(in);
			}
		});

		mClearRecord.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RecordInfo.cleanCallLog(mContext);
				ToastUtil.showShortToast(mContext, "清除成功", Gravity.CENTER);
				MainActivity.instens.refalsh();
				RecordDialog.this.dismiss();
			}
		});

		mAddBlackList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mAddBlackList.getText().toString().trim().equals("加入黑名单")) {
					if (record.getName() == null) {
						BlackListDaoFactory.getBlackListDao(mContext).addBlackList(record.getPhone(),
								record.getPhone());
					} else
						BlackListDaoFactory.getBlackListDao(mContext).addBlackList(record.getPhone(), record.getName());
					ToastUtil.showShortToast(mContext, "加入成功", Gravity.CENTER);
				} else if (mAddBlackList.getText().toString().trim().equals("移除黑名单")) {
					BlackListDaoFactory.getBlackListDao(mContext).deleteBlackList(record.getPhone());
					ToastUtil.showShortToast(mContext, "移除成功", Gravity.CENTER);
				}
				RecordDialog.this.dismiss();
			}

		});
	}
}

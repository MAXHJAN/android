package max.phonebook.ContentResolver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.util.Log;
import max.phonebook.Ben.Record;

public class RecordInfo {

	public RecordInfo() {
		// TODO Auto-generated constructor stub
	}

	public static List<Record> getRecord(Context context) {

		List<Record> list = new ArrayList<Record>();
		Record mRecord = null;
		Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				mRecord = new Record();
				// 号码
				String phone = cursor.getString(cursor.getColumnIndex(Calls.NUMBER));
				// 呼叫类型
				String type;
				switch (Integer.parseInt(cursor.getString(cursor.getColumnIndex(Calls.TYPE)))) {
				case Calls.INCOMING_TYPE:
					type = "呼入";
					break;
				case Calls.OUTGOING_TYPE:
					type = "呼出";
					break;
				case Calls.MISSED_TYPE:
					type = "未接";
					break;
				default:
					type = "挂断";// 应该是挂断.根据我手机类型判断出的
					break;
				}
				SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(Calls.DATE))));
				// 呼叫时间
				String time = sfd.format(date);
				// 联系人
				String name = cursor.getString(cursor.getColumnIndexOrThrow(Calls.CACHED_NAME));
				// 通话时间,单位:s
				String duration = cursor.getString(cursor.getColumnIndexOrThrow(Calls.DURATION));
				// String
				// id=cursor.getString(cursor.getColumnIndexOrThrow(Calls._ID));
				String _id = cursor.getString(cursor.getColumnIndex(Calls._ID));
				Log.e("TAG", "id=" + _id);
				mRecord.setId(_id);
				mRecord.setName(name);
				mRecord.setPhone(phone);
				mRecord.setType(type);
				mRecord.setTime(time);
				mRecord.setDuration(duration);
				list.add(mRecord);
				mRecord = null;
			} while (cursor.moveToNext());

		}
		return list;
	}

	/**
	 * 删除通话记录
	 * 
	 * @param id
	 *            通话记录id
	 */
	public static void deleteRecord(final String id, final Context context) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, CallLog.Calls._ID + "=?",
						new String[] { id });
			}
		}).start();
	}

	/**
	 * 删除某人所有通话记录
	 * 
	 * @param number
	 *            电话号码
	 * @param context
	 */
	public static void deletePersonRecord(final String number, final Context context) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, CallLog.Calls.NUMBER + "=?",
						new String[] { number });
			}
		}).start();
	}

	public static void cleanCallLog(Context context) {
		ContentResolver resolver = context.getContentResolver();
		resolver.delete(CallLog.Calls.CONTENT_URI, null, null);
	}
}

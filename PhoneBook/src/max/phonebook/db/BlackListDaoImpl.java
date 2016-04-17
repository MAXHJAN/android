package max.phonebook.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import max.phonebook.Ben.BlackList;

public class BlackListDaoImpl implements BlackListDao {

	private static BlackListDBHelper mDbHelper;

	public BlackListDaoImpl(Context context) {
		mDbHelper = BlackListDBHelper.getDBHelper(context);
	}

	public void addBlackList(String number, String name) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = null;
		try {
			database = mDbHelper.getWritableDatabase();
			database.execSQL("insert into blacklist(number,name) values(?,?)", new String[] { number, name });
			database.close();
		} catch (Exception e) {
			Log.i("999", "数据库表写入失败" + e.toString());
		}
	}

	public void deleteBlackList(String number) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = null;
		try {
			database = mDbHelper.getWritableDatabase();
			database.delete("blacklist", "number=?", new String[] { number });
			database.close();
		} catch (Exception e) {
			Log.i("TAG", "删除数据失败");
		}

	}

	@Override
	public boolean queryBlack(String number) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = null;
		Cursor cursor = null;
		try {
			database = mDbHelper.getWritableDatabase();
			String sql = "select * from blacklist where number='" + number + "'";
			cursor = database.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				database.close();
				return true;
			}
			database.close();
		} catch (Exception e) {
			Log.i("TAG", "查询数据失败");
		}
		return false;
	}

	@Override
	public List<BlackList> queryBlackList() {
		// TODO Auto-generated method stub
		SQLiteDatabase database = null;
		List<BlackList> mlist = new ArrayList<BlackList>();
		BlackList mBlackList;
		Cursor cursor = null;
		try {
			database = mDbHelper.getWritableDatabase();
			String sql = "select * from blacklist";
			cursor = database.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				mBlackList = new BlackList();
				mBlackList.setNumber((cursor.getString(1)));
				mBlackList.setName(cursor.getString(2));
				mlist.add(mBlackList);
				mBlackList = null;
			}
			database.close();
		} catch (Exception e) {
			Log.i("TAG", "查询数据失败");
		}

		return mlist;
	}

}

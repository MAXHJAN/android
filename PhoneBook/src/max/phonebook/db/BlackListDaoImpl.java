package max.phonebook.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
			Log.i("999", "���ݿ��д��ʧ��" + e.toString());
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
			Log.i("TAG", "ɾ������ʧ��");
		}

	}

	@Override
	public boolean queryBlack(String number) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = null;
		Cursor cursor=null;
		try {
			database = mDbHelper.getWritableDatabase();
			String sql="select * from blacklist where number='"+number+"'";
			cursor=database.rawQuery(sql, null);
			while(cursor.moveToNext()){
				database.close();
				return true;
			}
			database.close();
		} catch (Exception e) {
			Log.i("TAG", "��ѯ����ʧ��");
		}
		return false;
	}

}

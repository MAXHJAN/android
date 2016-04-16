package max.phonebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BlackListDBHelper extends SQLiteOpenHelper {

	private static String DATABASENAME = "blacklist.db";
	private static int mversion = 10;
	private static BlackListDBHelper mDbHelper;

	public BlackListDBHelper(Context context) {
		super(context, DATABASENAME, null, mversion);
	}

	/**
	 * 单例模式获取数据库操作助手对象
	 * 
	 * @param context
	 * @return
	 */
	public static synchronized BlackListDBHelper getDBHelper(Context context) {
		if (mDbHelper == null) {
			mDbHelper = new BlackListDBHelper(context);
		}
		return mDbHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE blacklist (id  INTEGER PRIMARY KEY AUTOINCREMENT,number  TEXT,name TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists blacklist");
		onCreate(db);
	}

}

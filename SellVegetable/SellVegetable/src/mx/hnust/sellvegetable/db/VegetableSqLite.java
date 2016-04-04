package mx.hnust.sellvegetable.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class VegetableSqLite extends SQLiteOpenHelper {

	private static VegetableSqLite mDbHelper;
	private static String DATABASENAME = "vegetable.db";
	private static int mversion = 32;
	public VegetableSqLite(Context context) {
		super(context, DATABASENAME, null, mversion);
		// TODO Auto-generated constructor stub
	}

	
	public  static synchronized VegetableSqLite getInstance(Context context){
		if(mDbHelper==null){
			mDbHelper=new VegetableSqLite(context);
		}
		return mDbHelper;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table vegetableinfo(_id integer primary key autoincrement,v_id varchar,name text ,info text ,price text ,imageurl text,number text)");
		db.execSQL("create table organicvegetableinfo(_id integer primary key autoincrement,v_id varchar,name text ,info text ,price text ,imageurl text,number text)");
		db.execSQL("create table fruitinfo(_id integer primary key autoincrement,v_id varchar,name text ,info text ,price text ,imageurl text,number text)");
		db.execSQL("create table meategginfo(_id integer primary key autoincrement,v_id varchar,name text ,info text ,price text ,imageurl text,number text)");
	    Log.e("123", "数据库创建成功");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists vegetableinfo");
		db.execSQL("drop table if exists organicvegetableinfo");
		db.execSQL("drop table if exists fruitinfo");
		onCreate(db);
	}

}

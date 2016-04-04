package mx.hnust.sellvegetable.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class VegetableDao {

	private VegetableSqLite helper;
	public VegetableDao(Context context) {
		// TODO Auto-generated constructor stub
		helper=VegetableSqLite.getInstance(context);
	}
		
	/**
	 * 清空数据库
	 */	
	public void deleteVegetableInfos(String tablename){		
		 SQLiteDatabase db=helper.getWritableDatabase();
		try {			
			db.delete(tablename,null,null);	
			Log.e("123", tablename+"清空数据库成功");
			db.close();
		} catch (Exception e) {				
			db.close();
			Log.i("123","数据库表转换清空失败"+tablename);			
		}
	}
	
	
	/**
	 * 保存数据到数据库
	 * @param Infos
	 */
	
	public void addVegetableInfo(List<VegetableInfo> Infos,String tablename){
		SQLiteDatabase database=null;
		try {
			database=helper.getWritableDatabase();
			List<VegetableInfo> Info=Infos;
			for(int i=0;i<Info.size();i++)
			{
				Log.e("123", "存入"+Info.size()+"-----"+Info.get(i).getId());
				database.execSQL("insert into "+tablename+"(v_id,name,info,price,imageurl,number) values(?,?,?,?,?,?)",
						new String[]{Info.get(i).getId(),Info.get(i).getName(),Info.get(i).getInfo(),Info.get(i).getPrice(),
								Info.get(i).getImageurl(),Info.get(i).getNumber()});
			}			
			database.close();
		} catch (Exception e) {			
				Log.i("999","数据库表写入失败"+e.toString());			
		}
		Log.e("123", "-------------------已存入数据库");
	}
	
	
	
	/**
	 * 读取数据库
	 */
	public List<VegetableInfo> queryall(String tablename)
	{
		SQLiteDatabase db=helper.getWritableDatabase();
		List<VegetableInfo> Infos=new ArrayList<VegetableInfo>();
		VegetableInfo info;
		Cursor cur=db.rawQuery("select v_id,name,info,price,imageurl,number from "+tablename , null);
		Log.e("123", "-------------------获取数据");
		while(cur.moveToNext())
		{
			Log.e("123", tablename+"-------------------数据"+cur.getString(1));
			info=new VegetableInfo();
			info.setId(cur.getString(0));
			info.setName(cur.getString(1));
			info.setInfo(cur.getString(2));
			info.setPrice(cur.getString(3));
			info.setImageurl(cur.getString(4));			
			info.setNumber(cur.getString(5));
			
			Infos.add(info);
			info=null;
		}
		
		Log.e("123", "-------------------已获取数据");
		return  Infos;
	}
}

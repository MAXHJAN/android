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
	 * ������ݿ�
	 */	
	public void deleteVegetableInfos(String tablename){		
		 SQLiteDatabase db=helper.getWritableDatabase();
		try {			
			db.delete(tablename,null,null);	
			Log.e("123", tablename+"������ݿ�ɹ�");
			db.close();
		} catch (Exception e) {				
			db.close();
			Log.i("123","���ݿ��ת�����ʧ��"+tablename);			
		}
	}
	
	
	/**
	 * �������ݵ����ݿ�
	 * @param Infos
	 */
	
	public void addVegetableInfo(List<VegetableInfo> Infos,String tablename){
		SQLiteDatabase database=null;
		try {
			database=helper.getWritableDatabase();
			List<VegetableInfo> Info=Infos;
			for(int i=0;i<Info.size();i++)
			{
				Log.e("123", "����"+Info.size()+"-----"+Info.get(i).getId());
				database.execSQL("insert into "+tablename+"(v_id,name,info,price,imageurl,number) values(?,?,?,?,?,?)",
						new String[]{Info.get(i).getId(),Info.get(i).getName(),Info.get(i).getInfo(),Info.get(i).getPrice(),
								Info.get(i).getImageurl(),Info.get(i).getNumber()});
			}			
			database.close();
		} catch (Exception e) {			
				Log.i("999","���ݿ��д��ʧ��"+e.toString());			
		}
		Log.e("123", "-------------------�Ѵ������ݿ�");
	}
	
	
	
	/**
	 * ��ȡ���ݿ�
	 */
	public List<VegetableInfo> queryall(String tablename)
	{
		SQLiteDatabase db=helper.getWritableDatabase();
		List<VegetableInfo> Infos=new ArrayList<VegetableInfo>();
		VegetableInfo info;
		Cursor cur=db.rawQuery("select v_id,name,info,price,imageurl,number from "+tablename , null);
		Log.e("123", "-------------------��ȡ����");
		while(cur.moveToNext())
		{
			Log.e("123", tablename+"-------------------����"+cur.getString(1));
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
		
		Log.e("123", "-------------------�ѻ�ȡ����");
		return  Infos;
	}
}

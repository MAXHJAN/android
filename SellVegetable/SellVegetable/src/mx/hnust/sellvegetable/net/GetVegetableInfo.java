package mx.hnust.sellvegetable.net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.util.Log;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.db.VegetableDao;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class GetVegetableInfo {
	
	private Context context;
	List<VegetableInfo> Infos=new ArrayList<VegetableInfo>();
	public GetVegetableInfo(Context context) {
		// TODO Auto-generated constructor stub 
		 this.context=context;
	}
	
	/**
	 * �Ӻ�̨��ȡ����
	 * ʹ��velloy���
	 */
	public void getInfo(String path,final String tablename)
	{
		
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(path, new Listener<JSONArray>(){						
				@Override
				public void onResponse(JSONArray array) {
					// TODO Auto-generated method stub	
					Log.e("123",tablename+"-----------"+array.toString() ); 
					if(Infos!=null)
						Infos.clear();
					Infos=new JsonUtils().analysisJSONArray(array);
					save(Infos,tablename);//���ݴ������ݿ�
				}				
		
		}, new Response.ErrorListener() {  
            @Override  
            public void onErrorResponse(VolleyError error) { 
            	
            	Log.e("123", "-===-=--=-="+error.toString());  
                Log.e("123", error.getMessage(), error);  
            } 
		        
	});
		MyAppLication.getHttpRequestQueue().add(jsonArrayRequest);
	}
	
	/**
	 * ���ݴ������ݿ�
	 * @param Info
	 */
	
	public void  save(final List<VegetableInfo> Info,final String tablename)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				VegetableDao vdao=new VegetableDao(context);
				vdao.deleteVegetableInfos(tablename);
				vdao.addVegetableInfo(Info,tablename);
			}
		}).start();
	}			

}

package mx.hnust.sellvegetable.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mx.hnust.sellvegetable.util.VegetableInfo;

public class JsonUtils {

	public JsonUtils() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ½âÎöjsonArray
	 */
	
  public List<VegetableInfo> analysisJSONArray(JSONArray array)
  {
	  List<VegetableInfo> vinfos=new ArrayList<VegetableInfo>();
		JSONObject object=null;
		 VegetableInfo vinfo=null;
		for(int i=0;i<array.length();i++){
			try {
				object=array.getJSONObject(i);
				vinfo=new VegetableInfo();
				
				String id=object.getString("v_id");
				String name=object.getString("name");
				String info=object.getString("info");
				String price=object.getString("price");
				String imageurl=object.getString("imageurl");	
				String number=object.getString("number");
				
				vinfo.setId(id);
				vinfo.setName(name);
				vinfo.setInfo(info);
				vinfo.setPrice(price);
				vinfo.setImageurl(imageurl);
				vinfo.setNumber(number);
				vinfos.add(vinfo);
				info=null;
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	return vinfos;
  }
 
  

	/**
	 * ½âÎöjsonArray
	 */
	
public List<VegetableInfo> analysisJSONArray2(JSONArray array)
{
	  List<VegetableInfo> vinfos=new ArrayList<VegetableInfo>();
		JSONObject object=null;
		 VegetableInfo vinfo=null;
		for(int i=0;i<array.length();i++){
			try {
				object=array.getJSONObject(i);
				vinfo=new VegetableInfo();
				
				String id=object.getString("v_id");
				String name=object.getString("name");
				String info=object.getString("info");
				String price=object.getString("price");
				String imageurl=object.getString("imageurl");	
				String number=object.getString("number");
				String ordlerid=object.getString("ordlerid");
				
				vinfo.setId(id);
				vinfo.setName(name);
				vinfo.setInfo(info);
				vinfo.setPrice(price);
				vinfo.setImageurl(imageurl);
				vinfo.setNumber(number);
				vinfo.setOrdlerid(ordlerid);
				vinfos.add(vinfo);
				info=null;
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	return vinfos;
}
  
  
  /**
   * ½âÎöJsonObject
   */
  public void analysisJSONObject()
  {
	  
  }
}

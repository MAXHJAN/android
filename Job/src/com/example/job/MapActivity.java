package com.example.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends Activity{
	//地理编码变量
	private int length;//cityresult数组长度
	List<String> items = new ArrayList<String>();
	private double[] info;
	//private BitmapDescriptor mMarker;
	private ListView listview;
	
	private MapView mapView = null;
	private BaiduMap baiduMap;
	private BitmapDescriptor mMarker;
	//定位相关变量
	private LocationClient mLocationClient;
	private MyLocationListener mLocationListener;
	private boolean isFirstIn = true;
	//private LocationMode mLocationMode;
	//private BitmapDescriptor mIconLocation;
	private String city;
	private ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	
	public void onCreate(Bundle  savedInstanceState)
    {
  	  super.onCreate(savedInstanceState);
  	//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
      //注意该方法要再setContentView方法之前实现  
      SDKInitializer.initialize(getApplicationContext());
  	  setContentView(R.layout.map);
  	  ActionBar bar=getActionBar();
	  bar.hide();
	  Intent intent=getIntent();
	  Bundle b=intent.getExtras();
	 city=b.getString("city");
	  items=b.getStringArrayList("items");
	  System.out.println("0000000-----"+city);
	  length=items.size()-1;
	  /////////////////////////////////////////////
	  for(Object obj:items)
		  System.out.println("我是map---------"+obj);	  
	  //////////////////////////////////////////////
	  mapView = (MapView) findViewById(R.id.mapView);
	  	baiduMap = mapView.getMap();
	  	listview(); 	
	  	initLocation(); 
	  	/****************************************************************************************************/
		  SharedPreferences preferences = getSharedPreferences("city1", Activity.MODE_PRIVATE);
		  int p=preferences.getInt("p",0);
		  System.out.println("我是map--ppp-------"+p);	  
		  info=new double[p];
	  	for(int i=0;i<p;i++)
	  		info[i]=Double.valueOf(preferences.getString(""+i, null));	  
		addOverlays(info);
		/*****************************************************************************************************/
		baiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				Bundle ab = marker.getExtraInfo();
				double ba=ab.getDouble("in");
				//Toast.makeText(MapActivity.this,""+ba,Toast.LENGTH_SHORT).show();
				Intent intent=new Intent();
				intent.setClass(MapActivity.this,JobinfoActivity.class);
				startActivity(intent);
				return false;
			}
		});
		
    }
	public void listview()
	{
		String[] a={"泰富重装集团有限公司 ","迅达科技集团股份有限公司 "," 湘潭兴业太阳能技术控股科技有限公司","湖南腾飞集团有限公","湖南开启时代电子信息技术有限公司"};
		String[] b={"产品销售员","售后服务","CNC编程员","维修工程师","软件工程师"};
		listview=(ListView) findViewById(R.id.map_listView1);
		 for(int i =0; i <a.length; i++) {  
	         Map<String,Object> item = new HashMap<String,Object>();  
	          item.put("comp",a[i]); 	         
	          item.put("job",b[i]);
	          item.put("address",items.get(i));
	          item.put("city",city);
	         mData.add(item);         
	      }  
		 SimpleAdapter adapter=new SimpleAdapter(MapActivity.this, mData,R.layout.jobmap,new String[]{"comp","job","address","city"},new int[]{R.id.j_t1,R.id.j_t2,R.id.j_t3,R.id.j_t4});
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				Intent intent=new Intent();
				intent.setClass(MapActivity.this,JobinfoActivity.class);
				startActivity(intent);
			}
		});
		
	}
	private void addOverlays(double[] ab)
	{
		mMarker = BitmapDescriptorFactory.fromResource(R.drawable.maker);
		baiduMap.clear();
		LatLng latLng = null;
		Marker marker = null;
		OverlayOptions options;
		for (int i=0;i<ab.length;i++)
		{
			// 经纬度
			latLng = new LatLng(ab[i++],ab[i]);
			// 图标
			options = new MarkerOptions().position(latLng).icon(mMarker)
					.zIndex(5);
			marker = (Marker) baiduMap.addOverlay(options);
			Bundle arg0 = new Bundle();
			arg0.putDouble("in",ab[i]);
			marker.setExtraInfo(arg0);
		}

		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		baiduMap.setMapStatus(msu);

	}
	private void initLocation()
	{

		//mLocationMode = LocationMode.NORMAL;
		mLocationClient = new LocationClient(this);
		mLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);

		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setIsNeedAddress(true);
		option.setOpenGps(true);
		option.setScanSpan(1000);
		mLocationClient.setLocOption(option);
		// 初始化图标
		//mIconLocation = BitmapDescriptorFactory
				//.fromResource(R.drawable.navi_map_gps_locked);
	}
	@Override  
	protected void onStart()
	{
		super.onStart();		
		// 开启定位
		baiduMap.setMyLocationEnabled(true);
		if (!mLocationClient.isStarted())
			mLocationClient.start();
	}
    protected void onDestroy() {  
        super.onDestroy();  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mapView.onPause();  
        }  
    protected void onStop()
	{
		super.onStop();
		// 停止定位
		baiduMap.setMyLocationEnabled(false);
		mLocationClient.stop();
		}
    private class MyLocationListener implements BDLocationListener
    {
    	public void onReceiveLocation(BDLocation location)
    	{
    		MyLocationData data = new MyLocationData.Builder()//
			.accuracy(location.getRadius())//
			.latitude(location.getLatitude())//
			.longitude(location.getLongitude())//
			.build();
	       baiduMap.setMyLocationData(data);
    		if (isFirstIn)
			{
				LatLng latLng = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
				baiduMap.animateMapStatus(msu);
				isFirstIn = false;
				//Toast.makeText(MapActivity.this, location.getAddrStr(),
						//Toast.LENGTH_SHORT).show();
			}
    	}
    }
}

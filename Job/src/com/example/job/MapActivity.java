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
	//����������
	private int length;//cityresult���鳤��
	List<String> items = new ArrayList<String>();
	private double[] info;
	//private BitmapDescriptor mMarker;
	private ListView listview;
	
	private MapView mapView = null;
	private BaiduMap baiduMap;
	private BitmapDescriptor mMarker;
	//��λ��ر���
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
  	//��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
      //ע��÷���Ҫ��setContentView����֮ǰʵ��  
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
		  System.out.println("����map---------"+obj);	  
	  //////////////////////////////////////////////
	  mapView = (MapView) findViewById(R.id.mapView);
	  	baiduMap = mapView.getMap();
	  	listview(); 	
	  	initLocation(); 
	  	/****************************************************************************************************/
		  SharedPreferences preferences = getSharedPreferences("city1", Activity.MODE_PRIVATE);
		  int p=preferences.getInt("p",0);
		  System.out.println("����map--ppp-------"+p);	  
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
		String[] a={"̩����װ�������޹�˾ ","Ѹ��Ƽ����Źɷ����޹�˾ "," ��̶��ҵ̫���ܼ����عɿƼ����޹�˾","�����ڷɼ������޹�","���Ͽ���ʱ��������Ϣ�������޹�˾"};
		String[] b={"��Ʒ����Ա","�ۺ����","CNC���Ա","ά�޹���ʦ","�������ʦ"};
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
			// ��γ��
			latLng = new LatLng(ab[i++],ab[i]);
			// ͼ��
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
		// ��ʼ��ͼ��
		//mIconLocation = BitmapDescriptorFactory
				//.fromResource(R.drawable.navi_map_gps_locked);
	}
	@Override  
	protected void onStart()
	{
		super.onStart();		
		// ������λ
		baiduMap.setMyLocationEnabled(true);
		if (!mLocationClient.isStarted())
			mLocationClient.start();
	}
    protected void onDestroy() {  
        super.onDestroy();  
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
        mapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mapView.onPause();  
        }  
    protected void onStop()
	{
		super.onStop();
		// ֹͣ��λ
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

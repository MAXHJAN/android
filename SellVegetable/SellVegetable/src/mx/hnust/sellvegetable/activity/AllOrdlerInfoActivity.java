package mx.hnust.sellvegetable.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.adapter.OrdlerListViewAdapter;
import mx.hnust.sellvegetable.net.JsonArrayPostRequest;
import mx.hnust.sellvegetable.net.JsonUtils;
import mx.hnust.sellvegetable.util.Constants;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class AllOrdlerInfoActivity extends Activity{

	private ListView ordlerListView;
	private OrdlerListViewAdapter mAdapter;
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/getPurchaceServlet";
	List<VegetableInfo> Infos=new ArrayList<VegetableInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allordlerinfo);
		getActionBar().hide();
		init();
		getOrdlerInfo();
	}
	
	
	private void init()
	{
		ordlerListView=(ListView) findViewById(R.id.ordlerlistview);
		ordlerListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Bundle bun=new Bundle();
				bun.putSerializable("info", Infos.get(arg2));
				Intent in=new Intent(AllOrdlerInfoActivity.this,OrdlerInfoActivity.class);
				in.putExtras(bun);
				startActivity(in);
			}
		});
	    
		
	}

	private void getOrdlerInfo()
	{	
		String pid=getSharedPreferences("Config", MODE_PRIVATE).getString("pid", null);
		Map<String,String> mMap = new HashMap<String, String>();
		mMap.put("pid", pid);
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(AllOrdlerInfoActivity.this);
		mpProgersssDialog.setMsg("获取数据中...");
		mpProgersssDialog.showProgersssDialog();
		JsonArrayPostRequest jsonArrayRequest = new JsonArrayPostRequest(PATH, new Listener<JSONArray>()
		{
			
			@Override
			public void onResponse(JSONArray array) {
				// TODO Auto-generated method stub			
				if(Infos!=null)
					Infos.clear();
				Infos=new JsonUtils().analysisJSONArray2(array);
				if(Infos==null||Infos.size()<1)
				{
					ToastUtil.showLongToast(AllOrdlerInfoActivity.this, 
							"获取数据失败", Gravity.CENTER);
					mpProgersssDialog.closeProgersssDialog();
					return;
				}
			
				mAdapter=new OrdlerListViewAdapter(AllOrdlerInfoActivity.this, Infos,
						ImageLoader.getInstance(), Constants.IMAGE_OPTIONS);
				ordlerListView.setAdapter(mAdapter);
				mpProgersssDialog.closeProgersssDialog();				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				mpProgersssDialog.closeProgersssDialog();	
				ToastUtil.showLongToast(AllOrdlerInfoActivity.this, 
						"网络错误,连接服务其失败", Gravity.CENTER);
			}
			
		}, mMap);
		MyAppLication.getHttpRequestQueue().add(jsonArrayRequest);
					
	}
	
	public void MyBack(View v)
	{
		finish();
		overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}

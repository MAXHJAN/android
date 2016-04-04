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
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.adapter.CartListViewAdapter;
import mx.hnust.sellvegetable.net.JsonArrayPostRequest;
import mx.hnust.sellvegetable.net.JsonUtils;
import mx.hnust.sellvegetable.util.Constants;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class ShoppingCartActivity extends Activity{

	private ListView CartListView;
	private CartListViewAdapter mAdapter;
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/getShoppingCartServlet";
	List<VegetableInfo> Infos=new ArrayList<VegetableInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allcartinfo);
		getActionBar().hide();
		init();
		getCartInfo();
	}

	
	
	private void init()
	{
		
		CartListView=(ListView) findViewById(R.id.cartlistView);
		CartListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	private void getCartInfo()
	{		
		String pid=getSharedPreferences("Config", MODE_PRIVATE).getString("pid", null);
		Map<String,String> mMap = new HashMap<String, String>();
		mMap.put("pid", pid);
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(ShoppingCartActivity.this);
		mpProgersssDialog.setMsg("获取数据中...");
		mpProgersssDialog.showProgersssDialog();
		JsonArrayPostRequest jsonArrayRequest = new JsonArrayPostRequest(PATH, new Listener<JSONArray>()
		{
			
			@Override
			public void onResponse(JSONArray array) {
				// TODO Auto-generated method stub			
				if(Infos!=null)
					Infos.clear();
				Infos=new JsonUtils().analysisJSONArray(array);
				if(Infos==null||Infos.size()<1)
				{
					ToastUtil.showLongToast(ShoppingCartActivity.this, 
							"暂无数据", Gravity.CENTER);
					mpProgersssDialog.closeProgersssDialog();
					return;
				}
			
				mAdapter=new CartListViewAdapter(ShoppingCartActivity.this, Infos,
						ImageLoader.getInstance(), Constants.IMAGE_OPTIONS);
				CartListView.setAdapter(mAdapter);
				mpProgersssDialog.closeProgersssDialog();				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				mpProgersssDialog.closeProgersssDialog();
				ToastUtil.showLongToast(ShoppingCartActivity.this, 
						"网络错误,连接服务器失败", Gravity.CENTER);
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

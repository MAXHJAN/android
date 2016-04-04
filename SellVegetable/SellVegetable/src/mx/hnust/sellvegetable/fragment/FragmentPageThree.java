package mx.hnust.sellvegetable.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.activity.MainActivity;
import mx.hnust.sellvegetable.activity.ShoppingCartActivity;
import mx.hnust.sellvegetable.adapter.CartListViewAdapter;
import mx.hnust.sellvegetable.net.JsonArrayPostRequest;
import mx.hnust.sellvegetable.net.JsonUtils;
import mx.hnust.sellvegetable.util.Constants;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class FragmentPageThree extends Fragment {

	private View mview;
	MainActivity activity;
	private ListView CartListView;
	private CartListViewAdapter mAdapter;
	private String PATH="http://192.168.191.1:8080/SellVegetableServer/getShoppingCartServlet";
	List<VegetableInfo> Infos=new ArrayList<VegetableInfo>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.fragment03, null);
	    }
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    
	    activity=(MainActivity) getActivity();
	    getOrdlerInfo();
	    init(mview);
		return mview;
	}

	
	private void init(View view)
	{
		
		CartListView=(ListView) view.findViewById(R.id.cartlistView);
		CartListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	
	private void getOrdlerInfo()
	{		
		String pid=activity.getSharedPreferences("Config",activity.MODE_PRIVATE).getString("pid", null);
		Map<String,String> mMap = new HashMap<String, String>();
		mMap.put("pid", pid);
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(activity);
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
					ToastUtil.showLongToast(activity, 
							"获取数据失败", Gravity.CENTER);
					mpProgersssDialog.closeProgersssDialog();
					return;
				}
			
				mAdapter=new CartListViewAdapter(activity, Infos,
						activity.getImageLoader(), Constants.IMAGE_OPTIONS);
				CartListView.setAdapter(mAdapter);
				mpProgersssDialog.closeProgersssDialog();				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				mpProgersssDialog.closeProgersssDialog();		
				// TODO Auto-generated method stub
				ToastUtil.showLongToast(activity, 
						"网络错误,连接服务器失败", Gravity.CENTER);
			}
			
		}, mMap);
		MyAppLication.getHttpRequestQueue().add(jsonArrayRequest);
					
	}
	
}

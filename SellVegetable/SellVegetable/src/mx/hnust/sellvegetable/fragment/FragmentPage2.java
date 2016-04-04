package mx.hnust.sellvegetable.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.activity.CommodityInfoActivity;
import mx.hnust.sellvegetable.activity.MainActivity;
import mx.hnust.sellvegetable.adapter.GridViewAdapter;
import mx.hnust.sellvegetable.db.VegetableDao;
import mx.hnust.sellvegetable.net.JsonUtils;
import mx.hnust.sellvegetable.util.Constants;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.util.VegetableInfo;
import mx.hnust.sellvegetable.view.GridViewWithHeaderAndFooter;
import mx.hnust.sellvegetable.view.ImageCycleView;
import mx.hnust.sellvegetable.view.ImageCycleView.ImageCycleViewListener;

public class FragmentPage2 extends BaseFragment {

	private String TAG="FragmentPage2";
	private View mview,handlerView;
	private ImageCycleView imagehandler;
	private GridViewWithHeaderAndFooter mGridView;
	private GridViewAdapter adapter;
	private List<VegetableInfo>  infos;
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/getOrganicInfoServlet";
	//private String PATH="http://192.168.191.1:8080/SellVegetableServer/getOrganicInfoServlet";
	private static final String TABLE_NAME="organicvegetableinfo";
	private SharedPreferences mSharedPreferences;	   
	private MainActivity activity;
	private ImageLoader mImageLoader;
	private ArrayList<String> mImageUrl = null;
	private String imageUrl1 = "http://121.42.202.244/sellvegetable/vegetable/a1.jpg";
	private String imageUrl2 = "http://121.42.202.244/sellvegetable/vegetable/a2.jpg";
	private String imageUrl3 = "http://121.42.202.244/sellvegetable/vegetable/a3.jpg";
	private String imageUrl4 = "http://121.42.202.244/sellvegetable/vegetable/a4.jpg";
	
	private Handler handler=new Handler()
	{
	 public void handleMessage(Message msg) {
	    	// TODO Auto-generated method stub
	    	super.handleMessage(msg);
	    	switch (msg.what) {
			case 0x01:	
				Log.e("123","由网络得到数据开始适配数据");
				      saveData(TABLE_NAME);//数据入库
				adapter=new GridViewAdapter(getActivity(), infos);
				mGridView.setAdapter(adapter);
				break;
			case 0x02:	
				Log.e("123","由数据库得到数据开始适配数据");				    
				adapter=new GridViewAdapter(getActivity(), infos);
				mGridView.setAdapter(adapter);
				break;

			}
	    }
};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.fragmentchild02, null);
			mGridView=(GridViewWithHeaderAndFooter) mview.findViewById(R.id.gridview02);	
			handlerView = LayoutInflater.from(getActivity()).inflate(
						R.layout.gridviewheadler02, null, true);
			 mGridView.addHeaderView(handlerView);
			//lazyLoad();
	    }
		else
			mGridView=(GridViewWithHeaderAndFooter) mview.findViewById(R.id.gridview02);
		activity=(MainActivity) getActivity();
		
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    mSharedPreferences=getActivity().getSharedPreferences("Config", getActivity().MODE_PRIVATE);
	    headlerView();
	    getData();
	    Log.e("321","onCreateView");
		return mview;
	}
  public void headlerView()
  {
	  imagehandler=(ImageCycleView) handlerView.findViewById(R.id.headlerview);				
		mImageUrl = new ArrayList<String>();
		mImageUrl.add(imageUrl1);
		mImageUrl.add(imageUrl2);
		mImageUrl.add(imageUrl3);
		mImageUrl.add(imageUrl4);
		imagehandler.setImageResources(mImageUrl, mAdCycleViewListener);
  }
	
	
	
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {
		@Override
		public void onImageClick(int position, View imageView) {
			// TODO 单击图片处理事件
			ToastUtil.showShortToast(activity, "此功能暂未开通", Gravity.CENTER);
		}
		@Override
		public void displayImage(String imageURL,ImageView imageView) {
			activity.getImageLoader().displayImage(imageURL, imageView,Constants.IM_IMAGE_OPTIONS);// 此处本人使用了ImageLoader对图片进行加装！
		}
	};
	
	/**
	 * 点击事件
	 */
	public void myListener()
	{
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Bundle bun=new Bundle();
				bun.putSerializable("info", infos.get(arg2));
				bun.putString("flag", "2");
				Intent in=new Intent(getActivity(),CommodityInfoActivity.class);
				in.putExtras(bun);
				startActivity(in);
			}
		});
		
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		 myListener();
		 Log.e("100","FragmentPage2-------lazyLoad");
	}
	
	/**
	 * 获取后台数据
	 */
	private void getData()
	{
		 
		if(infos!=null)
			infos.clear();			
		if(mSharedPreferences.getBoolean("isfirstcreatfrg", false))
		{
			JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(PATH, new Listener<JSONArray>(){						
				@Override
				public void onResponse(JSONArray array) {
					// TODO Auto-generated method stub	
					Log.e("123","-----------"+array.toString());
					Log.e("999","-----jsonArrayRequest-TABLE_NAME-----");
					infos=new JsonUtils().analysisJSONArray(array);	
					handler.sendEmptyMessage(0x01);
				}				
		
			}, new Response.ErrorListener() {  
            @Override  
            public void onErrorResponse(VolleyError error) { 
            	
            	ToastUtil.showShortToast(activity, "网络错误", Gravity.CENTER);
            	Log.e("123", "-===-=--=-="+error.toString());  
                Log.e("123", error.getMessage(), error);  
            } 
		        
			});
			MyAppLication.getHttpRequestQueue().add(jsonArrayRequest);	
			}
		else
		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					VegetableDao vdao=new VegetableDao(getActivity());
					infos=vdao.queryall(TABLE_NAME);
					
					Log.e("123","-----queryall------"+TABLE_NAME);
					Message msg=new Message();
					msg.what=0x02;
					handler.sendMessage(msg);
				}
			}).start();
			
		}
	    
	}
	
	/**
	 * 保存数据
	 */
   private void  saveData(final String tablename)
   {
	   new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			VegetableDao vdao=new VegetableDao(getActivity());
			vdao.deleteVegetableInfos(tablename);
			vdao.addVegetableInfo(infos,tablename);
		}
	}).start();
   }
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("321","onDestroy-2");
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		 mSharedPreferences.edit().putBoolean("isfirstcreatfrg", false).commit();
		Log.e("321","onDestroyView-2");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("321","onPause-2");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("321","onResume-2");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("321","onStart-2");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("321","onStop-2");
	}

}

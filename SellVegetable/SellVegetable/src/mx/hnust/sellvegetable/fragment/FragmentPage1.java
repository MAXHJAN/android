package mx.hnust.sellvegetable.fragment;


import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.activity.CommodityInfoActivity;
import mx.hnust.sellvegetable.activity.MainActivity;
import mx.hnust.sellvegetable.adapter.ListViewAdapter;
import mx.hnust.sellvegetable.db.VegetableDao;
import mx.hnust.sellvegetable.util.Constants;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.util.VegetableInfo;
import mx.hnust.sellvegetable.view.ImageCycleView;
import mx.hnust.sellvegetable.view.ImageCycleView.ImageCycleViewListener;

public class FragmentPage1 extends BaseFragment {

	private View mview,headerview;

	private ListView mListView;
	private ListViewAdapter adapter;
	
	private ImageCycleView mAdView;
	private ArrayList<String> mImageUrl = null;
	private String imageUrl1 = "http://121.42.202.244/sellvegetable/mtod/a1.jpg";
	private String imageUrl2 = "http://121.42.202.244/sellvegetable/mtod/a2.jpg";
	private String imageUrl3 = "http://121.42.202.244/sellvegetable/mtod/a3.jpg";
	private String imageUrl4 = "http://121.42.202.244/sellvegetable/mtod/a4.jpg";
	
	private List<VegetableInfo> info;
	private MainActivity activity;
	
	private Handler handler=new Handler()
		{
		 public void handleMessage(Message msg) {
		    	// TODO Auto-generated method stub
		    	super.handleMessage(msg);
		    	switch (msg.what) {
				case 0x01:	
					Log.e("123","得到数据开始适配数据");
					adapter=new ListViewAdapter(activity, info,activity.getImageLoader(),Constants.IMAGE_OPTIONS);
					mListView.setAdapter(adapter);
					break;

				}
		    }
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.fragmentchild01, null);
	    }
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    activity=(MainActivity) getActivity();
	    getinfo();
	    initView(mview);
	    MyListener();
		return mview;
	}
	
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		
		Log.e("100","FragmentPage1-------lazyLoad");
	}
	
	private void initView(View view)
	{
		mListView=(ListView) view.findViewById(R.id.listView);
		// 使用DisplayImageOptions.Builder()创建DisplayImageOptions				
				
				mImageUrl = new ArrayList<String>();
				mImageUrl.add(imageUrl1);
				mImageUrl.add(imageUrl2);
				mImageUrl.add(imageUrl3);
				mImageUrl.add(imageUrl4);
				
				headerview=View.inflate(activity, R.layout.listview_header, null);
				mListView.addHeaderView(headerview);
				mAdView = (ImageCycleView) headerview.findViewById(R.id.ad_view);
				mAdView.setImageResources(mImageUrl, mAdCycleViewListener);
				
				
	}
	
	
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {
		@Override
		public void onImageClick(int position, View imageView) {
			// TODO 单击图片处理事件
			ToastUtil.showShortToast(activity, "此功能暂未开通", Gravity.CENTER);
		}
		@Override
		public void displayImage(String imageURL,ImageView imageView) {
			ImageLoader.getInstance().displayImage(imageURL, imageView,Constants.IM_IMAGE_OPTIONS);// 此处本人使用了ImageLoader对图片进行加装！
		}
	};
	
	
	private void MyListener()
	{
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Bundle bun=new Bundle();
				bun.putSerializable("info", info.get(arg2-1));
				bun.putString("flag", "1");
				Intent in=new Intent(activity,CommodityInfoActivity.class);
				in.putExtras(bun);
				startActivity(in);
			}
		});
	}
	
	private void  getinfo()
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			info=new VegetableDao(getActivity()).queryall("vegetableinfo");
			Log.e("123","得到数据-------");
			handler.sendEmptyMessage(0x01);
			}
		}).start();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mAdView.startImageCycle();
		Log.e("555", "onResume");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		mAdView.pushImageCycle();
		Log.e("555", "onPause");
	}
	

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		mListView.removeHeaderView(headerview);
		Log.e("555", "onDestroyView");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mAdView.pushImageCycle();
	}

	
}

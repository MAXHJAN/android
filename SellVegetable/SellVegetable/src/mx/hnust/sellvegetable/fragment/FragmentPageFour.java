package mx.hnust.sellvegetable.fragment;

import java.io.File;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.activity.AllOrdlerInfoActivity;
import mx.hnust.sellvegetable.activity.LoginActivity;
import mx.hnust.sellvegetable.activity.MainActivity;
import mx.hnust.sellvegetable.activity.SettingActivity;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.view.CircleTextImageView;

public class FragmentPageFour extends Fragment implements View.OnClickListener{

	private View mview;
	private View[] tab=new View[5];
	private CircleTextImageView Ctiv;
	private MainActivity activity;	
	private SharedPreferences mPreferences;
	private static final String savePath = "file:///mnt/sdcard/SellVegetable/";
	private static final String savePath1 = "/sdcard/SellVegetable/";
	private String imageurl="";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.fragment04, null);
	    }
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    
	    activity=(MainActivity) getActivity();
	    mPreferences=activity.getSharedPreferences("Config", activity.MODE_APPEND);
	    initView(mview);
	    
		return mview;
	}

	private void initView(View view)
	{
		tab[0]= view.findViewById(R.id.myorder);
		tab[1]= view.findViewById(R.id.mymassage);
		tab[2]= view.findViewById(R.id.setting);
		tab[3]= view.findViewById(R.id.advice);
		tab[4]= view.findViewById(R.id.about);		
		for(int i=0;i<5;i++)
		{
			tab[i].setOnClickListener(this);
		}
		
		Ctiv=(CircleTextImageView) view.findViewById(R.id.profile_image);
		
		Ctiv.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.profile_image:
			startActivity(new Intent(activity,LoginActivity.class));
			break;
		case R.id.myorder:
			if(mPreferences.getBoolean("state", false))
			   startActivity(new Intent(activity,AllOrdlerInfoActivity.class));
			else
				ToastUtil.showShortToast(activity, "请先登录", Gravity.CENTER);
			break;
		case R.id.mymassage:	
			ToastUtil.showShortToast(activity, "此功能暂未开通", Gravity.CENTER);
			break;
		case R.id.setting:
			if(mPreferences.getBoolean("state", false))
			{
			   Intent in=new Intent(activity,SettingActivity.class);
			    startActivity(in);
			}
			else
				ToastUtil.showShortToast(activity, "请先登录", Gravity.CENTER);
			break;
		case R.id.advice:
			ToastUtil.showShortToast(activity, "此功能暂未开通", Gravity.CENTER);
			break;
		case R.id.about:
			ToastUtil.showShortToast(activity, "此功能暂未开通", Gravity.CENTER);
			break;
		}
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 mPreferences=activity.getSharedPreferences("Config", activity.MODE_APPEND);
		if(mPreferences.getBoolean("state", false))
		{
			String pid=mPreferences.getString("pid", "");
			if(!fileIsExists(pid))
			{
				imageurl=savePath+pid+".jpg";
			}
			else
			{
				imageurl=savePath+"temp.jpg";
				Log.e("321", "savePath-------------");
			}
			
				activity.getImageLoader().displayImage(imageurl, Ctiv);
				Ctiv.setText("");
		}
	}
	
	/**
	 * 判断文件是否存在
	 * @param path
	 * @return
	 */

	public boolean fileIsExists(String path){
        try{
                File f=new File("savePath1"+path+".jpg");
                if(!f.exists()){
                        return false;
                }
                
        }catch (Exception e) {
                // TODO: handle exception
                return false;
        }
        return true;
}
}

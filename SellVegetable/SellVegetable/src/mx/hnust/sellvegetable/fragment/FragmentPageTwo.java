package mx.hnust.sellvegetable.fragment;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.adapter.ViewPagerAdapter;

public class FragmentPageTwo extends Fragment {

	private View mview;
	private ViewPager mViewPager;
	private ArrayList<Fragment> mFragments; 
	private TextView title0,title1;
	private ImageView image;
	
	private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
	private int position_one;
    public final static int num = 2 ; //fragment个数
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.fragment02, null);
	    }
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    InitWidth(mview);
	    intview(mview);
	    inits();
		return mview;
	}

	private void InitWidth(View view) {
		  image=(ImageView) view.findViewById(R.id.bottom_line);	 
		   LayoutParams lp=image.getLayoutParams();	      
	        DisplayMetrics dm = new DisplayMetrics();
	        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
	        int screenW = dm.widthPixels;
	        bottomLineWidth=(int)(screenW /num);	        
	        lp.width=bottomLineWidth-20;
	        image.setLayoutParams(lp);	       
	        offset=10;
	        position_one = offset+bottomLineWidth;
	        
	        
	    }
	private void intview(View view)
	{
		title0=(TextView) view.findViewById(R.id.title01);
		title1=(TextView) view.findViewById(R.id.title02);
		title0.setTextColor(getResources().getColor(R.color.green));
		mViewPager=(ViewPager) mview.findViewById(R.id.viewpager02);
		
		title0.setOnClickListener(new MyOnClickListener(0));
		title1.setOnClickListener(new MyOnClickListener(1));
	}
	private void inits()
	{
		
		mFragments=new ArrayList<Fragment>();		
		mFragments.add(new FragmentPage5());
		mFragments.add(new FragmentPage6());
		mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mFragments));
		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
	            Animation animation = null;
	            switch (arg0) {
	            case 0:
	                if (currIndex == 1) {
	                    animation = new TranslateAnimation(position_one, offset, 0, 0);
	                    title1.setTextColor(getResources().getColor(R.color.black));
	                } 
	                title0.setTextColor(getResources().getColor(R.color.green));
	                break;
	            case 1:
	                if (currIndex == 0) {
	                    animation = new TranslateAnimation(offset, position_one, 0, 0);
	                    title0.setTextColor(getResources().getColor(R.color.black));
	                } 
	                title1.setTextColor(getResources().getColor(R.color.green));
	                break;
	            }
	            currIndex = arg0;
	            animation.setFillAfter(true);
	            animation.setDuration(300);
	            image.startAnimation(animation);
	        }
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	 public class MyOnClickListener implements View.OnClickListener {
	        private int index = 0;

	        public MyOnClickListener(int i) {
	            index = i;
	        }

	        @Override
	        public void onClick(View v) {
	            mViewPager.setCurrentItem(index);
	        }
	    };	
	
}

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

public class FragmentPageOne extends Fragment {

	private View mview;
	private ViewPager mViewPager;
	private ArrayList<Fragment> mFragments; 
	
	private TextView title1,title2,title3,title4;
	private ImageView image;
		
	private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
	private int position_one;
    public final static int num = 4 ; 
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.fragment01, null);
	    }
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
	    InitWidth(mview);
	    intview(mview);
	    inits(mview);
		return mview;
	}
	
	private void InitWidth(View view) {
		  image=(ImageView) view.findViewById(R.id.iv_bottom_line);
		   LayoutParams lp=image.getLayoutParams();	      
	        DisplayMetrics dm = new DisplayMetrics();
	        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
	        int screenW = dm.widthPixels;
	        lp.width=screenW / num;
	        image.setLayoutParams(lp);
	       // offset = (int) ((screenW / num - bottomLineWidth) / 2);
	        offset=0;
	        int avg = (int) (screenW / num);
	        position_one = avg + offset;
	        
	        
	    }
	private void intview(View view)
	{
		title1=(TextView) view.findViewById(R.id.title1);
		title2=(TextView) view.findViewById(R.id.title2);
		title3=(TextView) view.findViewById(R.id.title3);
		title4=(TextView) view.findViewById(R.id.title4);		
		title1.setTextColor(getResources().getColor(R.color.green));
		
		mViewPager=(ViewPager) view.findViewById(R.id.viewpager);
		mViewPager.setOffscreenPageLimit(0);
		title1.setOnClickListener(new MyOnClickListener(0));
		title2.setOnClickListener(new MyOnClickListener(1));
		title3.setOnClickListener(new MyOnClickListener(2));
		title4.setOnClickListener(new MyOnClickListener(3));
		}
	private void inits(View view)
	{
		
		mFragments=new ArrayList<Fragment>();
		mFragments.add(new FragmentPage1());
		mFragments.add(new FragmentPage2());
		mFragments.add(new FragmentPage3());
		mFragments.add(new FragmentPage4());
		mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mFragments));		
		mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
		mViewPager.setCurrentItem(0);
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
	    
	    public class MyOnPageChangeListener implements OnPageChangeListener
	    {

			@Override
			public void onPageScrollStateChanged(int arg0) {}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

	            Animation animation = null;
	            switch (arg0) {
	            case 0:
	            	switch (currIndex) {					
					case 1:
						animation = new TranslateAnimation(position_one, offset, 0, 0);
						 title2.setTextColor(getResources().getColor(R.color.black));
						break;
					case 2:
						animation = new TranslateAnimation(position_one*2, offset, 0, 0);
						title3.setTextColor(getResources().getColor(R.color.black));
						break;
					case 3:
						animation = new TranslateAnimation(position_one*3, offset, 0, 0);
						 title4.setTextColor(getResources().getColor(R.color.black));
						break;					
					}	               	                
                    title1.setTextColor(getResources().getColor(R.color.green));
	               
	                break;
	            case 1:
	            	switch (currIndex) {					
					case 0:
						animation = new TranslateAnimation(0, position_one, 0, 0);
						 title1.setTextColor(getResources().getColor(R.color.black));
						break;
					case 2:
						animation = new TranslateAnimation(position_one*2, position_one, 0, 0);
						title3.setTextColor(getResources().getColor(R.color.black));
						break;
					case 3:
						animation = new TranslateAnimation(position_one*3, position_one, 0, 0);
						 title4.setTextColor(getResources().getColor(R.color.black));
						break;					
					}	               	                
                    title2.setTextColor(getResources().getColor(R.color.green));
	                break;
	            case 2:
	            	switch (currIndex) {					
					case 0:
						animation = new TranslateAnimation(0, position_one*2, 0, 0);
						 title1.setTextColor(getResources().getColor(R.color.black));
						break;
					case 1:
						animation = new TranslateAnimation(position_one, position_one*2, 0, 0);
						title2.setTextColor(getResources().getColor(R.color.black));
						break;
					case 3:
						animation = new TranslateAnimation(position_one*3, position_one*2, 0, 0);
						 title4.setTextColor(getResources().getColor(R.color.black));
						break;					
					}	               	                
                    title3.setTextColor(getResources().getColor(R.color.green));
	                
	                break;
	            case 3:
	            	switch (currIndex) {					
					case 0:
						animation = new TranslateAnimation(0, position_one*3, 0, 0);
						 title1.setTextColor(getResources().getColor(R.color.black));
						break;
					case 1:
						animation = new TranslateAnimation(position_one, position_one*3, 0, 0);
						title2.setTextColor(getResources().getColor(R.color.black));
						break;
					case 2:
						animation = new TranslateAnimation(position_one*2, position_one*3, 0, 0);
						 title3.setTextColor(getResources().getColor(R.color.black));
						break;					
					}	               	                
                    title4.setTextColor(getResources().getColor(R.color.green));
	                break;
	            }
	            
	            currIndex = arg0;
	            animation.setFillAfter(true);
	            animation.setDuration(300);
	            image.startAnimation(animation);
	        
			}}
	
}

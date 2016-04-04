package com.example.job;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JobinfoActivity extends FragmentActivity implements OnClickListener{
	/**
	 *viewpager
	 */
	ViewPager viewpager;
	FragmentPagerAdapter adapter;
	private Button button1,button2;
	private Bitmap cursor;
	private int bitwidth,off,currentItem;
	// Matrix mat=new Matrix();
	private List<Fragment> lists=new ArrayList<Fragment>();
	public void onCreate(Bundle  savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobinfo);
		ActionBar bar2=getActionBar();
  	    bar2.hide();
		intview();
		button1.getBackground().setAlpha(100);
		viewpager=(ViewPager) findViewById(R.id.id_viewpager);
		viewpager.setAdapter(adapter);
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				if(arg0==0)
				{
					button1.getBackground().setAlpha(100); //设置透明度
				    button2.getBackground().setAlpha(255); 
				}
				else
					if(arg0==1)
					{
						button1.getBackground().setAlpha(255); 
						 button2.getBackground().setAlpha(100); 
					}
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
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.jobinfo_button1)
		{
		    viewpager.setCurrentItem(0);
		    button1.getBackground().setAlpha(100); //设置透明度
		    button2.getBackground().setAlpha(255); 
		}
		if(v.getId()==R.id.jobinfo_button2)
		{
			viewpager.setCurrentItem(1);
			 button1.getBackground().setAlpha(255); 
			 button2.getBackground().setAlpha(100); 
		}
		if(v.getId()==R.id.positiondetails_image3)
		{
			System.out.println("--------------nihao---------");
		}
		
	}
	public void intview()
	{
		button1=(Button) findViewById(R.id.jobinfo_button1);
		button1.setOnClickListener(this);
		button2=(Button) findViewById(R.id.jobinfo_button2);
		button2.setOnClickListener(this);
		Positiondetails pp=new Positiondetails();
		Companyinfo1  cc=new Companyinfo1();
		lists.add(pp);
		lists.add(cc);
		adapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{

			@Override
			public int getCount()
			{
				return lists.size();
			}

			@Override
			public Fragment getItem(int position)
			{
				return lists.get(position);
			}
		};
	}	

}

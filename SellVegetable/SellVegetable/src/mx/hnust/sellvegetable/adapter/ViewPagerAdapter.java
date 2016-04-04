package mx.hnust.sellvegetable.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

public class ViewPagerAdapter extends FragmentPagerAdapter{

	private ArrayList<Fragment> mFragments; 
	public ViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
		super(fm);
		// TODO Auto-generated constructor stub
		mFragments=fragments;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub		
		
		return mFragments.get(position);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}
	
	 @Override
	    public int getItemPosition(Object object) {
	        return super.getItemPosition(object);
	    }
	
}

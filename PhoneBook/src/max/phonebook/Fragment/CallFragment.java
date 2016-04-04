package max.phonebook.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import max.phonebook.R;

public class CallFragment extends Fragment {

	private View mview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mview == null)
	    {
			mview = inflater.inflate(R.layout.callfragment, null);
	    }
	    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	    ViewGroup parent = (ViewGroup) mview.getParent();
	    if (parent != null)
	    {
	      parent.removeView(mview);
	    }
		return mview;
	}


}

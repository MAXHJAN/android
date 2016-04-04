package mx.hnust.sellvegetable.fragment;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.support.v4.app.Fragment;
import android.util.Log;

public abstract class BaseFragment extends Fragment {
    
    /** Fragment当前状态是否可见 */
    protected boolean isVisible=false;     
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
         
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
            Log.e("100", "onVisible");
        } else {
            isVisible = false;
            onInvisible();
            Log.e("100", "onVisible");
        }
    }    
     
    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();     
    }
        
    /**
     * 不可见
     */
    protected void onInvisible() {        
         
    }
     
     
    /** 
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();
}

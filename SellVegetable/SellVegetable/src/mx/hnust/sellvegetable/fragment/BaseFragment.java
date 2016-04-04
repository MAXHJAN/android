package mx.hnust.sellvegetable.fragment;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.support.v4.app.Fragment;
import android.util.Log;

public abstract class BaseFragment extends Fragment {
    
    /** Fragment��ǰ״̬�Ƿ�ɼ� */
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
     * �ɼ�
     */
    protected void onVisible() {
        lazyLoad();     
    }
        
    /**
     * ���ɼ�
     */
    protected void onInvisible() {        
         
    }
     
     
    /** 
     * �ӳټ���
     * ���������д�˷���
     */
    protected abstract void lazyLoad();
}

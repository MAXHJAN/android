package max.phonebook;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import max.phonebook.Ben.Person;
import max.phonebook.ContentResolver.PhoneInfo;

public class SpalshActivity extends Activity {
	private MyAppLication MyApp;
	private ArrayList<Person> PhoneInfos=new ArrayList<Person>();//创建一个保存获得的联系人的列表来存放数据
	private int a=0,b=0;
	
	
	
	public Handler handler=new Handler()
	{
	      @SuppressWarnings("static-access")
		@Override
	    public void handleMessage(Message msg) {
	    	// TODO Auto-generated method stub
	    	super.handleMessage(msg);
	    	switch (msg.what) {
			case 0x01:	
				if(a==1&&b==1)
				{
				MyApp.setPhoneInfos(PhoneInfos);
				skip();
				}
				break;

			}
	    }
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.activity_spalsh, null);
        setContentView(view);
        ActionBar actionBar = getActionBar();  
        actionBar.hide(); 
        GetPhoneInfos();
        AlphaAnimation anima = new AlphaAnimation(0.2f, 1.0f);
        anima.setDuration(3500);// 设置动画显示时间
        view.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
	}

	
	@SuppressWarnings("static-access")
	public void GetPhoneInfos()
	{
		MyApp=(MyAppLication) getApplication();		
		  new Thread(new Runnable() {			
			@Override
			public void run() {				
				PhoneInfos.clear();
				PhoneInfos=(ArrayList<Person>) new PhoneInfo(SpalshActivity.this).getphoneinfo("");				
				Log.e("123", "--------------"+PhoneInfos.size());
				a=1;
				handler.sendEmptyMessage(0x01);//发信息
			}
		}).start();
	 
	}
	
	private class AnimationImpl implements AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
           // welcomeImg.setBackgroundResource(R.drawable.a3);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
           // skip(); // 动画结束后跳转到别的页面
        	b=1;
        	handler.sendEmptyMessage(0x01);//发信息
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void skip() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

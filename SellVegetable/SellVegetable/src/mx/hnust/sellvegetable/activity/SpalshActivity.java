package mx.hnust.sellvegetable.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.net.GetVegetableInfo;

public class SpalshActivity extends Activity{

	private String PATH="http://121.42.202.244:8080/SellVegetableServer/getVegetableInfoServlet";
	//private String PATH="http://192.168.199.139:8080/SellVegetableServer/getVegetableInfoServlet";
	
	private String TABLE_NAME="vegetableinfo";
	
	private SharedPreferences mSharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.activity_spalsh, null);
        setContentView(view);
		getActionBar().hide();
		
		getVegetableinfo();
		
		 AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
	        anima.setDuration(4000);// 设置动画显示时间
	        view.startAnimation(anima);
	        anima.setAnimationListener(new AnimationImpl());
	}

	
	private void getVegetableinfo() {
		// TODO Auto-generated method stub		
		new GetVegetableInfo(this).getInfo(PATH,TABLE_NAME);
	}


	private class AnimationImpl implements AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
           // welcomeImg.setBackgroundResource(R.drawable.a3);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        	mSharedPreferences=getSharedPreferences("Config",MODE_PRIVATE);
        	mSharedPreferences.edit().putBoolean("isfirstcreatfrg", true).commit();
        	mSharedPreferences.edit().putBoolean("isfirstcreatfrg2", true).commit();
        	mSharedPreferences.edit().putBoolean("isfirstcreatfrg3", true).commit();
            skip(); // 动画结束后跳转到别的页面
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void skip() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}

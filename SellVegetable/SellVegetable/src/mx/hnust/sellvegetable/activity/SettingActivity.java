package mx.hnust.sellvegetable.activity;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableRow;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.ToastUtil;

public class SettingActivity extends Activity {

	private TableRow PersonInfo,EliminateCache,Security;
	private Button pExit;
	private SharedPreferences mpreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		getActionBar().hide();
		mpreferences=getSharedPreferences("Config", MODE_APPEND);
		initView();
	}

	
	
	private void initView()
	{
		PersonInfo=(TableRow) findViewById(R.id.personinfo);
		EliminateCache=(TableRow) findViewById(R.id.eliminatecache);
		pExit=(Button) findViewById(R.id.person_exit);
		//个人资料
		PersonInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SettingActivity.this,PersonInfoActivity.class));
			}
		});
		//清除缓存
		EliminateCache.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ImageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
				ImageLoader.getInstance().clearDiskCache();  // 清除本地缓存
				ToastUtil.showShortToast(SettingActivity.this, "缓存清除完毕", Gravity.BOTTOM);
			}
		});
		pExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mpreferences.edit().putBoolean("state", false).commit();
				mpreferences.edit().putString("pid", null).commit();
				mpreferences.edit().putString("name", "").commit();
				mpreferences.edit().putString("password", "").commit();
				ToastUtil.showShortToast(SettingActivity.this, "退出成功", Gravity.CENTER);
				finish();
			}
		});
		
		Security=(TableRow) findViewById(R.id.security);
		Security.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SettingActivity.this,SecurityActivity.class));
			}
		});
	}
	
	
	public void MyBack(View v)
	{

		finish();
		overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
	
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}

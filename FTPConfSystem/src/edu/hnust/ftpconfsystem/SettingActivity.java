package edu.hnust.ftpconfsystem;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingActivity extends PreferenceActivity{

	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);
        
        //setContentView(R.layout.main);  
        addPreferencesFromResource(R.xml.setting);  
        
        ActionBar actionBar = getActionBar();
		actionBar.show();
        actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this); 
        
        sp.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener(){  

			@Override
			// sharedPreferences:句柄
			// key: 改变 键值
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
				// TODO Auto-generated method stub

				//Toast.makeText(SettingActivity.this, "XXXXXXXXX", Toast.LENGTH_SHORT).show();
				
				if (key.equals("ftp_auto_sync")) {
					Boolean autoSync = sharedPreferences.getBoolean("ftp_auto_sync", false);
					Toast.makeText(SettingActivity.this, "自动同步设置为："+autoSync, Toast.LENGTH_SHORT).show();	
				}else if(key.equals("ftp_ip")){
					String ip = sharedPreferences.getString("ftp_ip", "");
					Toast.makeText(SettingActivity.this, "IP设置为："+ip, Toast.LENGTH_SHORT).show();
				}else if(key.equals("ftp_port")){
					String port = sharedPreferences.getString("ftp_port", "");
					Toast.makeText(SettingActivity.this, "端口号设置为："+port, Toast.LENGTH_SHORT).show();
				}else if(key.equals("ftp_user")){
					String user = sharedPreferences.getString("ftp_user", "");
					Toast.makeText(SettingActivity.this, "用户名设置为："+user, Toast.LENGTH_SHORT).show();
				}else if(key.equals("ftp_password")){
					String password = sharedPreferences.getString("ftp_password", "");
					Toast.makeText(SettingActivity.this, "密码设置为："+password, Toast.LENGTH_SHORT).show();
				}else if(key.equals("local_url")){
					String local_url = sharedPreferences.getString("local_url", "");
					Toast.makeText(SettingActivity.this, "本地根目录设置为："+local_url, Toast.LENGTH_SHORT).show();
				}else if(key.equals("ftp_url")){
					String ftp_url = sharedPreferences.getString("ftp_url", "");
					Toast.makeText(SettingActivity.this, "FTP根目录设置为："+ftp_url, Toast.LENGTH_SHORT).show();
				}
			}
		});
    } 
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	switch (item.getItemId()) {
    	case android.R.id.home:
    		finish();
    		return true;
    	default:
			return super.onOptionsItemSelected(item);
		}
    }
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		//setResult(RESULT_OK,(new Intent()).setAction(uid)); 
		super.finish();
	}
}

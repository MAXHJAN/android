  package com.example.updateendclient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class UpdateManager {
		/**
		 * 终端更新程序
		 * @author max
		 */
	private String mSavePath;//apk下载路径
	
	private static final int DOWNLOAD_FINISH = 2;
	
	private static final String PATH = "http://121.201.8.196/tms/version.html";

	private String mVersion_code;
	private String mVersion_name;
	private String mVersion_desc;
	private String mVersion_path;

	private Context mContext;
	private SharedPreferences preferences;

	public UpdateManager(Context context) {
		mContext = context;
		preferences=mContext.getSharedPreferences("Config",mContext.MODE_WORLD_READABLE);   
	}

	private Handler mGetVersionHandler = new Handler(){
		public void handleMessage(Message msg) {
			JSONObject jsonObject = (JSONObject) msg.obj;
			System.out.println("================================="+jsonObject.toString());
			try {
				mVersion_code = jsonObject.getString("version_code");
				mVersion_name = jsonObject.getString("version_name");
				mVersion_desc = jsonObject.getString("version_desc");
				mVersion_path = jsonObject.getString("version_path");
				Log.e("111","----------downloadAPK-----");
				if (isUpdate()){					
					// 下载
					downloadAPK();
					Log.e("111","---------------showNoticeDialog");
				}
				
			} catch (Exception e){
				e.printStackTrace();
			}
		};
	};
	
	private Handler mUpdateProgressHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what){			
			case DOWNLOAD_FINISH:				
				int serverVersion = Integer.parseInt(mVersion_code);
				preferences=mContext.getSharedPreferences("Config",mContext.MODE_WORLD_READABLE);   
				 preferences.edit().putInt("Version",serverVersion).commit();
				 String path=Environment.getExternalStorageDirectory() + File.separator+ "EndClientdownload"+"/"+"EndClient.apk";
				String str=installAPK(path);//安装程序				
					//启动程序
					startOtherApp();
					Log.e("222", "程序启动成功"+str);
				
			}
		};
	};
	
	/*
	 * 检测软件是否需要更新
	 */
	public void checkUpdate() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		JsonObjectRequest request = new JsonObjectRequest(PATH, null, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				Log.e("111","---------------onResponse"+jsonObject.toString());
				Message msg = Message.obtain();
				Log.e("111","---------------onResponse--1");
				msg.obj = jsonObject;
				Log.e("111","---------------onResponse--2");
				mGetVersionHandler.sendMessage(msg);
				Log.e("111","---------------onResponse--3");
			}
			
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println("--------------"+arg0.toString());
				Log.e("111","---------------error");
			}
		});
		requestQueue.add(request);
	}

	/*
	 * 与本地版本比较判断是否需要更新
	 */
	public boolean isUpdate() {
		int serverVersion = Integer.parseInt(mVersion_code);//服务器端版本号
		int localVersion = preferences.getInt("Version", 1);	//本地版本号	
		Log.e("111","----------本地版本号----"+localVersion);
		if (serverVersion > localVersion)
			return true;
		else
			return false;
	}


	/*
	 * 开启新线程下载文件
	 */
	private void downloadAPK() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
						String sdPath = Environment.getExternalStorageDirectory() + File.separator;
						mSavePath = sdPath + "EndClientdownload";
						
						File dir = new File(mSavePath);
						if (!dir.exists())
						{
							dir.mkdir();
						Log.e("111","---------------下载文件");
						}				
						// 下载文件
						HttpURLConnection conn = (HttpURLConnection) new URL(mVersion_path).openConnection();
						conn.connect();
						InputStream is = conn.getInputStream();
						int length = conn.getContentLength();
						Log.e("111","---------------"+length);
						File apkFile = new File(mSavePath, mVersion_name);
						
						FileOutputStream fos = new FileOutputStream(apkFile);
						Log.e("111","---------------FileOutputStream"+apkFile.getAbsolutePath());
						byte[] buffer = new byte[1024];
						while (true){
							int numread = is.read(buffer);																				
							// 下载完成
							if (numread < 0){
								mUpdateProgressHandler.sendEmptyMessage(DOWNLOAD_FINISH);
								break;
							}
							fos.write(buffer, 0, numread);
						}
						fos.close();
						is.close();
						Log.e("111","---------------close");
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}

	/*
	 * 下载到本地后执行安装
	 */
	protected String installAPK(String apkAbsolutePath) {  
        String[] args = { "pm", "install", "-r", apkAbsolutePath };  
        String result = "";  
        ProcessBuilder processBuilder = new ProcessBuilder(args);  
        Process process = null;  
        InputStream errIs = null;  
        InputStream inIs = null;  
        try {  
            ByteArrayOutputStream baos = new ByteArrayOutputStream();  
            int read = -1;  
            process = processBuilder.start();
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {  
                baos.write(read);
            }
            baos.write("/n".getBytes());  
            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {  
                baos.write(read);
            }
            byte[] data = baos.toByteArray();  
            result = new String(data);  
        } catch (IOException e) {  
            e.printStackTrace();
        } catch (Exception e) {  
            e.printStackTrace();
        } finally {  
            try {  
                if (errIs != null) {  
                    errIs.close();
                }
                if (inIs != null) {  
                    inIs.close();
                }
            } catch (IOException e) {  
                e.printStackTrace();
            }
            if (process != null) {  
                process.destroy();
            }
        }
        return result;  
    }
public void startOtherApp(){
		
	try {		
		ComponentName componentName = new ComponentName(
	            "edu.hnust.endClient",
	            "edu.hnust.endClient.AutoRun");
	        Intent intent = new Intent();
	        Log.e("222", "程序启--------");
	        intent.setComponent(componentName);
	        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        mContext.startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
		}  
	}

}

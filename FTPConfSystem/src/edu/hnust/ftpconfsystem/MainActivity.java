package edu.hnust.ftpconfsystem;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import edu.hnust.ftpconfsystem.FTP.DownLoadProgressListener;

public class MainActivity extends Activity implements View.OnClickListener,OnItemClickListener{

	private static final String TAG = "MainActivity";  
    
    public static final String FTP_CONNECT_SUCCESSS = "ftp���ӳɹ�";  
    public static final String FTP_CONNECT_FAIL = "ftp����ʧ��";  
    public static final String FTP_DISCONNECT_SUCCESS = "ftp�Ͽ�����";  
    public static final String FTP_FILE_NOTEXISTS = "ftp���ļ�������";  
 
    public static final String FTP_DOWN_LOADING = "ftp�ļ���������";  
    public static final String FTP_DOWN_SUCCESS = "ftp�ļ����سɹ�";  
    public static final String FTP_DOWN_FAIL = "ftp�ļ�����ʧ��";  
    public static final String APP_PATH = "FTPConfSystem/download/conf/data";  
    public static final int GET_CODE = 0;
    
    public static final int MSG_UPDATE = 1; 
    public static final int MSG_FINISH = 2; 
    
    SharedPreferences  sp;
    
	public String inPath ;
	public String sdPath ;
	ArrayList<DownloadFileInfo> addFileList = new ArrayList<DownloadFileInfo>();
	ListView List_View;
	
	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//		String ip = sp.getString("ftp_ip", "");
		
		ActionBar actionBar = getActionBar();
		actionBar.show();
		
		setTitle("�����ļ��ַ�ϵͳ"); 
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
        
		actionBar.setDisplayShowTitleEnabled(true);
        
        setOverflowShowingAlways();
        LoadPreferences();
        sp=PreferenceManager.getDefaultSharedPreferences(this);
        inPath=sp.getString("local_url", "null");
        sdPath= Environment.getExternalStorageDirectory()+"";
        if(inPath.equals("null")){
        	 inPath = sdPath + "/" + APP_PATH;  
        	 SharedPreferences.Editor editor = sp.edit(); 
        	 editor.putString("local_url", inPath);
        	 editor.commit();
        }
        Log.e("����·��", "����SD��·����" + inPath );
        File file = new File(inPath); 
        if (!file.exists()){
        	file.mkdir();
        }
        Button buttonDownload = (Button)findViewById(R.id.buttonDownload);
        buttonDownload.setOnClickListener(this);
        
		setProgressBarVisibility(true);
		//setProgress(progressHorizontal.getProgress() * 100);

        List_View=(ListView) findViewById(R.id.List_View);
        File_Adapter Adter=new File_Adapter(this);
        List_View.setAdapter(Adter);
        List_View.setOnItemClickListener(this);
        Adter.scanFiles(inPath);
	}
	
	List<Runnable> runnableList;
    Object lock_obj = new Object();
    ExecutorService pool = Executors. newSingleThreadExecutor();
    
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId() == R.id.buttonDownload) {
			
		    Intent intent = new Intent();  
		    intent.setClassName(this,"edu.hnust.ftpconfsystem.Cricle_ProgressActivity");//��һ��activity  
		    startActivityForResult(intent, GET_CODE); 
		                         
//			Num = 0;
//			Log.e("Create_GenThread", sp.getString("local_url", sdPath + "/" + APP_PATH)+"-----"+ sp.getString("ftp_url", "/download/conf/data"));
//			GenThread T1 = new GenThread(sp.getString("local_url", sdPath + "/" + APP_PATH), sp.getString("ftp_url", "/download/conf/data"));
//			T1.start();
//			try {
//				T1.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//			
//			runnableList = new ArrayList<Runnable>();
//					
//			if (addFileList.size() > 0) {
//				for (int i = 0; i < addFileList.size(); ++i) {
//
//					DownloadFileInfo obj = addFileList.get(i);
//					Log.e("����������AddFile", obj.toString());
//					
//					Log.e("XXXX", "�������� " + obj.getFileName());
//					//tv_info.setText("�������� " + obj.getFileName());
//	
//					runnableList.add(new DownThread(obj));
//					
//					DownThread thread = new DownThread(obj);
//					//thread.start();
//					
////					try {
////						Thread.sleep(1000);
////					} catch (InterruptedException e1) {
////						// TODO Auto-generated catch block
////						e1.printStackTrace();
////					}
//					
////					try {
////						thread.join();
////					} catch (InterruptedException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//				}
//				startAllDownloads();
//			} else {
//				Log.e("����������AddFile", "AddFileΪ��, ��������");
//			}
//			
//			//ˢ��ListView, �õ��ļ�������
//			File_Adapter ad=(File_Adapter) List_View.getAdapter();
//			ad.scanFiles(inPath); 
//			
		}
	}
	public void onItemClick(AdapterView<?> parent, View v, int positiong, long id) {//��������ļ��� 
		// TODO Auto-generated method stub
		if(positiong==0){

			File_Adapter ad=(File_Adapter) List_View.getAdapter();
			Toast.makeText(this,ad.currPath+"\n"+ inPath, 1).show();
			if(ad.currPath.equals(inPath)||inPath.equals(ad.currPath+"/")) return;
			File f=new File(ad.currPath);
			ad.scanFiles(f.getParent()); 
		}else{
			Toast.makeText(this, inPath, 1).show();
			File_Adapter da=(File_Adapter) List_View.getAdapter();
			File f=da.list.get(positiong-1);
			if(f.isDirectory()){
				da.scanFiles(f.getPath());
			}else{
				openFile(f);
			}
		}
	} 
	 private void openFile(File file){  
	      
		    Intent intent = new Intent();  
		    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		    //����intent��Action����  
		    intent.setAction(Intent.ACTION_VIEW);  
		    //��ȡ�ļ�file��MIME����  
		    String type = getMimeType(file);  
		    //����intent��data��Type���ԡ�  
		    intent.setDataAndType(Uri.fromFile(file), type);  
		    //��ת  
		    startActivity(intent);    
		      
		}  
	public  String getMimeType(final File file) {
		    String extension = getExtension(file);
		    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
		}
	 	private static String getExtension(final File file) {
		    String suffix = "";
		    String name = file.getName();
		    final int idx = name.lastIndexOf(".");
		    if (idx > 0) {
		        suffix = name.substring(idx + 1);
		    }
		    return suffix;
		}
	
	
	

	/** 
     * ��ȡ����SD��·�� 
     * @return 
     */ 
	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // �ж�sd���Ƿ����
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// ��ȡ��Ŀ¼
		}
		return sdDir.toString();
	}
   
    
	private void LoadPreferences(){
		SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor=preferences.edit();
		//String name="xixi";
		//String age="22";
		//editor.putString("name", name);
		//editor.putString("age", age);
		editor.commit();
	}
	
	private void setOverflowShowingAlways() {
    	try {
    		ViewConfiguration config = ViewConfiguration.get(this);
    		Field menuKeyField = ViewConfiguration.class
    			.getDeclaredField("sHasPermanentMenuKey");
    		menuKeyField.setAccessible(true);
    		menuKeyField.setBoolean(config, false);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}	
    }
    
	public void deleteButton(File file){
		if (file.exists()) {
			File files[] = file.listFiles(); 
			for (int i = 0; i < files.length; i++) { 
				this.deleteFile(files[i]); 
			}
			File_Adapter ad=(File_Adapter) List_View.getAdapter();
			ad.scanFiles(inPath); 
	        if (!file.exists()){
	        	file.mkdir();
	        }
		}else{
			Log.e(TAG, file+"������ ");
		}
	}
	
	public void deleteFile(File file) {
		if (file.exists()) { // �ж��ļ��Ƿ����
			if (file.isFile()) { // �ж��Ƿ����ļ�
				file.delete(); // delete()���� ��Ӧ��֪�� ��ɾ������˼;
			} else if (file.isDirectory()) { // �����������һ��Ŀ¼
				File files[] = file.listFiles(); // ����Ŀ¼�����е��ļ� files[];
				for (int i = 0; i < files.length; i++) { // ����Ŀ¼�����е��ļ�
					this.deleteFile(files[i]); // ��ÿ���ļ� ������������е���
				}
			}
			file.delete();
		} else{
			Log.e(TAG, file+"������ ");
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    	
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	switch (item.getItemId()) {
    	case android.R.id.home:
    		finish();
    		return true;
		case R.id.delete:
			Toast.makeText(this, "�������", Toast.LENGTH_SHORT).show();
			deleteButton(new File(inPath));
			return true;
		case R.id.setting:
			Toast.makeText(this, "ϵͳ����", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(this, SettingActivity.class);
			startActivityForResult(i, GET_CODE);
			return true;
		case R.id.logout:
			//Toast.makeText(this, "�˳�ϵͳ", Toast.LENGTH_SHORT).show();
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
    }

	
    @Override
    public void onBackPressed() {
    	pool.shutdown();
    	// TODO Auto-generated method stub
    	new AlertDialog.Builder(this).setTitle("�˳�")  
    	.setMessage("��ȷ��Ҫ�˳���")
        .setIcon(android.R.drawable.ic_dialog_info)  
        .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
      
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
            	// �����ȷ�ϡ���Ĳ���  
            	MainActivity.this.finish();  
            }  
        })  
        .setNegativeButton("����", new DialogInterface.OnClickListener() {  
      
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
            	// ��������ء���Ĳ���,���ﲻ����û���κβ���  
            }  
        }).show();  
    
    	//super.onBackPressed();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode == GET_CODE){//ˢ���ļ�������
    		inPath=sp.getString("local_url", sdPath + "/" + APP_PATH);
			File_Adapter ad=(File_Adapter) List_View.getAdapter();
			ad.scanFiles(inPath); 
			File file = new File(inPath); 
	        if (!file.exists()){
	        	file.mkdir();
	        }
    	}
    }
   
}

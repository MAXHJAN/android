package edu.hnust.ftpconfsystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import edu.hnust.ftpconfsystem.FTP.DownLoadProgressListener;

public class Cricle_ProgressActivity extends Activity{
	private static final String TAG = "Cricle_ProgressActivity";  
    
    public static final String FTP_CONNECT_SUCCESSS = "ftp���ӳɹ�";  
    public static final String FTP_CONNECT_FAIL = "ftp����ʧ��";  
    public static final String FTP_DISCONNECT_SUCCESS = "ftp�Ͽ�����";  
    public static final String FTP_FILE_NOTEXISTS = "ftp���ļ�������";  
 
    public static final String FTP_DOWN_LOADING = "ftp�ļ���������";  
    public static final String FTP_DOWN_SUCCESS = "ftp�ļ����سɹ�";  
    public static final String FTP_DOWN_FAIL = "ftp�ļ�����ʧ��";  
    public static final String APP_PATH = "FTPConfSystem/download/conf/data";  

    public static final int MSG_UPDATE = 1; 
    public static final int MSG_FINISH = 2; 
    ArrayList<DownloadFileInfo> addFileList = new ArrayList<DownloadFileInfo>();
    SharedPreferences  sp;
	int progress;
	RoundProgressBar mRoundProgressBar;
	List<Runnable> runnableList;
    ExecutorService pool = Executors. newSingleThreadExecutor();
    int Num=1;
    int num;
	
	private Handler mHandler = new Handler() {
        public void handleMessage (Message msg) {//�˷�����ui�߳�����
            switch(msg.what) {
            case MSG_UPDATE:
            	String str1 = msg.getData().getString("text1");//����msg���ݹ����Ĳ���   
            	Log.e("XXXXXXXXXXXXXXXX", str1+"����5%�Ľ���");
                break;
            case MSG_FINISH:
            	String str2 = msg.getData().getString("text1");//����msg���ݹ����Ĳ���   
            	Log.e("XXXXXXXXXXXXXXXX", str2+" �������!!!!");
            	num++;
            	mRoundProgressBar.setProgress(num/Num*100);
            	if(num>=Num)
            		finish();
                break;
            }
        }
    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cricle_progress);

        sp=PreferenceManager.getDefaultSharedPreferences(this);
		mRoundProgressBar=(RoundProgressBar) findViewById(R.id.roundProgressBar3);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.e("Create_GenThread", sp.getString("local_url","")+"-----"+ sp.getString("ftp_url", "/download/conf/data"));
				GenThread T1 = new GenThread(sp.getString("local_url",""), sp.getString("ftp_url", ""));
				T1.start();
				try {
					T1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				runnableList = new ArrayList<Runnable>();
						
				if (addFileList.size() > 0) {
					for (int i = 0; i < addFileList.size(); ++i) {

						DownloadFileInfo obj = addFileList.get(i);
						Log.e("����������AddFile", obj.toString());
						
						Log.e("XXXX", "�������� " + obj.getFileName());
						//tv_info.setText("�������� " + obj.getFileName());

						runnableList.add(new DownThread(obj));
						
						DownThread thread = new DownThread(obj);
						thread.start();
						
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
						
//						try {
//							thread.join();
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
					startAllDownloads();
				} else {
					Log.e("����������AddFile", "AddFileΪ��, ��������");
				}
			}

			public void startAllDownloads(){
		        for(Runnable runnable: runnableList) {
		        	pool.execute(runnable);
		        	Log.e("XXXXXXXXXXXXX", "���� һ���ļ�");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
			}
		}).start();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		
	}
	// �õ�����FileMap   ����·��  "/storage/sdcard0/FTPConfSystem"    "/"
		// �Ƚϵõ��б�
		class GenThread extends Thread{
			String serverDir ;
			String localDir ;
			public GenThread(String localDir, String serverDir){
				this.localDir = localDir;
				this.serverDir = serverDir;
			}
			public void run(){
				try {
					addFileList = new FTP(sp).generateFileAddList(localDir, serverDir);
					Num=addFileList.size();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	    Object lock_obj = new Object();
	    
		class DownThread extends Thread{
			DownloadFileInfo obj;
			public DownThread(DownloadFileInfo obj){
				this.obj = obj;
			}
			public void run(){
				synchronized (lock_obj) {
					String serverPath = obj.getServerPath();
					String localPath = obj.getLocalPath();
					String fileName = obj.getFileName();
					
					if(fileName.equals("")){
						// ��Ŀ¼��ֻ��Ҫ���ش�������
						File file = new File(localPath);
						if (file.exists()){
							file.mkdir();
							Log.e("XXXXX", "����Ŀ¼"+file.getAbsolutePath());
						}
					}else{
						// ���ļ�����Ҫ����
						DownloadFile(serverPath, localPath, fileName);
					}
				}
			}
		}
		
		//���������� String serverPath, String localPath, String fileName
		//���������� "/install.exe", "/storage/sdcard0/FTPConfSystem/", "install.exe",
		public void DownloadFile(String serverPath, String localPath, String fileName){
			// ����
			try {
				// ���ļ�����
				Log.d("DownloadFile", serverPath+"---"+ localPath+"---"+fileName);
				new FTP(sp).downloadSingleFile(serverPath, localPath, fileName,
						new DownLoadProgressListener() {

							@Override
							public void onDownLoadProgress(
									String currentStep, 
									long downProcess, File file, String fileName) {
								Log.d(TAG, currentStep);
								if (currentStep
										.equals(MainActivity.FTP_DOWN_SUCCESS)) {
									Log.d(TAG,
											"-----xiazai--successful");
									Message msg = new Message();
									msg.what = MSG_FINISH;
									msg.arg1  = (int)downProcess;
									Bundle bundle = new Bundle();    
				                    bundle.putString("text1", fileName);  //��Bundle�д������       fileName
				                    msg.setData(bundle);//mes����Bundle��������
									Cricle_ProgressActivity.this.mHandler.sendMessage(msg);
								} else if (currentStep
										.equals(MainActivity.FTP_DOWN_LOADING)) {
									Log.d(TAG, "-----xiazai---"
											+ downProcess + "%");

									Message msg = new Message();
									Bundle bundle = new Bundle();    
				                    bundle.putString("text1", fileName);  //��Bundle�д������       fileName
				                    //bundle.putString("text2","Time��2011-09-05");  
				                    msg.setData(bundle);//mes����Bundle��������   
									msg.what = MSG_UPDATE;
									msg.arg1 = (int)downProcess;
									Cricle_ProgressActivity.this.mHandler.sendMessage(msg);
								}
							}

						});

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
}

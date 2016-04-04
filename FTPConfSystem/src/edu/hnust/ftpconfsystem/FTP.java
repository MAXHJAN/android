package edu.hnust.ftpconfsystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
  
public class FTP {  
    /** 
     * ��������. 
     */  
    private String hostName;  
  
    /** 
     * �˿ں� 
     */  
    private int serverPort;  
  
    /** 
     * �û���. 
     */  
    private String userName;  
  
    /** 
     * ����. 
     */  
    private String password;  
  
    /** 
     * FTP����. 
     */  
    private FTPClient ftpClient;  
  
//    public FTP() {  
//        this.hostName = "192.168.1.107";  
//        this.serverPort = 21;  
//        this.userName = "btang";  
//        this.password = "123";  
//        this.ftpClient = new FTPClient();  
//    }  
    public FTP(SharedPreferences sp) {  
        this.hostName = sp.getString("ftp_ip", "192.168.0.0");  
        this.serverPort = Integer.parseInt(sp.getString("ftp_port","21")) ;  
        this.userName = sp.getString("ftp_user", "admin");  
        this.password = sp.getString("ftp_password", "admin");  
        Log.e("FTPinfo", hostName+"--"+serverPort+"--"+userName+"--"+password);
        this.ftpClient = new FTPClient();  
    }  
    // -------------------------------------------------------�򿪹ر�����------------------------------------------------  
    /** 
     * ��FTP����. 
     *  
     * @throws IOException 
     */  
    public void openConnect() throws IOException {  
        // ����ת��  
        ftpClient.setControlEncoding("UTF-8");  
        int reply; // ��������Ӧֵ  
        // ������������  
        ftpClient.connect(hostName, serverPort);  
        // ��ȡ��Ӧֵ  
        reply = ftpClient.getReplyCode();  
        if (!FTPReply.isPositiveCompletion(reply)) {  
            // �Ͽ�����  
            ftpClient.disconnect();  
            throw new IOException("connect fail: " + reply);  
        }  
        // ��¼��������  
        ftpClient.login(userName, password);  
        // ��ȡ��Ӧֵ  
        reply = ftpClient.getReplyCode();  
        if (!FTPReply.isPositiveCompletion(reply)) {  
            // �Ͽ�����  
            ftpClient.disconnect();  
            throw new IOException("connect fail: " + reply);  
        } else {  
            // ��ȡ��¼��Ϣ  
            FTPClientConfig config = new FTPClientConfig(ftpClient  
                    .getSystemType().split(" ")[0]);  
            config.setServerLanguageCode("zh");  
            ftpClient.configure(config);  
            // ʹ�ñ���ģʽ��ΪĬ��  
            ftpClient.enterLocalPassiveMode();  
            // �������ļ�֧��  
            ftpClient  
                    .setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);  
        }  
    }  
  
    /** 
     * �ر�FTP����. 
     *  
     * @throws IOException 
     */  
    public void closeConnect() throws IOException {  
        if (ftpClient != null) {  
            // �˳�FTP  
            ftpClient.logout();  
            // �Ͽ�����  
            ftpClient.disconnect();  
        }  
    }  
  
    // --------------------------------�ϴ������ء�ɾ������---------------------------------------------  
      
    /*
     * ���ؽ��ȼ��� 
     */  
    public interface DownLoadProgressListener {  
        public void onDownLoadProgress(String currentStep, long downProcess, File file, String fileName);  
    }  
  
    
 // ---------------------------------------------�ļ����ط���-------------------------------------------  
    
    /** 
     * ���ص����ļ�����ʵ�ֶϵ�����. 
     *  
     * @param serverPath 
     *            FtpĿ¼���ļ�·�� 
     * @param localPath 
     *            ����Ŀ¼ 
     * @param fileName        
     *            ����֮����ļ����� 
     * @param listener 
     *            ������ 
     * @throws IOException 
     */  
    public void downloadSingleFile(String serverPath, String localPath, String fileName, DownLoadProgressListener listener)  
            throws Exception {  
  
        // ��FTP����  
        try {  
            this.openConnect();  
            listener.onDownLoadProgress(MainActivity.FTP_CONNECT_SUCCESSS, 0, null, serverPath);  
        } catch (IOException e1) {  
            e1.printStackTrace();  
            listener.onDownLoadProgress(MainActivity.FTP_CONNECT_FAIL, 0, null, serverPath);  
            return;  
        }  
  
        // ���жϷ������ļ��Ƿ����  
        FTPFile[] files = ftpClient.listFiles(serverPath);  
        if (files.length == 0) {  
            listener.onDownLoadProgress(MainActivity.FTP_FILE_NOTEXISTS, 0, null, serverPath);  
            return;  
        }  
  
        //���������ļ���  
        File mkFile = new File(localPath);  
        if (!mkFile.exists()) {  
            mkFile.mkdirs();  
        }  
  
        localPath = mkFile.getAbsolutePath() + "/" + fileName;  
        // �����ж����ص��ļ��Ƿ��ܶϵ�����  
        long serverSize = files[0].getSize(); // ��ȡԶ���ļ��ĳ���  
        File localFile = new File(localPath);  
        long localSize = 0;  
        if (localFile.exists()) {  
            localSize = localFile.length(); // ��������ļ����ڣ���ȡ�����ļ��ĳ���  
            
            Log.e("FTP","localSize="+localSize);
        	Log.e("FTP","serverSize="+serverSize);
        	
            if (localSize > serverSize) {  
                File file = new File(localPath);  
                file.delete(); 
                Log.e("FTP","localSize�쳣���ȷ��������󣬱���ɾ��");
            } else if (localSize == serverSize){
            	Log.e("FTP","һ�������贫");
            	return;
            } else{
            	Log.e("FTP","�Ѵ����ˣ�������С����Ҫ��");
            }
            	
        }  
         
        //��������������ػ��������
        // ����  
        long step = serverSize / 100;  
        long process = 0;  
        long currentSize = 0;  
        // ��ʼ׼�������ļ�  
        OutputStream out = new FileOutputStream(localFile, true);  
        ftpClient.setRestartOffset(localSize);  
        Log.e("FTP","localSizeƫ������ʼ="+localSize);
        
        InputStream input = ftpClient.retrieveFileStream(serverPath);  
        byte[] b = new byte[1024];  
        int length = 0;  
        while ((length = input.read(b)) != -1) {  
            out.write(b, 0, length);  
            currentSize = currentSize + length;  
            if (currentSize / step != process) {  
                process = currentSize / step;  
                if (process % 5 == 0) {  //ÿ��%5�Ľ��ȷ���һ��  
                    listener.onDownLoadProgress(MainActivity.FTP_DOWN_LOADING, process, null, serverPath);  
                }  
            }  
        }  
        out.flush();  
        out.close();  
        input.close();  
          
        // �˷�������ȷ����������ϣ����û�д˷��������ܻ�����ֳ�������  
        if (ftpClient.completePendingCommand()) {  
            listener.onDownLoadProgress(MainActivity.FTP_DOWN_SUCCESS, 0, new File(localPath), serverPath);  
        } else {  
            listener.onDownLoadProgress(MainActivity.FTP_DOWN_FAIL, 0, null, serverPath);  
        }  
  
        // �������֮��ر�����  
        this.closeConnect();  
        listener.onDownLoadProgress(MainActivity.FTP_DISCONNECT_SUCCESS, 0, null, serverPath);  

		
        return;  
    }  
  
    private String localDir;					//��ص��ļ�Ŀ¼
   	private String serverDir;					//Ҫͬ���ķ������ļ�Ŀ¼
   	
   	private Map<String, FileInfo> localFileMap;	//�����ļ���Ϣ
   	private Map<String, FileInfo> serverFileMap;	//�������ļ���Ϣ
   	// key: String url (Ŀ¼������)
   	// FileInfo: String fileName; Date lastMofifyTime; long fileSize;

   	
    public ArrayList<DownloadFileInfo> generateFileAddList(String localDir, String serverDir) throws Exception{
    	this.localDir = localDir;
		this.serverDir = serverDir;
		this.localFileMap = new TreeMap<String, FileInfo>();
		this.serverFileMap = new TreeMap<String, FileInfo>();
		scanningLocal(new File(localDir), localFileMap);	     //��ñ���Ŀ¼�µ�ԭʼ�ļ���Ϣ
		scanningServer(serverDir, serverFileMap);    //��ȡ�������ļ���Ϣ
		printFileInfo();
		
		deletedFile(); // ��ԭ��hashmap���жԱȣ�ɾ�������ڵ�
		ArrayList<DownloadFileInfo> add = addedFile();   // ����ԭ��û�е�

		//System.out.println("validate="+validate());
		return add;
    }
    
    /**
	 * �ݹ�ɨ������Ŀ¼�����ļ����ļ����Լ���Ӧ���޸�ʱ������Է���dirFileMap��
	 * @param localPath
	 * @param dirFileMap
	 */
	private void scanningLocal(File localDir, Map<String, FileInfo> dirFileMap) {	
		//Log.e("scanningLocal", localDir.getAbsolutePath());
		String[] fileList = localDir.list();
		if (fileList != null) {		//ָ��Ŀ¼����,��Ϊ��
			for (int i = 0; i < fileList.length; ++i) {
				String pathname = localDir.getAbsolutePath() + "/" + fileList[i];
				File tempFile = new File(pathname);
				String filePath = tempFile.getAbsolutePath();
				if (tempFile.isDirectory()) {
					FileInfo f1 = new FileInfo(tempFile.lastModified());
					dirFileMap.put(filePath, f1);
					scanningLocal(tempFile, dirFileMap);
				} else {
					String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
					FileInfo f2 = new FileInfo(fileName, tempFile.lastModified(), tempFile.length());
					dirFileMap.put(filePath, f2);
				}
			}
		}
	}
	
    /**
	 * �ݹ�ɨ������Ŀ¼�����ļ����ļ����Լ���Ӧ���޸�ʱ������Է���dirFileMap��
	 * @param serverDir
	 * @param dirFileMap
	 */
    public void scanningServer(String serverPath, Map<String, FileInfo> dirFileMap) throws Exception {

    	Log.e("scanningServer", " "+serverPath);
    	try {  
            this.openConnect();  
        } catch (IOException e1) {  
            e1.printStackTrace();  
            return;  
        }  
    	
    	if(!serverPath.endsWith("/"))
    		serverPath = serverPath + "/";
    	Log.e("scanningServer", "serverPath = "+serverPath);
    	ftpClient.changeWorkingDirectory(serverPath);
    	
    	FTPFile[] files = ftpClient.listFiles();
    	
    	if (files.length != 0) {  
    		for (int i = 0; i < files.length; ++i) {
    			
    			FTPFile tempFile = files[i];
    			if(!tempFile.getName().equals(".")&&!tempFile.getName().equals("..")){
    				String pathname = serverPath + tempFile.getName();
    				if (tempFile.isDirectory()) {         //tempFile�Ǹ��ļ���
        				FileInfo f1 = new FileInfo(tempFile.getTimestamp().getTimeInMillis());
    					dirFileMap.put(pathname, f1);
    					this.closeConnect();
    					scanningServer(pathname, dirFileMap);   //Ƕ�׵ݹ飬������dirFileMap����
        			}else{
        				FileInfo f2 = new FileInfo(tempFile.getName(), tempFile.getTimestamp().getTimeInMillis(), tempFile.getSize());
    					dirFileMap.put(pathname, f2);
        			}
    			}
    		}
    	}
    	  
    }
    
    
	/**
	 * �õ���Ҫɾ�����ļ����ļ��У�������ӵ�removeList��
	 * @param 
	 */
	private void deletedFile() {
		Iterator<String> it = localFileMap.keySet().iterator(); 
		List<String> removeList = new ArrayList<String>();      //���Ҫɾ�����ļ�
		String s = this.localDir;
		if (!s.endsWith("/")){
			s = s + "/";
		}
		while (it.hasNext()) {
			String key = it.next();
			Log.e("XXX", "key=" + key);
			Log.e("XXX", "key-replace=" + key.replace(s, "/"));
			FileInfo serverValue = serverFileMap.get(key.replace(s, "/"));
			//FileInfo serverValue = serverFileMap.get(key);
			if (serverValue == null) {			                //��������hashmap��û�����key,����list
				removeList.add(key);
			}
		}
		
		//��ʽִ��ɾ��
		for (int i = 0; i < removeList.size(); ++i) {
			Log.e("Remove1", removeList.get(i));
			File dir = new File(removeList.get(i));  
	        dir.delete();
		}
		
	}

	/**
	 * �õ���Ҫ���ص��ļ����ļ��У�����ӵ�addList��
	 * @param 
	 */
	private ArrayList<DownloadFileInfo> addedFile() {
		Iterator<String> it = serverFileMap.keySet().iterator(); 
		ArrayList<DownloadFileInfo> addList = new ArrayList<DownloadFileInfo>();	     //���Ҫ���ص��ļ������Ϣ
		String ss = this.localDir;
		if (ss.endsWith("/")){
			ss = new File(ss).getAbsolutePath();
		}
		while (it.hasNext()) {
			String key = it.next();
			FileInfo serverValue = serverFileMap.get(key);
			Log.e("XXX", "key=" + key);
			Log.e("XXX", "ss-key=" + (ss+key) );
			FileInfo localValue = localFileMap.get(ss + key);
			//FileInfo localValue = localFileMap.get(key);
			if (localValue == null) {					     //����hashmap��û�����key������ӽ�ȥ
				//serverValue -----> list
				String s = this.localDir;
				Log.e("111111", s);
				Log.e("222222", key);
				String path = s + key;
				
				if (serverValue.isDir()==true){   //Ŀ¼
					Log.e("333333", path);
					DownloadFileInfo obj = new DownloadFileInfo(key, path, serverValue.getFileName());
					Log.e("Add1", obj.toString());
					addList.add(obj);
				}else{    //�ļ�
					path = path.substring(0, path.lastIndexOf("/"));
					Log.e("333333", path);
					DownloadFileInfo obj = new DownloadFileInfo(key, path, serverValue.getFileName());
					Log.e("Add1", obj.toString());
					addList.add(obj);
				}
				//localFileMap.put(key, serverValue);
			}
		}
		
		return addList;
	}
	
	/**
	 * ����ļ���Ϣ�����ڲ���
	 */
	public void printFileInfo() {
		Log.e("printFileInfo", "-----localFileMap------size="+localFileMap.size());
		Iterator<String> it = localFileMap.keySet().iterator(); 
		while (it.hasNext()) {
			String key = it.next();
			Log.e("localFileMap", key);
			Log.e("localFileMap", localFileMap.get(key).toString());
		}
		
		Log.e("printFileInfo", "-----serverFileMap------size="+serverFileMap.size());
		Iterator<String> it2 = serverFileMap.keySet().iterator(); 
		while (it2.hasNext()) {
			String key2 = it2.next();
			Log.e("serverFileMap", key2);
			Log.e("serverFileMap", serverFileMap.get(key2).toString());
		}
	}
	
	public boolean validate(){
		
		boolean result = true;
		
		Iterator iter = localFileMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String)entry.getKey();
			FileInfo val = (FileInfo)entry.getValue();
			FileInfo vall = (FileInfo)serverFileMap.get(key);
			if(val!=vall){
				result = false;
				break;
			}
		}
		
		Iterator iter2 = serverFileMap.entrySet().iterator();
		while (iter2.hasNext()) {
			Map.Entry entry2 = (Map.Entry) iter2.next();
			String key2 = (String)entry2.getKey();
			FileInfo val2 = (FileInfo)entry2.getValue();
			FileInfo vall2 = (FileInfo)localFileMap.get(key2);
			if(val2!=vall2){
				result = false;
				break;
			}
		}
		
		return result;
	}
	
  
}  

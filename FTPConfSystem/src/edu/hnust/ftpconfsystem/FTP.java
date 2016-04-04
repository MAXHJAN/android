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
     * 服务器名. 
     */  
    private String hostName;  
  
    /** 
     * 端口号 
     */  
    private int serverPort;  
  
    /** 
     * 用户名. 
     */  
    private String userName;  
  
    /** 
     * 密码. 
     */  
    private String password;  
  
    /** 
     * FTP连接. 
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
    // -------------------------------------------------------打开关闭连接------------------------------------------------  
    /** 
     * 打开FTP服务. 
     *  
     * @throws IOException 
     */  
    public void openConnect() throws IOException {  
        // 中文转码  
        ftpClient.setControlEncoding("UTF-8");  
        int reply; // 服务器响应值  
        // 连接至服务器  
        ftpClient.connect(hostName, serverPort);  
        // 获取响应值  
        reply = ftpClient.getReplyCode();  
        if (!FTPReply.isPositiveCompletion(reply)) {  
            // 断开连接  
            ftpClient.disconnect();  
            throw new IOException("connect fail: " + reply);  
        }  
        // 登录到服务器  
        ftpClient.login(userName, password);  
        // 获取响应值  
        reply = ftpClient.getReplyCode();  
        if (!FTPReply.isPositiveCompletion(reply)) {  
            // 断开连接  
            ftpClient.disconnect();  
            throw new IOException("connect fail: " + reply);  
        } else {  
            // 获取登录信息  
            FTPClientConfig config = new FTPClientConfig(ftpClient  
                    .getSystemType().split(" ")[0]);  
            config.setServerLanguageCode("zh");  
            ftpClient.configure(config);  
            // 使用被动模式设为默认  
            ftpClient.enterLocalPassiveMode();  
            // 二进制文件支持  
            ftpClient  
                    .setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);  
        }  
    }  
  
    /** 
     * 关闭FTP服务. 
     *  
     * @throws IOException 
     */  
    public void closeConnect() throws IOException {  
        if (ftpClient != null) {  
            // 退出FTP  
            ftpClient.logout();  
            // 断开连接  
            ftpClient.disconnect();  
        }  
    }  
  
    // --------------------------------上传、下载、删除监听---------------------------------------------  
      
    /*
     * 下载进度监听 
     */  
    public interface DownLoadProgressListener {  
        public void onDownLoadProgress(String currentStep, long downProcess, File file, String fileName);  
    }  
  
    
 // ---------------------------------------------文件下载方法-------------------------------------------  
    
    /** 
     * 下载单个文件，可实现断点下载. 
     *  
     * @param serverPath 
     *            Ftp目录及文件路径 
     * @param localPath 
     *            本地目录 
     * @param fileName        
     *            下载之后的文件名称 
     * @param listener 
     *            监听器 
     * @throws IOException 
     */  
    public void downloadSingleFile(String serverPath, String localPath, String fileName, DownLoadProgressListener listener)  
            throws Exception {  
  
        // 打开FTP服务  
        try {  
            this.openConnect();  
            listener.onDownLoadProgress(MainActivity.FTP_CONNECT_SUCCESSS, 0, null, serverPath);  
        } catch (IOException e1) {  
            e1.printStackTrace();  
            listener.onDownLoadProgress(MainActivity.FTP_CONNECT_FAIL, 0, null, serverPath);  
            return;  
        }  
  
        // 先判断服务器文件是否存在  
        FTPFile[] files = ftpClient.listFiles(serverPath);  
        if (files.length == 0) {  
            listener.onDownLoadProgress(MainActivity.FTP_FILE_NOTEXISTS, 0, null, serverPath);  
            return;  
        }  
  
        //创建本地文件夹  
        File mkFile = new File(localPath);  
        if (!mkFile.exists()) {  
            mkFile.mkdirs();  
        }  
  
        localPath = mkFile.getAbsolutePath() + "/" + fileName;  
        // 接着判断下载的文件是否能断点下载  
        long serverSize = files[0].getSize(); // 获取远程文件的长度  
        File localFile = new File(localPath);  
        long localSize = 0;  
        if (localFile.exists()) {  
            localSize = localFile.length(); // 如果本地文件存在，获取本地文件的长度  
            
            Log.e("FTP","localSize="+localSize);
        	Log.e("FTP","serverSize="+serverSize);
        	
            if (localSize > serverSize) {  
                File file = new File(localPath);  
                file.delete(); 
                Log.e("FTP","localSize异常，比服务器还大，本地删除");
            } else if (localSize == serverSize){
            	Log.e("FTP","一样大，无需传");
            	return;
            } else{
            	Log.e("FTP","已存在了，但本地小，需要序传");
            }
            	
        }  
         
        //其他情况正常下载或继续下载
        // 进度  
        long step = serverSize / 100;  
        long process = 0;  
        long currentSize = 0;  
        // 开始准备下载文件  
        OutputStream out = new FileOutputStream(localFile, true);  
        ftpClient.setRestartOffset(localSize);  
        Log.e("FTP","localSize偏移量开始="+localSize);
        
        InputStream input = ftpClient.retrieveFileStream(serverPath);  
        byte[] b = new byte[1024];  
        int length = 0;  
        while ((length = input.read(b)) != -1) {  
            out.write(b, 0, length);  
            currentSize = currentSize + length;  
            if (currentSize / step != process) {  
                process = currentSize / step;  
                if (process % 5 == 0) {  //每隔%5的进度返回一次  
                    listener.onDownLoadProgress(MainActivity.FTP_DOWN_LOADING, process, null, serverPath);  
                }  
            }  
        }  
        out.flush();  
        out.close();  
        input.close();  
          
        // 此方法是来确保流处理完毕，如果没有此方法，可能会造成现程序死掉  
        if (ftpClient.completePendingCommand()) {  
            listener.onDownLoadProgress(MainActivity.FTP_DOWN_SUCCESS, 0, new File(localPath), serverPath);  
        } else {  
            listener.onDownLoadProgress(MainActivity.FTP_DOWN_FAIL, 0, null, serverPath);  
        }  
  
        // 下载完成之后关闭连接  
        this.closeConnect();  
        listener.onDownLoadProgress(MainActivity.FTP_DISCONNECT_SUCCESS, 0, null, serverPath);  

		
        return;  
    }  
  
    private String localDir;					//监控的文件目录
   	private String serverDir;					//要同步的服务器文件目录
   	
   	private Map<String, FileInfo> localFileMap;	//本地文件信息
   	private Map<String, FileInfo> serverFileMap;	//服务器文件信息
   	// key: String url (目录加名字)
   	// FileInfo: String fileName; Date lastMofifyTime; long fileSize;

   	
    public ArrayList<DownloadFileInfo> generateFileAddList(String localDir, String serverDir) throws Exception{
    	this.localDir = localDir;
		this.serverDir = serverDir;
		this.localFileMap = new TreeMap<String, FileInfo>();
		this.serverFileMap = new TreeMap<String, FileInfo>();
		scanningLocal(new File(localDir), localFileMap);	     //获得本地目录下的原始文件信息
		scanningServer(serverDir, serverFileMap);    //获取服务器文件信息
		printFileInfo();
		
		deletedFile(); // 与原来hashmap进行对比，删除不存在的
		ArrayList<DownloadFileInfo> add = addedFile();   // 增加原来没有的

		//System.out.println("validate="+validate());
		return add;
    }
    
    /**
	 * 递归扫描整个目录，将文件和文件夹以及对应的修改时间等属性放入dirFileMap中
	 * @param localPath
	 * @param dirFileMap
	 */
	private void scanningLocal(File localDir, Map<String, FileInfo> dirFileMap) {	
		//Log.e("scanningLocal", localDir.getAbsolutePath());
		String[] fileList = localDir.list();
		if (fileList != null) {		//指定目录存在,不为空
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
	 * 递归扫描整个目录，将文件和文件夹以及对应的修改时间等属性放入dirFileMap中
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
    				if (tempFile.isDirectory()) {         //tempFile是个文件夹
        				FileInfo f1 = new FileInfo(tempFile.getTimestamp().getTimeInMillis());
    					dirFileMap.put(pathname, f1);
    					this.closeConnect();
    					scanningServer(pathname, dirFileMap);   //嵌套递归，继续往dirFileMap填满
        			}else{
        				FileInfo f2 = new FileInfo(tempFile.getName(), tempFile.getTimestamp().getTimeInMillis(), tempFile.getSize());
    					dirFileMap.put(pathname, f2);
        			}
    			}
    		}
    	}
    	  
    }
    
    
	/**
	 * 得到需要删除的文件或文件夹，并并添加到removeList中
	 * @param 
	 */
	private void deletedFile() {
		Iterator<String> it = localFileMap.keySet().iterator(); 
		List<String> removeList = new ArrayList<String>();      //存放要删除的文件
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
			if (serverValue == null) {			                //服务器的hashmap中没有这个key,放入list
				removeList.add(key);
			}
		}
		
		//正式执行删除
		for (int i = 0; i < removeList.size(); ++i) {
			Log.e("Remove1", removeList.get(i));
			File dir = new File(removeList.get(i));  
	        dir.delete();
		}
		
	}

	/**
	 * 得到需要下载的文件或文件夹，并添加到addList中
	 * @param 
	 */
	private ArrayList<DownloadFileInfo> addedFile() {
		Iterator<String> it = serverFileMap.keySet().iterator(); 
		ArrayList<DownloadFileInfo> addList = new ArrayList<DownloadFileInfo>();	     //存放要下载的文件相关信息
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
			if (localValue == null) {					     //本地hashmap中没有这个key，就添加进去
				//serverValue -----> list
				String s = this.localDir;
				Log.e("111111", s);
				Log.e("222222", key);
				String path = s + key;
				
				if (serverValue.isDir()==true){   //目录
					Log.e("333333", path);
					DownloadFileInfo obj = new DownloadFileInfo(key, path, serverValue.getFileName());
					Log.e("Add1", obj.toString());
					addList.add(obj);
				}else{    //文件
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
	 * 输出文件信息，用于测试
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

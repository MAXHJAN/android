package edu.hnust.ftpconfsystem;

public class DownloadFileInfo {


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DownloadFileInfo: [serverPath=" + serverPath + ", localPath="
				+ localPath + ", fileName=" + fileName + "]";
	}


	private String serverPath; 
	private String localPath;
	private String fileName;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public DownloadFileInfo(String serverPath, String localPath, String fileName){
		this.serverPath = serverPath;
		this.localPath = localPath;
		this.fileName = fileName;     // fileName=""   �����ļ���,  �������ļ�
	}
	
	//�ļ���
	public DownloadFileInfo(String serverPath, String localPath){
		this.serverPath = serverPath;
		this.localPath = localPath;
		this.fileName = "";     // fileName=""   �����ļ���,  �������ļ�
	}
	
	/**
	 * @return the serverPath
	 */
	public String getServerPath() {
		return serverPath;
	}


	/**
	 * @param serverPath the serverPath to set
	 */
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}


	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return localPath;
	}


	/**
	 * @param localPath the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}


	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}

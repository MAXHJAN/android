package edu.hnust.ftpconfsystem;


public class FileInfo {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileInfo: [fileName=" + fileName + ", lastMofifyTime="
				+ lastMofifyTime + ", fileSize=" + fileSize + ", isDir="
				+ isDir + "]";
	}
	
	private String fileName;
	private long lastMofifyTime;
	private long fileSize;
	private boolean isDir;
	
	//专用于文件夹
	public FileInfo(long lastMofifyTime){
		this.isDir = true;
		this.fileName = "";
		this.lastMofifyTime = lastMofifyTime;
		this.fileSize = 0;
	}
	
	//专用于文件
	public FileInfo(String fileName, long lastMofifyTime, long fileSize){
		this.fileName = fileName;
		this.lastMofifyTime = lastMofifyTime;
		this.fileSize = fileSize;
		this.isDir = false;
	}
	
	/**
	 * @return the isDir
	 */
	public boolean isDir() {
		return isDir;
	}
	/**
	 * @param isDir the isDir to set
	 */
	public void setDir(boolean isDir) {
		this.isDir = isDir;
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
	/**
	 * @return the lastMofifyTime
	 */
	public long getLastMofifyTime() {
		return lastMofifyTime;
	}
	/**
	 * @param lastMofifyTime the lastMofifyTime to set
	 */
	public void setLastMofifyTime(long lastMofifyTime) {
		this.lastMofifyTime = lastMofifyTime;
	}
	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	

}

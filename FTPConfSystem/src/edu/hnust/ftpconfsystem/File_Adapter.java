package edu.hnust.ftpconfsystem;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class File_Adapter extends BaseAdapter {
	public Activity activity; //创建View时必须要提供Context
	public List<File> list=new LinkedList<File>(); //数据源（文件列表）
	public String currPath;//当前路径
	public String inPath;
	private Bitmap bmp_folder,bmp_file,bmp_upfolder;
	public int getCount() {
	// TODO Auto-generated method stub
		return list.size()+1;
	}
	public Object getItem(int arg0) {
	// TODO Auto-generated method stub
		return null;
	}
	public long getItemId(int position) {
	// TODO Auto-generated method stub
		return position;
	}
	public View getView(int position, View arg1, ViewGroup arg2) {
	// TODO Auto-generated method stub
		
		View v=View.inflate(activity,R.layout.item,null);
		TextView Txt_Name=(TextView) v.findViewById(R.id.Txt_Name);
		TextView Txt_Size=(TextView) v.findViewById(R.id.Txt_Size);
		ImageView img=(ImageView) v.findViewById(R.id.image_ico);
		if(position==0){
			Txt_Name.setText("");
			Txt_Name.setTextSize(8);
			Txt_Size.setText("...");
			Txt_Size.setTextSize(18);
			img.setImageBitmap(bmp_upfolder);
		}else{

			File f=list.get(position-1);
			Txt_Name.setText(f.getName());
			Txt_Size.setText(getFilesSize(f));
			if(f.isDirectory())
			img.setImageBitmap(bmp_folder);
			else
			img.setImageBitmap(getbmp(f.getName()));
		}
		return v;
	}
	public void scanFiles(String path)
	{
		list.clear();
		File dir=new File(path);
		File[] subFiles=dir.listFiles();
		if(subFiles!=null)
		for(File f:subFiles)
		list.add(f);
		this.notifyDataSetChanged();
		currPath=path;
	}
	public File_Adapter(Activity activity,String inpath)
	{
		this.activity=activity;
		this.inPath=inpath;
		bmp_upfolder=BitmapFactory.decodeResource(activity.getResources(),R.drawable.upfolder);//返回上一层,decodeResource图片解码，source资源，解码为Bitmap类型；
		bmp_folder=BitmapFactory.decodeResource(activity.getResources(),R.drawable.folder);//文件夹
		bmp_file=BitmapFactory.decodeResource(activity.getResources(),R.drawable.file);//未知文件
	}
	public File_Adapter(Activity activity)
	{
		this.activity=activity;
		bmp_upfolder=BitmapFactory.decodeResource(activity.getResources(),R.drawable.upfolder);
		bmp_folder=BitmapFactory.decodeResource(activity.getResources(),R.drawable.folder);
		bmp_file=BitmapFactory.decodeResource(activity.getResources(),R.drawable.file);
	}
	public Bitmap getbmp(String filename){
		Bitmap bmp_temp;
		String prefix=filename.substring(filename.lastIndexOf(".")+1);  
		if(prefix.equals("txt"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.txtfile);
		else if(prefix.equals("doc"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.docfile);
		else if(prefix.equals("ppt"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.pptfile);
		else if(prefix.equals("xls"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.xlsfile);
		else if(prefix.equals("pdf"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.pdffile);
		else if(prefix.equals("jpg")||prefix.equals("png")||prefix.equals("gif"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.jpgfile);
		else if(prefix.equals("mp3"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.mp3file);
		else if(prefix.equals("mp4"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.mp4file);
		else if(prefix.equals("rar"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.rarfile);
		else if(prefix.equals("zip"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.zipfile);
		else if(prefix.equals("xml"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.xmlfile);
		else if(prefix.equals("apk"))
			return bmp_temp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.apkfile);
		else
			return bmp_file;
	}
	public static String getFilesSize(File f)
	{
		int sub_index=0;
		String show="";
		if(f.isFile()){
			long length=f.length();
			if(length>=1073741824){
				sub_index=String.valueOf((float)length/1073741824).indexOf(".");
				show=((float)length/1073741824+"000").substring(0,sub_index+3)+"GB";
			}else if(length>=1048576){
				sub_index=(String.valueOf((float)length/1048576)).indexOf(".");
				show=((float)length/1048576+"000").substring(0,sub_index+3)+"MB";
			}else if(length>=1024){
				sub_index=(String.valueOf((float)length/1024)).indexOf(".");
				show=((float)length/1024+"000").substring(0,sub_index+3)+"KB";
			}else if(length<1024)
				show=String.valueOf(length)+"B";
		}
		return show;
	}
} 
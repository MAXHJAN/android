package com.example.job;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Personinfo extends Activity implements OnClickListener{

	private ImageView im,head;
	private Button bt;	
	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果

	public static final String IMAGE_UNSPECIFIED = "image/*";
	 public void onCreate(Bundle  savedInstanceState)
    {
  	  super.onCreate(savedInstanceState);
  	  setContentView(R.layout.personinfo);
  	  ActionBar bar=getActionBar();
	  bar.hide();
	  intview();
    }
	 public void intview(){
		 im=(ImageView) findViewById(R.id.personinfo_dack);
		 im.setOnClickListener(this);
		 head=(ImageView) findViewById(R.id.imageView1);
		 head.setOnClickListener(this);
		 bt=(Button) findViewById(R.id.pf_button1);
		 bt.setOnClickListener(this);
	 }
	 
	 private void changeHeadIcon() {
	        final CharSequence[] items = { "相册", "拍照" };
	        AlertDialog dlg = new AlertDialog.Builder(Personinfo.this)
	                .setTitle("选择图片")
	                .setItems(items, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int item) {
	                        // 这里item是根据选择的方式，
	                        if (item == 0) {
	                            
	                        	Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
	            				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	            						IMAGE_UNSPECIFIED);
	            				startActivityForResult(intent, PHOTOZOOM);
	                        } else {
	                        	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	            				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
	            						Environment.getExternalStorageDirectory(), "temp.jpg")));
	            				System.out.println("=============" + Environment.getExternalStorageDirectory());
	            				startActivityForResult(intent, PHOTOHRAPH);
	                        }
	                    }
	                }).create();
	        dlg.show();
	    }
	 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == NONE)
				return;
			// 拍照
			if (requestCode == PHOTOHRAPH) {
				// 设置文件保存路径这里放在跟目录下
				File picture = new File(Environment.getExternalStorageDirectory()
						+ "/temp.jpg");
				System.out.println("------------------------" + picture.getPath());
				startPhotoZoom(Uri.fromFile(picture));
			}

			if (data == null)
				return;

			// 读取相册缩放图片
			if (requestCode == PHOTOZOOM) {
				startPhotoZoom(data.getData());
			}
			// 处理结果
			if (requestCode == PHOTORESOULT) {
				Bundle extras = data.getExtras();
				if (extras != null) {
					Bitmap photo = extras.getParcelable("data");
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 -
																			// 100)压缩文件
					head.setImageBitmap(photo);
				}

			}

			super.onActivityResult(requestCode, resultCode, data);
		}

	 public void startPhotoZoom(Uri uri) {
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
			intent.putExtra("crop", "true");
			// aspectX aspectY 是宽高的比例
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// outputX outputY 是裁剪图片宽高
			intent.putExtra("outputX", 64);
			intent.putExtra("outputY", 64);
			intent.putExtra("return-data", true);
			startActivityForResult(intent, PHOTORESOULT);
		}
	 public void onClick(View v)
	 {
		 if(v.getId()==R.id.personinfo_dack)
		 {
			 Intent intent=new Intent();
			 intent.setClass(this,Setting.class);
			 startActivity(intent);
		 }
		 if(v.getId()==R.id.pf_button1)
		 {
			 Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
		 }
		 if(v.getId()==R.id.imageView1)//上传头像
		 {
			 changeHeadIcon();
		 }
	 }
}

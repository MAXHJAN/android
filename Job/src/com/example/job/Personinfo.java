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
	public static final int PHOTOHRAPH = 1;// ����
	public static final int PHOTOZOOM = 2; // ����
	public static final int PHOTORESOULT = 3;// ���

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
	        final CharSequence[] items = { "���", "����" };
	        AlertDialog dlg = new AlertDialog.Builder(Personinfo.this)
	                .setTitle("ѡ��ͼƬ")
	                .setItems(items, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int item) {
	                        // ����item�Ǹ���ѡ��ķ�ʽ��
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
			// ����
			if (requestCode == PHOTOHRAPH) {
				// �����ļ�����·��������ڸ�Ŀ¼��
				File picture = new File(Environment.getExternalStorageDirectory()
						+ "/temp.jpg");
				System.out.println("------------------------" + picture.getPath());
				startPhotoZoom(Uri.fromFile(picture));
			}

			if (data == null)
				return;

			// ��ȡ�������ͼƬ
			if (requestCode == PHOTOZOOM) {
				startPhotoZoom(data.getData());
			}
			// ������
			if (requestCode == PHOTORESOULT) {
				Bundle extras = data.getExtras();
				if (extras != null) {
					Bitmap photo = extras.getParcelable("data");
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 -
																			// 100)ѹ���ļ�
					head.setImageBitmap(photo);
				}

			}

			super.onActivityResult(requestCode, resultCode, data);
		}

	 public void startPhotoZoom(Uri uri) {
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
			intent.putExtra("crop", "true");
			// aspectX aspectY �ǿ�ߵı���
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// outputX outputY �ǲü�ͼƬ���
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
			 Toast.makeText(this, "����ɹ�", Toast.LENGTH_SHORT).show();
		 }
		 if(v.getId()==R.id.imageView1)//�ϴ�ͷ��
		 {
			 changeHeadIcon();
		 }
	 }
}

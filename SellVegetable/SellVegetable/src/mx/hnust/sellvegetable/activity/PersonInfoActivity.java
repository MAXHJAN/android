package mx.hnust.sellvegetable.activity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.view.CircleTextImageView;

public class PersonInfoActivity extends Activity{

	private TableRow Address;
	private CircleTextImageView personHeadler;
	private static final String savePath = "/sdcard/SellVegetable/";
	private static final String savePath1 = "file:///mnt/sdcard/SellVegetable/";
	
	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果

	public static final String IMAGE_UNSPECIFIED = "image/*";
	
	private String url="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalinfo);
		getActionBar().hide();
		url=getSharedPreferences("Config", MODE_APPEND).getString("pid", "temp")+".jpg";
		init();
	}
	
	private void init()
	{	
		Address=(TableRow) findViewById(R.id.addressinfo);
		personHeadler=(CircleTextImageView) findViewById(R.id.profile_image);
		
		personHeadler.setOnClickListener(new OnClickListener() {//点击跟换头像
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeHeadIcon();
			}
		});
		Address.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(PersonInfoActivity.this,AddressInfoActivity.class));
			}
		});
	}
	
	 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ImageLoader.getInstance().displayImage(savePath1+url, personHeadler);
	}

	private void changeHeadIcon() {
	        final CharSequence[] items = { "相册", "拍照" };
	        AlertDialog dlg = new AlertDialog.Builder(PersonInfoActivity.this)
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
	            						savePath, url)));
	            				System.out.println("=============" + savePath);
	            				startActivityForResult(intent, PHOTOHRAPH);
	                        }
	                    }
	                }).create();
	        dlg.show();
	    }
	
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == NONE)
				return;
			// 拍照
			if (requestCode == PHOTOHRAPH) {
				// 设置文件保存路径这里放在跟目录下
				File picture = new File(savePath
						+url);
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
					personHeadler.setImageBitmap(photo);
					try {
						saveBitmapToFile(photo,savePath+url);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
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
	 
	 
	 
	 
	 
	 public  void saveBitmapToFile(Bitmap bitmap, String _file)
	            throws IOException {
	        BufferedOutputStream os = null;
	        try {
	            File file = new File(_file);
	            // String _filePath_file.replace(File.separatorChar +
	            // file.getName(), "");
	            int end = _file.lastIndexOf(File.separator);
	            String _filePath = _file.substring(0, end);
	            File filePath = new File(_filePath);
	            if (!filePath.exists()) {
	                filePath.mkdirs();
	            }
	            file.createNewFile();
	            os = new BufferedOutputStream(new FileOutputStream(file));
	            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
	        } finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) {
	                    Log.e("321", e.getMessage(), e);
	                }
	            }
	        }
	 }
	
	public void MyBack(View v)//返回键
	{

		finish();
		overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
	
	}
}

package mx.hnust.sellvegetable;

import java.io.File;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Application;
import android.content.Context;

public class MyAppLication extends Application{

	private static RequestQueue mQueue;
	private static ImageLoader imageloader;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initImageLoader(getApplicationContext());
		mQueue= Volley.newRequestQueue(getApplicationContext()); 
		imageloader=ImageLoader.getInstance();
	}

	
	
	public static void initImageLoader(Context context) {
		//�����ļ���Ŀ¼
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, "SellVegetable/Cache"); 
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.memoryCacheExtraOptions(480, 800) // max width, max height���������ÿ�������ļ�����󳤿� 
				.threadPoolSize(3) //�̳߳��ڼ��ص�����
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator()) //�������ʱ���URI������MD5 ����
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/�����ͨ���Լ����ڴ滺��ʵ��
				.memoryCacheSize(2 * 1024 * 1024) // �ڴ滺������ֵ
				.diskCacheSize(50 * 1024 * 1024)  // 50 Mb sd��(����)��������ֵ
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// ��ԭ�ȵ�discCache -> diskCache
				.diskCache(new UnlimitedDiscCache(cacheDir))//�Զ��建��·��  
				.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)��ʱʱ��  
				.writeDebugLogs() // Remove for release app
				.build();
		//ȫ�ֳ�ʼ��������  
		ImageLoader.getInstance().init(config);
	}
	
	public static RequestQueue getHttpRequestQueue()
	{
		return mQueue;
	}
	
	public static ImageLoader getImageLoader()
	{
		return imageloader;
	}
}

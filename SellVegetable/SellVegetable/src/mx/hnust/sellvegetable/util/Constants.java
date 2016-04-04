/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package mx.hnust.sellvegetable.util;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.graphics.Bitmap;
import mx.hnust.sellvegetable.R;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class Constants {
	public static DisplayImageOptions IM_IMAGE_OPTIONS= new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.ic_empty)
	.showImageForEmptyUri(R.drawable.ic_error)
	.showImageOnFail(R.drawable.ic_error)
	.bitmapConfig(Bitmap.Config.RGB_565)
	.imageScaleType(ImageScaleType.EXACTLY_STRETCHED) 
	.displayer(new RoundedBitmapDisplayer(5)) // default 可以设置动画，比如圆角或者渐�?
	.cacheInMemory(true)
	.cacheOnDisc(false)
	.build();
	
	
	public static DisplayImageOptions IMAGE_OPTIONS = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_stub) // ����ͼƬ�����ڼ���ʾ��ͼƬ
			.showImageForEmptyUri(R.drawable.ic_empty) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
			.showImageOnFail(R.drawable.ic_error) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
			.cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
			.cacheOnDisk(true) // �������ص�ͼƬ�Ƿ񻺴���SD����
			.displayer(new RoundedBitmapDisplayer(5)) // ���ó�Բ��ͼƬ
			.build(); // �������		
	
	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}
}

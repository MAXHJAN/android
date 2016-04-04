package mx.hnust.sellvegetable.adapter;

import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.NetworkImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class GridViewAdapter extends BaseAdapter {

	private Context context;
	private ImageLoader imageLoader;
	private ImageCache imageCache;
	private RequestQueue requestQueue;
	List<VegetableInfo> infos;
	public GridViewAdapter(Context context,List<VegetableInfo> list) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.infos=list;	
		requestQueue=MyAppLication.getHttpRequestQueue();
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
		 imageCache = new ImageCache() {			
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}
			
			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		
		imageLoader=new ImageLoader(requestQueue, imageCache);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if(view==null)
		{
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
			viewHolder.image=(NetworkImageView) view.findViewById(R.id.imageinfo);
			viewHolder.text_info=(TextView) view.findViewById(R.id.g_info);
			viewHolder.text_price=(TextView) view.findViewById(R.id.g_price);
			viewHolder.text_num=(TextView) view.findViewById(R.id.g_number);
			view.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		
		viewHolder.text_info.setText(infos.get(position).getName()+"\n"+infos.get(position).getInfo());
		viewHolder.text_price.setText("гд"+infos.get(position).getPrice()+"/kg");
		viewHolder.text_num.setText(infos.get(position).getNumber());
		Log.e("321", "====GridViewAdapter===="+infos.get(position).getImageurl());
		viewHolder.image.setTag("url");
		viewHolder.image.setDefaultImageResId(R.drawable.ic_stub);
		viewHolder.image.setErrorImageResId(R.drawable.ic_error);
		viewHolder.image.setImageUrl(infos.get(position).getImageurl(), imageLoader);		
		return view;
	}
	
	
	class ViewHolder
	{
		public NetworkImageView image;
		public TextView text_info;
	    public TextView text_price;
	    public TextView text_num;
	}

}

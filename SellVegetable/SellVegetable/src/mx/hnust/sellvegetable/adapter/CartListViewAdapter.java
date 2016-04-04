package mx.hnust.sellvegetable.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class CartListViewAdapter extends BaseAdapter {
	
	private Context mContext;
	private List<VegetableInfo> infos;
	private DisplayImageOptions options; // 设置图片显示相关参数
	private ImageLoader imageLoader;	
	
	public CartListViewAdapter(Context mContext,List<VegetableInfo> info,
			ImageLoader imageLoader,DisplayImageOptions options) {
		// TODO Auto-generated constructor stub	
		this.mContext=mContext;
		infos=info;
		this.options=options;
		this.imageLoader=imageLoader;
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
			view = LayoutInflater.from(mContext).inflate(R.layout.cartlistview_item, null);
			viewHolder.image=(ImageView) view.findViewById(R.id.cimage);
			viewHolder.name=(TextView) view.findViewById(R.id.cname);
			viewHolder.text_info=(TextView) view.findViewById(R.id.ctext_info);
			viewHolder.text_price=(TextView) view.findViewById(R.id.ctext_price);
			viewHolder.text_num=(TextView) view.findViewById(R.id.ctext_num);
			view.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		
		viewHolder.name.setText(infos.get(position).getName());
		viewHolder.text_info.setText(infos.get(position).getInfo());
		viewHolder.text_price.setText("￥"+infos.get(position).getPrice());
		viewHolder.text_num.setText(infos.get(position).getNumber());
		Log.e("321", "========="+infos.get(position).getImageurl());
		imageLoader.displayImage(infos.get(position).getImageurl(),
				viewHolder.image, options);
		return view;
	}
  
	public  class ViewHolder
	{
		public ImageView image;
		
		public TextView name;
        public TextView text_info;
        public TextView text_price;
        public TextView text_num;
	}

}

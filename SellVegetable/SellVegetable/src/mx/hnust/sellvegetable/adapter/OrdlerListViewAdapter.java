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

public class OrdlerListViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<VegetableInfo> infos;
	private DisplayImageOptions options; // 设置图片显示相关参数
	private ImageLoader imageLoader;	
	
	public OrdlerListViewAdapter(Context mContext,List<VegetableInfo> info,ImageLoader imageLoader,DisplayImageOptions options) {
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
			view = LayoutInflater.from(mContext).inflate(R.layout.ordlerlistview_item, null);
			viewHolder.image=(ImageView) view.findViewById(R.id.image);
			viewHolder.ordlerid=(TextView) view.findViewById(R.id.ordlerid);
			viewHolder.text_info=(TextView) view.findViewById(R.id.text_info);
			viewHolder.text_price=(TextView) view.findViewById(R.id.text_price);
			viewHolder.text_num=(TextView) view.findViewById(R.id.text_num);
			viewHolder.info=(TextView) view.findViewById(R.id.info);
			view.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder) view.getTag();
		}
				
		viewHolder.ordlerid.setText("订单号:"+infos.get(position).getOrdlerid());
		viewHolder.text_info.setText(infos.get(position).getName()+"\n"+infos.get(position).getInfo());
		viewHolder.text_price.setText("￥"+infos.get(position).getPrice());
		//viewHolder.text_num.setText(infos.get(position).getNumber());
		viewHolder.text_num.setText("x1");
		viewHolder.info.setText("商品数量:1  总计 ￥"+infos.get(position).getPrice());
		Log.e("321", "====123====="+infos.get(position).getImageurl());
		imageLoader.displayImage(infos.get(position).getImageurl(),
				viewHolder.image, options);
		return view;
	}
  
	public  class ViewHolder
	{
		public ImageView image;
		
		public TextView ordlerid;
        public TextView text_info;
        public TextView text_price;
        public TextView text_num;
        public TextView info;
	}
}

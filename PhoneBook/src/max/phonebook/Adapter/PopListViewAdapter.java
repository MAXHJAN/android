package max.phonebook.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import max.phonebook.R;

public class PopListViewAdapter extends BaseAdapter {

	private List<String> list;
	private Context context;
	public PopListViewAdapter(List<String> list,Context cont) {
		// TODO Auto-generated constructor stub
		this.list=list;
		context=cont;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.poplistview_item, null);
			viewHolder.menu=(TextView) convertView.findViewById(R.id.menuitem);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.menu.setText(list.get(position));
		return convertView;
	}

	class ViewHolder{		
		 TextView menu;//Í·Ïñ
	}
}

package max.phonebook.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import max.phonebook.R;
import max.phonebook.Adapter.SortAdapter.ViewHolder;
import max.phonebook.Ben.Person;

public class mListviewAdapter extends BaseAdapter{

	private List<Person> listperson = null;
	private Context mContext;
	
	 public mListviewAdapter(Context mContext, List<Person> list) {
		// TODO Auto-generated constructor stub
		 this.mContext=mContext;
		 listperson=list;
	}
	 
		/**
		 * 当ListView数据发生变化时,调用此方法来更新ListView
		 * @param list
		 */
	public void updateListView(List<Person> list){
		this.listperson = list;
		notifyDataSetChanged();
	
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listperson.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listperson.get(position);
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
		
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);
			viewHolder.Head=(ImageView) view.findViewById(R.id.lv_head);
			viewHolder.Name=(TextView) view.findViewById(R.id.lv_name);
			viewHolder.PhoneNumber=(TextView) view.findViewById(R.id.lv_phone);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		viewHolder.Head.setImageResource(R.drawable.head);
		viewHolder.Name.setText(this.listperson.get(position).getName());
		viewHolder.PhoneNumber.setText(this.listperson.get(position).getPhonenumber());
		return view;
	}

	class ViewHolder{
		
		 ImageView Head;//头像
		 TextView Name;// 姓名
		TextView PhoneNumber;// 电话
	}
}

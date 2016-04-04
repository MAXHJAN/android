package max.phonebook.Adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import max.phonebook.R;
import max.phonebook.Ben.Person;

public class PhoneListViewAdapter extends BaseAdapter{

	private ArrayList<Person> PhoneInfos=null;
	public LayoutInflater minflater;
	
	public  PhoneListViewAdapter(Context context,ArrayList<Person> mPhoneInfos) {
		// TODO Auto-generated constructor stub
		this.PhoneInfos=mPhoneInfos;
		minflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PhoneInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return PhoneInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {ViewHolder viewhold;
	if(convertView==null)
	{
		 convertView=minflater.inflate(R.layout.phonelistview_item, null);
		  viewhold=new ViewHolder();
		  viewhold.Head=(ImageView) convertView.findViewById(R.id.head);
		  viewhold.Name=(TextView) convertView.findViewById(R.id.name);
		  viewhold.PhoneNumber=(TextView) convertView.findViewById(R.id.phone);
		  convertView.setTag(viewhold);
	}
	else
	{
		viewhold=(ViewHolder) convertView.getTag();
	}
	viewhold.Head.setImageResource(R.drawable.head);
	viewhold.Name.setText(PhoneInfos.get(position).getName());
	viewhold.PhoneNumber.setText(PhoneInfos.get(position).getPhonenumber());
	
	return convertView;
	}
	class ViewHolder{
		 ImageView Head;//头像
		 TextView Name;// 姓名
		TextView PhoneNumber;// 电话
	}
}

package max.phonebook.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import max.phonebook.R;
import max.phonebook.Ben.Record;
import max.phonebook.ContentResolver.RecordInfo;
import max.phonebook.View.CustomSwipeListView;
import max.phonebook.View.SwipeItemView;
import max.phonebook.View.SwipeItemView.OnSlideListener;

public class RecordListViewAdapter extends BaseAdapter {

	private Context context;
	private List<Record> list;
	private SwipeItemView mLastSlideViewWithStatusOn;
	private LayoutInflater mInflater;

	public RecordListViewAdapter(Context mContext, List<Record> mlist) {
		// TODO Auto-generated constructor stub
		this.context = mContext;
		this.list = mlist;
		mInflater = LayoutInflater.from(context);
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		SwipeItemView slideView = (SwipeItemView) convertView;
		if (slideView == null) {

			View itemView = mInflater.inflate(R.layout.recordlistview_item, null);
			slideView = new SwipeItemView(context);
			slideView.setContentView(itemView);
			viewHolder = new ViewHolder(slideView);
			slideView.setOnSlideListener(new OnSlideListener() {

				@Override
				public void onSlide(View view, int status) {
					// TODO Auto-generated method stub
					if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
						mLastSlideViewWithStatusOn.shrink();
					}

					if (status == SLIDE_STATUS_ON) {
						mLastSlideViewWithStatusOn = (SwipeItemView) view;
					}
				}
			});
			slideView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) slideView.getTag();
		}
		if (CustomSwipeListView.mFocusedItemView != null) {
			CustomSwipeListView.mFocusedItemView.shrink();
		}
		Record item = list.get(position);
		if (item.getName() == null) {
			viewHolder.Head.setText("?");
			viewHolder.Name.setText(item.getPhone());
			viewHolder.Number.setText(item.getTime());
		} else {
			viewHolder.Head.setText(item.getName().substring(0, 1));
			viewHolder.Name.setText(item.getName());
			viewHolder.Number.setText(item.getTime() + " " + item.getPhone());
		}
		viewHolder.Type.setText(item.getType());

		viewHolder.deleteHolder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RecordInfo.deleteRecord(list.get(position).getId(), context);
				list.remove(position);
				notifyDataSetChanged();
				Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
			}
		});
		return slideView;
	}

	private static class ViewHolder {

		TextView Head;// 头像
		TextView Name;// 姓名
		TextView Number;// 电话
		TextView Type;// 类型

		public ViewGroup deleteHolder;

		public ViewHolder(View view) {
			// TODO Auto-generated constructor stub
			Head = (TextView) view.findViewById(R.id.r_lv_head);
			Name = (TextView) view.findViewById(R.id.r_lv_name);
			Number = (TextView) view.findViewById(R.id.r_lv_phone);
			Type = (TextView) view.findViewById(R.id.r_lv_type);
			deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
		}
	}
}

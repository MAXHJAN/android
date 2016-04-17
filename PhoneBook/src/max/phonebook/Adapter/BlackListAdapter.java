package max.phonebook.Adapter;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import max.phonebook.R;
import max.phonebook.Ben.BlackList;
import max.phonebook.ContentResolver.RecordInfo;
import max.phonebook.View.CustomSwipeListView;
import max.phonebook.View.SwipeItemView;
import max.phonebook.View.SwipeItemView.OnSlideListener;
import max.phonebook.db.BlackListDaoFactory;
import max.phonebook.utils.ToastUtil;

public class BlackListAdapter extends BaseAdapter {

	private List<BlackList> mlist = null;
	private Context mContext;
	private SwipeItemView mLastSlideViewWithStatusOn;

	public BlackListAdapter(Context mContext, List<BlackList> list) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		mlist = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		SwipeItemView slideView = (SwipeItemView) view;
		if (slideView == null) {
			View itemView = LayoutInflater.from(mContext).inflate(R.layout.blacklist_item, null);
			slideView = new SwipeItemView(mContext);
			slideView.setContentView(itemView);
			viewHolder = new ViewHolder();
			viewHolder.Head = (TextView) slideView.findViewById(R.id.blv_head);
			viewHolder.Name = (TextView) slideView.findViewById(R.id.blv_name);
			viewHolder.PhoneNumber = (TextView) slideView.findViewById(R.id.blv_phone);
			viewHolder.deleteHolder = (ViewGroup) slideView.findViewById(R.id.holder);
			viewHolder.Title = (TextView) slideView.findViewById(R.id.delete);
			viewHolder.Title.setText("移除");
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
		if (mlist.get(position).getName().equals(mlist.get(position).getNumber()))
			viewHolder.Head.setText("?");
		else
			viewHolder.Head.setText(mlist.get(position).getName().substring(0, 1));
		viewHolder.Name.setText(this.mlist.get(position).getName());
		viewHolder.PhoneNumber.setText(mlist.get(position).getNumber());
		
		viewHolder.deleteHolder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BlackListDaoFactory.getBlackListDao(mContext).deleteBlackList(mlist.get(position).getNumber());
				mlist.remove(position);
				notifyDataSetChanged();
				ToastUtil.showShortToast(mContext, "移除成功", Gravity.CENTER);
			}
		});
		return slideView;
	}

	class ViewHolder {

		TextView Head;// 头像
		TextView Name;// 姓名
		TextView PhoneNumber;// 电话
		TextView Title;// 电话
		
		ViewGroup deleteHolder;
	}

}

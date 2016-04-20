package com.ryg.slideview;

import java.util.List;

import com.ryg.slideview.SwipeItemView.OnSlideListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class HistoryListViewAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<HistoryListItemObject> mMessageItems;
    private Context context;
    private SwipeItemView mLastSlideViewWithStatusOn;
    public HistoryListViewAdapter(Context context,List<HistoryListItemObject> mMessageItems) {
        mInflater = LayoutInflater.from(context);
        this.mMessageItems=mMessageItems;
        this.context=context;
    }

    @Override
    public int getCount() {
        return mMessageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        SwipeItemView slideView = (SwipeItemView) convertView;
        if (slideView == null) {
            View itemView = mInflater.inflate(R.layout.history_listview_items, null);

            slideView = new SwipeItemView(context);
            slideView.setContentView(itemView);

            holder = new ViewHolder(slideView);
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
            slideView.setTag(holder);
        } else {
            holder = (ViewHolder) slideView.getTag();
        }
        HistoryListItemObject item = mMessageItems.get(position);
//        item.slideView = slideView;
        if(CustomSwipeListView.mFocusedItemView!=null){
        CustomSwipeListView.mFocusedItemView.shrink();
        }

        holder.icon.setImageResource(item.getIconRes());
        holder.title.setText(item.getTitle());
        holder.msg.setText(item.getMsg());
//        holder.time.setText(item.time);
        holder.deleteHolder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mMessageItems.remove(position);
				Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
				notifyDataSetChanged();
			}
		});

        return slideView;
    }
    private static class ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView msg;
//        public TextView time;
        public ViewGroup deleteHolder;

        ViewHolder(View view) {
            icon = (ImageView) view.findViewById(R.id.icon);
            title = (TextView) view.findViewById(R.id.title);
            msg = (TextView) view.findViewById(R.id.msg);
//            time = (TextView) view.findViewById(R.id.time);
            deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
        }
    }
}
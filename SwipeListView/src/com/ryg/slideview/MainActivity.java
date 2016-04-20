package com.ryg.slideview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener {


    private CustomSwipeListView mListView;

    private List<HistoryListItemObject> mMessageItems = new ArrayList<HistoryListItemObject>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListView = (CustomSwipeListView) findViewById(R.id.list);
        HistoryListItemObject item = null;
        for (int i = 0; i < 200; i++) {
        	item= new HistoryListItemObject();
        	if(i%3==0){
                item.setIconRes(R.drawable.share_qq);
        	}
        	if(i%3==1){
        		item.setIconRes(R.drawable.share_weixin);
        	}
        	if(i%3==2){
        		item.setIconRes(R.drawable.share_qqkongjian);
        	}
                item.setTitle("Title");
                item.setMsg("msg..."+String.valueOf(i)) ;
                mMessageItems.add(item);
            }
            
        mListView.setAdapter(new HistoryListViewAdapter(this,mMessageItems));
        mListView.setOnItemClickListener(this);
    }

//    private class SlideAdapter extends BaseAdapter {
//
//        private LayoutInflater mInflater;
//
//        SlideAdapter() {
//            super();
//            mInflater = getLayoutInflater();
//        }
//
//        @Override
//        public int getCount() {
//            return mMessageItems.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return mMessageItems.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            SlideView slideView = (SlideView) convertView;
//            if (slideView == null) {
//                View itemView = mInflater.inflate(R.layout.list_item, null);
//
//                slideView = new SlideView(MainActivity.this);
//                slideView.setContentView(itemView);
//
//                holder = new ViewHolder(slideView);
//                slideView.setOnSlideListener(MainActivity.this);
//                slideView.setTag(holder);
//            } else {
//                holder = (ViewHolder) slideView.getTag();
//            }
//            MessageItem item = mMessageItems.get(position);
//            item.slideView = slideView;
//            item.slideView.shrink();
//
//            holder.icon.setImageResource(item.iconRes);
//            holder.title.setText(item.title);
//            holder.msg.setText(item.msg);
////            holder.time.setText(item.time);
//            holder.deleteHolder.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View arg0) {
//					// TODO Auto-generated method stub
//					mMessageItems.remove(position);
//					notifyDataSetChanged();
//				}
//			});
//
//            return slideView;
//        }
//
//    }

/*    public class MessageItem {
        public int iconRes;
        public String title;
        public String msg;
        public String time;
        public SlideView slideView;
    }*/
//
//    private static class ViewHolder {
//        public ImageView icon;
//        public TextView title;
//        public TextView msg;
////        public TextView time;
//        public ViewGroup deleteHolder;
//
//        ViewHolder(View view) {
//            icon = (ImageView) view.findViewById(R.id.icon);
//            title = (TextView) view.findViewById(R.id.title);
//            msg = (TextView) view.findViewById(R.id.msg);
////            time = (TextView) view.findViewById(R.id.time);
//            deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
//        }
//    }
//
//    public void onSlide(View view, int status) {
//        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
//            mLastSlideViewWithStatusOn.shrink();
//        }
//
//        if (status == SLIDE_STATUS_ON) {
//            mLastSlideViewWithStatusOn = (SlideView) view;
//        }
//    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

   
}

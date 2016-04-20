package com.ryg.slideview;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryListItemObject implements Parcelable{
	public int iconRes;
    public String title;
    public String msg;
//    public SwipeItemView slideView;
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getIconRes() {
		return iconRes;
	}
	public void setIconRes(int iconRes) {
		this.iconRes = iconRes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
//	public SwipeItemView getSlideView() {
//		return slideView;
//	}
//	public void setSlideView(SwipeItemView slideView) {
//		this.slideView = slideView;
//	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}

package mx.hnust.sellvegetable.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import mx.hnust.sellvegetable.R;

public class ToastUtil {
	
	private static Toast toast;
	private static View view;
	private static TextView text;
	private ToastUtil() {
	}
	@SuppressLint("ShowToast")
	private static void getToast(Context context) {
	if (toast == null) {
	toast = new Toast(context);
	}
	if (view == null) {
	view = LayoutInflater.from(context).inflate(R.layout.mytoast, null);
	}
	if(text==null)
		text=(TextView) view.findViewById(R.id.toast_text);
	toast.setView(view);
	}
	public static void showShortToast(Context context, CharSequence msg,int gravity) {
	showToast(context.getApplicationContext(), msg, Toast.LENGTH_SHORT,gravity);
	}	
	public static void showLongToast(Context context, CharSequence msg,int gravity) {
	showToast(context.getApplicationContext(), msg, Toast.LENGTH_LONG,gravity);
	}
	
	private static void showToast(Context context, CharSequence msg,
	int duration,int gravity ) {
	try {
		getToast(context);		
		text.setText(msg);
		toast.setDuration(duration);
		toast.setGravity(gravity, 0, 0);
		toast.show();
	} catch (Exception e) {
	/*LogUtil.d(e.getMessage());*/
	}
	
	}

}

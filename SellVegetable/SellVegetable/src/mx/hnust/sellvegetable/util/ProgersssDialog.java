package mx.hnust.sellvegetable.util;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import mx.hnust.sellvegetable.R;



public class ProgersssDialog extends Dialog {

    
    private TextView txt;
        
    public ProgersssDialog(Context context) {
            super(context, R.style.MyDialogStyle);
           
            //加载布局文件
          View view=  View.inflate(context, R.layout.loading, null);
          //dialog添加视图
          setContentView(view);
         txt= (TextView) view.findViewById(R.id.tv_msg);
            //给图片添加动态效果
         
           
          
           this.setCancelable(false);
          
          
    }
    
    /**
     * 对话框设置内容
     * @param msg
     */
    public void setMsg(String msg){
            txt.setText(msg);
    }
   
    /**
     * 显示对话框
     */
    public void showProgersssDialog(){
    	 this.show();
}
    /**
     * 关闭对话框
     */
    public void closeProgersssDialog(){
    	this.dismiss();
}

}
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
           
            //���ز����ļ�
          View view=  View.inflate(context, R.layout.loading, null);
          //dialog�����ͼ
          setContentView(view);
         txt= (TextView) view.findViewById(R.id.tv_msg);
            //��ͼƬ��Ӷ�̬Ч��
         
           
          
           this.setCancelable(false);
          
          
    }
    
    /**
     * �Ի�����������
     * @param msg
     */
    public void setMsg(String msg){
            txt.setText(msg);
    }
   
    /**
     * ��ʾ�Ի���
     */
    public void showProgersssDialog(){
    	 this.show();
}
    /**
     * �رնԻ���
     */
    public void closeProgersssDialog(){
    	this.dismiss();
}

}
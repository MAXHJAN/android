package mx.hnust.sellvegetable.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.Constants;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class PurchaseInfoActivity extends Activity  implements OnClickListener{

	private View mview,Addrs;
	private Button bt_enter; 
	private VegetableInfo info;
	private String flag;
	private TextView mName,mPhone,mAddress,pInfo,pPrice,pNum,pAllPrice;
	private ImageView pImage;
	private String PATH1="http://121.42.202.244:8080/SellVegetableServer/getAdrInfoServlet";
	private String PATH3="http://121.42.202.244:8080/SellVegetableServer/setPurchaseServlert";
	private Bundle bun;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.purchaseinfo);
		getActionBar().hide();		
        getAddressData();
        initView();
       
	}

	public void initView()
	{
		pImage=(ImageView) findViewById(R.id.p_image);
		
		Addrs=findViewById(R.id.addrs);
		bt_enter=(Button) findViewById(R.id.p_enter);
		Addrs.setOnClickListener(this);
		bt_enter.setOnClickListener(this);
		
		mName=(TextView) findViewById(R.id.mname);
		mPhone=(TextView) findViewById(R.id.mphone);
		mAddress=(TextView) findViewById(R.id.maddress);
		bun=getIntent().getExtras();
		info=(VegetableInfo)bun .getSerializable("info");
		flag=bun.getString("flag");
		pInfo=(TextView) findViewById(R.id.p_info);
		pPrice=(TextView) findViewById(R.id.p_price);
		pNum=(TextView) findViewById(R.id.p_num);
		
		ImageLoader.getInstance().displayImage(info.getImageurl(), pImage, Constants.IMAGE_OPTIONS);
		pInfo.setText(info.getInfo());
		pPrice.setText("￥"+info.getPrice());
		pNum.setText(info.getNumber());
		
		pAllPrice=(TextView) findViewById(R.id.price);
		pAllPrice.setText("总计:￥"+info.getNumber());
	}
				
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addrs:
				
			       Intent in=new Intent();
			       in.setClass(PurchaseInfoActivity.this,AddressInfoActivity.class);
			       in.putExtra("flag", 1);
			       startActivityForResult(in, 1);
			break;
		case R.id.p_enter:
			String name=mName.getText().toString().trim();
			String phone=mPhone.getText().toString().trim();
			String address=mAddress.getText().toString().trim();
			if(name.equals("")||phone.equals("")||address.equals(""))
			{
				ToastUtil.showLongToast(PurchaseInfoActivity.this, "请将地址信息填写完整", Gravity.BOTTOM);
				return;
			}
			sendPurchaseData();
			break;
		default:
			break;
		}
	}
	
	

	private void getAddressData()
	{
		String pid=getSharedPreferences("Config", MODE_PRIVATE).getString("pid", null);
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(PurchaseInfoActivity.this);
		mpProgersssDialog.setMsg("获取地址中中...");
		JSONObject obj=new JSONObject();
		try {
			obj.put("pid", pid);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mpProgersssDialog.showProgersssDialog();
		JsonObjectRequest newRequest = new JsonObjectRequest(
			    Request.Method.POST, PATH1,
			    obj, new Response.Listener<JSONObject>() {

			        @Override
			        public void onResponse(JSONObject jsonobj) {
			             
			        	try {
							String name=jsonobj.getString("name");														
							String phone=jsonobj.getString("phone");														
							String address=jsonobj.getString("address");														
							mName.setText("收货人:"+name);			
							mPhone.setText("联系电话:"+phone);			
							mAddress.setText(address);			
							mpProgersssDialog.closeProgersssDialog();							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							mpProgersssDialog.closeProgersssDialog();
							e.printStackTrace();
						}
			        }
			    }, new Response.ErrorListener() {

			        @Override
			        public void onErrorResponse(VolleyError error) {
			        	ToastUtil.showLongToast(PurchaseInfoActivity.this, 
								"地址信息获取失败", Gravity.CENTER);
			        	mpProgersssDialog.closeProgersssDialog();
			        }
			    });
		MyAppLication.getHttpRequestQueue().add(newRequest);	
	}
	
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==1)
		{
			Bundle bl=data.getExtras();
			mName.setText("收货人:"+bl.getString("name"));
			mPhone.setText("联系电话:"+bl.getString("phone"));
			mAddress.setText(bl.getString("address"));	
			Log.e("123", "--"+bl.getString("address"));
		}
	}
	
	
	private void sendPurchaseData()
	{
		String pid=getSharedPreferences("Config", MODE_PRIVATE).getString("pid", null);
		JSONObject obj=new JSONObject();
		try {
			obj.put("pid", pid);
			obj.put("tid", flag);
			obj.put("tvid", info.getId());			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JsonObjectRequest newRequest = new JsonObjectRequest(
			    Request.Method.POST, PATH3,
			    obj, new Response.Listener<JSONObject>() {

			        @Override
			        public void onResponse(JSONObject jsonobj) {
			             
			        	try {
							String code=jsonobj.getString("code");								
							if(code.equals("200"))
							{
								ToastUtil.showLongToast(PurchaseInfoActivity.this, 
										"已购买", Gravity.CENTER);
								startActivity(new Intent(PurchaseInfoActivity.this,AllOrdlerInfoActivity.class));
								
							}else{
							
								ToastUtil.showLongToast(PurchaseInfoActivity.this, 
										"购买失败", Gravity.CENTER);
								
							}
												
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
			        }
			    }, new Response.ErrorListener() {

			        @Override
			        public void onErrorResponse(VolleyError error) {
			        	ToastUtil.showLongToast(PurchaseInfoActivity.this, 
								"网络错误，连接服务器失败", Gravity.CENTER);
			        	
			        }
			    });
		MyAppLication.getHttpRequestQueue().add(newRequest);	
	
	}

	public void MyBack(View v)
	{
		finish();
		overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}

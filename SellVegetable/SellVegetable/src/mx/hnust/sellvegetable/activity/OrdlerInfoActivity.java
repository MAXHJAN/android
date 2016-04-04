package mx.hnust.sellvegetable.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.Constants;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class OrdlerInfoActivity extends Activity implements OnClickListener{
	
	private View ordlerid;
	private Button bt_enter,bt_exit; 
	private VegetableInfo info;
	private TextView mName,mPhone,mAddress,Info,Price,Num,Oid;
	private ImageView pImage;
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/getAdrInfoServlet";
	private String PATH1="http://121.42.202.244:8080/SellVegetableServer/getAdrInfoServlet";
	private Bundle bun;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ordlerinfo);
		getActionBar().hide();
		getAddressData();
		initView();
	}

	public void initView()
	{
		pImage=(ImageView) findViewById(R.id.o_image);		
		bt_enter=(Button) findViewById(R.id.enter);
		bt_enter.setOnClickListener(this);
		bt_exit=(Button) findViewById(R.id.exit);
		bt_exit.setOnClickListener(this);
		
		mName=(TextView) findViewById(R.id.mname);
		mPhone=(TextView) findViewById(R.id.mphone);
		mAddress=(TextView) findViewById(R.id.maddress);		
		
		bun=getIntent().getExtras();
		info=(VegetableInfo)bun .getSerializable("info");	
		Info=(TextView) findViewById(R.id.o_info);
		Price=(TextView) findViewById(R.id.o_price);
		Num=(TextView) findViewById(R.id.o_num);
		Oid=(TextView) findViewById(R.id.o_id);
		
		ImageLoader.getInstance().displayImage(info.getImageurl(), pImage, Constants.IMAGE_OPTIONS);
		mName.setText(info.getName());
		Info.setText(info.getInfo());
		Price.setText("￥"+info.getPrice());
		Num.setText(info.getNumber());
		Oid.setText("订单号:"+info.getOrdlerid());
		
	}

	
	private void getAddressData()
	{
		String pid=getSharedPreferences("Config", MODE_PRIVATE).getString("pid", null);
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(OrdlerInfoActivity.this);
		mpProgersssDialog.setMsg("获取数据中...");
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
			        	ToastUtil.showLongToast(OrdlerInfoActivity.this, 
								"信息获取失败", Gravity.CENTER);
			        	mpProgersssDialog.closeProgersssDialog();
			        }
			    });
		MyAppLication.getHttpRequestQueue().add(newRequest);	
	}
	
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.enter:
			ToastUtil.showLongToast(OrdlerInfoActivity.this, "已确认收货", Gravity.CENTER);
			bt_enter.setText("已收货");
			break;
		case R.id.exit:
			ToastUtil.showLongToast(OrdlerInfoActivity.this, "此功能暂未开通", Gravity.CENTER);
			break;

		default:
			break;
		}
	}
	
	public void MyBack(View v)
	{
		finish();
		overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
	}
	
}

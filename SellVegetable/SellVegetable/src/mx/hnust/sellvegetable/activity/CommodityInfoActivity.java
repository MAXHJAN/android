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
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.Constants;
import mx.hnust.sellvegetable.util.ToastUtil;
import mx.hnust.sellvegetable.util.VegetableInfo;

public class CommodityInfoActivity extends Activity implements View.OnClickListener{

	
	private View Kefu ,mview;
	private ImageView ImageInfo;
	private TextView cInfos,cPrice,cNum;
	private Button bt_Buy,bt_Car;
	private ImageLoader imageLoader;
	private VegetableInfo info;
	private String flag;
	private SharedPreferences mPreferences;
	
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/setShoppingCartServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commodity_info);
		getActionBar().hide();
		/*getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*/
		initView();
	}

	/*public void onWindowFocusChanged(boolean hasFocus) {
    	// TODO Auto-generated method stub
    	super.onWindowFocusChanged(hasFocus);

    	Rect outRect = new Rect();  
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        mview=(View) findViewById(R.id.c_view);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) mview.getLayoutParams(); //取控件textView当前的布局参数  
        linearParams.height = outRect.top;// 控件的高强制设成20   
        mview.setLayoutParams(linearParams);
        
    }*/
	
	private void initView()
	{
		mPreferences=getSharedPreferences("Config", MODE_PRIVATE);
		imageLoader = ImageLoader.getInstance();
		Bundle bun=getIntent().getExtras();
		info=(VegetableInfo) bun.getSerializable("info");
		flag=bun.getString("flag");
		Kefu=findViewById(R.id.kefu);
		Kefu.setOnClickListener(this);	
		ImageInfo=(ImageView) findViewById(R.id.imageinfo);//图片信息
		cInfos=(TextView) findViewById(R.id.c_infos);//描述信息
		cPrice=(TextView) findViewById(R.id.c_price);//价格
		cNum=(TextView) findViewById(R.id.c_num);//数量
		cInfos.setText(info.getInfo());
		cPrice.setText("￥"+info.getPrice());
		cNum.setText(info.getNumber());
		
		
		imageLoader.displayImage(info.getImageurl(),
				ImageInfo, Constants.IM_IMAGE_OPTIONS);
		
		bt_Car=(Button) findViewById(R.id.bt_car);
		bt_Buy=(Button) findViewById(R.id.bt_buy);
		bt_Car.setOnClickListener(this);
		bt_Buy.setOnClickListener(this);
		
	}
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.kefu:
			Intent intent = new Intent(
					Intent.ACTION_CALL,
					Uri.parse("tel:"+"13617327621"));
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;	
		case R.id.bt_buy:
			if(mPreferences.getBoolean("state", false))
			{
				Bundle bl=new Bundle();
				bl.putSerializable("info", info);
				bl.putString("flag", flag);
				Intent in=new Intent(this,PurchaseInfoActivity.class);
				in.putExtras(bl);
			    startActivity(in);
			}
			    else
			    {
			    	ToastUtil.showShortToast(CommodityInfoActivity.this, "请先登录", Gravity.BOTTOM);
			    	startActivity(new Intent(CommodityInfoActivity.this,LoginActivity.class));
			    }
			break;
		case R.id.bt_car:
			if(mPreferences.getBoolean("state", false))
			      addCart();
			else
		    {
		    	ToastUtil.showShortToast(CommodityInfoActivity.this, "请先登录", Gravity.BOTTOM);
		    	startActivity(new Intent(CommodityInfoActivity.this,LoginActivity.class));
		    }
			break;		
		default:
			break;
		}
	}
	
	/**
	 * 加入购物车
	 */
	private void addCart() {
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
			    Request.Method.POST, PATH,
			    obj, new Response.Listener<JSONObject>() {

			        @Override
			        public void onResponse(JSONObject jsonobj) {
			             
			        	try {
							String code=jsonobj.getString("code");	
							
							if(code.equals("200"))
							{
								ToastUtil.showLongToast(CommodityInfoActivity.this, 
										"添加购物车成功", Gravity.CENTER);
								
								
							}else{
							
								ToastUtil.showLongToast(CommodityInfoActivity.this, 
										"添加购物车成功", Gravity.CENTER);
								
							}
												
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
			        }
			    }, new Response.ErrorListener() {

			        @Override
			        public void onErrorResponse(VolleyError error) {
			        	ToastUtil.showLongToast(CommodityInfoActivity.this, 
								"网络错误，连接服务器失败", Gravity.CENTER);
			        	
			        }
			    });
		MyAppLication.getHttpRequestQueue().add(newRequest);	
	
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
    
	public void MyBack(View v)
	{

		finish();
		overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
	
	}
}

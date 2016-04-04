package mx.hnust.sellvegetable.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.IsCellPhone;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;

public class AddressInfoActivity extends Activity{

	private RequestQueue mQueue;
	private EditText sname,sphone,addressInfo;
	private Button Submit;
	private String PATH1="http://121.42.202.244:8080/SellVegetableServer/getAdrInfoServlet";
	private String PATH2="http://121.42.202.244:8080/SellVegetableServer/setAdrInfoServlet";
	private int flag=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addressinfo);
		getActionBar().hide();
		mQueue=MyAppLication.getHttpRequestQueue();
		flag=getIntent().getIntExtra("flag", 0);
		init();
		getData();
	}

	
	private void init()
	{
		sname=(EditText) findViewById(R.id.s_name);
		sphone=(EditText) findViewById(R.id.s_phone);
		addressInfo=(EditText) findViewById(R.id.addressinfo);
		Submit=(Button) findViewById(R.id.sunmit);
		Submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name= sname.getText().toString().trim();
				String phone=sphone.getText().toString().trim();
				String address=addressInfo.getText().toString().trim();
				if(name.equals("")||phone.equals("")||address.equals("")||phone.equals(""))
				{
					ToastUtil.showLongToast(AddressInfoActivity.this, "请将信息填写完整", Gravity.BOTTOM);
					return;
				}
				if(!IsCellPhone.isCellphone(phone))
				{
					ToastUtil.showLongToast(AddressInfoActivity.this, "电话号码格式有误", Gravity.BOTTOM);
					return;
				}
				sendData(name,phone,address);
			}
		});
	}
	
	private void getData()
	{
		String pid=getSharedPreferences("Config", MODE_PRIVATE).getString("pid", null);
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(AddressInfoActivity.this);
		mpProgersssDialog.setMsg("获取中...");
		JSONObject obj=new JSONObject();
		try {
			obj.put("pid", pid);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mpProgersssDialog.showProgersssDialog();
		JsonObjectRequest newRequest = new JsonObjectRequest(
			    Request.Method.POST, PATH1, obj, new Response.Listener<JSONObject>() {

			        @Override
			        public void onResponse(JSONObject jsonobj) {
			             
			        	try {
							String name=jsonobj.getString("name");														
							String phone=jsonobj.getString("phone");														
							String address=jsonobj.getString("address");														
							sname.setText(name);			
							sphone.setText(phone);			
							addressInfo.setText(address);			
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
			        	ToastUtil.showLongToast(AddressInfoActivity.this, 
								"地址信息获取失败", Gravity.CENTER);
			        	mpProgersssDialog.closeProgersssDialog();
			        }
			    });
		mQueue.add(newRequest);	
	}
	
	
	
	/**
	 * 数据存入后台数据库
	 */
	private void sendData(final String name,final String phone,final String address)
	{

		String pid=getSharedPreferences("Config", MODE_PRIVATE).getString("pid", null);
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(AddressInfoActivity.this);
		mpProgersssDialog.setMsg("保存中...");
		mpProgersssDialog.showProgersssDialog();
		JSONObject obj=new JSONObject();
		try {
			obj.put("pid", pid);
			obj.put("name", name);
			obj.put("phone", phone);
			obj.put("address", address);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JsonObjectRequest newMissRequest = new JsonObjectRequest(
			    Request.Method.POST, PATH2,  obj, new Response.Listener<JSONObject>() {

			        @Override
			        public void onResponse(JSONObject jsonobj) {
			             
			        	try {
							String Code=jsonobj.getString("code");	
							if(Code.equals("200"))//登录成功
							{
								ToastUtil.showLongToast(AddressInfoActivity.this, 
										"保存成功", Gravity.CENTER);
								
								if(flag==1)
								{
									Bundle bl=new Bundle();
									bl.putString("name", name);
									bl.putString("phone", phone);
									bl.putString("address", address);
								    Intent intent=new Intent();
								    intent.putExtras(bl);
								    setResult(1, intent);
								    finish();
								}
								else
								{
									finish();
								}
							}else
								if(Code.equals("202"))//密码错误
								{
									ToastUtil.showLongToast(AddressInfoActivity.this, 
											"保存失败", Gravity.CENTER);
								}
							mpProgersssDialog.closeProgersssDialog();							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							ToastUtil.showLongToast(AddressInfoActivity.this, 
									"连接服务器失败", Gravity.CENTER);
							mpProgersssDialog.closeProgersssDialog();
							e.printStackTrace();
						}
			        }
			    }, new Response.ErrorListener() {

			        @Override
			        public void onErrorResponse(VolleyError error) {
			        	ToastUtil.showLongToast(AddressInfoActivity.this, 
								"连接服务器失败1", Gravity.CENTER);
			        	mpProgersssDialog.closeProgersssDialog();
			        }
			    });
		mQueue.add(newMissRequest);
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

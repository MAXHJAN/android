package mx.hnust.sellvegetable.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.content.SharedPreferences;
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

public class ResisterActivity extends Activity{

	private EditText Name,Password,Passwd,Phone;
	private Button Register;
	private SharedPreferences preferences;
	//private String PATH="http://121.42.202.244:8080/SellVegetableServer/RegisterServlet";
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/RegisterServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.register);
		getActionBar().hide();
		preferences=getSharedPreferences("Config", MODE_PRIVATE);
		init();
	}

	
	
	private void init()
	{
		Name=(EditText) findViewById(R.id.r_name);
		Password=(EditText) findViewById(R.id.r_password);
		Passwd=(EditText) findViewById(R.id.r_passwd);
		Phone=(EditText) findViewById(R.id.r_phone);
		Register=(Button) findViewById(R.id.register);
		Register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=Name.getText().toString().trim();
				String password=Password.getText().toString().trim();
				String passwd=Passwd.getText().toString().trim();
				String phone=Phone.getText().toString().trim();
				if(name.equals("")||password.equals("")||passwd.equals("")||phone.equals(""))
				{
					ToastUtil.showLongToast(ResisterActivity.this, "请将信息填写完整", Gravity.BOTTOM);
					return;
				}
				if(!password.equals(passwd))
				{
					ToastUtil.showLongToast(ResisterActivity.this, "两次密码输入不一致", Gravity.BOTTOM);
					return;
				}
				if(!IsCellPhone.isCellphone(phone))
				{
					ToastUtil.showLongToast(ResisterActivity.this, "电话号码格式有误", Gravity.BOTTOM);
					return;
				}
				
				sendRegisterData(name,password,phone);
			}
		});
	}
	
	/**
	 * 用户信息存入后台
	 * @param name
	 * @param password
	 * @param phone
	 */
	
	private void sendRegisterData(String name,String password,String phone)
	{

		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(ResisterActivity.this);
		mpProgersssDialog.setMsg("注册中...");
		mpProgersssDialog.showProgersssDialog();
		
		JSONObject obj=new JSONObject();	
		try {
			obj.put("Name", name);
			obj.put("Password", password);
			obj.put("Phone", phone);			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JsonObjectRequest newRegisterRequest = new JsonObjectRequest(
			    Request.Method.POST, PATH,
			    obj, new Response.Listener<JSONObject>() {

			        @Override
			        public void onResponse(JSONObject jsonobj) {
			             
			        	try {
							String Code=jsonobj.getString("code");
							if(Code.equals("200"))//注册成功
							{
								
								ToastUtil.showLongToast(ResisterActivity.this, 
										"注册成功"+Code, Gravity.CENTER);								
								preferences.edit().putString("pid", jsonobj.getString("pid")).commit();
								preferences.edit().putBoolean("state",true).commit();
								preferences.edit().putString("name","").commit();
								preferences.edit().putString("password","").commit();
								finish();
								
							}else
								if(Code.equals("202"))//注册失败
								{
									ToastUtil.showLongToast(ResisterActivity.this, 
											"注册失败"+Code, Gravity.CENTER);
								}
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
			        	ToastUtil.showLongToast(ResisterActivity.this, 
								"注册失败1", Gravity.CENTER);
			        	mpProgersssDialog.closeProgersssDialog();
			        }
			    });
		  MyAppLication.getHttpRequestQueue().add(newRegisterRequest);
	
		
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

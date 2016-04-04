package mx.hnust.sellvegetable.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;

public class LoginActivity extends Activity {

	private Button Login,bt_Register;
	private EditText ed_Name,ed_Pasw;
	private SharedPreferences mSharedPreferences;
	//private String PATH="http://121.42.202.244:8080/SellVegetableServer/LoginServlet";
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/LoginServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		getActionBar().hide();
		mSharedPreferences=getSharedPreferences("Config", MODE_APPEND);
		initView();
	}

	
	
	private void initView()
	{
		ed_Name=(EditText) findViewById(R.id.ed_name);
		ed_Pasw=(EditText) findViewById(R.id.ed_pasw);
		ed_Name.setText(mSharedPreferences.getString("name", ""));
		ed_Pasw.setText(mSharedPreferences.getString("password", ""));
		
		Login=(Button) findViewById(R.id.login);
		bt_Register=(Button) findViewById(R.id.bt_register);
		
		Login.setOnClickListener(new OnClickListener() {//登录
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*if(getSharedPreferences("Config", MODE_PRIVATE).getBoolean("state", false))
				{
					ToastUtil.showShortToast(LoginActivity.this, "你已登录", Gravity.BOTTOM);
					return;
				}*/
				String name=ed_Name.getText().toString().trim();
				String password=ed_Pasw.getText().toString().trim();
				
				if(name.equals("")||password.equals(""))
				{
					ToastUtil.showLongToast(LoginActivity.this, "账号或密码不能为空",Gravity.CENTER);
				}
				else
				{
					login(name,password);
				}
			}
		});
		
		bt_Register.setOnClickListener(new OnClickListener() {//注册
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,ResisterActivity.class));
			}
		});
	}
	/**
	 * 发送数据后台验证	
	 * @param name
	 * @param password
	 */
	private void login(final String name,final String password)
	{
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(LoginActivity.this);
		mpProgersssDialog.setMsg("登录中...");
		mpProgersssDialog.showProgersssDialog();
		Map<String, String> params = new HashMap<String, String>();
		params.put("Name", name);
		params.put("Password", password);
		JsonObjectRequest newMissRequest = new JsonObjectRequest(
			    Request.Method.POST, PATH,
			    new JSONObject(params), new Response.Listener<JSONObject>() {

			        @Override
			        public void onResponse(JSONObject jsonobj) {
			             
			        	try {
							String Code=jsonobj.getString("code");
							if(Code.equals("200"))//登录成功
							{
								ToastUtil.showLongToast(LoginActivity.this, 
										"登录成功", Gravity.CENTER);
								
								mSharedPreferences.edit()
								.putBoolean("state", true).commit();
								mSharedPreferences.edit().
								putString("pid", jsonobj.getString("pid")).commit();								
								mSharedPreferences.edit().putString("name", name).commit();
								mSharedPreferences.edit().putString("password", password).commit();
								finish();
							}else
								if(Code.equals("202"))//密码错误
								{
									ToastUtil.showLongToast(LoginActivity.this, 
											"密码或用户名错误", Gravity.CENTER);
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
			        	ToastUtil.showLongToast(LoginActivity.this, 
								"登录失败", Gravity.CENTER);
			        	mpProgersssDialog.closeProgersssDialog();
			        }
			    });
		  MyAppLication.getHttpRequestQueue().add(newMissRequest);
	}
	
	
	/*
	private void login2(final String name,final String password)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			JSONObject obj=	new Registernet().senddata(name, password, PATH);
			if(obj!=null)
			{
			   try {
				String code=obj.getString("code");
				Message msg=new Message();
				msg.what=0x01;
				msg.obj=code;
				handler.sendMessage(msg);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else
			{
				Message msg=new Message();
				msg.what=0x02;
				handler.sendMessage(msg);
			}
			}
		}).start();
	}*/
	
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

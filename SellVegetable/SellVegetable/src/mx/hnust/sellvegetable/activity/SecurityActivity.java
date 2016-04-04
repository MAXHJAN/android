package mx.hnust.sellvegetable.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
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
import android.widget.TableRow;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;

public class SecurityActivity extends Activity{

	private EditText ePhone,eMail;
	private TableRow Changepswd;
	private Button tEnter;
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/setPhoneEmailServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.security);
		getActionBar().hide();
		init();
	}

  public  void	init()
  {
	  ePhone=(EditText) findViewById(R.id.ephone);
	  eMail=(EditText) findViewById(R.id.eemail);
	  Changepswd=(TableRow) findViewById(R.id.changepswd);
	  Changepswd.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(SecurityActivity.this,ChangePaswdActivity.class));
		}
	});
	  
	  
	  tEnter=(Button) findViewById(R.id.enter1);
	  tEnter.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String   phone=ePhone.getText().toString().trim();
			String   email=eMail.getText().toString().trim();
			if(phone.equals("")||email.equals(""))
			{
				ToastUtil.showShortToast(SecurityActivity.this, "数据不能为空", Gravity.CENTER);
				return;
			}
			 setdata(phone,email);
		}
	});
	  
  }
	
	
  private void  setdata(String phone,String email)
	{
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(SecurityActivity.this);
		mpProgersssDialog.setMsg("提交中...");
		mpProgersssDialog.showProgersssDialog();
		
		JSONObject obj=new JSONObject();	
		try {
			obj.put("pid", getSharedPreferences("Config", MODE_APPEND).getString("pid", ""));
			obj.put("phone", phone);
			obj.put("email", email);			
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
								
								ToastUtil.showLongToast(SecurityActivity.this, 
										"提交成功", Gravity.CENTER);				
								//finish();
								
							}else							
								{
									ToastUtil.showLongToast(SecurityActivity.this, 
											"提交失败", Gravity.CENTER);
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
			        	ToastUtil.showLongToast(SecurityActivity.this, 
								"网络错误", Gravity.CENTER);
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

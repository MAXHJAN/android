package mx.hnust.sellvegetable.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import mx.hnust.sellvegetable.MyAppLication;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.util.ProgersssDialog;
import mx.hnust.sellvegetable.util.ToastUtil;

public class ChangePaswdActivity extends Activity{

	private EditText paswd1,paswd2,paswd3;
	private Button Change;
	private String PATH="http://121.42.202.244:8080/SellVegetableServer/ChangePaswdServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepassword);
		getActionBar().hide();
		init();
	}

	
	
	private void init()
	{
		paswd1=(EditText) findViewById(R.id.edpswd);
		paswd2=(EditText) findViewById(R.id.edpassword);
		paswd3=(EditText) findViewById(R.id.edpaswd);
		
		Change=(Button) findViewById(R.id.change);
		Change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String   pswd1=paswd1.getText().toString().trim();
				String   pswd2=paswd2.getText().toString().trim();
				String   pswd3=paswd3.getText().toString().trim();
				
				if(pswd1.equals("")||pswd2.equals("")||pswd3.equals(""))
				{
					ToastUtil.showShortToast(ChangePaswdActivity.this, "数据不能为空", Gravity.CENTER);
					return;
				}
				if(!pswd2.equals(pswd3))
				{
					ToastUtil.showShortToast(ChangePaswdActivity.this, "两次密码输入不一致", Gravity.CENTER);
					return;					
				}
				setdata(pswd1,pswd2,pswd3);
			}
		});
	}
	
	
	private void  setdata(String ps1,String ps2,String ps3)
	{
		final ProgersssDialog mpProgersssDialog=new ProgersssDialog(ChangePaswdActivity.this);
		mpProgersssDialog.setMsg("修改中...");
		mpProgersssDialog.showProgersssDialog();
		
		JSONObject obj=new JSONObject();	
		try {
			obj.put("pid", getSharedPreferences("Config", MODE_APPEND).getString("pid", ""));
			obj.put("ps1", ps1);
			obj.put("ps2", ps2);			
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
								
								ToastUtil.showLongToast(ChangePaswdActivity.this, 
										"修改成功", Gravity.CENTER);				
								finish();
								
							}else
								{
									ToastUtil.showLongToast(ChangePaswdActivity.this, 
											"更改失败", Gravity.CENTER);
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
			        	ToastUtil.showLongToast(ChangePaswdActivity.this, 
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

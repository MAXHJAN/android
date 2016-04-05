package max.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import max.phonebook.ContentResolver.GetPhoneInfo;

public class AddContactActivity extends Activity{

	private TextView PersonName,mTitle;
	private EditText PersonPhone,PersonEmail,PersonAddress;
	private Button SavePerson;
	private ImageView MyBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addperson);
		getActionBar().hide();
		initsView();
	}

	
	private void initsView()
	{
		mTitle=(TextView) findViewById(R.id.persontitle);
		mTitle.setText("新建联系人");
		PersonName=(TextView) findViewById(R.id.add_personname);
		PersonPhone=(EditText) findViewById(R.id.add_personphone);
		PersonEmail=(EditText) findViewById(R.id.add_personemail);
		PersonAddress=(EditText) findViewById(R.id.add_personaddress);				
		
		PersonPhone.setText(getIntent().getExtras().getString("PersonName"));	
		MyBack=(ImageView) findViewById(R.id.myback);
		
		SavePerson=(Button) findViewById(R.id.submit);
		SavePerson.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=PersonName.getText().toString().trim();
				String phone=PersonPhone.getText().toString().trim();
				String email=PersonEmail.getText().toString().trim();
				String address=PersonAddress.getText().toString().trim();
				new GetPhoneInfo(AddContactActivity.this).testAddContacts(name, phone, email, address);
				Toast.makeText(AddContactActivity.this, "保存成功", Toast.LENGTH_LONG).show();
			}
		});
		
		MyBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AddContactActivity.this,MainActivity.class));
				finish();
			}
		});
					
	}
	
	
}

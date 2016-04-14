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
import max.phonebook.Ben.Person;
import max.phonebook.ContentResolver.PhoneInfo;

public class EditContactActivity extends Activity{

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
		final Person person=(Person) getIntent().getExtras().getSerializable("person");
		mTitle=(TextView) findViewById(R.id.persontitle);
		mTitle.setText("�༭��ϵ��");
		PersonName=(TextView) findViewById(R.id.add_personname);		
		PersonPhone=(EditText) findViewById(R.id.add_personphone);
		PersonEmail=(EditText) findViewById(R.id.add_personemail);
		PersonAddress=(EditText) findViewById(R.id.add_personaddress);				
		
		PersonName.setText(person.getName());
		PersonPhone.setText(person.getPhonenumber());	
		PersonEmail.setText(person.getEmail());
		PersonAddress.setText(person.getAddress());
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
				new PhoneInfo(EditContactActivity.this).testUpdate(name, phone, email,address,person.getID());
				Toast.makeText(EditContactActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
			}
		});
		
		
		MyBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				finish();
			}
		});
	}
}

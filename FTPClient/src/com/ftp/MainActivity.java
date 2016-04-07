package com.ftp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText txtHostName;
    
    private EditText txtUserName;
    
    private EditText txtPasswrod;
    
    private Button buttonLogin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initView();
    }

    private void initView() {
        txtHostName = (EditText) findViewById(R.id.txt_host_name);
        txtUserName = (EditText) findViewById(R.id.txt_user_name);
        txtPasswrod = (EditText) findViewById(R.id.txt_password);
        buttonLogin = (Button) findViewById(R.id.button_login);
        
        buttonLogin.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FTPActivity.class);
                intent.putExtra("hostName", txtHostName.getText().toString());
                intent.putExtra("userName", txtUserName.getText().toString());
                intent.putExtra("password", txtPasswrod.getText().toString());
                startActivity(intent);
            }
        });
    }
}

package com.example.job;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Mhj extends Activity{

	public void onCreate(Bundle  savedInstanceState)
	  {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mhjmhj);
	    SharedPreferences preferences = getSharedPreferences("city1", Activity.MODE_PRIVATE);  		
	    TextView tv=(TextView) findViewById(R.id.mhj);
	    tv.setText(preferences.getString("city",null));//获取当前城市   	
	  }
}

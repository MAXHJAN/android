package max.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SettingActivity extends Activity {

	private LinearLayout mAbout;
	private LinearLayout mBlackLists;
	private LinearLayout mLeadingIn;
	private LinearLayout mLeadingOut;

	private ImageView MyBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		getActionBar().hide();
		init();
	}

	private void init() {
		mAbout = (LinearLayout) findViewById(R.id.about);
		mBlackLists = (LinearLayout) findViewById(R.id.blist);
		mLeadingIn = (LinearLayout) findViewById(R.id.leadin);
		mLeadingOut = (LinearLayout) findViewById(R.id.leadout);
		MyBack = (ImageView) findViewById(R.id.myback);

		mAbout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		mBlackLists.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SettingActivity.this, BlackListActivity.class));
			}
		});

		mLeadingIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		mLeadingOut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}

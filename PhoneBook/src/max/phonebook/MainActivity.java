package max.phonebook;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import max.phonebook.Adapter.PopListViewAdapter;
import max.phonebook.Adapter.mViewPagerAdapter;
import max.phonebook.Fragment.CallFragment;
import max.phonebook.Fragment.ContactsFragment;
import max.phonebook.Fragment.RecordFragment;



public class MainActivity extends FragmentActivity {
    

	private ViewPager mViewPager;
	private  TextView Title; 
	 private View Contact,Call,Record,view; 
	 private ImageView ContactImage,CallImage,RecordImage,PhoneMenu;
	 private int currIndex = 0;//��ǰҳ��0,1,2
	 private ArrayList<Fragment> mFragments;
	 
	 private PopupWindow popupWindow;  	  
	 private ListView lv_pop;  	
	 private List<String> menulist;
	 
	 private long firstTime=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();		
		IntView();
		Inits();
		
	}
				
	@SuppressWarnings("unused")
	private void IntView()
	{
		mViewPager=(ViewPager) findViewById(R.id.viewpager);
		Title=(TextView) findViewById(R.id.phonetitle);
		Contact=findViewById(R.id.contact);
		Call=findViewById(R.id.call);
		Record=findViewById(R.id.record);
		PhoneMenu=(ImageView) findViewById(R.id.phonemenu);
		ContactImage=(ImageView) findViewById(R.id.contactimage);
		CallImage=(ImageView) findViewById(R.id.callimage);
		RecordImage=(ImageView) findViewById(R.id.recordimage);
		ContactImage.setImageResource(R.drawable.contact);
		
		Contact.setOnClickListener(new MyOnClickListener(0));
		Call.setOnClickListener(new MyOnClickListener(1));
		Record.setOnClickListener(new MyOnClickListener(2));
		
		PhoneMenu.setOnClickListener(new OnClickListener() {//�˵���ť�ļ���
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopWindow();
			}
		});
	}
	
	
	private void Inits()
	{
		mFragments=new ArrayList<Fragment>();		
		mFragments.add(new ContactsFragment());
		mFragments.add(new CallFragment());
		mFragments.add(new RecordFragment());
		mViewPager.setAdapter(new mViewPagerAdapter(getSupportFragmentManager(), mFragments));		
		mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
		mViewPager.setCurrentItem(0);
	}

	/**
	 * ViewPager�ļ����¼�
	 * @author Administrator
	 *
	 */
	 public class MyOnPageChangeListener implements OnPageChangeListener
	    {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 0:
				if (currIndex==1) {				  
					CallImage.setImageResource(R.drawable.keybood_1);
				 }
				else
				{
					RecordImage.setImageResource(R.drawable.record_1);
				}
				ContactImage.setImageResource(R.drawable.contact);
				Title.setText("��ϵ��");
				break;

			case 1:
				if (currIndex==2) {				  
					RecordImage.setImageResource(R.drawable.record_1);
				 }
				else
				{
					ContactImage.setImageResource(R.drawable.contact_1);
				}
				CallImage.setImageResource(R.drawable.keybood);
				Title.setText("����绰");
				break;
			case 2:
				if (currIndex==0) {				  
					ContactImage.setImageResource(R.drawable.contact_1);
				 }
				else
				{
					CallImage.setImageResource(R.drawable.keybood_1);
				}
				RecordImage.setImageResource(R.drawable.record);
				Title.setText("ͨ����¼");
				break;

			}
			
			currIndex = arg0;
		}
	}
	 
	 
       /**
        * ��ť�����¼�
        * @author Administrator
        *
        */
	 public class MyOnClickListener implements View.OnClickListener {
	        private int index = 0;

	        public MyOnClickListener(int i) {
	            index = i;
	        }

	        @Override
	        public void onClick(View v) {
	            mViewPager.setCurrentItem(index);
	        }
	    };	
	    
	  
	    public void PopWindow()
		{
			 if (popupWindow == null) {  
		            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		  
		            view = layoutInflater.inflate(R.layout.popwindow, null);  
		  
		            lv_pop = (ListView) view.findViewById(R.id.poplistview);  
		            // ��������  
		            menulist = new ArrayList<String>();  
		            menulist.add("�½���ϵ��");  
		            menulist.add("ϵͳ����");  
		            menulist.add("�˳�ϵͳ");  
		           
		  
		            PopListViewAdapter Adapter = new PopListViewAdapter(menulist,MainActivity.this);  
		            lv_pop.setAdapter(Adapter);  
		            // ����һ��PopuWidow����  
		            popupWindow = new PopupWindow(view, 400, 400);  
		        }  
				
			 // ʹ��ۼ�  
		        popupWindow.setFocusable(true);  
		        // ����������������ʧ  
		        popupWindow.setOutsideTouchable(true); 
		        popupWindow.setBackgroundDrawable(new BitmapDrawable());  
		        popupWindow.showAsDropDown(PhoneMenu,-60,30);
		        
		        
		        
		        lv_pop.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Bundle bun=new Bundle();
							bun.putString("PersonName", "");
							Intent inten=new Intent(MainActivity.this,AddContactActivity.class);
							inten.putExtras(bun);
							startActivity(inten);
							if(popupWindow!=null)
								popupWindow.dismiss();
							break;
						case 1:
							if(popupWindow!=null)
								popupWindow.dismiss();
							break;
						case 2:
							if(popupWindow!=null)
								popupWindow.dismiss();
							System.exit(0);
							break;
			
						}
					}
				});
		}
	    
	    
	    @Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			if(mViewPager!=null)
				mViewPager=null;
			if( Contact!=null)
				Contact=null;
			if(Call!=null)
				Call=null;
			if(Record!=null)
				Record=null;
			if(mFragments!=null)
				mFragments=null;
			if(ContactImage!=null)
				ContactImage=null;
			if(CallImage!=null)
				CallImage=null;
			if(RecordImage!=null)
				RecordImage=null;
			
			if(popupWindow!=null)
				popupWindow=null;
			if(lv_pop!=null)
				lv_pop=null;
			if(view!=null)
				view=null;
			if(menulist!=null)
				menulist=null;
		}
	    
	    
	    public boolean onKeyUp(int keyCode, KeyEvent event) 
	    {
	   	 if(keyCode==KeyEvent.KEYCODE_BACK)
	   	 {
	   		 if(System.currentTimeMillis()-firstTime>3000)
	   		 {
	   			 Toast.makeText(this, "�ٰ�һ���˳�����...",Toast.LENGTH_SHORT).show();
	   			 firstTime=System.currentTimeMillis();
	   		 }
	   		 else
	   		 {
	   			 Intent intent2=new Intent();
	   			 intent2.setAction(Intent.ACTION_MAIN);
	   			 intent2.addCategory(Intent.CATEGORY_HOME);
	   			 startActivity(intent2);
	   		 }
	              return true;
	   	 }
	   	 return super.onKeyUp(keyCode, event);
	    }

}

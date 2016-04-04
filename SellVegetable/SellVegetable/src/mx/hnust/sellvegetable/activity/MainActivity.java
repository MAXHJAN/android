package mx.hnust.sellvegetable.activity;


import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import mx.hnust.sellvegetable.R;
import mx.hnust.sellvegetable.fragment.FragmentPageFour;
import mx.hnust.sellvegetable.fragment.FragmentPageOne;
import mx.hnust.sellvegetable.fragment.FragmentPageTwo;

public class MainActivity extends FragmentActivity implements OnClickListener{
	
    private View home,classify,shoppingcart,personalcenter,mview; 
    private ImageView homeimage,classifyimage,shoppingcartimage,personalcenterimage;
    @SuppressWarnings("unused")
	private TextView hometitle,classifytitle,shoppingcarttitle,personalcentertitle;
    private FragmentTransaction beginTransaction;
    public ImageLoader mImageLoader;
    private int flag=0;
    Resources resources;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();//隐藏AcionBar
		/*getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*/
        mImageLoader=ImageLoader.getInstance();
		resources = getResources();
		initView();
	}
	
	
	/*public void onWindowFocusChanged(boolean hasFocus) {
    	// TODO Auto-generated method stub
    	super.onWindowFocusChanged(hasFocus);

    	Rect outRect = new Rect();  
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        mview=(View) findViewById(R.id.view);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) mview.getLayoutParams(); //取控件textView当前的布局参数  
        linearParams.height = outRect.top;// 控件的高强制设成20   
        mview.setLayoutParams(linearParams);
        
    }*/

	/** 
     * 初始化组件 
     */  
    private void initView(){
    	home=findViewById(R.id.home);
    	classify=findViewById(R.id.classify);
    	shoppingcart=findViewById(R.id.shoppingcart);
    	personalcenter=findViewById(R.id.personalcenter); 
    	homeimage=(ImageView) findViewById(R.id.homeimage);
    	classifyimage=(ImageView) findViewById(R.id.classifyimage);
    	shoppingcartimage=(ImageView) findViewById(R.id.shoppingcartimage);
    	personalcenterimage=(ImageView) findViewById(R.id.personalcenterimage);
    	 
    	hometitle=(TextView) findViewById(R.id.hometitle);
    	classifytitle=(TextView) findViewById(R.id.classifytitle);
    	shoppingcarttitle=(TextView) findViewById(R.id.shoppingcarttitle);
    	personalcentertitle=(TextView) findViewById(R.id.personalcentertitle);
    	
    	homeimage.setImageResource(R.drawable.ic_home1);
    	hometitle.setTextColor(getResources().getColor(R.color.green));
    	
      beginTransaction=getSupportFragmentManager().beginTransaction();
      beginTransaction.replace(R.id.mainview, new FragmentPageOne()).commit();
      homeimage.setImageResource(R.drawable.ic_home1);
      home.setOnClickListener(this);
      classify.setOnClickListener(this);
      shoppingcart.setOnClickListener(this);
      personalcenter.setOnClickListener(this);
    }  
    
    public void onClick(View v)
    {
    	switch (v.getId()) {
		case R.id.home:	
			switch (flag) {
			case 1:
				classifyimage.setImageResource(R.drawable.ic_classify);
				classifytitle.setTextColor(resources.getColor(R.color.black));
				break;
			case 2:
				shoppingcartimage.setImageResource(R.drawable.ic_shoppingcart);
				shoppingcarttitle.setTextColor(resources.getColor(R.color.black));
				break;
			case 3:
				personalcenterimage.setImageResource(R.drawable.ic_personalcenter);
				personalcentertitle.setTextColor(resources.getColor(R.color.black));
	           break;
			}
			homeimage.setImageResource(R.drawable.ic_home1);
			hometitle.setTextColor(resources.getColor(R.color.green));
				flag=0;		
			 beginTransaction=getSupportFragmentManager().beginTransaction();
			beginTransaction.replace(R.id.mainview, new FragmentPageOne()).commit();			
			break;
			
		case R.id.classify:
			
			switch (flag) {
			case 0:
				homeimage.setImageResource(R.drawable.ic_home);
				hometitle.setTextColor(resources.getColor(R.color.black));
				break;
			case 2:
				shoppingcartimage.setImageResource(R.drawable.ic_shoppingcart);
				shoppingcarttitle.setTextColor(resources.getColor(R.color.black));
				break;
			case 3:
				personalcenterimage.setImageResource(R.drawable.ic_personalcenter);
				personalcentertitle.setTextColor(resources.getColor(R.color.black));
	           break;

			}
			classifyimage.setImageResource(R.drawable.ic_classify1);
			classifytitle.setTextColor(resources.getColor(R.color.green));
				flag=1;	
			 beginTransaction=getSupportFragmentManager().beginTransaction();
					beginTransaction.replace(R.id.mainview, new FragmentPageTwo()).commit();
			break;
			
		case R.id.shoppingcart:
			
			/*switch (flag) {
			case 0:
				homeimage.setImageResource(R.drawable.ic_home);
				hometitle.setTextColor(resources.getColor(R.color.black));
				break;
			case 1:
				classifyimage.setImageResource(R.drawable.ic_classify);
				classifytitle.setTextColor(resources.getColor(R.color.black));
				break;
			case 3:
				personalcenterimage.setImageResource(R.drawable.ic_personalcenter);
				personalcentertitle.setTextColor(resources.getColor(R.color.black));
	           break;
			}*/
			//homeimage.setImageResource(R.drawable.ic_home1);
			//hometitle.setTextColor(resources.getColor(R.color.green));
			//	flag=2;	
			 startActivity(new Intent(MainActivity.this,ShoppingCartActivity.class));
			break;
		case R.id.personalcenter:
			switch (flag) {
			case 0:
				homeimage.setImageResource(R.drawable.ic_home);
				hometitle.setTextColor(resources.getColor(R.color.black));
				break;
			case 1:
				classifyimage.setImageResource(R.drawable.ic_classify);
				classifytitle.setTextColor(resources.getColor(R.color.black));
				break;
			case 2:
				shoppingcartimage.setImageResource(R.drawable.ic_shoppingcart);
				shoppingcarttitle.setTextColor(resources.getColor(R.color.black));
	           break;
			}
			personalcenterimage.setImageResource(R.drawable.ic_personalcenter1);
			personalcentertitle.setTextColor(resources.getColor(R.color.green));
				flag=3;	
			 beginTransaction=getSupportFragmentManager().beginTransaction();
			beginTransaction.replace(R.id.mainview, new FragmentPageFour()).commit();
			break;

		default:
			break;
		}
    }
    
    public  ImageLoader getImageLoader()
    {
    	
    	return mImageLoader;
    }
        
}

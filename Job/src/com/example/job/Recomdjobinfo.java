package com.example.job;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Recomdjobinfo extends Fragment{

	private TextView textview;
	private Button bt1,bt2;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.recommendjobinfo, null);
		textview=(TextView) view.findViewById(R.id.rdj_text);
		 textview.setText("3年以上Web项目或产品的设计与开发经验，具有终端安全防护类产品开发经验者优先 \n\n"
		 		+ "1、精通Java编程，能够熟练应用Spring、Struts、Hibernate等主流开发框架 \n\n"
		 		+ "2、熟悉多线程并发技术和异步调用技术，并有相关经验\n\n"
		 		+ "3、熟悉HTML、XML、JavaScript、AJAX、JQuery、ExtJs前端开发技术；\n\n"
		 		+ "4、能够熟练应用MySQL，拥有优秀的数据库设计能力 \n\n"
		 		+ "5、深刻理解OOA/OOD/OOP思想,熟练掌握多种常用的设计模式 \n\n"
		 		+ "6、较强的自我学习能力和分析解决问题的能力，具备良好的文档编制习惯和代码书写规范 \n\n"
		 		+ "7、强烈的责任心与团队合作能力 ");
		 bt1=(Button) view.findViewById(R.id.rjd_button1);
		 bt2=(Button) view.findViewById(R.id.rjd_button2);
		 bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bt1.setText("已拒绝");
				bt2.setText("接受");
			}
		});
		
		 bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bt2.setText("已接受");
				bt1.setText("拒绝");
			}
		});
		return view;
	}
}

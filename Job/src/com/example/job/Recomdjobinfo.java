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
		 textview.setText("3������Web��Ŀ���Ʒ������뿪�����飬�����ն˰�ȫ�������Ʒ�������������� \n\n"
		 		+ "1����ͨJava��̣��ܹ�����Ӧ��Spring��Struts��Hibernate������������� \n\n"
		 		+ "2����Ϥ���̲߳����������첽���ü�����������ؾ���\n\n"
		 		+ "3����ϤHTML��XML��JavaScript��AJAX��JQuery��ExtJsǰ�˿���������\n\n"
		 		+ "4���ܹ�����Ӧ��MySQL��ӵ����������ݿ�������� \n\n"
		 		+ "5��������OOA/OOD/OOP˼��,�������ն��ֳ��õ����ģʽ \n\n"
		 		+ "6����ǿ������ѧϰ�����ͷ������������������߱����õ��ĵ�����ϰ�ߺʹ�����д�淶 \n\n"
		 		+ "7��ǿ�ҵ����������ŶӺ������� ");
		 bt1=(Button) view.findViewById(R.id.rjd_button1);
		 bt2=(Button) view.findViewById(R.id.rjd_button2);
		 bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bt1.setText("�Ѿܾ�");
				bt2.setText("����");
			}
		});
		
		 bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bt2.setText("�ѽ���");
				bt1.setText("�ܾ�");
			}
		});
		return view;
	}
}

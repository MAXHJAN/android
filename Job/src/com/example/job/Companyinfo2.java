package com.example.job;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Companyinfo2 extends Fragment{
	/**
	 * ��˾��ϸ��Ϣ
	 */
	private TextView textview;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.companyinfo2, null);
		textview=(TextView) view.findViewById(R.id.c2_text);
		textview.setText("SEPHORA��˿ܽ�����������ڷ���·����������(LVMH)��"
				+ "��ȫ��ױƷ����Ȩ�����ֲ� 31������1930�ҵ��̡�"
				+ "SEPHORA���й��ѿ���168�һ�ױƷר���꣬�ֱ�λ���Ϻ���������"
				+ "���ݡ����ڡ����������������졢���ݡ����������ݡ����ݡ���򡢹�������"
				+ "�������Ͼ�����ɳ�����졢������������֣�ݵȡ� SEPHORA���ź����"
				+ "���µĵ��̣�ʹ�˿Ϳ������ɵط����Գ���70��һ������Ʒ�ƣ������д�"
				+ "ͳ���������֪��Ʒ�ƣ�Ҳ��רҵ��Ǳ��Ʒ�ƣ�����SEPHORA˿ܽ��Ʒ�ơ���"
				+ "Ʒ�����൱��ȫ�����������������������ױ�������ȫ����ˮ��Ʒ���Լ�ϴԡ"
				+ "������Ȳ�Ʒ�����������������רҵ���������ͣ�����ʽ�Ļ��ܣ���ר��ͬ��"
				+ "���е���Ʒ��ʹ�˿���SEPHORA�����ɵ�ѡ������������ʣ�����ȫ������"
				+ "��Ʒ������֮�⣬SEPHORA��Ϊ�˿��ṩ��ѵ����ݲ�ױ����רҵ������"
				+ "���ʣ��ṩ����Ե�����ָ�����顣��չ�е�SEPHORA CHINA"
				+ "2005��4�£�SEPHORA���Ϻ��������й�SEPHORA�̵꣬����������ص�"
				+ "���µĵ��̺ͷɿ���ٶ����й��������ȫ����չ������Ϊֹ��SEPHORA�Ѿ��ɹ���"
				+ "�����й���61�����У�ӵ�н�2500����Ա���� ���ѳɹ�������173�һ�ױƷר���ꡣ");
      return view;
	}
}

package com.example.job;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Companyinfo1 extends Fragment{
	/**
	 * ��˾��ϸ��Ϣ
	 */
	private TextView textview;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.companyinfo1, null);
		textview=(TextView) view.findViewById(R.id.c1_text);
		textview.setText("���Ͽ���ʱ��������Ϣ�������޹�˾����ơ�����ʱ������������2000��7�£�λ�ڳ���̶����һ�廯��������"
				+ "���Ҽ����ÿ����������Ż���ҵ԰����һ�Ҽ����С�������������һ�����Ӫ���¼�����ҵ����Ʒ�㷺Ӧ���ڵ������������̻�е"
				+ "�����ó����������豸���������硢���й���������ش�װ���������γ�����ϵ��300�����ͺš���˾רע��Ϊ�ͻ��ṩ�ش�"
				+ "װ���Զ�������ƽ̨����������ϵͳӦ�á����ܴ��м�����һ�廯�����������Ʒ�ͷ��񡣡�����ʱ����������Ϊ�й��Զ������ܿ�"
				+ "����ҵ������ߣ�����Ϊȫ��ͻ��������ļ�ֵ������˾ӵ��һ֧�����ʡ�רҵ�����з��Ŷӣ��Ⱥ�������Ƽ���ѧ���й�������"
				+ "��װ�ױ�����ѧԺ�����ϴ�ѧ����̶��ѧ�ȹ���֪����У�����������ˡ�����ѧ���С����أ����Ͽ�չ��������ǰ�ؿ�����������˾��"
				+ "����һ�����ӡ��������Զ����ȷ����רҵ�����˲ţ��н���һ�����Ҽ�ʡ����������Ŀ���з���һ�����й����Ƚ�ˮƽ���²�Ʒ���з�"
				+ "�Ŷӱ�������ѧ��ռ85%���ϣ�ƽ�������30�꣬��Ч�ر�֤�˼������»������з���������˾�ѻ�ö���֪ʶ��Ȩ��ר������������ÿ"
				+ "��50%���ٶȵ�����ȷ����˾��Ʒ�뼼��ʼ�մ��ڹ���һ��ˮƽ��");
      return view;
	}
}

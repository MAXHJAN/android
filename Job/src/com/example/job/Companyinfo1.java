package com.example.job;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Companyinfo1 extends Fragment{
	/**
	 * 公司详细信息
	 */
	private TextView textview;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.companyinfo1, null);
		textview=(TextView) view.findViewById(R.id.c1_text);
		textview.setText("湖南开启时代电子信息技术有限公司（简称“开启时代”）成立于2000年7月，位于长株潭三市一体化核心区域、"
				+ "国家级经济开发区——九华工业园，是一家集科研、生产、销售于一体的民营高新技术企业。产品广泛应用于电气传动、工程机械"
				+ "、军用车辆、矿用设备、风力发电、城市轨道车辆等重大装备领域，已形成六大系列300余种型号。公司专注于为客户提供重大"
				+ "装备自动化控制平台、车辆网络系统应用、智能传感技术等一体化解决方案、产品和服务。“开启时代”致力成为中国自动化智能控"
				+ "制行业的领军者，竭力为全球客户创造更大的价值。　公司拥有一支高素质、专业化的研发团队，先后与国防科技大学、中国人民解放"
				+ "军装甲兵工程学院、中南大学、湘潭大学等国内知名高校合作，建立了“产、学、研”基地，联合开展相关领域的前沿开发工作。公司汇"
				+ "聚了一批电子、电气、自动化等方面的专业技术人才，承接了一批国家及省部级科研项目，研发出一批具有国际先进水平的新产品。研发"
				+ "团队本科以上学历占85%以上，平均年龄仅30岁，有效地保证了技术创新活力和研发能力。公司已获得多项知识产权和专利技术，并以每"
				+ "年50%的速度递增，确保公司产品与技术始终处于国际一流水平。");
      return view;
	}
}

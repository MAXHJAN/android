package com.example.job;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Companyinfo2 extends Fragment{
	/**
	 * 公司详细信息
	 */
	private TextView textview;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.companyinfo2, null);
		textview=(TextView) view.findViewById(R.id.c2_text);
		textview.setText("SEPHORA（丝芙兰），隶属于法国路威酩轩集团(LVMH)，"
				+ "是全球化妆品零售权威，分布 31个国家1930家店铺。"
				+ "SEPHORA在中国已开设168家化妆品专卖店，分别位于上海、北京、"
				+ "广州、深圳、沈阳、大连、重庆、杭州、宁波、温州、苏州、天津、哈尔滨、"
				+ "西安、南京、长沙、常熟、无锡、长春及郑州等。 SEPHORA开放和设计"
				+ "精致的店铺，使顾客可以自由地发掘尝试超过70个一线美容品牌，其中有传"
				+ "统经典的世界知名品牌，也有专业的潜力品牌，还有SEPHORA丝芙兰品牌。产"
				+ "品种类相当齐全，包括：护肤保养、各类彩妆、最多最全的香水产品，以及洗浴"
				+ "、配件等产品。正规的引进渠道，专业的物流配送，开放式的货架，和专柜同步"
				+ "发行的新品，使顾客在SEPHORA可自由地选择和试用最新鲜，最齐全的美容"
				+ "产品。除此之外，SEPHORA还为顾客提供免费的美容彩妆服务，专业的美容"
				+ "顾问，提供有针对的美容指导建议。发展中的SEPHORA CHINA"
				+ "2005年4月，SEPHORA在上海开设了中国SEPHORA商店，并且以其独特的"
				+ "精致的店铺和飞快的速度向中国各大城市全面扩展。迄今为止，SEPHORA已经成功进"
				+ "入了中国的61个城市，拥有近2500多名员工。 现已成功开设了173家化妆品专卖店。");
      return view;
	}
}

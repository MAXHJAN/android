package mx.hnust.sellvegetable.util;

import java.io.Serializable;

public class VegetableInfo implements Serializable{

	private String id;//id
	private String name;//名字
	private String info;//信息
	private String price;//价钱
	private String imageurl;//图像url
	private String number;//剩余数量
	private String ordlerid;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getOrdlerid() {
		return ordlerid;
	}
	public void setOrdlerid(String ordlerid) {
		this.ordlerid = ordlerid;
	}
}

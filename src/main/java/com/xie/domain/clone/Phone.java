package com.xie.domain.clone;

public class Phone {
	private String number;
	private String area;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "Phone [number=" + number + ", area=" + area + "]";
	}
	
}

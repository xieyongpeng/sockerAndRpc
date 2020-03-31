package com.xie.domain.clone;

public class Person implements Cloneable{
	private String name;
	private String sex;
	private Phone phone;
	
	@Override
	protected Object clone()  {
		Person p=null;
		try {
			p = (Person) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", phone=" + phone
				+ "]";
	}
	
}

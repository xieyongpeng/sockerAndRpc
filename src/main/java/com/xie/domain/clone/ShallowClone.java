package com.xie.domain.clone;

public class ShallowClone {
	public static void main(String[] args) {
		Person p=new Person();
		p.setName("李丽丽");
		p.setSex("nam");
		Phone phone = new Phone();
		phone.setArea("成都");
		phone.setNumber("121");
		p.setPhone(phone);
		
		Person p2=(Person) p.clone();
		
		System.out.println("p:"+p.toString());
		System.out.println("p2:"+p2.toString());
		
		p2.setName("leixiaotao");
		p2.getPhone().setArea("四川乐山");
		System.out.println("-------其中一个对象修改值过后-------");
		System.out.println("p:"+p.toString());
		System.out.println("p2:"+p2.toString());

	}
}

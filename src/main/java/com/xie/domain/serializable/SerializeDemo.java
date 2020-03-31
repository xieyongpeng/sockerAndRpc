package com.xie.domain.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeDemo {
	public static void main(String[] args) throws Exception {
		// 序列化操作
        SerializePerson();
        //反序列化操作
        Person person=DeSerializePerson();
        System.out.println(person);
        System.out.println(person.height);
	}
	// 序列化操作
	private static void SerializePerson(){
		try {
			ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("person")));
			Person person=new Person();
            person.setAge(18);
            person.setName("mic");
            oo.writeObject(person);
            oo.flush();
            
            Person person2=new Person();
            person2.setAge(19);
            person2.setName("mik");
            oo.writeObject(person2);
            oo.flush();
            
            System.out.println("序列化成功: "+new File("person").length());
            oo.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//反序列化
	private static Person DeSerializePerson() throws Exception{
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("person")));
			Person person=(Person)ois.readObject();
			person.height = 4;
			if(ois!=null){
				ois.close();
			}
			return person;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

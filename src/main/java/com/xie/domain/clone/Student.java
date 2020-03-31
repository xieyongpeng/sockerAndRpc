package com.xie.domain.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4627445596162140628L;
	private String name;
    private int age;
    private Teacher teacher;

    public Object deepClone() throws Exception{
    	//序列化
    	ByteArrayOutputStream out=new ByteArrayOutputStream();
    	ObjectOutputStream stream = new ObjectOutputStream(out);
    	
    	stream.writeObject(this);
    	
    	ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    	ObjectInputStream inputStream = new ObjectInputStream(in);
    	return inputStream.readObject();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", teacher="
				+ teacher + "]";
	}

}

package com.xie.domain.clone;

public class DeepClone {
	public static void main(String[] args) throws Exception {
		Teacher teacher=new Teacher();
        teacher.setName("mic");

        Student student=new Student();
        student.setName("沐风");
        student.setAge(35);
        student.setTeacher(teacher);

        Student student2=(Student) student.deepClone(); //克隆一个对象
        System.out.println(student);
        System.out.println("-------其中一个对象修改值过后-------");
        student2.getTeacher().setName("james");
        student2.setAge(22);
        
        System.out.println(student2);
	}
}

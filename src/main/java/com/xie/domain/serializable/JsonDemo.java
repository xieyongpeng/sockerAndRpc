package com.xie.domain.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class JsonDemo {
	public static void main(String[] args) throws Exception {
//		excuteWithJack();
//		excuteWithFastJson();
//		excuteWithProtoBuf();
		excuteWithHessian();
	}
	//初始化
    private static Person init(){
        Person person=new Person();
        person.setName("mic");
        person.setAge(18);
        return person;
    }
    
  //初始化
    private static Person2 init2(){
        Person2 person=new Person2();
        person.setName("mic");
        person.setAge(18);
        return person;
    }
    
	public static void excuteWithJack() throws Exception{
		Person person=init();
		
		ObjectMapper mapper = new ObjectMapper();
		byte[] writeBytes=null;
		Long start=System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			//序列化
			writeBytes = mapper.writeValueAsBytes(person);
		}
		System.out.println("Json序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+writeBytes.length);
		//反序列化
		Person person1 = mapper.readValue(writeBytes,Person.class);
		System.out.println(person1);
	}
	
	public static void excuteWithFastJson() throws Exception{
		Person person=init();
		
		byte[] writeBytes=null;
		Long start=System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			//序列化
			writeBytes = JSON.toJSONBytes(person);
		}
		System.out.println("fastjson序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+writeBytes.length);
		//反序列化
		Person person1 = JSON.parseObject(writeBytes, Person.class);
		System.out.println(person1);
	}
	
	private static void excuteWithProtoBuf() throws Exception{
		Person2 person2 = init2();
		Codec<Person2> personCodec = ProtobufProxy.create(Person2.class);
		
		Long start=System.currentTimeMillis();
		byte[] writeBytes=null;
		for(int i=0;i<10000;i++){
			//序列化
			writeBytes = personCodec.encode(person2);
		}
		System.out.println("protobuf序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+writeBytes.length);
		//反序列化
		Person2 person22 = personCodec.decode(writeBytes);
		System.out.println(person22);
	}
	
	private static void excuteWithHessian() throws Exception {
		Person person=init();
		int count = 0;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		HessianOutput output = new HessianOutput(baos);
		Long start=System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			//序列化
			output.writeObject(person);
			if(i==0){
				count = baos.toByteArray().length;
			}
		}
		//流转化成直接数据
		System.out.println("Hessian序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "单个大小->"+count + "   总大小->" + baos.toByteArray().length);
		//反序列化
		HessianInput input = new HessianInput(new ByteArrayInputStream(baos.toByteArray()));
		Person person1=(Person)input.readObject();
		System.out.println(person1);
	}
}

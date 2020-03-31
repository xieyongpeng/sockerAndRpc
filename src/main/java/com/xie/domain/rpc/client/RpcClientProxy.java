package com.xie.domain.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcClientProxy<T> {
	private Class<T> serviceInterface;
	private InetSocketAddress address;
	
	public RpcClientProxy(Class<T> serviceInterface, String ip,int port) {
		this.serviceInterface = serviceInterface;
		this.address = new InetSocketAddress(ip, port);
	}
	
	@SuppressWarnings("unchecked")
	public T getClientIntance(){
		return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface},new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Socket socket = null;
				ObjectOutputStream output = null;
				ObjectInputStream input = null;
				try{
					// 2.创建Socket客户端，根据指定地址连接远程服务提供者
					socket = new Socket();
					socket.connect(address);
					
					// 3.将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务提供者
					//ObjectOutputStream这是一个对象序列化的方式
					output  = new ObjectOutputStream(socket.getOutputStream());
					//使用utf-8编码(传输接口名)
					output.writeUTF(serviceInterface.getName());
					//使用utf-8编码(传输调用的方法名称)
					output.writeUTF(method.getName());
					//将方法参数类型传递过去
					output.writeObject(method.getParameterTypes());
					//将方法参数传递过去
					output.writeObject(args);
					
					// 4.同步阻塞等待服务器返回应答，获取应答后返回
					input = new ObjectInputStream(socket.getInputStream());
					return input.readObject();
				}catch(Exception e){
					throw e;
				}finally{
					if (socket != null) socket.close();
		            if (output != null) output.close();
		            if (input != null) input.close();
				}
			}
		});
	}
}

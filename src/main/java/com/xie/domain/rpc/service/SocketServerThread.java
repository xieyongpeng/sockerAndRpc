package com.xie.domain.rpc.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

public class SocketServerThread implements Runnable {

	private Socket socket;
	private HashMap<String, Class<?>> serviceRegistry;

	public SocketServerThread(Socket socket, HashMap<String, Class<?>> serviceRegistry) {
		this.socket = socket;
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public void run() {
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		try {
			if (socket != null) {
				input = new ObjectInputStream(socket.getInputStream());
				//接口名
				String serviceName = input.readUTF();
				//方法名
				String methodName = input.readUTF();
				System.out.println (methodName);
				//参数类型
				Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
				//参数对象
				Object[] arguments = (Object[]) input.readObject();
				//获取接口的实现类
				Class serviceClass = serviceRegistry.get(serviceName);
				if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + " not found");
                }
				//反射执行指定方法
				Method method = serviceClass.getMethod(methodName, parameterTypes);
				Object result = method.invoke(serviceClass.newInstance(), arguments);
				output = new ObjectOutputStream (socket.getOutputStream());
				output.writeObject(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}

}

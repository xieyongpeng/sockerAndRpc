package com.xie.domain.rpc.service;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RpcServer {
	private static final HashMap<String, Class<?>> serviceRegistry = new HashMap<>();
	private int port;
	
	public RpcServer(int port){
		this.port = port;
	}
	
	public RpcServer register(Class serviceInterface , Class impl){
		serviceRegistry.put(serviceInterface.getName(), impl);
		return this;
	}
	
	public void bind() throws Exception{
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("start server");
        Socket socket=null;
        try{
        	//服务端可以循环调用
        	while(true){
        		socket = serverSocket.accept();
        		new Thread(new SocketServerThread(socket,serviceRegistry)).start();
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
}

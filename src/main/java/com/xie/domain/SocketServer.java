package com.xie.domain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = null;
		try {
			//启动一个服务
			serverSocket= new ServerSocket(8888);
			while(true){
				Socket socket=serverSocket.accept();
				//使用lambda表达式
				//有请求过来就创建一个线程进行处理
				new Thread(()->{
					//读取数据
					try {
						//读取数据
						BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
						//发送数据
						PrintWriter writer =new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
						//放到一个循环里面表示创建的这个线程能持续和客户端进行通信
						while(true){
							String clientData=reader.readLine(); //读取客户端发送过来的消息
							if(clientData==null){
                                break;
                            }else{
                            	 System.out.println("服务端接收到的数据："+clientData);
                            }
							writer.println("Hello Mic; ^^");
							writer.flush();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(serverSocket!=null){
                serverSocket.close();
            }
		}
	}
}

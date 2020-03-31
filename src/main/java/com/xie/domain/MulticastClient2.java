package com.xie.domain;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastClient2 {
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("224.1.4.5");
			
			MulticastSocket socket = new MulticastSocket(9999);
			
			//加到指定的组里面
			socket.joinGroup(address);
			
			byte[] buf=new byte[256];
			while(true){
				DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
				socket.receive(datagramPacket);
				
				String msg=new String(datagramPacket.getData());
                System.out.println("接收到的数据："+msg);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

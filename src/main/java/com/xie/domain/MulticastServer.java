package com.xie.domain;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class MulticastServer {
	public static void main(String[] args) {
		try {
			//地址段：224.0.0.0 - 239.255.255.255
			InetAddress address = InetAddress.getByName("224.1.4.5");
			
			MulticastSocket socket = new MulticastSocket();
			
			for(int i=0;i<10;i++){
				String data="Hello Mic";
				byte[] bytes=data.getBytes();
				socket.send(new DatagramPacket(bytes,bytes.length,address,9999));
				TimeUnit.SECONDS.sleep(2);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
